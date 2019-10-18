package com.saucefan.stuff.enteramatrix

import kotlin.random.Random

class Matrix (var height:Int,var width:Int) {
    var arr = Array(height) { Array(this.width) { 1 } }

    fun sHeight (x:Int) {
        this.height =x
        fixArr ()
    }
    fun sWidth (x:Int) {
        this.width =x
        fixArr ()


    }
    private fun fixArr () {
//make a new array
        //loop through the new array
        //if the current position in the new array should exist (i.e. current value of x and y or height and width or h and w
        // is less than the size of the old array or the arrays within that array (i.e array[x].size)

        var newArr = Array(height) { Array(this.width) { 1 } }
        for (h in 0 until arr.size) {
            for (w in 0 until arr[0].size) {
                if (h<=arr.size && w<=arr[0].size) {
                    newArr[h][w] = arr[h][w]
                }
                else {
                    newArr[h][w] = Random.nextInt(9)
                }
            }
        }
        arr=newArr

    }

 fun size ():Int {
     return arr.size
 }

    fun sizeHeight ():Int {
        return arr.size
    }
    fun sizeWidth ():Int {
        return arr[0].size
    }




}

operator fun Matrix.set(ix:Int,iy:Int,int:Int){
    this.arr[ix][iy] = int
}
operator fun Matrix.get(ix:Int,iy:Int):Int {
    return this.arr[ix][iy]
}


infix operator fun Matrix.rem(int:Int): Int {
    var total = 1
    for (h in 0 until this.sizeHeight() - 1) {
        for (w in 0 until this.sizeWidth() - 1) {
            total = this[h, w] * total
            total = this[h + 1, w + 1] * total
        }
    }
return total
}

infix operator fun Matrix.times(int:Int): Int {
    var total = 1
    for (h in 0 until this.sizeHeight()) {
        for (w in 0 until this.sizeWidth()){
            total = this[h, w] *total
    }
}

 return total * int

}