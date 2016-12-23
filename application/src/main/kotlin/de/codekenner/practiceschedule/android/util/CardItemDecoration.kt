package de.codekenner.practiceschedule.android.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import de.codekenner.practiceschedule.extensions.half

class CardItemDecoration(private val verticalSpace: Int, private val horizontalSpace: Int) : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
		super.getItemOffsets(outRect, view, parent, state)

		outRect.apply {
			top = verticalSpace.half()
			bottom = verticalSpace.half()
			left = horizontalSpace.half()
			right = horizontalSpace.half()
		}
	}
}
