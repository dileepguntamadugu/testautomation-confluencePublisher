package com.confluence.publish;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.confluence.api.GetService;
import com.confluence.api.PostService;
import com.confluence.api.PutService;
import com.confluence.publish.PublishAggregatedData;

public class PublishAggregatedData {
	private static final Logger logger = LogManager.getLogger(PublishAggregatedData.class); 
	public static void main(String args[]) {
		BasicConfigurator.configure();
		GetService gs = new GetService();
		PutService ps = new PutService();
		ps.putService(gs.getService());	
	}
}
