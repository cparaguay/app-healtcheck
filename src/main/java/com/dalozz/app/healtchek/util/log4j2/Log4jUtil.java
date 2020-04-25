package com.dalozz.app.healtchek.util.log4j2;

import java.text.MessageFormat;


public class Log4jUtil {

	private static final String INIT = "[[{0}[[";
	private static final String END = "]]{0}]]";
	
	private Log4jUtil() {
	}
	
	public static String inicioLog(String metodo) {
		return MessageFormat.format(INIT, metodo);
	}
	
	public static String inicioLog(String metodo, Object ...params) {
		String metodoConstruido = MessageFormat.format(INIT, metodo);
		return MessageFormat.format(metodoConstruido, params);
	}
	
	public static String finLog(String metodo) {
		return MessageFormat.format(END, metodo);
	}
	
	public static String finLog(String metodo, Object ...params) {
		String metodoConstruido = MessageFormat.format(END, metodo);
		return MessageFormat.format(metodoConstruido, params);	}
}
