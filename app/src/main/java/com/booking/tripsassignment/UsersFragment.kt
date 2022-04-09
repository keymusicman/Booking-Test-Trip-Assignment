package com.booking.tripsassignment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class UsersFragment : Fragment(R.layout.fragment_users) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.btn_1).setOnClickListener {
            (activity as MainActivity).showUser(899848)
        }
        view.findViewById<View>(R.id.btn_2).setOnClickListener {
            (activity as MainActivity).showUser(48098)
        }
        view.findViewById<View>(R.id.btn_3).setOnClickListener {
            (activity as MainActivity).showUser(8984747)
        }
        view.findViewById<View>(R.id.btn_4).setOnClickListener {
            (activity as MainActivity).showUser(5678923)
        }
        view.findViewById<View>(R.id.btn_5).setOnClickListener {
            (activity as MainActivity).showUser(99999)
        }
        view.findViewById<View>(R.id.btn_6).setOnClickListener {
            (activity as MainActivity).showUser(1)
        }
    }
}