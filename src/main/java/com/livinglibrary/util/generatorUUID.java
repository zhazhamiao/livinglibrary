package com.livinglibrary.util;

import java.util.UUID;

public class generatorUUID {
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.replaceAll("-", "");
	}
}
