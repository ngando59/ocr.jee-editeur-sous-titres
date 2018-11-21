package com.ocr.beans;

import com.ocr.model.SrtFile;

/**
 * @author ngando
 * This class represent a translation object
 */
public class Translation {
	private int id;
	private String language;
	private int idSubtitle;
	private SrtFile content;
	
	public Translation() {}

	public Translation(int id, String language, int idSubtitle, SrtFile content) {
		this.id = id;
		this.language = language;
		this.idSubtitle = idSubtitle;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public SrtFile getContent() {
		return content;
	}

	public void setContent(SrtFile content) {
		this.content = content;
	}

	public int getIdSubtitle() {
		return idSubtitle;
	}

	public void setIdSubtitle(int idSubtitle) {
		this.idSubtitle = idSubtitle;
	}
	
}
