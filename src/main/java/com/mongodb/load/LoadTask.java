package com.mongodb.load;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BulkWriteException;
import com.mongodb.BulkWriteResult;

public class LoadTask implements Runnable {
    
    protected static final Logger logger = LoggerFactory.getLogger(Loader.class);
    
    POCTestOptions testOpts;
    POCTestResults testResults;
    MongoTemplate template;
    
    public LoadTask(MongoTemplate template, POCTestOptions t, POCTestResults r) {
        this.template = template;
        this.testOpts = t;
        this.testResults = r;
        
    }

    public void run() {
        
        
        BulkOperations ops = template.bulkOps(BulkMode.UNORDERED, TestRecord.class);
        
        List<TestRecord> insertList = new ArrayList<TestRecord>(testOpts.batchSize);
        
        //BulkWriteOperation bulkWrite = collection.initializeUnorderedBulkOperation();
        int c = 0;
        //System.out.println("Worker thread " + workerID + " Started.");
        while (testResults.GetSecondsElapsed() < testOpts.duration) {
            c++;
            for (int i = 0; i < testOpts.batchSize; i++) {
                insertList.add(new TestRecord(testOpts));
            }
            
            ops.insert(insertList);
            execute(ops, testOpts.batchSize);
            insertList.clear();
        }
        
    }
    
    private void execute(BulkOperations bulkWrite, int count) {
        BulkWriteResult bwResult = null;
        Date starttime = new Date();
        try {
            bwResult = bulkWrite.execute();
            //event.incrementCount(result.getInsertedCount());
        } catch (BulkWriteException bulkWriteException) {
            logger.warn("BulkWriteException: " + bulkWriteException.getWriteErrors().size() + "/" + count + " records failed.");
            //event.incrementError(bulkWriteException.getWriteErrors().size());
            bwResult = bulkWriteException.getWriteResult();
            //event.incrementCount(result.getInsertedCount());
        } catch (Exception e) {
            // TODO - need to figure out how to account for these errors that aren't BulkWriteException
            // e.g. CommandFailureException
            //e.printStackTrace();
            //event.incrementError(count);
        }
        
        Date endtime = new Date();

        Long taken = endtime.getTime() - starttime.getTime();


        int icount = bwResult.getInsertedCount();
        int ucount = bwResult.getMatchedCount();

        // If the bulk op is slow - ALL those ops were slow

        if (taken > testOpts.slowThreshold) {
            testResults.RecordSlowOp("inserts", icount);
            testResults.RecordSlowOp("updates", ucount);
        }
        testResults.RecordOpsDone("inserts", icount);
        
    }

}
