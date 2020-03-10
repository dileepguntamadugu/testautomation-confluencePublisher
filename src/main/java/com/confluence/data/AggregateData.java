package com.confluence.data;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class AggregateData {
	private static final String[] HEADERS = {"Story","Title","Result","Date","Stability","Duration (s)"};

	public HashMap<String, HashMap<String, String>> returnResultsData(Properties prop) throws IOException{
		HashMap<String, HashMap<String, String>> metaDataMap = new HashMap<>();
		Reader in = new FileReader(FileSystems.getDefault().getPath(prop.getProperty("results.path")).toAbsolutePath().toString());
	    Iterable<CSVRecord> records = CSVFormat.DEFAULT
	      .withHeader(HEADERS)
	      .withFirstRecordAsHeader()
	      .parse(in);
	    int counter = 0;
	    for (CSVRecord record : records) {
	    	HashMap<String, String> columnDataMap = new HashMap<>();
	    	counter++;
	    	for(String column: HEADERS) {
	    		columnDataMap.put(column, record.get(column));
	    	}
	    	metaDataMap.put(String.valueOf(counter), columnDataMap);
	    }
	    return metaDataMap;
	}
}
