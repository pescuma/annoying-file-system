package org.pescuma.annoyingfilesystem;

import java.util.List;

public interface Path {
	
	String getFullPath();
	
	String getName();
	
	PathAttributes getAttributes();
	
	PathPermissions getPermissions();
	
	PathContents getContents();
	
	Path getParent();
	
	List<Path> getChildren();
	
	Path getChild(String name, String... relativeNames);
	
	void createAsFile();
	
	void createAsFolder();
	
	void createParentFolders();
	
	boolean equals(Path other);
}
