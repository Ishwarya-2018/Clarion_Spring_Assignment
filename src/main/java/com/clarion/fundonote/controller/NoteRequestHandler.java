package com.clarion.fundonote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clarion.fundonote.dto.UserNoteDTO;
import com.clarion.fundonote.model.UserNotes;
import com.clarion.fundonote.service.NoteService;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders = { "jwtToken" })
@RequestMapping("user/notes")
public class NoteRequestHandler {

	@Autowired
	private NoteService noteService;

	@PostMapping("/create_note")
	public ResponseEntity<UserNotes> createNote(@RequestBody UserNoteDTO userNoteDto, @RequestHeader String token) {
		UserNotes notes = noteService.createNote(userNoteDto, token);
		return new ResponseEntity<UserNotes>(notes, HttpStatus.OK);
	}

	@DeleteMapping("/delete_note")
	public ResponseEntity<UserNotes> deleteNote(@RequestParam Long noteId, @RequestHeader String token) {
		UserNotes notes = noteService.deleteNode(noteId, token);
		return new ResponseEntity<UserNotes>(notes, HttpStatus.OK);
	}

	@PutMapping("/update_note")
	public ResponseEntity<UserNotes> updateNote(@RequestBody UserNotes updateNoteDto, @RequestHeader String token) {
		UserNotes notes = noteService.updateNotes(updateNoteDto, token);
		return new ResponseEntity<UserNotes>(notes, HttpStatus.OK);
	}

	@PutMapping("/change-color/{color}")
	public ResponseEntity<UserNotes> updateColor(@PathVariable("color") String color,
			@RequestParam("noteId") Long noteId, @RequestHeader String token) {
		System.out.println("reched inside update color method");
		System.out.println(noteId);
		System.out.println(color);
		System.out.println(token);
		UserNotes notes = noteService.updateColor(noteId, color, token);
		return new ResponseEntity<UserNotes>(notes, HttpStatus.OK);
	}

	@GetMapping("/get_notes")
	public ResponseEntity<List<UserNotes>> getNotes(@RequestHeader String token) {
		List<UserNotes> notes = noteService.getNotes(token);
		return new ResponseEntity<List<UserNotes>>(notes, HttpStatus.OK);
	}
	
	@GetMapping("/get_notesforuser")
	public ResponseEntity<List<UserNotes>> getNotesForUser(@RequestParam String email, @RequestHeader String token) {
		List<UserNotes> notes = noteService.getNotesForUser(email,token);
		return new ResponseEntity<List<UserNotes>>(notes, HttpStatus.OK);
	}

}
