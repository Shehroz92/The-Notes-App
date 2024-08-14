package eu.practice.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import eu.practice.notesapp.database.NoteDatabase
import eu.practice.notesapp.repository.NoteRepository
import eu.practice.notesapp.viewmodel.NoteViewModel
import eu.practice.notesapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

    }

    private fun setupViewModel(){
        val noteRepository = NoteRepository( NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory( application , noteRepository )

        noteViewModel  = ViewModelProvider(this , viewModelProviderFactory ) [ NoteViewModel::class.java]
    }

}