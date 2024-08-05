package com.example.dbtask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation.findNavController
import com.example.dbtask.R
import com.google.android.material.textfield.TextInputEditText

class AddFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phoneEditText: TextInputEditText = view.findViewById(R.id.phonenum)
        val msgEditText: TextInputEditText = view.findViewById(R.id.msgval)

        val nxt: Button = view.findViewById(R.id.next)
        nxt.setOnClickListener {
            val phoneval = phoneEditText.text.toString()
            val msgval = msgEditText.text.toString()

            val navController = findNavController(view)
            val action =
                com.example.dbtask.view.AddFragmentDirections.actionAddFragmentToDetailsFragment(
                    phoneval,
                    msgval
                )
            navController.navigate(action)
        }

        val closeButton: Button = view.findViewById(R.id.close)
        closeButton.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }
}
