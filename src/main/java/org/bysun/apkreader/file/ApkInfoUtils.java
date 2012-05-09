package org.bysun.apkreader.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bysun.apkreader.model.ApkInfoDto;
public class ApkInfoUtils {
	private final static String BASE_PATH = ApkInfoUtils.class.getClassLoader().getResource("").getFile()+"aapt.exe dump badging ";
	private final static String RP_REGEX = "package: name='(.*?)' versionCode='(.*?)' versionName='(.*?)'";
	
	public static ApkInfoDto readApkInfo(File apk){
		ApkInfoDto info = new ApkInfoDto();
		if(!apk.exists()||apk.isDirectory())
			return info;
		info.setName(apk.getName());
		StringBuilder comm = new StringBuilder(BASE_PATH);
		comm.deleteCharAt(0);
		comm.append('"').append(apk.getAbsolutePath()).append('"');
		InputStream in = null;
		InputStreamReader inr = null;
		BufferedReader br = null;
		try {
			Process p = Runtime.getRuntime().exec(comm.toString());
			String infoStr = null;
			in = p.getErrorStream();
			inr = new InputStreamReader(in);
			br = new BufferedReader(inr);
			while ((infoStr=br.readLine())!=null) {
				throw new IOException("无法解析Apk文件信息");
			}
			infoStr = null;
			br.close();
			inr.close();
			in.close();
			
			in = p.getInputStream();
			inr = new InputStreamReader(in);
			br = new BufferedReader(inr);
			while ((infoStr=br.readLine())!=null) {
				if(infoStr.startsWith("package: name='")){
					String[] infos = infoStr.replaceAll(RP_REGEX, "$1;;;$2;;;$3").split(";;;");
					info.setPkgName(infos[0]);
					info.setVersion(Integer.parseInt(infos[1]));
					info.setVersionCode(infos[2]);
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null)
					br.close();
			} catch (IOException e) {}
			try {
				if(inr!=null)
					inr.close();
			} catch (IOException e) {}	
			try {
				if(in!=null)
					in.close();
			} catch (IOException e) {}
		}
		return info;
	}
	
	
}
