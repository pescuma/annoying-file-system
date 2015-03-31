package org.pescuma.annoyingfilesystem;

import java.util.List;

public interface Path {
	
	String getFullPath();
	
	String getName();
	
	PathAttributes getAttributes();
	
	PathPermissions getPermissions();
	
	PathContents getContents();
	
	/**
	 * @return null if it is a root
	 */
	Path getParent();
	
	/**
	 * @return Never null. Returns an empty list if this path does not exist.
	 */
	List<Path> getChildren();
	
	/**
	 * Get a child, given a relative path inside it
	 */
	Path getChild(String name, String... relativeNames);
	
	/**
	 * Create this path as a file, if it does not exist
	 * 
	 * @throws if it already exists and is a folder
	 * @throws if can not create or fails during creation
	 */
	void createAsFile();
	
	/**
	 * Create this path as a folder, if it does not exist
	 * 
	 * @throws if it already exists and is a file
	 * @throws if can not create or fails during creation
	 */
	void createAsFolder();
	
	/**
	 * Create the parent folder (and its parents), if needed
	 */
	void createParentFolders();
	
	@Override
	boolean equals(Object other);
}
