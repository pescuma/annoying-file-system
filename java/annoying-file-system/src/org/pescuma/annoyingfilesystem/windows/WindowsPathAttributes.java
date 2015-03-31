package org.pescuma.annoyingfilesystem.windows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

import org.pescuma.annoyingfilesystem.PathAttributes;

public class WindowsPathAttributes implements PathAttributes {

	private String path;
	private Path jpath;
	private BasicFileAttributes attributes;

	public WindowsPathAttributes(String path) {
		this.path = path;
		this.jpath = Paths.get(path);

		try {
			attributes = Files.readAttributes(jpath, BasicFileAttributes.class);
		} catch (IOException e) {
			attributes = null;
		}
	}

	@Override
	public boolean exists() {
		return attributes != null;
	}

	@Override
	public boolean isRoot() {
		return path.length() <= 3;
	}

	@Override
	public boolean isFile() {
		if (attributes == null)
			return false;

		return !attributes.isDirectory();
	}

	@Override
	public boolean isFolder() {
		if (attributes == null)
			return false;

		return attributes.isDirectory();
	}

	@Override
	public boolean isSymbolicLink() {
		if (attributes == null)
			return false;

		return Files.isSymbolicLink(jpath);
	}

	@Override
	public Date getCreationDate() {
		if (attributes == null)
			return null;

		FileTime date = attributes.creationTime();
		if (date == null)
			return null;

		return new Date(date.toMillis());
	}

	@Override
	public Date getLastModificationDate() {
		if (attributes == null)
			return null;

		FileTime date = attributes.lastModifiedTime();
		if (date == null)
			return null;

		return new Date(date.toMillis());
	}

	@Override
	public Date getLastAccessDate() {
		if (attributes == null)
			return null;

		FileTime date = attributes.lastAccessTime();
		if (date == null)
			return null;

		return new Date(date.toMillis());
	}

	@Override
	public long size() {
		if (attributes == null)
			return 0;

		return attributes.size();
	}

}
