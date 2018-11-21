package com.ocr.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ocr.beans.Archive;
import com.ocr.forms.UploadForm;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Archive archive = new Archive();
		archive.loadSubtitles();
		session.setAttribute("archive", archive);
		if(session.getAttribute("uploadForm") != null) {
			session.removeAttribute("uploadForm");
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UploadForm uploadForm = new UploadForm();
		uploadForm.uploadRequest(request);
		HttpSession session = request.getSession();
		request.setAttribute("uploadForm", uploadForm);
		session.setAttribute("uploadForm", uploadForm);
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}
}
