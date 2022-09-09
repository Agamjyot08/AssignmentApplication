package com.agamjyot.assignmentapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.agamjyot.assignmentapp.R
import com.agamjyot.assignmentapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private var name: String = ""
    private var desc: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = arguments?.getString(KEY_NAME).toString()
        desc = arguments?.getString(KEY_DESC).toString()

        setUi()
    }

    private fun setUi() {
        binding.apply {
            tvName.text = name
            tvDesc.text = desc
        }
    }

    companion object {
        private const val KEY_NAME = "key_name"
        private const val KEY_DESC = "key_desc"

        fun getArgs(name: String, desc: String) = Bundle().also {
            it.putString(KEY_NAME, name)
            it.putString(KEY_DESC, desc)
        }

        fun open(navController: NavController, name: String, desc: String) {
            val bundle = getArgs(name, desc)
            navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }


}