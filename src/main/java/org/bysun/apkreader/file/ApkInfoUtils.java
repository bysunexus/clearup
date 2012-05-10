package org.bysun.apkreader.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.bysun.apkreader.model.ApkInfoDto;
public class ApkInfoUtils {
	private final static String BASE_PATH = decode(ApkInfoUtils.class.getClassLoader().getResource("").getFile())+"aapt.exe dump badging ";
	private final static String RP_REGEX = "package: name='(.*?)' versionCode='(.*?)' versionName='(.*?)'";
	
	private static String decode(String arg){
		try {
			// ת��һ�±��� ����20%֮��ĳ���
			return URLDecoder.decode(arg, System.getProperty( "file.encoding"));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	
	public static ApkInfoDto readApkInfo(File apk){
		ApkInfoDto info = new ApkInfoDto();
		if(!apk.exists()||apk.isDirectory())
			return info;
		
		// ��ȡ�ļ���Ϣ
		info.setName(apk.getName());
		StringBuilder comm = new StringBuilder(BASE_PATH);
		// ɾ������ĸ /
		comm.deleteCharAt(0);
		// ����apk�ļ�·��
		comm.append('"').append(apk.getAbsolutePath()).append('"');
		InputStream in = null;
		InputStreamReader inr = null;
		BufferedReader br = null;
		Process p = null;
		try {
			// �����ⲿ����
			p = Runtime.getRuntime().exec(comm.toString());
			String infoStr = null;			
			in = p.getInputStream();
			// ȡ����Ϣ
			inr = new InputStreamReader(in);
			br = new BufferedReader(inr);
			// ��ȡ
			while ((infoStr=br.readLine())!=null) {
				// ֻ���ȡ�汾��Ϣ
				if(infoStr.startsWith("package: name='")){
					// ���������Ϣ
					String[] infos = infoStr.replaceAll(RP_REGEX, "$1;;;$2;;;$3").split(";;;");
					// ��ֵ
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
			if(p!=null){
				p.destroy();
			}
		}
		return info;
	}	
}
