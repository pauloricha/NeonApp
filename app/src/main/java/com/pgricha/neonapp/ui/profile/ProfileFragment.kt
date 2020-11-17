package com.pgricha.neonapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pgricha.neonapp.R
import com.pgricha.neonapp.model.Profile
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_send_money)?.setOnClickListener {
            findNavController().navigate(R.id.contacts_fragment, null)
        }

        view.findViewById<Button>(R.id.btn_transfers_history)?.setOnClickListener {
            findNavController().navigate(R.id.transfer_history_fragment, null)
        }

        viewModel.fetchGetProfile()

        viewModel.token.observe(viewLifecycleOwner, Observer<Profile> { profile ->
            tv_name.text = profile.name
            tv_email.text = profile.email
        })
    }
}