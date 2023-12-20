package com.uday.service;

import java.util.List;

import com.uday.entity.Notes;
import com.uday.entity.User;

public interface NotesService {
	
	public Notes saveNotes(Notes notes);
	
	public Notes getNotesById(int id);

	public List<Notes> getNotesByUser(User user);
	
	public Notes updateNotes(Notes notes);
	
	public boolean deleteNotes(int id);
}
