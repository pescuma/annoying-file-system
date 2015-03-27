package org.pescuma.annoyingfilesystem;

import java.io.InputStream;
import java.io.OutputStream;

public interface PathContents {
	
	long getSize();
	
	String readAsString();
	
	String readAsString(String encoding);
	
	void writeAsString(String text);
	
	void writeAsString(String text, String encoding);
	
	InputStream createInputStrean();
	
	OutputStream createOutputStream();
	
}
