package io.wwdaigo.githubissues.modules.list.adapters.tabbar

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class IssuesPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val fragments:MutableList<Fragment> = ArrayList()
    private val titles:MutableList<String> = ArrayList()

    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
    override fun getPageTitle(position: Int) = titles[position]

    fun addPage(title: String, fragment: Fragment) {
        fragments.add(fragment)
        titles.add(title)
    }
}