package com.confluence.publish;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.confluence.api.GetService;
import com.confluence.api.PutService;
import com.confluence.data.AggregateData;
import com.confluence.properties.PropertiesLoader;
import com.confluence.publish.PublishAggregatedData;

public class PublishAggregatedData {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(PublishAggregatedData.class); 
	public static void main(String args[]) throws IOException {
		PropertiesLoader pl = new PropertiesLoader();
		AggregateData aggData = new AggregateData();
		aggData.returnResultsData(pl.loader()).forEach((k,v)->{
			GetService gs = new GetService();
			PutService ps = new PutService();
			try {
				ps.putService(gs.getService(pl.loader()),pl.loader(),v);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		});
		
	}
}