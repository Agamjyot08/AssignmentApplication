package com.agamjyot.assignmentapp.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agamjyot.assignmentapp.data.models.Contact
import com.agamjyot.assignmentapp.databinding.RecyclerItemBinding
import com.bumptech.glide.Glide

class MainAdapter(private var contacts: ArrayList<Contact>, private val itemClickCallback: ItemClickListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(var itemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]

        holder.itemBinding.apply {
            tvName.text = contact.name
            tvNumber.text = contact.number
            root.setOnClickListener {
                itemClickCallback.onItemClick(contact, position, it)
            }
        }

//        Glide.with(holder.itemBinding.root.context).load(contact.pic)
//            .into(holder.itemBinding.ivPic)
    }

    fun filterList(filterlist: ArrayList<Contact>) {
        contacts = filterlist
        notifyDataSetChanged()
    }

    fun interface ItemClickListener {
        fun onItemClick(item: Contact, position: Int, view: View)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}