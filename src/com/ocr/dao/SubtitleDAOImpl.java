package com.ocr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ocr.beans.Subtitle;
import com.ocr.beans.Translation;
import com.ocr.model.SrtFile;
/**
 * @author ngando
 * 
 */
public class SubtitleDAOImpl implements SubtitleDAO {

	private DaoFactory daoFactory;
	private static final String UPDATE_SUBTITLE = "UPDATE tbl_subtitle SET title=?, language=?, content=? WHERE id=?";
	private static final String ADD_TRANSLATION = "INSERT INTO tbl_translation(language, id_origin, content) VALUES(?, ?, ?);"; 
	private static final String SELECT_TRANSLATION_BY_ORIGIN = "SELECT * FROM tbl_translation WHERE id_origin = ?"; 
	private static final String SELECT_TRANSLATION_BY_ID = "SELECT * FROM tbl_translation WHERE id = ?"; 
	private static final String UPDATE_TRANSLATION = "UPDATE tbl_translation SET language=?, content=? WHERE id=?";
	
	public SubtitleDAOImpl() {
		this.daoFactory = new DaoFactory();
	}
	
	public SubtitleDAOImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void addSubtitle(Subtitle subtitle) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
	        preparedStatement = connexion.prepareStatement("INSERT INTO tbl_subtitle(title, language, content) VALUES(?, ?, ?);");
	        preparedStatement.setString(1, subtitle.getTitle());
	        preparedStatement.setString(2, subtitle.getLanguage());
	        preparedStatement.setString(3, subtitle.getContent().toString());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }	
	}

	@Override
	public ArrayList<Subtitle> getAllIdAndTitleOfSubtitles() {
		ArrayList<Subtitle> subtitles = new ArrayList<Subtitle>();
	    Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    try {
	    	connexion = daoFactory.getConnection();
	        statement = connexion.createStatement();
	        resultat = statement.executeQuery("SELECT id, title FROM tbl_subtitle;");
	        while (resultat.next()) {
	        	int id = resultat.getInt("id");
	        	String title = resultat.getString("title");
	            Subtitle sub = new Subtitle();
	            sub.setId(id);
	            sub.setTitle(title);
	            subtitles.add(sub);
	        }
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return subtitles;
	}
	
	@Override
	public ArrayList<Subtitle> getAllSubtitles() {
		ArrayList<Subtitle> subtitles = new ArrayList<Subtitle>();
	    Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultat = null;
	    try {
	    	connexion = daoFactory.getConnection();
	        statement = connexion.createStatement();
	        resultat = statement.executeQuery("SELECT id, title, language, content FROM tbl_subtitle;");
	        while (resultat.next()) {
	        	int id = resultat.getInt("id");
	        	String title = resultat.getString("title");
	            String language = resultat.getString("language");
	            String content = resultat.getString("content");
	            SrtFile srtFile = new SrtFile();
	            srtFile.loadSubtitles(content);
	            Subtitle sub = new Subtitle(id, title, language, srtFile);
	            sub.setTranslations(this.getTranslationFromSubtitle(id));
	            subtitles.add(sub);
	        }
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return subtitles;
	}

	@Override
	public Subtitle getSubtitleById(int id) {
		Subtitle result = null;
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultat = null;
	    try {
	    	connexion = daoFactory.getConnection();
	    	preparedStatement = connexion.prepareStatement("SELECT id, title, language, content FROM tbl_subtitle WHERE id = ?;");
	        preparedStatement.setInt(1, id);
	        resultat = preparedStatement.executeQuery();
	        if(resultat.next()) {
		        int SubtitleId = resultat.getInt("id");
	        	String title = resultat.getString("title");
	            String language = resultat.getString("language");
	            String content = resultat.getString("content");
	            SrtFile srtFile = new SrtFile();
	            srtFile.loadSubtitles(content);
	            Subtitle subtitle = new Subtitle(SubtitleId, title, language, srtFile);
	            subtitle.setTranslations(this.getTranslationFromSubtitle(id));
	            return subtitle;
	        }
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		return result;
	}

	@Override
	public void addTranslationForSubtitle(Translation translation) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(ADD_TRANSLATION);	 
			preparedStatement.setString(1, translation.getLanguage());
			preparedStatement.setInt(2, translation.getIdSubtitle());
			preparedStatement.setString(3, translation.getContent().toString());
	        preparedStatement.executeUpdate();  
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateSubtitle(Subtitle subtitle) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
		try {
	    	connexion = daoFactory.getConnection(); 
	    	preparedStatement = connexion.prepareStatement(UPDATE_SUBTITLE);	    	 
	    	preparedStatement.setString(1, subtitle.getTitle());
	    	preparedStatement.setString(2, subtitle.getLanguage());
	    	preparedStatement.setString(3, subtitle.getContent().toString());	 
	    	preparedStatement.setInt(4, subtitle.getId());	             
	    	preparedStatement.executeUpdate();
	    	preparedStatement.close();
		} catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}

	@Override
	public ArrayList<Translation> getTranslationFromSubtitle(int idOriginSubtitle) {
		ArrayList<Translation> translations = new ArrayList<Translation>();
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultat = null;
	    try {
	    	connexion = daoFactory.getConnection();
	    	preparedStatement = connexion.prepareStatement(SELECT_TRANSLATION_BY_ORIGIN);
	        preparedStatement.setInt(1, idOriginSubtitle);
	        resultat = preparedStatement.executeQuery();
	        while (resultat.next()) {
		        int translationID = resultat.getInt("id");
		        int originID = resultat.getInt("id_origin");
	            String language = resultat.getString("language");
	            String content = resultat.getString("content");
	            SrtFile srtFile = new SrtFile();
	            srtFile.loadSubtitles(content);
	            translations.add(new Translation(translationID, language, originID, srtFile));
	        }
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		return translations;
	}

	@Override
	public Translation getTranslationById(int id) {
		Translation translation = null;
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultat = null;
	    try {
	    	connexion = daoFactory.getConnection();
	    	preparedStatement = connexion.prepareStatement(SELECT_TRANSLATION_BY_ID);
	        preparedStatement.setInt(1, id);
	        resultat = preparedStatement.executeQuery();
	        if(resultat.next()) {
	        	translation = new Translation();
	        	translation.setId(resultat.getInt("id"));
	        	translation.setIdSubtitle(resultat.getInt("id_origin"));
	        	translation.setLanguage(resultat.getString("language"));
	        	String content = resultat.getString("content");
	            SrtFile srtFile = new SrtFile();
	            srtFile.loadSubtitles(content);
	            translation.setContent(srtFile);
	        }
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		return translation;
	}

	@Override
	public void updateTranslation(Translation translation) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
		try {
	    	connexion = daoFactory.getConnection(); 
	    	preparedStatement = connexion.prepareStatement(UPDATE_TRANSLATION);	    	 
	    	preparedStatement.setString(1, translation.getLanguage());
	    	preparedStatement.setString(2, translation.getContent().toString());
	    	preparedStatement.setInt(3, translation.getId());	             
	    	preparedStatement.executeUpdate();
	    	preparedStatement.close();
		} catch (SQLException e) {
	    	e.printStackTrace();
	    }	
	}	

}
