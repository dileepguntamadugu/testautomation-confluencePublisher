package com.confluence.publish;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.confluence.api.GetService;
import com.confluence.api.PostService;
import com.confluence.api.PutService;
import com.confluence.properties.PropertiesLoader;
import com.confluence.publish.PublishAggregatedData;

public class PublishAggregatedData {
	private static final Logger logger = LogManager.getLogger(PublishAggregatedData.class); 
	public static void main(String args[]) throws IOException {
		BasicConfigurator.configure();
		PropertiesLoader pl = new PropertiesLoader();
		GetService gs = new GetService();
		PutService ps = new PutService();
		ps.putService(gs.getService(pl.loader()),pl.loader());	
	}
}
