package org.pescuma.annoyingfilesystem.windows;

import org.pescuma.annoyingfilesystem.InvalidPathException;

public class InvalidWindowsPathException extends InvalidPathException {
	
	private static final long serialVersionUID = 5864780738238030287L;
	
	public InvalidWindowsPathException(String path) {
		super(path, WindowsFileSystem.fileNameInvalidChars, WindowsFileSystem.pathValidations);
	}
	
}
