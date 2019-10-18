package com.saucefan.stuff.enteramatrix

import android.content.Context

fun Array<Array<Int>>.times (ix:Int) {
   // return this[ix] * this[ix2]
   this[ix][ix] * this[ix+1][ix+1]
}
operator fun Int.times(point: Point): Point = Point(point.x * this, point.y * this)
/*

fun Array<Array<Int>>.get(ix:Int,ix2:Int):Int {
 return this[ix][ix] * this[ix2][ix2]

}*/
