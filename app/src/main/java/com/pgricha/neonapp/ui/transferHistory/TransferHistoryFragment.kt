package com.pgricha.neonapp.ui.transferHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.pgricha.neonapp.R
import com.pgricha.neonapp.model.Contact
import com.pgricha.neonapp.ui.contacts.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_transfers_history.*

class TransferHistoryFragment : Fragment() {

    private val viewModel: ContactsViewModel by activityViewModels()
    private val graphicTransfersAdapter = GraphicTransfersAdapter(arrayListOf())
    private val transferHistoryAdapter = TransferHistoryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transfers_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar_transfers_history)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            activity?.onBackPressed()
        })

        rv_graph_transfers_history.apply {
            val mLayoutManager = LinearLayoutManager(context)
            mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rv_graph_transfers_history.layoutManager = mLayoutManager
            rv_graph_transfers_history.itemAnimator = DefaultItemAnimator()
            adapter = graphicTransfersAdapter
        }

        rv_contacts_transfers_history.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transferHistoryAdapter
        }

        if(viewModel.transferContacts.size > 0){
            graphicTransfersAdapter.update(viewModel.transferContacts)
            transferHistoryAdapter.update(viewModel.transferContacts)
        } else {
            Toast.makeText(context, "Você ainda não efetuou nenhuma transferência",
                Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }
    }
}