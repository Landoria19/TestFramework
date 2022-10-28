package dataProviders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentFileWriter {
	private final String propertyFilePath = "src/test/resources/extent.properties";
	
	
	public ExtentFileWriter() {
	}
	
	public void setProperty(String key,String value) throws IOException {
		FileInputStream in = new FileInputStream(this.propertyFilePath);
		Properties properties = new Properties();
		properties.load(in);
		in.close();

		FileOutputStream  writer = new FileOutputStream(this.propertyFilePath);
		properties.setProperty(key, value);
		properties.store(writer, null);	
		writer.close();
	}
				
}
