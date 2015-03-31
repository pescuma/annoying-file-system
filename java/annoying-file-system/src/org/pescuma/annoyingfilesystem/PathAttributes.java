package org.pescuma.annoyingfilesystem;

import java.util.Date;

public interface PathAttributes {
	
	boolean exists();
	
	boolean isRoot();
	
	boolean isFile();
	
	boolean isFolder();
	
	boolean isSymbolicLink();
	
	Date getCreationDate();
	
	Date getLastAccessDate();
	
	Date getLastModificationDate();
	
	long size();
}
