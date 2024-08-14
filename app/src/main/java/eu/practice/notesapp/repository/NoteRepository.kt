package eu.practice.notesapp.repository

import eu.practice.notesapp.database.NoteDatabase
import eu.practice.notesapp.model.Note

class NoteRepository ( private var db : NoteDatabase) {
    suspend fun insertNote( note: Note) = db.getNoteDa0().insertNote( note )

    suspend fun deleteNote( note: Note) = db.getNoteDa0().deleteNote( note )

    suspend fun updateNote( note: Note) = db.getNoteDa0().updateNote( note )

    fun getAllNOtes() = db.getNoteDa0().getAllNotes()
    fun searchNote(query :String?) = db.getNoteDa0().searchNote(query)

}