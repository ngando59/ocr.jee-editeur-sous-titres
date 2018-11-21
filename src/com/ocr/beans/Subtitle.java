package com.ocr.beans;
import java.util.ArrayList;

import com.ocr.model.SrtFile;
/**
 * @author ngando
 * This class represent a subtitle object
 */
public class Subtitle {
	private int id; 
	private String title;
	private String language;
	private SrtFile content;
	protected ArrayList<Translation> translations;
	
	public Subtitle() {
		super();
		title = "";
		language = "";
		content = new SrtFile();
		translations = new ArrayList<>();
	}
	
	public Subtitle(String title, String language, SrtFile content) {
		super();
		this.title = title;
		this.language = language;
		this.content = content;
		translations = new ArrayList<Translation>();
	}

	public Subtitle(int id, String title, String language, SrtFile content) {
		this.id = id;
		this.title = title;
		this.language = language;
		this.content = content;
		this.translations = new ArrayList<Translation>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArrayList<Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(ArrayList<Translation> translations) {
		this.translations = translations;
	}

	public SrtFile getContent() {
		return content;
	}

	public void setContent(SrtFile content) {
		this.content = content;
	}

	/**
	 * Add a translation de the subtitle
	 * @param : translation to add
	 * */
	public void addTranslation(Translation translation) {
		this.translations.add(translation);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
