package org.pescuma.annoyingfilesystem.windows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.pescuma.annoyingfilesystem.Path;
import org.pescuma.annoyingfilesystem.PathAttributes;
import org.pescuma.annoyingfilesystem.PathContents;

public class WindowsPath implements Path {
	
	private final String path;
	private final File file;
	
	public WindowsPath(String path) {
		this.path = path;
		file = new File(path);
	}
	
	@Override
	public String getFullPath() {
		return path;
	}
	
	@Override
	public String getName() {
		if (isRoot())
			return path.substring(0, 2);
		else
			return file.getName();
	}
	
	@Override
	public boolean canReadContents() {
		if (!file.exists())
			return false;
		
		if (file.isDirectory())
			return false;
		else
			return file.canRead();
	}
	
	@Override
	public boolean canWriteContents() {
		if (isRoot())
			return false;
		
		if (!file.exists())
			return getParent().canAddChild();
		
		if (file.isDirectory())
			return false;
		else
			return file.canWrite();
	}
	
	@Override
	public PathContents getContents() {
		// TODO Auto-generated method stub
		return null;
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
	
	@Override
	public List<Path> getChildren() {
		List<Path> result = new ArrayList<Path>();
		
		if (!file.exists())
			return result;
		
		for (File child : file.listFiles())
			result.add(new WindowsPath(child.getAbsolutePath()));
		return result;
	}
	
	@Override
	public WindowsPath getChild(String... names) {
		StringBuilder childPath = new StringBuilder();
		
		if (path.endsWith("\\"))
			childPath.append(path.substring(0, path.length() - 1));
		else
			childPath.append(path);
		
		for (String name : names) {
			childPath.append("\\");
			childPath.append(name);
			
			if (name == "" || name == "." || name == "..")
				throw new InvalidWindowsPathException(childPath.toString());
			
			for (char c : InvalidWindowsPathException.invalidChars)
				if (name.indexOf(c) >= 0)
					throw new InvalidWindowsPathException(childPath.toString());
		}
		
		return new WindowsPath(childPath.toString());
	}
	
	@Override
	public boolean exists() {
		return file.exists();
	}
	
	@Override
	public boolean isFile() {
		return !file.isDirectory();
	}
	
	@Override
	public boolean isFolder() {
		return file.isDirectory();
	}
	
	@Override
	public void createAsFile() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void createAsFolder() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void createParentFolder() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public PathAttributes getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isRoot() {
		return path.length() <= 3;
	}
	
	@Override
	public Path getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int hashCode() {
		return path.toLowerCase().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WindowsPath other = (WindowsPath) obj;
		if (!path.equalsIgnoreCase(other.path))
			return false;
		return true;
	}
	
	@Override
	public boolean equals(Path other) {
		return equals((Object) other);
	}
	
}
