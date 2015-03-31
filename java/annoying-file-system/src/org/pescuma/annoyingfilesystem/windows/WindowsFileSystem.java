package org.pescuma.annoyingfilesystem.windows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.pescuma.annoyingfilesystem.FileSystem;
import org.pescuma.annoyingfilesystem.Path;

public class WindowsFileSystem implements FileSystem {
	
	public static final String[] pathValidations = new String[] {
			"It must start with a driver leter and a :", //
			"It must have folders separated by \\", //
			"It can not have a empty file or folder name", //
			"The files and folders can not be named . or .." };
	
	public static final char[] fileNameInvalidChars = new char[] { '\\', '/', ':', '*', '?', '"',
			'<', '>', '|' };
	
	public static final char separator = '\\';
	
	@Override
	public WindowsPath createPath(String path) {
		
		if (path.length() < 2)
			throw new InvalidWindowsPathException(path);
		
		path = path.replace('/', separator);
		
		String separatorAsString = Character.toString(separator);
		
		if (path.length() > 2) {
			if (path.charAt(2) != separator)
				throw new InvalidWindowsPathException(path);
			
			if (path.indexOf(separatorAsString + separator) >= 0)
				throw new InvalidWindowsPathException(path);
			
		} else {
			path += separator;
		}
		
		path = getCanonical(path);
		
		String[] fileNames = path.split(separatorAsString + separator);
		
		if (validateRoot(fileNames[0]) != null)
			throw new InvalidWindowsPathException(path);
		
		for (int i = 1; i < fileNames.length; i++) {
			if (validateFileName(fileNames[i]) != null)
				throw new InvalidWindowsPathException(path);
		}
		
		// TODO Keep this info?
		if (path.length() > 3 && path.endsWith(separatorAsString))
			path = path.substring(0, path.length() - 1);
		
		return new WindowsPath(this, path);
	}
	
	private String getCanonical(String path) {
		try {
			File file = new File(path);
			return file.getCanonicalPath();
		} catch (IOException e) {
			return path;
		}
	}
	
	@Override
	public List<Path> getRoots() {
		List<Path> result = new ArrayList<Path>();
		for (File file : File.listRoots())
			result.add(new WindowsPath(this, file.getAbsolutePath()));
		return result;
	}
	
	@Override
	public String validateRoot(String name) {
		if (name.length() < 2 || name.length() > 3 || !Character.isLetter(name.charAt(0))
				|| name.charAt(1) != ':')
			return pathValidations[0];
		
		if (name.length() == 3 && name.charAt(2) != separator)
			return pathValidations[1];
		
		return null;
	}
	
	@Override
	public String validateFileName(String name) {
		if (name == null)
			throw new IllegalArgumentException("name == null");
		
		if (name.equals(""))
			return pathValidations[2];
		
		if (name.equals(".") || name.equals(".."))
			return pathValidations[3];
		
		for (char c : fileNameInvalidChars)
			if (name.indexOf(c) >= 0)
				return "Path can not contain a " + c;
		
		return null;
	}
}
