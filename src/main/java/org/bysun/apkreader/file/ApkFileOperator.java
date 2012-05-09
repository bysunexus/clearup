package org.bysun.apkreader.file;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bysun.apkreader.model.ApkInfoDto;

/**
 * apk�ļ�������
 */
public class ApkFileOperator {
	private Map<String,Integer> version;//��Ű汾 ��ɾ������
	private String baseFilePath;//�������·��

	public ApkFileOperator(String baseFilePath) {
		super();
		this.version = new HashMap<String,Integer>();
		this.baseFilePath = baseFilePath;
	}
	
	public void process(List<File> apks){
		for (File apk : apks) {
			// ��ȡapk�ļ���Ϣ
			ApkInfoDto info = ApkInfoUtils.readApkInfo(apk);
			// ����apk�ļ�
			processFile(info,apk);
		}
	}

	private void processFile(ApkInfoDto info,File apk) {
		if(StringUtils.isBlank(info.getPkgName())||info.getVersion()==-1)
			return;
		// ȡ���Ѵ���İ汾��
		Integer vson = version.get(info.getPkgName());
		if(vson==null){ //��û���Ѵ���
			// ����汾��
			version.put(info.getPkgName(), info.getVersion());
			// �ļ�����
			renameFile(apk,buildFileName(info.getPkgName(),info.getVersion()));
		}else{
			if(info.getVersion()>vson.intValue()){
				// ����汾��
				version.put(info.getPkgName(), info.getVersion());
				// ɾ�����ļ�
				deleteFile(buildFileName(info.getPkgName(),vson.intValue()));
				// ���������ļ�
				renameFile(apk,buildFileName(info.getPkgName(),info.getVersion()));
			}else{
				// ɾ���ļ�
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
