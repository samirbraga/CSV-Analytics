package br.com.csvanalytics.model;

import java.util.*;

public class Session{
	public static Map<String, Map> session = new HashMap<String, Map>();

	public static void putSession(String hash, Map datas){
		session.put(hash, datas);
	}

	public static void updateSession(String hash, String key, Object value){
		session.get(hash).put(key, value);
	}

	public static Map getSession(String hash){
		return session.get(hash);
	}

	public static boolean checkExistence(String hash){
		Map aux = session.get(hash);

		if (aux != null) {
			return true;

		}else{
			return false;
		}
	}
}