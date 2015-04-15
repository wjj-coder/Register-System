package com.sys.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.sys.util.FreeMarkerResolver;


public class FreemarkerInitPath implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		String path = sc.getRealPath("/");
		System.out.println("init:"+path);
		File rootDir = new File(path);
		File webinfo = new File(rootDir, "pages");
		File templateDir = new File(webinfo, "template");
		System.out.println(templateDir.isDirectory());
		System.out.println(templateDir.exists());
		String templatePath = templateDir.getPath();
		FreeMarkerResolver.templatePath = templatePath;
		System.out.println(FreeMarkerResolver.templatePath);
	}

}
