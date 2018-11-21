package com.ocr.model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.http.Part;

/**
 * @author ngando
 * This class represent a srt file
 */
public class SrtFile {
	
	private ArrayList<SubtitleBlock> subtitles;
	
	public SrtFile(ArrayList<SubtitleBlock> subtitles) {
		super();
		this.subtitles = subtitles;
	}

	public SrtFile() {
		super();
		subtitles = new ArrayList<SubtitleBlock>();
	}

	public ArrayList<SubtitleBlock> getSubtitles() {
		return subtitles;
	}

	public void setSubtitles(ArrayList<SubtitleBlock> subtitles) {
		this.subtitles = subtitles;
	}
	
	public void addSubtitle(SubtitleBlock sub) {
		this.subtitles.add(sub);
	}
	
	public String toString() {
		String result = "";
		for(SubtitleBlock sub : subtitles) {
			result += sub.toString();
			result += "\n";
		}
		return result;
	}
	
	/**
	 * Load a subtitle from the file
	 * @param file : file .srt
	 * */
	public void loadSubtitles(File file) {
		InputStream ips; 
		InputStreamReader ipsr;
		BufferedReader br;
		this.subtitles = new ArrayList<SubtitleBlock>();
		try {
			ips = new FileInputStream(file);
			ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
			String line;
			while( (line = br.readLine()) != null ) {
				int subtitleId = Integer.valueOf(line.replaceAll("[\r\n]+", ""));
				String time = br.readLine().replaceAll("[\r\n]+", "");
				String content = br.readLine();
				String nextLine =  "";
				while( ( (nextLine = br.readLine()) != null) && !(nextLine.replaceAll("[\r\n]+", "").equals("") ) ) {
					content += "\n"+nextLine;
				}
				SubtitleBlock sub = new SubtitleBlock(subtitleId, time, content);
				this.addSubtitle(sub);
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Le fichier "+file+" n'existe pas ! \n");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		}
	}
	
	/**
	 * Load a subtitle from the file
	 * @param file : file .srt
	 * */
	public void loadSubtitles(Part part) {
		InputStream ips; 
		InputStreamReader ipsr;
		BufferedReader br;
		this.subtitles = new ArrayList<SubtitleBlock>();
		try {
			ips = part.getInputStream();
			ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
			String line;
			while( (line = br.readLine()) != null ) {
				int subtitleId = Integer.valueOf(line.replaceAll("[\r\n]+", ""));
				String time = br.readLine().replaceAll("[\r\n]+", "");
				String content = br.readLine();
				String nextLine =  "";
				while( ( (nextLine = br.readLine()) != null) && !(nextLine.replaceAll("[\r\n]+", "").equals("") ) ) {
					content += "\n"+nextLine;
				}
				SubtitleBlock sub = new SubtitleBlock(subtitleId, time, content);
				this.addSubtitle(sub);
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Le fichier "+part+" n'existe pas ! \n");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		}
	}
	
	/**
	 * Load a subtitle from texte
	 * @param str : string content 
	 * */
	public void loadSubtitles(String str) {
		InputStream ips; 
		InputStreamReader ipsr;
		BufferedReader br;
		this.subtitles = new ArrayList<SubtitleBlock>();
		try {
			ips = new ByteArrayInputStream(str.getBytes());
			ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
			String line;
			while( (line = br.readLine()) != null ) {
				int subtitleId = Integer.valueOf(line.replaceAll("[\r\n]+", ""));
				String time = br.readLine().replaceAll("[\r\n]+", "");
				String content = br.readLine();
				String nextLine =  "";
				while( ( (nextLine = br.readLine()) != null) && !(nextLine.replaceAll("[\r\n]+", "").equals("") ) ) {
					content += "\n"+nextLine;
				}
				SubtitleBlock sub = new SubtitleBlock(subtitleId, time, content);
				this.addSubtitle(sub);
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.err.println("format de fichier incorrect ! \n");
		}
	}
	
	/**
	 * Save subtitle on the file
	 * */
	public void saveSubtitles(File file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write(this.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SubtitleBlock getSubtitleBlockById(int id) {
		SubtitleBlock subBlock = null;
		for(SubtitleBlock sbk : this.getSubtitles()) {
			if(sbk.getId() == id ) {
				subBlock = sbk;
			}
		}
		return subBlock;
	}
	
}
