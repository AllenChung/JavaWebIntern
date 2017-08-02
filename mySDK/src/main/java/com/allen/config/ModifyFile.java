package com.allen.config;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.allen.cache.MyCache;

//thread for change config and save
public class ModifyFile implements Runnable {
	
	private String key;
	
	private String value;
	
	public ModifyFile(String key, String value) {
		this.key = key;
		this.value = value;
	}

	//store the new config in file
	private void modifyConfig() throws IOException {
		OutputStream fos = new BufferedOutputStream(
				new FileOutputStream(ModifyFile.class.getClassLoader().getResource("config.properties").getPath()));
		Properties prop = new Properties();
		prop.setProperty(key, value);
		prop.store(fos, "log level");
		fos.close();
	}

	//change the config of log level
	private void changeLogLevel(String level) throws Exception {
		//read and modify the log level
		SAXReader reader = new SAXReader();
		Document document = reader
				.read(new File(ModifyFile.class.getClassLoader().getResource("logback.xml").getPath()));
		Element logElement = document.getRootElement().element("root");
		Attribute attribute = logElement.attribute("level");
		attribute.setText(level);
		
		//write the file back
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(
				new FileWriter(new File(ModifyFile.class.getClassLoader().getResource("logback.xml").getPath())),
				format);
		writer.write(document);
		writer.close();
	}

	public void run() {
		try {
			process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void process() throws Exception {
		String value = MyCache.cacheMap.get(this.key);
		//if the value is null or isn't up to date, then change it
		if ((value == null) || (!value.equals(this.value))) {
			changeLogLevel(this.value);
			MyCache.cacheMap.put(this.key, this.value);
			modifyConfig();
		}
	}
}
