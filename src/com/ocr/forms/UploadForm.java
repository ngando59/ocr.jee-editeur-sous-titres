package com.ocr.forms;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.ocr.beans.Subtitle;
import com.ocr.model.SrtFile;

public class UploadForm {
    private boolean messageError; 
	private Subtitle subtitle;
	
    public UploadForm() {
    	messageError = false;
    	subtitle = null;
	}
    
	public void uploadRequest(HttpServletRequest request) {	
		try {
			Part part = request.getPart("myfile");
			String filename = this.getFileName(part);
			if(!filename.endsWith(".srt")) {
				messageError = false;
			} else {
				SrtFile srtFile = new SrtFile();
				srtFile.loadSubtitles(part);
				subtitle = new Subtitle();
				subtitle.setTitle(this.filterName(filename));
				subtitle.setContent(srtFile);
			}
		} catch (IllegalStateException | IOException | ServletException e) {
			messageError = true;
			e.printStackTrace();
		}
	}
	
	private String getFileName(Part part) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
	}

	private String filterName(String pathName) {
		String result = "";
		String[] splitPathName = pathName.split("\\\\");
		result = splitPathName[splitPathName.length-1].replaceAll(".srt", "");
		return result;
	}
	
	
	public boolean isMessageError() {
		return messageError;
	}

	public Subtitle getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Subtitle subtitle) {
		this.subtitle = subtitle;
	}
	
	
}
