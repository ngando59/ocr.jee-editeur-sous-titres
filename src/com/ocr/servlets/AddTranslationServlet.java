package com.ocr.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ocr.forms.AddTranslationForm;

/**
 * Servlet implementation class AddTranslationServlet
 */
@WebServlet("/AddTranslationServlet")
public class AddTranslationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTranslationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddTranslationForm addTranslationForm = new AddTranslationForm();
		addTranslationForm.getPost(request);
		addTranslationForm.addToBdd();
		//System.out.println("avant redirect");
		//((HttpServletResponse) response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY); 
		//((HttpServletResponse) response).addHeader("Location", request.getRequestURL().toString());
		//response.sendRedirect("subtitle?id="+addTranslationForm.getTranslation().getIdSubtitle());
	}

}
