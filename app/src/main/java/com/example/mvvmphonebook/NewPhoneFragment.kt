package com.example.mvvmphonebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mvvmphonebook.databinding.FragmentNewPhoneBinding
import com.example.mvvmphonebook.db.PhoneEntity
import com.example.mvvmphonebook.viewmodel.PhoneViewModel


class NewPhoneFragment : Fragment() {
    private lateinit var binding: FragmentNewPhoneBinding
    private lateinit var viewModel: PhoneViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPhoneBinding.inflate(inflater)
        viewModel = (activity as MainActivity).viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btaddtodb.setOnClickListener {
            insertPhone()
        }
    }
    fun insertPhone(){
        if(binding.ednameadd.text.isNotEmpty()&&
            binding.edlastnameadd.text.isNotEmpty()&&
            binding.edphoneadd.text.isNotEmpty()){
            val name = binding.ednameadd.text.toString()
            val lastname = binding.edlastnameadd.text.toString()
            val phone = binding.edphoneadd.text.toString()
            val email = "email"
            val currentPhoneadd = PhoneEntity(null ,name, lastname, phone)
            viewModel.insertPhone(currentPhoneadd)
            findNavController().navigate(R.id.action_newPhoneFragment_to_phoneListFragment)
            Toast.makeText(context?.applicationContext, "added successfully", Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(context?.applicationContext, "fill all places", Toast.LENGTH_LONG).show()
        }

    }

}
