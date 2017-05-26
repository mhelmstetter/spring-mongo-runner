package com.mongodb.load;


import java.io.PrintWriter;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;



public class POCTestReporter implements Runnable {
    
    protected static final Logger logger = LoggerFactory.getLogger(POCTestReporter.class);
    
	POCTestResults testResults;
	MongoClient mongoClient;
	POCTestOptions testOpts;

	POCTestReporter(POCTestResults r, MongoClient mc, POCTestOptions t) {
		mongoClient = mc;
		testResults = r;
		testOpts = t;

	}


	private void logData()
	{
		PrintWriter outfile = null;
		
		
		

		Long insertsDone = testResults.GetOpsDone("inserts");
		//if (testResults.GetSecondsElapsed() < testOpts.reportTime)
		//	return;
		System.out.println("------------------------");
		
		System.out.format("After %d seconds, %d new records inserted - collection has %d in total \n",
				testResults.GetSecondsElapsed(), insertsDone, testResults.initialCount + insertsDone);
		
		if(outfile != null)
		{
			outfile.format("%d,%d", testResults.GetSecondsElapsed(), insertsDone);
		}
		
		
		HashMap<String, Long> results = testResults
				.GetOpsPerSecondLastInterval();
		String[] opTypes = POCTestResults.opTypes;

		for (String o: opTypes)
		{
			System.out.format("%d %s per second since last report ",
					results.get(o), o);
			
			if(outfile != null)
			{
				outfile.format(",%s,%d", o,results.get(o));
			}
			
			
			Long opsDone = testResults.GetOpsDone(o);
			if (opsDone > 0) {
				Double fastops = 100 - (testResults.GetSlowOps(o) * 100.0)
						/ opsDone;
				System.out.format("%.2f %% in under %d milliseconds", fastops,
						testOpts.slowThreshold);
				if(outfile != null)
				{
					outfile.format(",%.2f", fastops);
				}
			} else {
				System.out.format("%.2f %% in under %d milliseconds",(float)100,testOpts.slowThreshold);
				if(outfile != null)
				{ outfile.format(",%d", 100);}
			}
			System.out.println();
		
		}
		if(outfile != null)
		{ outfile.println();
		outfile.close();
		}
		System.out.println();
	}
	
	public void run() {

		logData();
	
	}
}