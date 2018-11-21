package com.ocr.forms;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.ocr.beans.Translation;
import com.ocr.model.SrtFile;
import com.ocr.model.SubtitleBlock;

public class ExportTranslationForm {
	private Translation translation;
	private String title;

	public ExportTranslationForm() {
		super();
		translation = new Translation();
		title= "";
	}
	
	public void getPost(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String language = request.getParameter("translateLanguage");
		SrtFile contents = new SrtFile();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if(paramName.startsWith("idSub-")) {
				int idSub = Integer.valueOf(request.getParameter(paramName));
				String beginContent = request.getParameter("begin-"+idSub);
				String endContent = request.getParameter("end-"+idSub);
				String subContent = request.getParameter("translateSub-"+idSub);
				SubtitleBlock subBlock = new SubtitleBlock(idSub, beginContent, endContent, subContent);
				contents.addSubtitle(subBlock);
			}
		}
		title = request.getParameter("title");
		this.translation.setLanguage(language);
		this.translation.setContent(contents);
	}

	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation(Translation translation) {
		this.translation = translation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
