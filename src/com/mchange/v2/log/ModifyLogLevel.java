package com.mchange.v2.log;

import java.util.logging.Level;

public class ModifyLogLevel {  

	/**
	 * 覆盖掉C3P0数据库连接池日志的方法，让日志不要在控制台输出
	 * @param level
	 */
	public static void modifyInfoLevel(Level level) {
		MLevel.INFO.level=level;
	}
}
