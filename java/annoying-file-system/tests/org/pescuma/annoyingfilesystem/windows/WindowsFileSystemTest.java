package org.pescuma.annoyingfilesystem.windows;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
	public void getFullPathWithoutSlashInTheEnd() {
		Path path = fs.createPath("c:\\abc\\bb\\");
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
	
	@Test
	public void getSimpleChildOfRoot() {
		Path path = fs.createPath("c:");
		assertEquals("C:\\a", path.getChild("a").getFullPath());
	}
	
	@Test
	public void getDoubleSimpleChildOfRoot() {
		Path path = fs.createPath("c:");
		assertEquals("C:\\a\\x.y", path.getChild("a", "x.y").getFullPath());
	}
	
	@Test
	public void getComplexChildOfRoot() {
		Path path = fs.createPath("c:");
		assertEquals("C:\\a\\x.y", path.getChild("a\\x.y").getFullPath());
	}
	
	@Test
	public void getRelativeChildOfRoot() {
		Path path = fs.createPath("c:");
		assertEquals("C:\\b\\x.y", path.getChild("a\\..\\b\\x.y").getFullPath());
	}
	
	@Test
	public void getComplexRelativeChildOfRoot() {
		Path path = fs.createPath("c:");
		assertEquals("C:\\b\\t", path.getChild("a\\..\\b\\x.y", "\\..\\t").getFullPath());
	}
	
	@Test
	public void getChildWithLotsOfSlashes() {
		Path path = fs.createPath("c:");
		assertEquals("C:\\a\\b", path.getChild("\\a\\", "\\b\\").getFullPath());
	}
	
	@Test
	public void simpleEquals() {
		assertEquals(true, fs.createPath("c:\\a").equals(fs.createPath("c:\\a")));
	}
	
	@Test
	public void simpleNotEquals() {
		assertEquals(false, fs.createPath("c:\\a").equals(fs.createPath("c:\\b")));
	}
	
	@Test
	public void equalsIgnoreCase() {
		assertEquals(true, fs.createPath("c:\\a").equals(fs.createPath("C:\\A")));
	}
	
	@Test
	public void equalsIgnoreSlashAtEnd() {
		assertEquals(true, fs.createPath("c:\\a").equals(fs.createPath("c:\\a\\")));
	}
	
	@Test
	public void equalsIgnoreSlashAtEndInRoot() {
		assertEquals(true, fs.createPath("c:").equals(fs.createPath("c:\\")));
	}
	
	@Test
	public void handleIncorrectSlashes() {
		Path path = fs.createPath("c:/a/b");
		assertEquals("C:\\a\\b", path.getFullPath());
	}
	
	@Test
	public void validateRootC() {
		assertNull(fs.validateRoot("C:"));
	}
	
	@Test
	public void validateRootCaseInsensitive() {
		assertNull(fs.validateRoot("c:"));
	}
	
	@Test
	public void validateRootWithSlash() {
		assertNull(fs.validateRoot("C:\\"));
	}
	
	@Test
	public void validateRootWithTwoLetters() {
		assertNotNull(fs.validateRoot("CD:"));
	}
	
	@Test
	public void validateRootNoColon() {
		assertNotNull(fs.validateRoot("C"));
	}
	
	@Test
	public void validateRootDoubleColon() {
		assertNotNull(fs.validateRoot("C::"));
	}
	
	@Test
	public void validateFileNameNoExtension() {
		assertNull(fs.validateFileName("ab"));
	}
	
	@Test
	public void validateFileNameWithExtension() {
		assertNull(fs.validateFileName("ab"));
	}
	
	@Test
	public void validateFileNameIsNotRoot() {
		assertNotNull(fs.validateFileName("C:"));
	}
	
	@Test
	public void validateFileNameIsNotEmpty() {
		assertNotNull(fs.validateFileName(""));
	}
	
	@Test
	public void validateFileNameIsNotReserved() {
		assertNotNull(fs.validateFileName("."));
		assertNotNull(fs.validateFileName(".."));
	}
}
