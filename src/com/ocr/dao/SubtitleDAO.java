package com.ocr.dao;

import java.util.ArrayList;

import com.ocr.beans.Subtitle;
import com.ocr.beans.Translation;

public interface SubtitleDAO {
	/**
	 * Add the subtitleBean on a table 
	 * */
	void addSubtitle(Subtitle subtitle);
	/**
	 * Update the subtitleBean on a table 
	 * */
	void updateSubtitle(Subtitle subtitle);
	/**
	 * Update the TranslationBean on a table 
	 * */
	void updateTranslation(Translation translation);
	/**
	 * @return All the subtitle on a table
	 * */
	ArrayList<Subtitle> getAllSubtitles();
    /**
     * @param id : the id of the subtitle founded
     * @return the subtitle found by the id
     * */
	Subtitle getSubtitleById(int id);
	/**
	 * Add a translation give in parameter to the subtitle give in parameter
	 *@param subtitle : the subtitle original
	 *@parma translation : the translation
	 * */
	void addTranslationForSubtitle(Translation translation);
	/**
	 * @return All the subtitle on a table
	 * */
	public ArrayList<Subtitle> getAllIdAndTitleOfSubtitles();
	/**
	 * @param idOriginSubtitle : id of the subtitle
	 * @return all the translation from subtitle where id is idOriginSubtitle
	 * */
	public ArrayList<Translation> getTranslationFromSubtitle(int idOriginSubtitle);
    /**
     * @param id : the id of the translation founded
     * @return the translation found by the id
     * */
	public Translation getTranslationById(int id);
	
}
