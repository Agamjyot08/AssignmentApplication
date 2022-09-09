package com.agamjyot.assignmentapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agamjyot.assignmentapp.data.models.Contact
import com.agamjyot.assignmentapp.databinding.FragmentMainBinding
import com.agamjyot.assignmentapp.domain.adapters.MainAdapter
import com.agamjyot.assignmentapp.domain.viewmodels.MainViewModel
import com.agamjyot.assignmentapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter

    private var list: ArrayList<Contact> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    private val itemClickCallback = MainAdapter.ItemClickListener { contact, _, _ ->
        DetailFragment.open(findNavController(), contact.name, contact.description)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservable()
        Apicall()

        binding.searchBar.etText.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().lowercase()
            filterWithQuery(query)
        }
    }

    private fun attachAdapter(list: ArrayList<Contact>) {
        mainAdapter = MainAdapter(list, itemClickCallback)
        binding.recyclerView.adapter = mainAdapter
    }

    private fun toggleRecyclerView(list: ArrayList<Contact>) {
        if (list.isEmpty()) {
            binding.recyclerView.visibility = View.INVISIBLE
        } else {
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    private fun filterWithQuery(query: String) {
        if (query.isNotEmpty()) {
            val filteredList: ArrayList<Contact> = onFilterChanged(query)
            attachAdapter(filteredList)
            toggleRecyclerView(filteredList)
        } else if (query.isEmpty()) {
            attachAdapter(list)
        }
    }


    private fun onFilterChanged(filterQuery: String): ArrayList<Contact> {
        val filteredList = ArrayList<Contact>()
        for (it in list) {
            if (it.name.lowercase(Locale.getDefault()).contains(filterQuery)) {
                filteredList.add(it)
            }
        }
        return filteredList
    }

    private fun setupObservable() {
        lifecycleScope.launch {
            viewModel.getDataRes.collectLatest {
                when (it) {
                    is Resource.Success -> {
                        try {
                            list.clear()
                            list.addAll(it.value.Contacts)
                            Log.d("LogTag", list.toString())
                            binding.recyclerView.apply {
                                layoutManager = LinearLayoutManager(activity)
                                mainAdapter = MainAdapter(list, itemClickCallback)
                                adapter = mainAdapter
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                requireContext(),
                                "oops..! Something went wrong.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    is Resource.Failure -> {
                        Toast.makeText(
                            requireContext(),
                            "oops..! Something went wrong.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun Apicall() {
        viewModel.getItems()
    }


}