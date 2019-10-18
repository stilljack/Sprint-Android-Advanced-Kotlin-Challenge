package com.saucefan.stuff.enteramatrix

class Matrix (val height:Int,val width:Int) {

    val arr = Array(height) { Array(this.width) { 0 } }




}

operator fun Matrix.get(ix:Int,iy:Int):Int {
    return this.arr[ix][iy]
}

operator fun Matrix.set(ix:Int,iy:Int,int:Int){
    this.arr[ix][iy] = int
}