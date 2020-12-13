package com.example.movieapp.util.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import java.lang.Math.abs

open class OnSwipeTouchListener(ctx: Context) : OnTouchListener {

    val gestureDetector: GestureDetector

    companion object {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100
    }

    init {
        gestureDetector = GestureDetector(ctx, GestureListener())
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {


        var isTouch = false

        if (gestureDetector != null && event != null) {

            isTouch = gestureDetector.onTouchEvent(event)
        } else {
            isTouch = true
        }

        return isTouch

    }

    inner class GestureListener : SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            return false
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {


                val diffY = e1?.y?.let { e2?.y?.minus(it) }
                val diffX = e1?.x?.let { e2?.x?.minus(it) }

                if (diffX != null && diffY != null) {

                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                            result = true
                        }
                    } else {

                    }
                } else {
                    onSwipeRight()

                    result = true
                }


                /*else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                   if (diffY > 0) {
                       onSwipeBottom()
                   } else {
                       onSwipeTop()
                   }
                   result = true
               }*/
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }


    }

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeTop() {}

    open fun onSwipeBottom() {}

    open fun onSwipeDown() {

    }
}