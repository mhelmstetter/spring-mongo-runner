package com.mongodb.load;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.ParseException;
import org.apache.commons.configuration.ConfigurationException;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Loader {

    protected static final Logger logger = LoggerFactory.getLogger(Loader.class);

    //private ThreadPoolExecutor pool = null;

    MongoClient mongoClient;
    MongoTemplate mongoTemplate;
    
    
    public Loader(POCTestOptions testOpts) {
        mongoClient = new MongoClient(new MongoClientURI(testOpts.connectionDetails));
        MongoDatabase db = mongoClient.getDatabase(testOpts.databaseName);
        
        mongoClient.getDatabase("admin").runCommand(new Document("ping",1));
        mongoTemplate = new MongoTemplate(mongoClient, testOpts.databaseName);
        
        MongoCollection<Document>  coll = db.getCollection(testOpts.collectionName);
        if(testOpts.emptyFirst)
        {
            mongoTemplate.dropCollection(TestRecord.class);
        }
        
    }

    private void load(com.mongodb.load.POCTestOptions testOpts, com.mongodb.load.POCTestResults testResults) throws IOException {

        Runnable reporter = new POCTestReporter(testResults,mongoClient,testOpts);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(reporter, 0, testOpts.reportTime, TimeUnit.SECONDS);
        
        ExecutorService testexec = Executors.newFixedThreadPool(testOpts.numThreads);


        for (int i = 0; i < (testOpts.numThreads); i++) {
            testexec.execute(new LoadTask(mongoTemplate, testOpts, testResults));
        }
    
        testexec.shutdown();
    
        try {
            testexec.awaitTermination(Long.MAX_VALUE,
                    TimeUnit.SECONDS);
            //System.out.println("All Threads Complete: " + b);
            executor.shutdown();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            
        }

    }



    public static void main(String[] args) throws ConfigurationException, IOException {
        
        POCTestOptions testOpts;
        
        try {
            testOpts = new POCTestOptions(args);
            // Quit after displaying help message
            if (testOpts.helpOnly) {
                return;
            }
            
            if(testOpts.arrayupdates > 0 && (testOpts.arraytop<1 || testOpts.arraynext<1))
            {
                System.out.println("You must specify an array size to update arrays");
                return;
            }
           
            
        } catch (ParseException e) {

            System.err.println(e.getMessage());
            return;
        }


        POCTestResults testResults = new POCTestResults();
        Loader runner = new Loader(testOpts);
        runner.load(testOpts,testResults);
    }

}
