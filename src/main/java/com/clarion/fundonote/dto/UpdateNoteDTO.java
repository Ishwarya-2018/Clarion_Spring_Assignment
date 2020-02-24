package com.clarion.fundonote.dto;

public class UpdateNoteDTO 
{
	private String noteTitle;
	private String description;
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
