package com.cjw.demo.doc.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private static final String SFNO_PREFIX = "SF";
	private static final String SFNO_SPLIT_FLG = "0";
	private static final int USERID_LEN_32 = 32;
	private static final String PK_ERR_ID_PERFIX = "er";
	private static int PK_LENGTH = 32;
	// Win环境路径匹配模式
	private static final String WIN_PATH_PATTERN = "(?i)^[a-z]:([/|\\\\])([^/|\\\\])*(\\1\\w+\\.\\w+$)*";
	// Linux环境路径匹配模式
	private static final String LUX_PATH_PATTERN = "(/[^/|.]+)*(\\w+\\.\\w+)*";

	// 把字符串的回车转换成"\\n"
	public static String convert(final String s) {
		String str;
		str = s.replace("\r\n", "\n").replace("\r", "\n");
		String[] split = str.split("\n");
		str = "";
		for (int i = 0; i < split.length; i++) {
			str += split[i] + (i == split.length - 1 ? "" : "\\\\n");
		}
		return str;
	}

	// 判断是否不为空串
	public static boolean isNotEmpty(final String s) {
		return (s != null) && (!"".equals(s.trim()));
	}

	// 判断是否为空串
	public static boolean isEmpty(final String s) {
		return (s == null) || ("".equals(s.trim()));
	}

	// 判断是否为空" "返回true
	public static boolean isBlank(String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0))
			return true;
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// 判断是否不为空" "返回false
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	// 首字母大写
	public static String initialString(final String name) {
		if (name == null) {
			return "";
		}
		String s = name.trim();
		if (!"".equals(s)) {
			String i = s.substring(0, 1);
			s = i.toUpperCase() + s.substring(1);
		}
		return s;
	}

	// 首字母小写
	public static String lowFirstString(final String name) {
		if (name == null) {
			return "";
		}
		String s = name.trim();
		if (!"".equals(s)) {
			String i = s.substring(0, 1);
			s = i.toLowerCase() + s.substring(1);
		}
		return s;
	}

	// 逗号分割字符串
	public static List<Long> getListIds(final String listIds) {
		List<Long> list = new ArrayList<Long>();
		String[] stringList = listIds.split(",");
		for (int i = 0; i < stringList.length; i++) {
			list.add(i, Long.parseLong(stringList[i]));
		}
		return list;
	}

	// 逗号分割字符串
	public static List<String> getListStrs(final String listIds) {
		List<String> list = new ArrayList<String>();
		String[] stringList = listIds.split(",");
		for (int i = 0; i < stringList.length; i++) {
			list.add(i, stringList[i]);
		}
		return list;
	}

	// 判断字符是否为空
	public static boolean checkedIsEmpty(final String str) {
		if (str == null || str.equals("")) {
			return true;
		}

		return false;
	}

	// 判断字符串是否相同，null == ""
	public static boolean checkedEqualString(final String str1, final String str2) {
		if ((str1 == null && str2 == null) || (str1 == null && str2.equals("")) || (str2 == null && str1.equals(""))
				|| str1.equals(str2)) {
			return true;
		}

		return false;
	}

	// 不足32位前面补 0
	public static String fillBeginWith0(String str) {
		if (null != str && !"".equals(str)) {
			int len = str.length();
			for (int i = 0; i < USERID_LEN_32 - len; i++) {
				str = "0" + str;
			}
		}
		return str;
	}

	// 判断是否是允许的字符串（过滤特殊字符）
	public static boolean isPermitChars(final String str) {
		boolean flag = true;
		if (isNotEmpty(str)) {
			String regex = "[` ~!#$%^&*()+=|{}':;',\\[\\]<>/?~！#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(str);
			if (m.find()) {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean isNaN(final String obj) {
		if (isEmpty(obj)) {
			return false;
		} else {
			return Pattern.matches("^\\d{1,}+$", obj.toString());
		}
	}

	// 是否是[float,double]类型数据
	public static boolean isDouble(String str) {
		if (isEmpty(str)) {
			return false;
		} else {
			return Pattern.matches("^[0-9]+\\.[0-9]{1,}$", str.toString());
		}
	}
	// 是否是[float,double]类型数据
	public static boolean isInt(String str) {
		if (isEmpty(str)) {
			return false;
		} else {
			return Pattern.matches("[+-]?[1-9]+[0-9]*(\\.[0-9]+)?", str.toString());
		}
	}

	// 匹配文件路径
	public static boolean matchPath(String path) {
		boolean isMatched = false;
		if (!isEmpty(path)) {
			if (path.matches(WIN_PATH_PATTERN) || path.matches(LUX_PATH_PATTERN)) {
				isMatched = true;
			}
		}
		return isMatched;
	}

	// 生成主键
	public static String genPrimaryKey() {
		try {
			String pk = getUUID();
			return pk;
		} catch (Exception e) {
			return genPkWhenPostErr();
		}
	}

	// 生成唯一长整型
	public static long randomLong() {
		return Math.abs(UUID.randomUUID().getLeastSignificantBits());
	}

	// 生成唯一短整型
	public static int randomInt() {
		return (int) (Math.abs(UUID.randomUUID().getLeastSignificantBits()) % Integer.MAX_VALUE);
	}

	// 生成唯一浮点型
	public static double randomDouble() {
		return Math.abs(UUID.randomUUID().getLeastSignificantBits()) / Integer.MAX_VALUE;
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "").toUpperCase();
		return uuidStr;
	}

	public static String genPkWhenPostErr() {
		StringBuffer bf = new StringBuffer(PK_ERR_ID_PERFIX);
		String timeLongVar = String.valueOf(new Date().getTime());
		bf.append(timeLongVar);
		java.util.Random rdm = new java.util.Random();
		String rdmVar = String.valueOf(rdm.nextLong());
		bf.append(rdmVar);
		String ret = (bf != null && bf.length() > PK_LENGTH) ? bf.toString().substring(0, PK_LENGTH) : bf.toString();
		return ret.toUpperCase();
	}

	// 根据Unicode编码完整的判断中文汉字和符号
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	// 完整的判断中文汉字和符号
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 生成顺丰号后缀
	 * 
	 * @return string
	 */
	private static String getSfNoSuffixByRdm() {
		return genPrimaryKey();
	}

	/**
	 * 生成SF NUMBER
	 * 
	 * @return string
	 */
	public static String genSfNo() {
		String sfNo = GenSfNoPerfix() + getSfNoSuffixByRdm();
		return sfNo;
	}

	/**
	 * 
	 * @return string
	 */
	private static String GenSfNoPerfix() {
		StringBuffer sfNoBuf = new StringBuffer(SFNO_PREFIX);
		SimpleDateFormat yyyyMmDd = new SimpleDateFormat("yyyyMMdd");
		String strYmd = yyyyMmDd.format(new Date());
		sfNoBuf.append(strYmd);
		sfNoBuf.append(SFNO_SPLIT_FLG);
		sfNoBuf.append(strYmd);
		return sfNoBuf.toString();
	}

	public static int getStrLength(String value) {
		try {
			return value == null ? 0 : value.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			return 0;
		}
	}

	public static String nullValue() {
		return null;
	}
	
	public static String listToString(List<String> stringList) {

		if (stringList == null || stringList.size() <= 0) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	/**
	 * 去掉符串中的空格、回车、换行符、制表符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str){
		String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
	}
}
