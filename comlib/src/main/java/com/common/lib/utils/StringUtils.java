package com.common.lib.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {
	/**
	 * 验证
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0 || s.equals("null");
	}

	/**
	 * 验证邮箱的合法性 ^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		if (isEmpty(email))
			return false;
		String pattern_ = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
		Pattern pattern = Pattern.compile(pattern_);
		Matcher mat = pattern.matcher(email);
		return mat.matches();
	}

	/**
	 * 判断日期格式是否正确
	 * 
	 * @param sDate
	 * @return
	 */
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length < 1)
			return false;

		int i = 0;
		if (length > 1 && chars[0] == '-')
			i = 1;

		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	/**
	 * 验证姓名是中文
	 */
	public static boolean isValidUserName(String name) {
		if (isEmpty(name)) {
			return false;
		} else {

			// ^[_a-zA-Z0-9+\.\u4e00-\u9fa5]{2,6}$
			name = new String(name.getBytes());// 用GBK编码
			// final String pattern_ = "^[_a-zA-Z0-9+\\.\u4e00-\u9fa5]{2,6}$";
			String pattern_ = "^[\u4e00-\u9fa5]{2,6}$";
			Pattern pattern = Pattern.compile(pattern_);
			Matcher mat = pattern.matcher(name);
			return mat.matches();
		}

	}

	/**
	 * 验证密码为字母、数字、下划线两者及以上8-20个字符 ^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)\\w{8,20}$
	 * (?!^(\\d+|[a-zA-Z]+|[_]+)$)^[\\w]{8,20}$
	 */
	public static boolean checkPassword(String password) {
		boolean tag = false;
		if (isEmpty(password)) {
			tag = false;
		} else {
			String pattern_ = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)\\w{8,20}$";

			Pattern pattern = Pattern.compile(pattern_);
			Matcher mat = pattern.matcher(password);
			return mat.matches();
		}

		return tag;
	}

	/**
	 * 验证密码为英文加数字
	 */
	public static boolean checkPasswordVar(String password) {
		boolean tag = false;
		if (isEmpty(password)) {
			tag = false;
		} else {
			String pattern_ = "^[A-Za-z0-9]{6,16}$";
			Pattern pattern = Pattern.compile(pattern_);
			Matcher mat = pattern.matcher(password);
			return mat.matches();
		}

		return tag;
	}

	public static boolean isValidMobile(String mobile) {
		if (StringUtils.isEmpty(mobile))
			return false;
		// Pattern pattern=Pattern.compile("^[13,14,15,18,19]\\d{9}$");
		Pattern pattern = Pattern
				.compile("^((13[0-9])|(14[7])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	public static boolean isValidQQ(String qq) {
		if (StringUtils.isEmpty(qq))
			return false;
		// Pattern pattern=Pattern.compile("^[13,14,15,18,19]\\d{9}$");
		Pattern pattern = Pattern.compile("^[1-9]\\d{3,}$");
		Matcher matcher = pattern.matcher(qq);
		return matcher.matches();
	}

	public static boolean isValidAmount(String amount) {
		if (StringUtils.isEmpty(amount))
			return false;
		Pattern pattern = Pattern
				.compile("^((\\d{1,})|([0-9]+\\.[0-9]{1,2}))$");
		Matcher matcher = pattern.matcher(amount);
		return matcher.matches();
	}

	public static boolean isValidNumber(String url) {
		if (StringUtils.isEmpty(url))
			return false;
		Pattern pattern = Pattern.compile("^\\d{1,}$");
		Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}

	/**
	 * 文本框中过滤特殊字符 [~@#$%^&*_+<>@#￥%] < > % ' " $ = (后台限制的字符)
	 */
	public static String StringFilter(String content) {
		if (isEmpty(content)) {
			return "";
		} else {
			String pattern_ = "[<>%'\"$=]";

			Pattern pattern = Pattern.compile(pattern_);
			Matcher mat = pattern.matcher(content);
			return mat.replaceAll("*").trim();
		}
	}

	/**
	 * 验证文本框中是否包含特殊字符 [~@#$%^&*_+<>@#￥%] < > % ' " $ = (后台限制的字符)
	 */
	public static boolean hasLawlessStr(String content) {
		if (isEmpty(content)) {
			return false;
		} else {
			String pattern_ = "[<>%'\"$=]";

			Pattern pattern = Pattern.compile(pattern_);
			Matcher mat = pattern.matcher(content);
			return mat.find();
		}
	}

	/**
	 * 验证是否境外手机 (^\+?\d{1,3}?(\(\d{2,5})\))(\d{3,15})(-(\d{4,8}))?$
	 */
	public static boolean isValidMobileForeign(String content) {
		if (isEmpty(content)) {
			return false;
		} else {
			String pattern_ = "(^\\+?\\d{1,3}?(\\(\\d{2,5})\\))(\\d{3,15})(-(\\d{4,8}))?$";

			Pattern pattern = Pattern.compile(pattern_);
			Matcher mat = pattern.matcher(content);
			return mat.matches();
		}
	}

	/**
	 * 验证港澳台身份证
	 */
	public static boolean checkForeignIdCard(String idCard) {
		boolean tag = false;
		if (isEmpty(idCard)) {
			tag = false;
		} else {
			String pattern_ = "^[A-Za-z0-9()]{6,25}$";
			Pattern pattern = Pattern.compile(pattern_);
			Matcher mat = pattern.matcher(idCard);
			return mat.matches();
		}

		return tag;
	}

	/**
	 * 验证密身份证
	 */
	public static boolean checkIdCard(String idCard) {
		boolean tag = false;
		if (isEmpty(idCard)) {
			tag = false;
		} else {
			String pattern_ = "^[0-9xX]{15,18}$";
			Pattern pattern = Pattern.compile(pattern_);
			Matcher mat = pattern.matcher(idCard);
			return mat.matches();
		}

		return tag;
	}

	/**
	 * 将String转换为Long
	 * 
	 * @param str
	 * @return
	 */
	public static long parserLong(String str) {
		if (str == null || (str = str.trim()).length() <= 0) {
			return 0;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param
	 * @return
	 */
	public static String subZeroAndDot(String input) {
		if (input.indexOf(".") > 0) {
			input = input.replaceAll("0+?$", "");// 去掉多余的0
			input = input.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return input;
	}

	/**
	 * @Title: accountNoEncrypt
	 * @Description: 账号打码加密
	 * @param @param accountNo
	 * @param @return 设定参数
	 * @return String 返回类型
	 * @throws
	 */
	public static String accountNoEncrypt(String accountNo) {
		if (StringUtils.isEmpty(accountNo))
			return "";

		if (StringUtils.isValidMobile(accountNo))// 手机号打码方式
		{

			accountNo = accountNo.substring(0, 3) + "****"
					+ accountNo.substring(7);
		} else if (StringUtils.isValidEmail(accountNo))// 邮箱打码方式
		{
			if (accountNo.lastIndexOf("@") < 4) {
				accountNo = accountNo.charAt(0)
						+ "***"
						+ accountNo.substring(accountNo.indexOf("@"),
								accountNo.length());
			} else {
				accountNo = accountNo.substring(0, 3)
						+ "***"
						+ accountNo.substring(accountNo.indexOf("@"),
								accountNo.length());
			}
		} else {
			if (accountNo.length() > 7) {
				accountNo = accountNo.substring(0, 4) + "****"
						+ accountNo.substring(accountNo.length() - 2);
			} else {
				accountNo = accountNo.substring(0, 2) + "***"
						+ accountNo.substring(accountNo.length() - 2);
			}

		}

		return accountNo;
	}

	/**
	 * 商家订单号打码规则 accountNo大于10位，打码
	 * 
	 * @param accountNo
	 * @return
	 */
	public static String orderNoEncrypt(String accountNo) {
		if (StringUtils.isEmpty(accountNo))
			return "";

		{
			if (accountNo.length() > 10) {
				accountNo = "****"
						+ accountNo.substring(accountNo.length() - 10);
			}
		}
		return accountNo;
	}


	/**
	 * 将list集合转化成String
	 * 
	 * @param list
	 * @return
	 */
	public static String listToString(List<?> list) {
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 == list.size()) {
				result += list.get(i).toString();
			} else {
				result += list.get(i).toString() + " ";
			}
		}
		return result;
	}



	/**
	 * 检查对象是否为6-20个字符
	 */
	public static boolean isNumCount(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length <= 20 && length >=6)
			return true;
		else
			return  false;
	}

	/**
	 * 检查对象是否为6-20个字符
	 */
	public static boolean isEmailNum(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length <= 50 && length >=6)
			return true;
		else
			return  false;
	}

	/**
	 * 校验 手机格式 6-20
	 * add by yuwm 2017.04.07
	 * @param mobile moblie
	 * @return t/f
	 */
	public static boolean isMatchesMobile(String mobile) {
		Pattern p = Pattern.compile("^\\d{6,20}$");
		Matcher m = p.matcher(mobile);
		if (m.matches()) {
			return true;
		}else{
			return false;
		}
	}

}
