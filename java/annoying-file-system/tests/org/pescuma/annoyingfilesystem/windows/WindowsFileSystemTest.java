package org.pescuma.annoyingfilesystem.windows;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.pescuma.annoyingfilesystem.Path;

public class WindowsFileSystemTest {
	
	private WindowsFileSystem fs;
	
	@Before
	public void setUp() throws Exception {
		fs = new WindowsFileSystem();
	}
	
	@Test
	public void createRootNoSlash() {
		assertNotNull(fs.createPath("c:"));
	}
	
	@Test
	public void createRootSlash() {
		assertNotNull(fs.createPath("c:\\"));
	}
	
	@Test
	public void createFileWithName() {
		assertNotNull(fs.createPath("c:\\abc"));
	}
	
	@Test
	public void getRootName() {
		Path path = fs.createPath("c:");
		assertEquals("C:", path.getName());
	}
	
	@Test
	public void getRootNameWithSlash() {
		Path path = fs.createPath("c:\\");
		assertEquals("C:", path.getName());
	}
	
	@Test
	public void getFileName() {
		Path path = fs.createPath("c:\\abc");
		assertEquals("abc", path.getName());
	}
	
	@Test
	public void getFileNameWithSpecialChars() {
		Path path = fs.createPath("c:\\Abc $)(&^%$£!.a..bbs");
		assertEquals("Abc $)(&^%$£!.a..bbs", path.getName());
	}
	
	@Test
	public void createFileWithNameInsideFolder() {
		assertNotNull(fs.createPath("c:\\abc\\bb"));
	}
	
	@Test
	public void getFileNameInsideFolder() {
		Path path = fs.createPath("c:\\abc\\bb");
		assertEquals("bb", path.getName());
	}
	
	@Test
	public void getRootFullPath() {
		Path path = fs.createPath("c:");
		assertEquals("C:\\", path.getFullPath());
	}
	
	@Test
	public void getFileFullPath() {
		Path path = fs.createPath("c:\\abc");
		assertEquals("C:\\abc", path.getFullPath());
	}
	
	@Test
	public void getFileInsideFolderFullPath() {
		Path path = fs.createPath("c:\\abc\\bb");
		assertEquals("C:\\abc\\bb", path.getFullPath());
	}
	
	@Test
	public void getRootsIsNotEmpty() {
		assertNotNull(!fs.getRoots().isEmpty());
	}
	
	@Test
	public void getParentOfRoot() {
		Path path = fs.createPath("c:\\");
		assertNull(path.getParent());
	}
	
	@Test
	public void getParentOfFile() {
		Path path = fs.createPath("c:\\abc");
		assertEquals("C:\\", path.getParent().getFullPath());
	}
	
	@Test
	public void getParentOfFileInsideFolder() {
		Path path = fs.createPath("c:\\abc\\dde.e");
		assertEquals("C:\\abc", path.getParent().getFullPath());
	}
}
