package org.pescuma.annoyingfilesystem.windows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.pescuma.annoyingfilesystem.FileSystem;
import org.pescuma.annoyingfilesystem.Path;

public class WindowsFileSystem implements FileSystem {
	
	@Override
	public Path createPath(String path) {
		
		if (path.length() < 2 || path.charAt(1) != ':' || !Character.isLetter(path.charAt(0)))
			throw new InvalidWindowsPathException(path);
		
		if (path.length() > 2) {
			if (path.charAt(2) != '\\')
				throw new InvalidWindowsPathException(path);
			
			if (path.indexOf("\\\\") >= 0)
				throw new InvalidWindowsPathException(path);
		} else {
			path += "\\";
		}
		
		path = getCanonical(path);
		
		WindowsPath result = new WindowsPath(path.substring(0, 3));
		
		if (path.length() > 3) {
			if (path.endsWith("\\"))
				path = path.substring(0, path.length() - 1);
			
			result = result.getChild(path.substring(3).split("\\\\"));
		}
		
		return result;
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
			result.add(new WindowsPath(file.getAbsolutePath()));
		return result;
	}
}
