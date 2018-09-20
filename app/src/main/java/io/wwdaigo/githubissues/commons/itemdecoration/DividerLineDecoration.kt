package io.wwdaigo.githubissues.commons.itemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.widget.FrameLayout
import io.wwdaigo.common.extensions.forEach
import io.wwdaigo.githubissues.R

class DividerLineDecoration(val context: Context): RecyclerView.ItemDecoration() {

    private val divider = ColorDrawable(ContextCompat.getColor(context, R.color.divider))
    private val height = 1

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        parent?.let {
            val left = it.paddingLeft
            val right = it.width - left

            it.forEach { _ ->
                val params = it.layoutParams as FrameLayout.LayoutParams
                val ty = it.translationY
                val top = (it.top - params.topMargin + ty).toInt()
                val bottom = top + height

                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }
    }
}