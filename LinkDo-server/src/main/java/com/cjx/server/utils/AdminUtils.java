package com.cjx.server.utils;

import com.cjx.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 操作员工具类
 *
 * @author CJX
 * @since 1.0.0
 */
public class AdminUtils {

	/**
	 * 获取当前登录操作员
	 *
	 * @return
	 */
	public static Admin getCurrentAdmin() {
		return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}