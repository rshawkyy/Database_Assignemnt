package com.example.dbtask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.dbtask.MessageRepositoryImpl
import com.example.dbtask.R
import com.example.dbtask.db.LocalDataSource
import com.example.dbtask.view.DetailsFragmentArgs
import com.example.dbtask.viewModel.DetailsViewModel
import com.example.dbtask.viewModel.DetailsViewModelFactory
import kotlinx.coroutines.launch
import androidx.navigation.fragment.navArgs

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var detailsViewModelFactory: DetailsViewModelFactory
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val messageRepository = MessageRepositoryImpl.getInstance(
            LocalDataSource.getInstance(requireContext())
        )
        detailsViewModelFactory = DetailsViewModelFactory(messageRepository)
        detailsViewModel = ViewModelProvider(this, detailsViewModelFactory).get(DetailsViewModel::class.java)

        val phoneTextView: TextView = view.findViewById(R.id.phone_value)
        val msgTextView: TextView = view.findViewById(R.id.msg_value)

        val phone = args.phone
        val msg = args.msg

        phoneTextView.text = phone
        msgTextView.text = msg

        // Set up button click listeners
        val writeButton: Button = view.findViewById(R.id.writebtn)
        val readButton: Button = view.findViewById(R.id.readbtn)

        writeButton.setOnClickListener {
            lifecycleScope.launch {
                detailsViewModel.insertMessage(phone, msg)
                phoneTextView.text = ""
                msgTextView.text = ""
            }
        }

        readButton.setOnClickListener {
            detailsViewModel.loadLastMessage()
            detailsViewModel.lastMessage.observe(viewLifecycleOwner) { message ->
                phoneTextView.text = message?.phone ?: " "
                msgTextView.text = message?.msg ?: " "
            }
        }

        val closeButton: Button = view.findViewById(R.id.close2)
        closeButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
