package com.ocr.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ocr.beans.Translation;
import com.ocr.forms.ExportTranslationForm;

/**
 * Servlet implementation class downloadCurrentTranslationServlet
 */
@WebServlet("/downloadCurrentTranslationServlet")
public class DownloadCurrentTranslationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096;    
	private Translation translation;
	private String title;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadCurrentTranslationServlet() {
        super();
        translation = null;
        title = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		upload(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExportTranslationForm uploadForm = new ExportTranslationForm();
		uploadForm.getPost(request);
		translation = uploadForm.getTranslation();
		title = uploadForm.getTitle();
		upload(request, response);
	}

	private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(translation != null) {
			String filename = title+"("+translation.getLanguage()+").srt";
			System.out.println("fichier : "+filename);
			InputStream inputStream = new ByteArrayInputStream(translation.getContent().toString().getBytes());
            int fileLength = inputStream.available();
            ServletContext context = getServletContext();
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
		}
	}
}
