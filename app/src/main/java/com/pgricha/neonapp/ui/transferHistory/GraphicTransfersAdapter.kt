package com.pgricha.neonapp.ui.transferHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pgricha.neonapp.R
import com.pgricha.neonapp.model.Contact
import com.pgricha.neonapp.util.getMockImage
import kotlinx.android.synthetic.main.item_adapter_contact.view.*
import kotlinx.android.synthetic.main.item_adapter_contact.view.img_contact
import kotlinx.android.synthetic.main.item_adapter_contact.view.tv_value
import kotlinx.android.synthetic.main.item_adapter_transfer_history.view.*
import java.util.*

class GraphicTransfersAdapter(private val contacts: ArrayList<Contact>) :
    RecyclerView.Adapter<GraphicTransfersAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val line = view.line
        private val contactValue = view.tv_value
        private val contactImage = view.img_contact

        fun bind(contact: Contact) {
            val params = line.getLayoutParams()
            params.height = contact.value.toString().replace("R$ ", "").replace(",", ".").toBigDecimal().intValueExact()
            line.setLayoutParams(params)

            contactImage.setImageResource(getMockImage(contact.name));
            contactValue.text = contact.value.toString()
        }
    }

    fun update(contacts: List<Contact>) {
        for (contact in contacts){
            if(contact.value != null){
                this.contacts.add(contact)
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int = contacts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_transfer_history,
            parent, false)
    )
}