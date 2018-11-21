package com.ocr.model;

/**
 * @author ngando
 * This class represent a block of subtitle
 */
public class SubtitleBlock {
	private int id;
	private String begin; 
	private String end;
	private String content;
	
	public SubtitleBlock(int id, String begin, String end, String content) {
		super();
		this.id = id;
		this.begin = begin;
		this.end = end;
		this.content = content;
	}
	
	/**
	 * Constructor in format 00:00:27,560 --> 00:00:29,990
	 * */
	public SubtitleBlock(int id, String time, String content) {
		super();
		this.id = id;
		String[] subTime = time.split(" --> ");
		this.begin = subTime[0];
		this.end = subTime[1];
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		String result = "";
		result += this.id+"\n";
		result += this.begin+" --> "+this.end+"\n";
		result += this.content+"\n";
		return result;
	}
}
