package com.ocr.forms;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.ocr.beans.Translation;
import com.ocr.dao.SubtitleDAOImpl;
import com.ocr.model.SrtFile;
import com.ocr.model.SubtitleBlock;

public class UpdateTranslateForm {
	private Translation translation;
	private SubtitleDAOImpl dao = new SubtitleDAOImpl();
	
	public UpdateTranslateForm() {
		this.translation = new Translation();
	}
	
	public void getPost(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int id = Integer.valueOf(request.getParameter("id"));
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
		this.translation.setId(id);
		this.translation.setLanguage(language);
		this.translation.setContent(contents);
	}
	
	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation(Translation translation) {
		this.translation = translation;
	}

	public SubtitleDAOImpl getDao() {
		return dao;
	}

	public void setDao(SubtitleDAOImpl dao) {
		this.dao = dao;
	}

	public void updateTranslationOnBdd(Translation translation) {
		dao.updateTranslation(translation);
	}
}
