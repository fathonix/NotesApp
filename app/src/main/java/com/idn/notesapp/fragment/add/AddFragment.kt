package com.idn.notesapp.fragment.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.idn.notesapp.R
import com.idn.notesapp.data.model.NoteData
import com.idn.notesapp.data.viewModelsData.NotesViewModel
import com.idn.notesapp.databinding.FragmentAddBinding
import com.idn.notesapp.fragment.SharedViewModels

class AddFragment : Fragment() {

    private var _addBinding: FragmentAddBinding? = null
    private val addBinding get() = _addBinding!!

    private val notesViewModel: NotesViewModel by viewModels()
    private val sharedViewModels: SharedViewModels by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _addBinding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        addBinding.spPriority.onItemSelectedListener = sharedViewModels.listener
        return addBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mTitle = addBinding.etTitle.text.toString()
        val mPriority = addBinding.spPriority.selectedItem.toString()
        val mDesc = addBinding.etDesc.text.toString()

        val validation = sharedViewModels.verifyDataFromUser(mTitle, mDesc)
        if (validation) {
            val newData = NoteData(
                0, mTitle, sharedViewModels.parsePriority(mPriority), mDesc
            )
            notesViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Berhasil ditambahkan.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Tolong isi yaaa...", Toast.LENGTH_SHORT).show()
        }
    }
}