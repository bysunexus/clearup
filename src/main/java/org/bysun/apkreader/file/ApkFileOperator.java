package org.bysun.apkreader.file;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bysun.apkreader.model.ApkInfoDto;

/**
 * apk文件操作类
 */
public class ApkFileOperator {
	private Map<String,Integer> version;//存放版本 以删旧留新
	private String baseFilePath;//基本存放路径

	public ApkFileOperator(String baseFilePath) {
		super();
		this.version = new HashMap<String,Integer>();
		this.baseFilePath = baseFilePath;
	}
	
	public void process(List<File> apks){
		for (File apk : apks) {
			// 读取apk文件信息
			ApkInfoDto info = ApkInfoUtils.readApkInfo(apk);
			// 处理apk文件
			processFile(info,apk);
		}
	}

	private void processFile(ApkInfoDto info,File apk) {
		if(StringUtils.isBlank(info.getPkgName())||info.getVersion()==-1)
			return;
		// 取得已处理的版本号
		Integer vson = version.get(info.getPkgName());
		if(vson==null){ //还没有已处理
			// 放入版本号
			version.put(info.getPkgName(), info.getVersion());
			// 文件改名
			renameFile(apk,buildFileName(info.getPkgName(),info.getVersion()));
		}else{
			if(info.getVersion()>vson.intValue()){
				// 放入版本号
				version.put(info.getPkgName(), info.getVersion());
				// 删除老文件
				deleteFile(buildFileName(info.getPkgName(),vson.intValue()));
				// 重命名新文件
				renameFile(apk,buildFileName(info.getPkgName(),info.getVersion()));
			}else{
				// 删除文件
				apk.delete();
			}
		}
	}
	
	private void renameFile(File apk,String newName){
		apk.renameTo(new File(baseFilePath+File.separatorChar+newName));
	}
	
	private void deleteFile(String fileName){
		File file = new File(baseFilePath+File.separatorChar+fileName);
		if(!file.isDirectory()&&file.exists()){
			file.delete();
		}
	}
	
	private String buildFileName(String pkgName,int version){
		String fileName = pkgName+"_"+version+".apk";
		return fileName;
	}

	public Map<String, Integer> getVersion() {
		return version;
	}
}
