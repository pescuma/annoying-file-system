package org.pescuma.annoyingfilesystem;

import java.util.Date;

public interface PathAttributes {
	
	boolean exists();
	
	/**
	 * @return true if is a valid root name. It does not need to exist
	 */
	boolean isRoot();
	
	/**
	 * @return true if exists and is a file
	 */
	boolean isFile();
	
	/**
	 * @return true if exists and is a folder
	 */
	boolean isFolder();
	
	/**
	 * @return true if exists and is a valid file or folder symbolic link
	 */
	boolean isSymbolicLink();
	
	/**
	 * @return Creation date or null if this path does not exist or is not supported by file system
	 */
	Date getCreationDate();
	
	/**
	 * @return Creation date or null if this path does not exist or is not supported by file system
	 */
	Date getLastAccessDate();
	
	/**
	 * @return Creation date or null if this path does not exist or is not supported by file system
	 */
	Date getLastModificationDate();
	
	/**
	 * @return 0 if this path does not exist
	 */
	long getSize();
}
