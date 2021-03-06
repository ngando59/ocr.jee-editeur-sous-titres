package com.ocr.forms;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.ocr.beans.Subtitle;
import com.ocr.dao.SubtitleDAOImpl;
import com.ocr.model.SrtFile;
import com.ocr.model.SubtitleBlock;

public class UpdateForm {
	private Subtitle subtitle;
	private SubtitleDAOImpl dao = new SubtitleDAOImpl();
	
	public UpdateForm() {
		this.subtitle = new Subtitle();
	}
	
	public void getPost(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		String language = request.getParameter("language");
		SrtFile contents = new SrtFile();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if(paramName.startsWith("idSub-")) {
				int idSub = Integer.valueOf(request.getParameter(paramName));
				String beginContent = request.getParameter("begin-"+idSub);
				String endContent = request.getParameter("end-"+idSub);
				String subContent = request.getParameter("editSub-"+idSub);
				SubtitleBlock subBlock = new SubtitleBlock(idSub, beginContent, endContent, subContent);
				contents.addSubtitle(subBlock);
			}
		}
		this.subtitle.setId(id);
		this.subtitle.setTitle(title);
		this.subtitle.setLanguage(language);
		this.subtitle.setContent(contents);
	}
	
	public Subtitle getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Subtitle subtitle) {
		this.subtitle = subtitle;
	}
	
	public void updateOnBdd(Subtitle subtitle) {
		dao.updateSubtitle(subtitle);
	}
}
