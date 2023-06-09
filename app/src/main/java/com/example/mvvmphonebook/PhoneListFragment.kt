package com.example.mvvmphonebook

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmphonebook.databinding.FragmentPhoneListBinding
import com.example.mvvmphonebook.db.PhoneEntity
import com.example.mvvmphonebook.homeadapter.PhoneListAdapter
import com.example.mvvmphonebook.viewmodel.PhoneViewModel


class PhoneListFragment : Fragment(), SearchView.OnQueryTextListener {
    val app = MainActivity()
    lateinit var binding: FragmentPhoneListBinding
    lateinit var adapter: PhoneListAdapter
    lateinit var viewModel: PhoneViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneListBinding.inflate(inflater)
        viewModel = (activity as MainActivity).viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adjustRecyclerView()
        binding.bttonewphone.setOnClickListener {
            findNavController().navigate(R.id.action_phoneListFragment_to_newPhoneFragment)
        }
        binding.btdeleteAll.setOnClickListener {
            deleteAll()
        }
    }
    fun adjustRecyclerView(){
        binding.recycler.layoutManager = LinearLayoutManager(context)
        adapter = PhoneListAdapter()
        binding.recycler.adapter = adapter
        viewModel.getAllPhones().observe(viewLifecycleOwner, {list->
            adapter.differ.submitList(list)
            updateUi(list)
        })
    }
    fun updateUi(list: List<PhoneEntity>){
        if(list != null){
            binding.recycler.visibility = View.VISIBLE
            binding.cardnophones.visibility = View.GONE
        }else{
            binding.recycler.visibility = View.GONE
            binding.cardnophones.visibility = View.VISIBLE
        }

    }
    fun deleteAll(){
        viewModel.deleteAll()
    }

    //search view adjusting
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menusearch, menu)
        val searcher = menu.findItem(R.id.searchview).actionView as SearchView
        searcher.isSubmitButtonEnabled = false
        searcher.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        searchPhone(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchPhone(newText)
        }
        return true
    }
    fun searchPhone(query: String?){
        val fullquery = "%$query%"
        viewModel.searchPhone(fullquery).observe(this, {searchlist->
            adapter.differ.submitList(searchlist)
        })
    }

}