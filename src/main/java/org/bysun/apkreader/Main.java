package org.bysun.apkreader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bysun.apkreader.directory.FileListBuilderAPI7;
import org.bysun.apkreader.directory.IFileListBuilder;
import org.bysun.apkreader.file.ApkFileOperator;

public class Main {
	public static void main(String[] args) throws IOException {
		String path = "D:/appt";
		IFileListBuilder lb = new FileListBuilderAPI7(path, "*.{apk}");
		List<File> apks = lb.listFile();
		ApkFileOperator operator = new ApkFileOperator(path);
		operator.process(apks);
	}
}
