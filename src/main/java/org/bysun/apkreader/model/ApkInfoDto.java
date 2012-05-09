package org.bysun.apkreader.model;

import java.io.Serializable;

/**
 * apk文件信息
 */
public class ApkInfoDto implements Serializable {

	private static final long serialVersionUID = 1445137834899840703L;
	
	private String name;
	private String pkgName;
	private int version = -1;
	private String versionCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pkgName == null) ? 0 : pkgName.hashCode());
		result = prime * result + version;
		result = prime * result + ((versionCode == null) ? 0 : versionCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApkInfoDto other = (ApkInfoDto) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pkgName == null) {
			if (other.pkgName != null)
				return false;
		} else if (!pkgName.equals(other.pkgName))
			return false;
		if (version != other.version)
			return false;
		if (versionCode == null) {
			if (other.versionCode != null)
				return false;
		} else if (!versionCode.equals(other.versionCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApkInfoDto [name=" + name + ", pkgName=" + pkgName + ", version=" + version
				+ ", versionCode=" + versionCode + "]";
	}
}
