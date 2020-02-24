package com.clarion.fundonote.service.serviceimp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clarion.fundonote.dto.UserNoteDTO;
import com.clarion.fundonote.model.UserDetails;
import com.clarion.fundonote.model.UserNotes;
import com.clarion.fundonote.repository.NoteRepository;
import com.clarion.fundonote.repository.UserRepository;
import com.clarion.fundonote.service.NoteService;
import com.clarion.fundonote.utils.JwtUtility;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	private ModelMapper modelMapper;

	public UserNotes createNote(UserNoteDTO userNoteDto, String token) {//passing parameter of userNoteDto and token to create a note
		Long userId = JwtUtility.validateToken(token);//validate the token to create a note
		UserNotes notes = null;
		if (userId != null) {//check userId is not equal to null
			Optional<UserDetails> userDetails = userRepository.findById(userId);
			if (userDetails.isPresent())//userDetails is present then map userNoteDto to notes
				notes = modelMapper.map(userNoteDto, UserNotes.class);//map userNoteDto to notes and then set value
			notes.setCreatedDateTime(LocalDateTime.now());
			notes.setUpdatedDateTime(LocalDateTime.now());
			userDetails.get().getNotes().add(notes);
			notes = noteRepository.save(notes);
			return notes;
		}
		return notes;
	}

	@Override
	public UserNotes deleteNode(Long noteId, String token) {//pass noteId and token to delete note
		Long userId = JwtUtility.validateToken(token);//validate token to remove note
		Optional<UserDetails> userDetails = userRepository.findById(userId);//find the userId from userRepository
		Optional<UserNotes> notes = null;
		if (userDetails.isPresent() && noteId != null)//check user and his id is present then delete by id
			notes = noteRepository.findById(noteId);
		noteRepository.deleteById(noteId);
		return notes.get();
	}

	@Override
	public UserNotes updateNotes(UserNotes updateNoteDto, String token) {//updateNoteDto and pass token to update the token
		Long userId = JwtUtility.validateToken(token);//validate token for update note
		Optional<UserDetails> userDetails = userRepository.findById(userId);
		Optional<UserNotes> note = null;
		if (userDetails.isPresent())
			note = noteRepository.findById(updateNoteDto.getId());//if user is present then find by id from noteRepository and set dto entities
		note.get().setNoteTitle(updateNoteDto.getNoteTitle());
		note.get().setDescription(updateNoteDto.getDescription());
		note.get().setUpdatedDateTime(LocalDateTime.now());
		noteRepository.save(note.get());
		return note.get();
	}

	@Override
	public List<UserNotes> getNotes(String token) {//for getting notes passing only token
		Long userId = JwtUtility.validateToken(token);//validate token 
		Optional<UserDetails> userDetails = userRepository.findById(userId);
		List<UserNotes> notes = null;
		if (userDetails.isPresent())//if user is present then get all the notes of the user
			notes = userDetails.get().getNotes();
		return notes;
	}

	@Override
	public UserNotes updateColor(Long noteId, String color, String token) {//pass noteId color along token to update color of the note
		System.out.println("color: " + color);
		Long userId = JwtUtility.validateToken(token);//validate token to update color
		Optional<UserDetails> userDetails = userRepository.findById(userId);//find userId from userRepository
		UserNotes userNote = null;
		if (userDetails.isPresent()) {
			Optional<UserNotes> notes = noteRepository.findById(noteId);//find noteId from noteRepository and add to notes
			userNote = notes.get();//get the noteId and assign to userNote and set the properties to userNote
			userNote.setColor(color);
			userNote.setUpdatedDateTime(LocalDateTime.now());
			noteRepository.save(userNote);
		}
		return userNote;
	}
	
	@Override
	public List<UserNotes> getNotesForUser(String email, String token) {//pass email and token of the particular user to get notes of particular notes
		Long userId = JwtUtility.validateToken(token);//validate token
		Optional<UserDetails> userDetails = userRepository.findById(userId);
		List<UserNotes> notes = null;
		if (userDetails.isPresent()) {
			Optional<UserDetails> searchUserDetails = userRepository.findByEmail(email);//find by email to search the user and if it is present then get all the notes related to particular user
			if (searchUserDetails.isPresent())
				notes = searchUserDetails.get().getNotes();
		}
		return notes;
	}
}
