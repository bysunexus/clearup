package org.bysun.apkreader.directory;

import java.io.File;
import java.util.List;

//import java.io.File;
//import java.io.IOException;
//import java.nio.file.DirectoryStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;

/**
 * java7 ��Ŀ¼�ļ��б��ȡʵ�� 
 * �б�Ŀ¼��ָ����ʽ���ļ� ʹ��JAVA7 api
 */
public class FileListBuilderAPI7 implements IFileListBuilder {
	@Override
	public List<File> listFile() {return null;}
	@Override
	public String getBasePathStr() {return null;}
	@Override
	public File getBasePath() {return null;}
	
//	private Path path; //�ļ�·��
//	private String filter ; // �ļ����͹���
//	private List<File> fileList; // ��ȡ�����ļ��б�
//
//	/**
//	 * @param path
//	 * @param filter like *.{apk,java,class,....}
//	 * @throws IOException 
//	 */
//	public FileListBuilderAPI7(Path path, String filter) throws IOException {
//		super();
//		this.fileList = new ArrayList<File>();
//		this.path = path;
//		this.filter = filter;
//		validBasePath();
//	}
//
//	/**
//	 * @param path
//	 * @param filter like *.{apk,java,class,....}
//	 * @throws IOException 
//	 */
//	public FileListBuilderAPI7(String path, String filter) throws IOException {
//		super();
//		this.fileList = new ArrayList<File>();
//		this.path = Paths.get(path);
//		this.filter = filter;
//		validBasePath();
//		try {
//			DirectoryStream<Path> stream = Files.newDirectoryStream(path, filter);
//			for (Path path : stream) {
//				fileList.add(path.toFile());
//			}
//		} catch (IOException e) {
//	
//		}
//	}
//	
//	
//
//	@Override
//	public List<File> listFile() {
//		return fileList;
//	}
//
//	@Override
//	public String getBasePathStr() {
//		return path.toFile().getAbsolutePath();
//	}
//
//	@Override
//	public File getBasePath() {
//		return path.toFile();
//	}
//	
//	private void validBasePath() throws IOException {
//		if(!Files.isDirectory(path)||!Files.exists(path)){
//			throw new IOException("ָ���Ĳ���·����ָ��·��������!");
//		}
//	}
}
