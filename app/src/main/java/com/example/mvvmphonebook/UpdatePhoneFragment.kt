package com.example.mvvmphonebook

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mvvmphonebook.databinding.FragmentUpdatePhoneBinding
import com.example.mvvmphonebook.db.PhoneEntity
import com.example.mvvmphonebook.viewmodel.PhoneViewModel


class UpdatePhoneFragment : Fragment() {
    private lateinit var binding: FragmentUpdatePhoneBinding
    val args: UpdatePhoneFragmentArgs by navArgs()
    lateinit var viewModel: PhoneViewModel

    lateinit var currentPhoneUpdate: PhoneEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdatePhoneBinding.inflate(inflater)
        currentPhoneUpdate = args.phone!!
        binding.ednameupdate.setText(currentPhoneUpdate.name)
        binding.edlastnameupdate.setText(currentPhoneUpdate.lastname)
        binding.edphoneupdate.setText(currentPhoneUpdate.phone)
        //init viewmodel
        viewModel = (activity as MainActivity).viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btupdate.setOnClickListener {
            updatePhone()
        }
    }

    //fun for updating Phone
    private fun updatePhone(){
        val name = binding.ednameupdate.text.toString()
        val lastname = binding.edlastnameupdate.text.toString()
        val phone = binding.edphoneupdate.text.toString()
        val email = "email"
        if(name.isNotEmpty() && lastname.isNotEmpty() && phone.isNotEmpty()){
            val updatedphone = PhoneEntity(0, name, lastname, phone)
            viewModel.updatePhone(updatedphone)
            findNavController().navigate(R.id.action_updatePhoneFragment_to_phoneListFragment)
            Toast.makeText(context?.applicationContext, "updated successfully", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context?.applicationContext, "please fill all places", Toast.LENGTH_LONG).show()
        }
    }

    //delete menu init
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.deletemenu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btdelete -> deletePhone()
        }
        return super.onOptionsItemSelected(item)
    }

    //delete fun
    fun deletePhone(){
        AlertDialog.Builder(context).apply {
            setTitle("delete")
            setMessage("do you really want to delete this contact")
            setPositiveButton("Delete"){_, _, ->
                viewModel.deletePhone(currentPhoneUpdate)
                findNavController().navigate(R.id.action_updatePhoneFragment_to_phoneListFragment)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }


}