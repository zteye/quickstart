package com.zteye.quickstart.optional;

import java.util.Optional;
import java.util.Properties;

public class OptionalUtility {

	public static Optional<Integer> stringToInt(String a) {

		try {
			return Optional.of(Integer.parseInt(a));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public static int readDuration(Properties properties, String name) {
		String value = properties.getProperty(name);
		if (value != null) {
			try {
				int i = Integer.parseInt(value);
				if (i > 0) {
					return i;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static int readDurationWithOptinal(Properties properties, String name) {
		// �����Ҫ���ʵ�����ֵ�����ڣ�Properites.getProperty(String)�����ķ���ֵ����һ��null��
		//ʹ��noNullable���������Ϳ��Խ���ֵת��ΪOptional���󣻽�����������ʹ��flatMap��һ��Optional<String>ת��ΪOptional<Integer>����
		//���ʹ��filter���˵�������Ȼ��Ϳ���ʹ��orElse��ȡ����ֵ������ò����򷵻�Ĭ��ֵ0��

		return Optional.ofNullable(properties.getProperty(name)).flatMap(OptionalUtility::stringToInt)
				.filter(integer -> integer > 0).orElse(0);
	}

}
