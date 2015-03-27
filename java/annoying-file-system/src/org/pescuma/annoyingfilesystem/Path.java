package org.pescuma.annoyingfilesystem;

public interface Path {
	
	String getFullPath();
	
	String getName();
	
	boolean canReadContents();
	
	boolean canWriteContents();
	
	PathContents getContents();
	
	boolean canHaveChildren();
	
	boolean canListChildren();
	
	boolean canAddChild();
	
	Iterable<Path> getChildren();
	
	Path getChild(String... names);
	
	Path createChild(String name);
	
	boolean exists();
	
	boolean isFile();
	
	boolean isFolder();
	
	void createAsFile();
	
	void createAsFolder();
	
	void createParentFolder();
	
	PathAttributes getAttributes();
}
