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
 * java7 的目录文件列表读取实现 
 * 列表目录下指定格式的文件 使用JAVA7 api
 */
public class FileListBuilderAPI7 implements IFileListBuilder {
	@Override
	public List<File> listFile() {return null;}
	@Override
	public String getBasePathStr() {return null;}
	@Override
	public File getBasePath() {return null;}
	
//	private Path path; //文件路径
//	private String filter ; // 文件类型过滤
//	private List<File> fileList; // 读取到的文件列表
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
//			throw new IOException("指定的不是路径或指定路径不存在!");
//		}
//	}
}
