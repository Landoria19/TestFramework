package managers;

import dataProviders.ExtentFileWriter;


public class FileWriterManager {
	private static FileWriterManager fileWriterManager = new FileWriterManager();
	private static ExtentFileWriter extentFileWriter;

	private FileWriterManager() {
	}

	 public static FileWriterManager getInstance( ) {
	      return fileWriterManager;
	 }
	 
	 public ExtentFileWriter getExtentWriter() {
		 return (extentFileWriter == null) ? new ExtentFileWriter() : extentFileWriter;
	 }
}
