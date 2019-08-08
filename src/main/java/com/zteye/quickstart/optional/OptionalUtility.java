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
		// 如果需要访问的属性值不存在，Properites.getProperty(String)方法的返回值就是一个null，
		//使用noNullable工厂方法就可以将该值转换为Optional对象；接下来，可以使用flatMap将一个Optional<String>转换为Optional<Integer>对象；
		//最后使用filter过滤掉负数，然后就可以使用orElse获取属性值，如果拿不到则返回默认值0。

		return Optional.ofNullable(properties.getProperty(name)).flatMap(OptionalUtility::stringToInt)
				.filter(integer -> integer > 0).orElse(0);
	}

}
