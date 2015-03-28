package org.pescuma.annoyingfilesystem;

import java.util.List;

public interface Path {
	
	String getFullPath();
	
	String getName();
	
	boolean canReadContents();
	
	boolean canWriteContents();
	
	PathContents getContents();
	
	boolean canHaveChildren();
	
	boolean canListChildren();
	
	boolean canAddChild();
	
	List<Path> getChildren();
	
	Path getChild(String... names);
	
	boolean exists();
	
	boolean isFile();
	
	boolean isFolder();
	
	void createAsFile();
	
	void createAsFolder();
	
	void createParentFolder();
	
	PathAttributes getAttributes();
	
	boolean isRoot();
	
	Path getParent();
	
	boolean equals(Path other);
}
