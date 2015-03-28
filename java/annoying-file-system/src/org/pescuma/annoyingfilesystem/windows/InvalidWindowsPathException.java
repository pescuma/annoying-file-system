package org.pescuma.annoyingfilesystem.windows;

import org.pescuma.annoyingfilesystem.InvalidPathException;

public class InvalidWindowsPathException extends InvalidPathException {
	
	private static final long serialVersionUID = 5864780738238030287L;
	
	public static final String[] validations = new String[] { "It must start with a driver leter and a :",
			"It must have folders separated by \\", "It can not have a empty folder name",
			"The folders can not be named . or .." };
	
	public static final char[] invalidChars = new char[] { '\\', '/', ':', '*', '?', '"', '<', '>', '|' };
	
	public InvalidWindowsPathException(String path) {
		super(path, invalidChars, validations);
	}
	
}
