package eu.practice.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import eu.practice.notesapp.MainActivity
import eu.practice.notesapp.R
import eu.practice.notesapp.databinding.FragmentHomeBinding
import eu.practice.notesapp.adapter.NoteAdapter
import eu.practice.notesapp.viewmodel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener , MenuProvider {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var notesAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner , Lifecycle.State.RESUMED )

        notesViewModel = ( activity as MainActivity ).noteViewModel
        binding.addNoteFab.setOnClickListener {
               it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }

    }


}
