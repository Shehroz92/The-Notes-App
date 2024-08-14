package eu.practice.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.practice.notesapp.model.Note

@Database( entities = [ Note :: class ] , version = 1 )
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDa0(): NotesDao

    companion object{
      @Volatile
        private var instance : NoteDatabase ? = null
        private val LOCk = Any()

        operator fun invoke ( context: Context) = instance ?:
        synchronized(LOCk){
            instance ?:
            createDatabase(context).also{
                 instance = it
            }
        }

        private fun createDatabase (context: Context) =
            Room.databaseBuilder(
                context.applicationContext ,
                NoteDatabase ::class.java ,
                "note_db"

            ).build()




    }


}