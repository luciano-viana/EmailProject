package br.com.emailproject.util;

import org.apache.log4j.Logger;

//Class para utilizar os recursos do LogUtil "visualizar Logs"

public class LogUtil {

	private LogUtil() {
	}

	// Logger importado na pasta lib "log4j-1.2.17.jar"
	// Logger vai ser utilizado de forma genérica
	public static Logger getLogger(Object object) {
		return Logger.getLogger(object.getClass());
	}
}