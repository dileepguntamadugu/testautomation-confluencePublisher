package com.confluence.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesLoader {
	public static void main(String args[]) throws IOException {
		/*Path path = FileSystems.getDefault().getPath("confluence.properties").toAbsolutePath();
		System.out.println(path.toString());*/
		PropertiesLoader pl = new PropertiesLoader();
		Properties pr = pl.loader();
		System.out.println(pr.get("space.key"));
	}
	
	public Properties loader() throws IOException {
		Properties prop = new Properties();
		Path path = FileSystems.getDefault().getPath("confluence.properties").toAbsolutePath();
		String propFileName = path.toString();
		InputStream inputstream = new FileInputStream(propFileName);
		if(inputstream!=null) {
			try {
				prop.load(inputstream);
			} catch (IOException e) {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}finally {
				inputstream.close();
			}
		}
		return prop;	
	}
}
