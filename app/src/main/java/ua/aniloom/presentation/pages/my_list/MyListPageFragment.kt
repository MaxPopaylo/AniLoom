package ua.aniloom.presentation.pages.my_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.aniloom.R


class MyListPageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_list_page, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MyListPageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}