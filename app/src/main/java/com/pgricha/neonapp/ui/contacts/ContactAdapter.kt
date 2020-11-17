package com.pgricha.neonapp.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pgricha.neonapp.R
import com.pgricha.neonapp.model.Contact
import com.pgricha.neonapp.util.getMockImage
import kotlinx.android.synthetic.main.item_adapter_contact.view.*
import java.util.*

class ContactAdapter(private val contacts: ArrayList<Contact>,
                     private val contactAdapterClickListener: ContactAdapterClickListener) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    interface ContactAdapterClickListener {
        fun onContactAdapterClickListener(contact: Int)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val contactName = view.tv_name
        private val contactPhone = view.tv_phone
        private val contactImage = view.img_contact

        fun bind(contact: Contact) {
            contactName.text = contact.name
            contactPhone.text = contact.phone
            contactImage.setImageResource(getMockImage(contact.name));
        }
    }

    fun update(contacts: List<Contact>) {
        this.contacts.clear()
        this.contacts.addAll(contacts)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])

        holder.itemView.setOnClickListener {
            contactAdapterClickListener.onContactAdapterClickListener(position)
        }
    }

    override fun getItemCount(): Int = contacts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_contact,
            parent, false)
    )
}