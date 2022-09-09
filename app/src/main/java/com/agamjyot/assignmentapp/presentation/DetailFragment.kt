package com.agamjyot.assignmentapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.agamjyot.assignmentapp.R
import com.agamjyot.assignmentapp.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private var name: String = ""
    private var desc: String = ""
    private var img: String = ""

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
        img = arguments?.getString(KEY_IMG).toString()

        setUi()
    }

    private fun setUi() {
        binding.apply {
            tvName.text = name
            tvDesc.text = desc
        }

        Glide.with(binding.root.context).load(img)
            .into(binding.ivPic)
    }

    companion object {
        private const val KEY_NAME = "key_name"
        private const val KEY_DESC = "key_desc"
        private const val KEY_IMG = "key_img"

        fun getArgs(name: String, desc: String, img: String) = Bundle().also {
            it.putString(KEY_NAME, name)
            it.putString(KEY_DESC, desc)
            it.putString(KEY_IMG, img)
        }

        fun open(navController: NavController, name: String, desc: String, img: String) {
            val bundle = getArgs(name, desc, img)
            navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }


}