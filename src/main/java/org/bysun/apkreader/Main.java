package org.bysun.apkreader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bysun.apkreader.directory.FileListBuilderAPI6;
import org.bysun.apkreader.directory.IFileListBuilder;
import org.bysun.apkreader.file.ApkFileOperator;

public class Main {
	public static void main(String[] args){
		String path = "D:/appt";
		try {
			process(path);
		} catch (IOException e) {
		}
	}
	
	public static void process(String path) throws IOException{
		IFileListBuilder lb = new FileListBuilderAPI6(path, "*.{apk}");
		List<File> apks = lb.listFile();
		ApkFileOperator operator = new ApkFileOperator(path);
		operator.process(apks);		
	}
}
