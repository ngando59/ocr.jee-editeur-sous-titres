package com.ocr.beans;

import java.util.ArrayList;

import com.ocr.dao.SubtitleDAOImpl;

public class Archive {
	
	private SubtitleDAOImpl dao = new SubtitleDAOImpl();
	private ArrayList<Subtitle> subtitles;
	
	public Archive() {
		this.subtitles = new ArrayList<Subtitle>();
	}

	public ArrayList<Subtitle> getSubtitles() {
		return subtitles;
	}

	public void setSubtitles(ArrayList<Subtitle> subtitles) {
		this.subtitles = subtitles;
	}
	
	public void loadSubtitles() {
		this.subtitles = dao.getAllIdAndTitleOfSubtitles();
	}
	
}
