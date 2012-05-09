package org.bysun.apkreader.directory;

import java.io.File;
import java.util.List;

/**
 * ����Ŀ¼���ļ��б�
 */
public interface IFileListBuilder {
	/**
	 * �����ļ��б�
	 */
	List<File> listFile();
	/**
	 * ȡ�û���·��
	 */
	String getBasePathStr();
	/**
	 * ȡ�û���·��
	 */
	File getBasePath();
}
