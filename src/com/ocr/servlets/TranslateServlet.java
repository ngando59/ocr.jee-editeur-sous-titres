package com.ocr.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ocr.beans.Archive;
import com.ocr.beans.Subtitle;
import com.ocr.beans.Translation;
import com.ocr.dao.SubtitleDAOImpl;

/**
 * Servlet implementation class TranslateServlet
 */
@WebServlet("/TranslateServlet")
public class TranslateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TranslateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Archive archive = new Archive();
		archive.loadSubtitles();
		session.setAttribute("archive", archive);		
		SubtitleDAOImpl dao = new SubtitleDAOImpl();
		if(request.getParameter("id") != null) {
			int id = Integer.valueOf(request.getParameter("id"));
			Translation translation = dao.getTranslationById(id);
			Subtitle origin = dao.getSubtitleById(translation.getIdSubtitle());
			request.setAttribute("translate", translation);
			request.setAttribute("origin", origin);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/translate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
