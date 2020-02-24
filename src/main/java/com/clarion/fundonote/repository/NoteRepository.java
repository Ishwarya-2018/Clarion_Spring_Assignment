package com.clarion.fundonote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clarion.fundonote.model.UserNotes;

@Repository // implementing traditional Java EE patterns such as "Data Access Object"(DAO)
public interface NoteRepository extends JpaRepository<UserNotes, Long> {// JpaRepository is JPA specific extension of
																		// Repository and contains all the API for
																		// operations
}
