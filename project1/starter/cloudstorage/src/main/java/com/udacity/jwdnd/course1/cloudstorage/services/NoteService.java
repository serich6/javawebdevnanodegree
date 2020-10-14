package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        System.out.println("Creating a note service");
        this.noteMapper = noteMapper;
    }


    public Note getNotes(User user) {
        System.out.println("Getting the notes");
        return noteMapper.getNote(user.getUserId().toString());
    }

    public Integer saveNote(Note note) {
        Integer id = note.getNoteid();
        if (id == null) {
            id = noteMapper.insert(note);
        } else {
            noteMapper.update(note);
        }
        System.out.println("Called create note");
        return id;
    }

    public Integer deleteNote(Note note) {
        System.out.println("Called delete note");
        return noteMapper.delete(note);
    }
}
