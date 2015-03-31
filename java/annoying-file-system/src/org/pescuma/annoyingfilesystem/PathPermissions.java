package org.pescuma.annoyingfilesystem;

public interface PathPermissions {
	
	/**
	 * @return true if this path exists and it can be read
	 */
	boolean canReadContents();
	
	/**
	 * @return true if this path exists and it can be written to
	 */
	boolean canWriteContents();
	
	/**
	 * @return true if this path exists and it can have children
	 */
	boolean canHaveChildren();
	
	/**
	 * @return true if this path exists and its children can be listed
	 */
	boolean canListChildren();
	
	/**
	 * @return true if this path exists and a child can be created
	 */
	boolean canAddChild();
	
}
