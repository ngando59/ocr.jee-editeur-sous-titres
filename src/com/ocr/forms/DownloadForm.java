package com.ocr.forms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ocr.beans.Subtitle;

public class DownloadForm {
	private static final int BUFFER_SIZE = 4096;  
	
	public void downloadSubtitle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("registerForm") != null) {
			RegisterForm registerForm = (RegisterForm)session.getAttribute("registerForm");
			Subtitle subtitle = registerForm.getSubtitle();		
			String filename = subtitle.getTitle()+".srt";
			InputStream inputStream = new ByteArrayInputStream(subtitle.getContent().toString().getBytes());
            int fileLength;
			try {
				fileLength = inputStream.available();
				 ServletContext context = request.getServletContext();
		            String mimeType = context.getMimeType(filename);
		            if (mimeType == null) {        
		                mimeType = "application/octet-stream";
		            }              
		            response.setContentType(mimeType);
		            response.setContentLength(fileLength);
		            String headerKey = "Content-Disposition";
		            String headerValue = String.format("attachment; filename=\"%s\"", filename);
		            response.setHeader(headerKey, headerValue);
		            OutputStream outStream = response.getOutputStream(); 
		            byte[] buffer = new byte[BUFFER_SIZE];
		            int bytesRead = -1;           
		            while ((bytesRead = inputStream.read(buffer)) != -1) {
		                outStream.write(buffer, 0, bytesRead);
		            }
		            inputStream.close();
		            outStream.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}           
		}
	}
}
