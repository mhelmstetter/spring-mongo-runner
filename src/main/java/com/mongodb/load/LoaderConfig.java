package com.mongodb.load;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class LoaderConfig {
    
    private static final int DEFAULT_QUEUE_SIZE = 1000000;
    
    public static final int DISPLAY_MILLIS = 15000;
    public static final int SLEEP_TIME = 500;
    public static final int DEFAULT_BATCH_SIZE = 100;
    public static final int DEFAULT_THREADS = 8;

    private String databaseName;
    private String collectionName;
    
    private MongoTemplate mongoTemplate;
    
    private boolean dropCollection;
    private Integer batchSize = DEFAULT_BATCH_SIZE;
    
    private int queueSize;
    private int threads = DEFAULT_THREADS;
    

    
    public LoaderConfig(CommandLine line) throws ParseException {
       
        loadProperties(line);
        
    }
    

    
    private void loadProperties(CommandLine line) throws ParseException {
        
        String connectionUri = line.getOptionValue("u");
        MongoClientURI uri = new MongoClientURI(connectionUri);
        MongoClient mongoClient = new MongoClient(uri);
        
        databaseName = line.getOptionValue("d");
        MongoTemplate template = new MongoTemplate(mongoClient, databaseName);
        
        //dropCollection = props.getBoolean("dropCollection", false);
        batchSize = ((Long)line.getParsedOptionValue("b")).intValue();
        //line.getParsedOptionValue(opt)
        
        setQueueSize(DEFAULT_QUEUE_SIZE);
        
        if (line.hasOption("t")) {
            threads = ((Long)line.getParsedOptionValue("t")).intValue();
        }
        
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    

    
    public boolean isDropCollection() {
        return dropCollection;
    }

    public void setDropCollection(boolean dropCollection) {
        this.dropCollection = dropCollection;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }



    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

}
