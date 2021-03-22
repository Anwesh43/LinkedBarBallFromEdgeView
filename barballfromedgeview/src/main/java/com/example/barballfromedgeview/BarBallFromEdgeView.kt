package com.example.barballfromedgeview

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

val colors : Array<Int> = arrayOf(
    "#f44336",
    "#3F51B5",
    "#1A237E",
    "#F57F17",
    "#00C853"
).map {
    Color.parseColor(it)
}.toTypedArray()
val parts : Int = 4
val barFactor : Float = 9.8f
val circleFactor : Float = 26.2f
val delay : Long = 20
val scGap : Float = 0.02f / parts
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawBarBallFromEdge(i : Int, scale : Float, w : Float, h : Float, paint : Paint) {
    val barSize : Float = h / barFactor
    val ballSize : Float = Math.min(w, h) / circleFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val sf4 : Float = sf.divideScale(3, parts)
    save()
    translate(w / 2, (h / 2 - ballSize / 2) * sf4)
    paint.color = colors[i]
    drawRect(RectF(0f, 0f, w, barSize * sf1), paint)
    paint.color = backColor
    for (j in 0..1) {
        save()
        scale(1f - 2 * j, 1f)
        translate(-w / 2 + ballSize + (w / 2 - ballSize) * sf3, ballSize)
        drawCircle(0f, 0f, ballSize * sf2, paint)
        restore()
    }
    restore()
}

fun Canvas.drawBBFENode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    drawBarBallFromEdge(i, scale, w, h, paint)
}