package com.example.mvvmphonebook.homeadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmphonebook.PhoneListFragmentDirections
import com.example.mvvmphonebook.databinding.OnephoneBinding
import com.example.mvvmphonebook.db.PhoneEntity

class PhoneListAdapter(): RecyclerView.Adapter<PhoneListAdapter.PhoneListHolder>() {
    lateinit var binding: OnephoneBinding
    class PhoneListHolder(item: View): RecyclerView.ViewHolder(item)

    val diffutil = object : DiffUtil.ItemCallback<PhoneEntity>(){
        override fun areItemsTheSame(oldItem: PhoneEntity, newItem: PhoneEntity): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.lastname == newItem.lastname &&
                    oldItem.phone == newItem.phone &&
                    oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhoneEntity, newItem: PhoneEntity): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, diffutil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneListHolder {
        binding = OnephoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneListHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PhoneListHolder, position: Int) {
        val currentPhone = differ.currentList[position]
        binding.tvname.text = currentPhone.name
        binding.tvlastname.text = currentPhone.lastname
        binding.tvphone.text = currentPhone.phone
        holder.itemView.setOnClickListener {
            val directions = PhoneListFragmentDirections.actionPhoneListFragmentToUpdatePhoneFragment(currentPhone)
            it.findNavController().navigate(directions)
        }
    }
}