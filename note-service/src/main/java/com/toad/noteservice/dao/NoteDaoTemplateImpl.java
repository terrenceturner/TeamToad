package com.toad.noteservice.dao;

import com.toad.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteDaoTemplateImpl implements NoteDao{

    //Prepared Statements
    private static final String INSERT_NOTE =
            "insert into note (book_id, note) values (?, ?)";
    private static final String SELECT_NOTE =
            "select * from note where book_id = ?";
    private static final String SELECT_ALL_NOTES =
            "select * from note";
    private static final String SELECT_NOTES_BY_BOOK_SQL =
            "select * from note where book_id = ?";
    private static final String UPDATE_NOTE =
            "update note set book_id = ?, note = ? where note_id = ?";
    private static final String DELETE_NOTE =
            "delete from note where note_id = ?";
    private static final String DELETE_ALL_SQL =
            "delete from note";


    //JDBC Constructor
    @Autowired
    JdbcTemplate jdbcTemplate;

    public NoteDaoTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //CRUD
    @Override
    @Transactional
    public Note addNote(Note note) {
        jdbcTemplate.update(INSERT_NOTE,
                note.getBookId(),
                note.getNote());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        note.setNoteId(id);
        return note;
    }

    @Override
    public Note getNote(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_NOTE, this::mapRowToNote, id);
        } catch (EmptyResultDataAccessException ex) {
            // if nothing is returned for this query, just return null
            return null;
        }
    }

    @Override
    public List<Note> getAllNotes() {
        return jdbcTemplate.query(SELECT_ALL_NOTES, this::mapRowToNote);
    }

    @Override
    public List<Note> getNotesByBookId(int id) {
        return jdbcTemplate.query(SELECT_NOTES_BY_BOOK_SQL, this::mapRowToNote, id);
    }

    @Override
    public void updateNote(Note note) {
        jdbcTemplate.update(UPDATE_NOTE,
                note.getBookId(),
                note.getNote(),
                note.getNoteId());

    }

    @Override
    public void deleteNote(int id) {
        jdbcTemplate.update(DELETE_NOTE, id);

    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL_SQL);
    }

    //RowMapper
    private Note mapRowToNote(ResultSet rs, int rowNum) throws SQLException {
        Note note = new Note();
        note.setNoteId(rs.getInt("note_id"));
        note.setBookId(rs.getInt("book_id"));
        note.setNote(rs.getString("note"));
        return note;
    }
}
