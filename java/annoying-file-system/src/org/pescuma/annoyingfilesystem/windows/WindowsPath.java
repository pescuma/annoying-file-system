package org.pescuma.annoyingfilesystem.windows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.pescuma.annoyingfilesystem.Path;
import org.pescuma.annoyingfilesystem.PathContents;

public class WindowsPath implements Path {
	
	private final WindowsFileSystem fs;
	private final String path;
	private final File file;
	private WindowsPathAttributes attibutes;
	private WindowsPathPermissions permissions;
	
	public WindowsPath(WindowsFileSystem fs, String path) {
		this.fs = fs;
		this.path = path;
		file = new File(path);
	}
	
	@Override
	public String getFullPath() {
		return path;
	}
	
	@Override
	public String getName() {
		if (getAttributes().isRoot())
			return path.substring(0, 2);
		else
			return file.getName();
	}
	
	@Override
	public WindowsPathAttributes getAttributes() {
		if (attibutes == null)
			attibutes = new WindowsPathAttributes(path);
		
		return attibutes;
	}
	
	@Override
	public WindowsPathPermissions getPermissions() {
		if (permissions == null)
			permissions = new WindowsPathPermissions(path, file, getAttributes());
		
		return permissions;
	}
	
	@Override
	public PathContents getContents() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Path getParent() {
		if (getAttributes().isRoot())
			return null;
		
		int pos = path.lastIndexOf('\\');
		if (pos == 2)
			pos++;
		
		return new WindowsPath(fs, path.substring(0, pos));
	}
	
	@Override
	public List<Path> getChildren() {
		List<Path> result = new ArrayList<Path>();
		
		if (!getAttributes().exists())
			return result;
		
		for (File child : file.listFiles())
			result.add(fs.createPath(child.getAbsolutePath()));
		
		return result;
	}
	
	@Override
	public WindowsPath getChild(String name, String... relativeNames) {
		StringBuilder childPath = new StringBuilder();
		
		if (path.endsWith("\\"))
			childPath.append(path.substring(0, path.length() - 1));
		else
			childPath.append(path);
		
		if (!name.startsWith("\\"))
			childPath.append("\\");
		
		childPath.append(name);
		
		for (String rn : relativeNames) {
			if (!rn.startsWith("\\"))
				childPath.append("\\");
			
			childPath.append(rn);
		}
		
		return fs.createPath(childPath.toString());
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
	public void createParentFolders() {
		// TODO Auto-generated method stub
		
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
		return path.equalsIgnoreCase(other.path);
	}
	
	@Override
	public String toString() {
		return path;
	}
	
}
