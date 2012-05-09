/**
 * 
 */
package org.bysun.apkreader.directory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ABC
 *
 */
public class FileListBuilderAPI6Test {
	IFileListBuilder builder ;
	@Before
	public void init() throws IOException{
		builder = new FileListBuilderAPI6("D:/DOC", "*.{pdf}");
	}
	/**
	 * Test method for {@link org.bysun.apkreader.directory.FileListBuilderAPI6#listFile()}.
	 */
	@Test
	public void testListFile() {
		List<File> list = builder.listFile();
		for(File f : list){
			System.out.println(f.getName());
		}
	}

	/**
	 * Test method for {@link org.bysun.apkreader.directory.FileListBuilderAPI6#getBasePathStr()}.
	 */
	@Test
	public void testGetBasePathStr() {
		System.out.println(builder.getBasePathStr());
	}

	/**
	 * Test method for {@link org.bysun.apkreader.directory.FileListBuilderAPI6#getBasePath()}.
	 */
	@Test
	public void testGetBasePath() {
		System.out.println(builder.getBasePath().getAbsolutePath());
	}

}
