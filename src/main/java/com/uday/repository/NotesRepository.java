package com.uday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uday.entity.Notes;
import com.uday.entity.User;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

	public List<Notes> findByUser(User user);
}
