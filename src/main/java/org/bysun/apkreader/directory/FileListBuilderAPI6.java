package org.bysun.apkreader.directory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 列表目录下指定格式的文件 使用JAVA6 api
 */
public class FileListBuilderAPI6 implements IFileListBuilder {
	private List<File> fileList; // 读取到的文件列表
	private File basePath; // 基本路径
	private NameFilter filter; // 文件类型过滤

	/**
	 * @param basePath
	 *            基本路径
	 * @param extNames
	 *            扩展文件名限制 like *.{apk,java,class,....}
	 * @throws IOException
	 */
	public FileListBuilderAPI6(File basePath, String extNames) throws IOException {
		super();
		this.fileList = new ArrayList<File>();
		this.basePath = basePath;
		this.filter = new NameFilter(extNames);
		validBasePath();

	}

	/**
	 * @param basePathStr
	 *            基本路径串
	 * @param extName
	 *            扩展文件名限制 like *.{apk,java,class,....}
	 * @throws IOException
	 */
	public FileListBuilderAPI6(String basePathStr, String extNames) throws IOException {
		super();
		this.fileList = new ArrayList<File>();
		this.basePath = new File(basePathStr);
		this.filter = new NameFilter(extNames);
		validBasePath();
	}

	@Override
	public List<File> listFile() {
		readFileList(basePath);
		return fileList;
	}

	private void readFileList(File dir) {
		File[] files = dir.listFiles(filter);
		fileList.addAll(Arrays.asList(files));
	}

	private void validBasePath() throws IOException {
		if (!basePath.isDirectory() || !basePath.exists()) {
			throw new IOException("指定的不是路径或指定路径不存在!");
		}
	}

	public String getBasePathStr() {
		return basePath.getAbsolutePath();
	}

	public File getBasePath() {
		return basePath;
	}

	private class NameFilter implements FileFilter {
		private Pattern pattern;
		private static final String regexMetaChars = ".^$+{[]|()";
		private static final String globMetaChars = "\\*?[{";
		private char EOL = 0; // TBD

		public NameFilter(String extNames) {
			super();
			init(extNames);
		}

		private void init(String extNames) {
			String expr = toRegexPattern(extNames);
			pattern = Pattern.compile(expr, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

		}

		private boolean isRegexMeta(char c) {
			return regexMetaChars.indexOf(c) != -1;
		}

		private boolean isGlobMeta(char c) {
			return globMetaChars.indexOf(c) != -1;
		}

		private char next(String glob, int i) {
			if (i < glob.length()) {
				return glob.charAt(i);
			}
			return EOL;
		}

		private String toRegexPattern(String globPattern) {
			boolean inGroup = false;
			StringBuilder regex = new StringBuilder("^");
			int i = 0;
			while (i < globPattern.length()) {
				char c = globPattern.charAt(i++);
				switch (c) {
				case '\\':
					// escape special characters
					if (i == globPattern.length()) {
						throw new PatternSyntaxException("No character to escape", globPattern,
								i - 1);
					}
					char next = globPattern.charAt(i++);
					if (isGlobMeta(next) || isRegexMeta(next)) {
						regex.append('\\');
					}
					regex.append(next);
					break;
				case '/':
					regex.append("\\\\");
					break;
				case '[':
					// don't match name separator in class
					regex.append("[[^\\\\]&&[");
					if (next(globPattern, i) == '^') {
						// escape the regex negation char if it appears
						regex.append("\\^");
						i++;
					} else {
						// negation
						if (next(globPattern, i) == '!') {
							regex.append('^');
							i++;
						}
						// hyphen allowed at start
						if (next(globPattern, i) == '-') {
							regex.append('-');
							i++;
						}
					}
					boolean hasRangeStart = false;
					char last = 0;
					while (i < globPattern.length()) {
						c = globPattern.charAt(i++);
						if (c == ']') {
							break;
						}
						if (c == '/' || c == '\\'){
							throw new PatternSyntaxException("Explicit 'name separator' in class",
									globPattern, i - 1);
						}
						// TBD: how to specify ']' in a class?
						if (c == '\\' || c == '[' || c == '&' && next(globPattern, i) == '&') {
							// escape '\', '[' or "&&" for regex class
							regex.append('\\');
						}
						regex.append(c);

						if (c == '-') {
							if (!hasRangeStart) {
								throw new PatternSyntaxException("Invalid range", globPattern,
										i - 1);
							}
							if ((c = next(globPattern, i++)) == EOL || c == ']') {
								break;
							}
							if (c < last) {
								throw new PatternSyntaxException("Invalid range", globPattern,
										i - 3);
							}
							regex.append(c);
							hasRangeStart = false;
						} else {
							hasRangeStart = true;
							last = c;
						}
					}
					if (c != ']') {
						throw new PatternSyntaxException("Missing ']", globPattern, i - 1);
					}
					regex.append("]]");
					break;
				case '{':
					if (inGroup) {
						throw new PatternSyntaxException("Cannot nest groups", globPattern, i - 1);
					}
					regex.append("(?:(?:");
					inGroup = true;
					break;
				case '}':
					if (inGroup) {
						regex.append("))");
						inGroup = false;
					} else {
						regex.append('}');
					}
					break;
				case ',':
					if (inGroup) {
						regex.append(")|(?:");
					} else {
						regex.append(',');
					}
					break;
				case '*':
					if (next(globPattern, i) == '*') {
						// crosses directory boundaries
						regex.append(".*");
						i++;
					} else {
						// within directory boundary
						regex.append("[^\\\\]*");
					}
					break;
				case '?':
					regex.append("[^\\\\]");
					break;

				default:
					if (isRegexMeta(c)) {
						regex.append('\\');
					}
					regex.append(c);
				}
			}

			if (inGroup) {
				throw new PatternSyntaxException("Missing '}", globPattern, i - 1);
			}

			return regex.append('$').toString();
		}

		@Override
		public boolean accept(File file) {
			if (file.isDirectory())
				readFileList(file);
			return pattern.matcher(file.getName()).matches();
		}
	}
}
