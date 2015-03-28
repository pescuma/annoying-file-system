package org.pescuma.annoyingfilesystem;

import java.util.List;

public interface FileSystem {
	
	Path createPath(String path);
	
	List<Path> getRoots();
	
}
