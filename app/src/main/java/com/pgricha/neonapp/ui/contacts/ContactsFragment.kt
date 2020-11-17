package com.pgricha.neonapp.ui.contacts

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pgricha.neonapp.R
import com.pgricha.neonapp.model.Contact
import com.pgricha.neonapp.util.MoneyTextWatcher
import com.pgricha.neonapp.util.getMockImage
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.layout_dialog_send_money.view.*
import java.util.*
import kotlin.collections.ArrayList

class ContactsFragment : Fragment(), ContactAdapter.ContactAdapterClickListener {

    private lateinit var contacts: List<Contact>
    private val viewModel: ContactsViewModel by activityViewModels()
    private val contactAdapter = ContactAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            activity?.onBackPressed()
        })

        viewModel.fetchGetContacts()

        rv_contacts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contactAdapter
        }

        viewModel.contacts.observe(viewLifecycleOwner, Observer<List<Contact>> { contacts ->
            contacts?.let {
                this.contacts = contacts
                contactAdapter.update(it)
                loading.visibility = View.GONE
            }
        })
    }

    override fun onContactAdapterClickListener(position: Int) {
        val dialog = LayoutInflater.from(context).inflate(
            R.layout.layout_dialog_send_money, null
        )

        val contact = viewModel.contacts.value!!.get(position)
        dialog.tv_name.text =  contact.name
        dialog.tv_phone.text = contact.phone
        dialog.img_contact.setImageResource(getMockImage(contact.name));

        val mBuilder = AlertDialog.Builder(context)
            .setView(dialog)

        val alertDialog = mBuilder.show()

        dialog.edt_value.addTextChangedListener(
            MoneyTextWatcher(dialog.edt_value, Locale("pt", "BR"))
        )

        dialog.btnSendMoney.setOnClickListener {
            alertDialog.dismiss()
            viewModel.sendMoney(dialog.edt_value.text.toString(), contact)
            for (contact in this.contacts){
                if(contact.value != null){
                    viewModel.transferContacts.add(contact)
                }
            }
        }
    }
}