package com.blogspot.dboissin.simplegallery.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhotosDisplayFilter implements Filter {
	
	private static final String BASE_URL_PATTERN = "photos/";
	private static final String PATH = "/mnt/photos/p_sharegallery";
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		String imgpath = PATH + ((HttpServletRequest)req).getServletPath().replaceFirst(BASE_URL_PATTERN, "");
		if (!imgpath.toLowerCase().endsWith(".jpg")) {
			((HttpServletResponse)resp).setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		File img = new File(imgpath);
		if (!img.exists()) {
			((HttpServletResponse)resp).setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		OutputStream out = resp.getOutputStream();
		FileInputStream in = new FileInputStream(img);
		resp.setContentType("image/jpeg");
		byte[] buffer = new byte[2048];
		int count = 0;
		int off = 0;
		do {
			count = in.read(buffer, off, 2048);
			out.write(buffer, off, count);
		} while (count == buffer.length);
		out.flush();
		out.close();	
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
