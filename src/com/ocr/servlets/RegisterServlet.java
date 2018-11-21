package com.ocr.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ocr.beans.Archive;
import com.ocr.forms.RegisterForm;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Archive archive = new Archive();
		archive.loadSubtitles();
		session.setAttribute("archive", archive);
		this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterForm registerForm = new RegisterForm();
		registerForm.getPost(request);
		registerForm.addToBdd();
		HttpSession session = request.getSession();
		Archive archive = new Archive();
		archive.loadSubtitles();
		session.setAttribute("archive", archive);
		request.setAttribute("registerForm", registerForm);
		this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}
}
