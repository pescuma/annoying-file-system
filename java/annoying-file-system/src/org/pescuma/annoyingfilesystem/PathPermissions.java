package org.pescuma.annoyingfilesystem;

public interface PathPermissions {
	
	boolean canReadContents();
	
	boolean canWriteContents();
	
	boolean canHaveChildren();
	
	boolean canListChildren();
	
	boolean canAddChild();
	
}
