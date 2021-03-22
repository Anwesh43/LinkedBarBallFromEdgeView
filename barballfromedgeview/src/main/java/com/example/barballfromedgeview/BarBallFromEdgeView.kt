package com.example.barballfromedgeview

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

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