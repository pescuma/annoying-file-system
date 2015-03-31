package org.pescuma.annoyingfilesystem;

import java.util.List;

public interface FileSystem {
	
	/**
	 * Creates a new path from a absolute path name.
	 * 
	 * The file or folder does not need to exist. It just need to be a valid name.
	 * 
	 * @return Never null
	 * @throws InvalidPathException if path is an invalid name
	 */
	Path createPath(String path);
	
	/**
	 * @return At least 1 element
	 */
	List<Path> getRoots();
	
	/**
	 * @return null if no error or a message explaining the error
	 */
	String validateFileName(String name);
	
	/**
	 * @return null if no error or a message explaining the error
	 */
	String validateRoot(String name);
	
}
