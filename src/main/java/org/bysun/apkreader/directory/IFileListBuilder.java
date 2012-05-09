package org.bysun.apkreader.directory;

import java.io.File;
import java.util.List;

/**
 * 生成目录下文件列表
 */
public interface IFileListBuilder {
	/**
	 * 生成文件列表
	 */
	List<File> listFile();
	/**
	 * 取得基础路径
	 */
	String getBasePathStr();
	/**
	 * 取得基础路径
	 */
	File getBasePath();
}
