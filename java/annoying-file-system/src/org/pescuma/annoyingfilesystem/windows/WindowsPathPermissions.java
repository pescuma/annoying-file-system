package org.pescuma.annoyingfilesystem.windows;

import java.io.File;

import org.pescuma.annoyingfilesystem.PathPermissions;

public class WindowsPathPermissions implements PathPermissions {

	private final String path;
	private final File file;
	private final WindowsPathAttributes attributes;

	public WindowsPathPermissions(String path, File file,
			WindowsPathAttributes attributes) {
		this.path = path;
		this.file = file;
		this.attributes = attributes;
	}

	@Override
	public boolean canReadContents() {
		return attributes.isFile() && file.canRead();
	}

	@Override
	public boolean canWriteContents() {
		return attributes.isFile() && file.canWrite();
	}

	@Override
	public boolean canHaveChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canListChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAddChild() {
		// TODO Auto-generated method stub
		return false;
	}

}
