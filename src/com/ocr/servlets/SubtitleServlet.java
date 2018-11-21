package com.ocr.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ocr.beans.Archive;
import com.ocr.beans.Subtitle;
import com.ocr.dao.SubtitleDAOImpl;

@SuppressWarnings("serial")
public class SubtitleServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Archive archive = new Archive();
		archive.loadSubtitles();
		session.setAttribute("archive", archive);
		int id = Integer.valueOf(request.getParameter("id"));
		SubtitleDAOImpl dao = new SubtitleDAOImpl();
		Subtitle subtitle = dao.getSubtitleById(id);
		request.setAttribute("subtitle", subtitle);
		this.getServletContext().getRequestDispatcher("/WEB-INF/subtitle.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
