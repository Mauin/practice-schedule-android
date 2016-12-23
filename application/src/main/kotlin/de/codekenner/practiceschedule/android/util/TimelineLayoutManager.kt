package de.codekenner.practiceschedule.android.util

import android.support.v7.widget.RecyclerView
import android.util.Log
import de.codekenner.practiceschedule.extensions.log

// For reference: https://github.com/GreaseMonk/android-timetable-core/blob/develop/src/main/java/com/greasemonk/timetable/FixedGridLayoutManager.java
class TimelineLayoutManager(val layouter: TimetableLayouter) : RecyclerView.LayoutManager() {

	var scrollOffset: Int = 0

	override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
		if (itemCount == 0) {
			log("itemcount == 0")
			detachAndScrapAttachedViews(recycler)
			return
		}

		val to: Int = state!!.itemCount - 1

		(0..to).forEach {
			Log.e("TEST", "layout $it")
			val viewForPosition = recycler!!.getViewForPosition(it)
			addView(viewForPosition)
			measureChildWithMargins(viewForPosition, 0, 0)

			val width = layouter.getWidth(it, viewForPosition.parent)
			val height = layouter.getHeight(it)
			val top = layouter.getTop(it)
			val left = layouter.getLeft(it, viewForPosition.parent)
			layoutDecorated(viewForPosition, left, top, left + width, top + height)
		}
	}

	override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
		if (childCount == 0 || dy == 0) {
			return 0
		}

		var toScroll = dy
		if (dy < -scrollOffset) {
			toScroll = -scrollOffset
		}

		var botSpace = getDecoratedBottom(getChildAt(childCount - 1)) - height
		if (botSpace < 0) botSpace = 0

		if (dy > botSpace) {
			toScroll = botSpace
		}

		scrollOffset += toScroll
		offsetChildrenVertical(-toScroll)
		return toScroll
	}

	override fun canScrollVertically() = true

	override fun canScrollHorizontally() = false

	override fun supportsPredictiveItemAnimations() = false // Make this return true for fancy item animations

	override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
		return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT)
	}
}
