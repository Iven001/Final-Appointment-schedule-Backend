package com.project.appointment_schedule_management.utils;

import javax.servlet.http.HttpServletRequest;

public class EmailUtil {

	public static String getSiteUrl(HttpServletRequest request) {
		String siteUrl = request.getRequestURL().toString();
		return siteUrl.replace(request.getServletPath(), "");
	}
}
