package org.pescuma.annoyingfilesystem;

import java.io.InputStream;
import java.io.OutputStream;

public interface PathContents {
	
	/**
	 * @return 0 if this path does not exist
	 */
	long getSize();
	
	/**
	 * Read all the contents as one string, using UTF-8 encoding
	 * 
	 * @return null if this path does not exist
	 */
	String readAsString();
	
	/**
	 * Read all the contents as one string
	 * 
	 * @return null if this path does not exist
	 */
	String readAsString(String encoding);
	
	/**
	 * Overwrite the file with this text, using UTF-8 encoding
	 */
	void writeAsString(String text);
	
	/**
	 * Overwrite the file with this text
	 */
	void writeAsString(String text, String encoding);
	
	/**
	 * @return null if this path does not exists
	 */
	InputStream createInputStrean();
	
	/**
	 * Creates the file if this path does not exist. Fails if the parent folder does not exist.
	 */
	OutputStream createOutputStream();
	
}
