package org.pescuma.annoyingfilesystem.windows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.pescuma.annoyingfilesystem.FileSystem;
import org.pescuma.annoyingfilesystem.Path;

public class WindowsFileSystem implements FileSystem {
	
	public static final String[] pathValidations = new String[] {
			"It must start with a driver leter and a :", "It must have folders separated by \\",
			"It can not have a empty folder name", "The folders can not be named . or .." };
	
	public static final char[] fileNameInvalidChars = new char[] { '\\', '/', ':', '*', '?', '"',
			'<', '>', '|' };
	
	public static final char separator = '\\';
	
	@Override
	public WindowsPath createPath(String path) {
		
		if (path.length() < 2 || path.charAt(1) != ':' || !Character.isLetter(path.charAt(0)))
			throw new InvalidWindowsPathException(path);
		
		path = path.replace('/', separator);
		
		if (path.length() > 2) {
			if (path.charAt(2) != separator)
				throw new InvalidWindowsPathException(path);
			
			if (path.indexOf(Character.toString(separator) + separator) >= 0)
				throw new InvalidWindowsPathException(path);
			
		} else {
			path += separator;
		}
		
		path = getCanonical(path);
		
		if (path.length() > 3) {
			// TODO Keep this info?
			if (path.endsWith(Character.toString(separator)))
				path = path.substring(0, path.length() - 1);
			
			String insideRoot = path.substring(3);
			for (char c : fileNameInvalidChars)
				if (c != separator)
					if (insideRoot.indexOf(c) >= 0)
						throw new InvalidWindowsPathException(path);
		}
		
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
}
