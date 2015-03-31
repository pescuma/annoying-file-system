package org.pescuma.annoyingfilesystem;

import java.util.List;

public interface FileSystem {
	
	Path createPath(String path);
	
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
