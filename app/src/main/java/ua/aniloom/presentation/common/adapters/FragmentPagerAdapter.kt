package ua.aniloom.presentation.common.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPagerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle
) :  FragmentStateAdapter(manager,lifecycle )  {
    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val titleList: MutableList<String> = ArrayList()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
        notifyItemInserted(fragmentList.size - 1)
    }

    fun getPageTitle(position: Int): CharSequence = titleList[position]
}