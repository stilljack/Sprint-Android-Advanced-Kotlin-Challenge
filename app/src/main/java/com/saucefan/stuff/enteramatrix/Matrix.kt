package com.saucefan.stuff.enteramatrix

class Matrix (var height:Int,var width:Int) {
    var arr = Array(height) { Array(this.width) { 0 } }



    //height and width alterations
    fun sHeight (x:Int) {
        this.height =x
        fixArr ()
    }
    fun sWidth (x:Int) {
        this.width =x
        fixArr ()
    }
    private fun fixArr () {
        arr = Array(height) { Array(this.width) { 0 } }
    }

 fun size ():Int {
     return arr.size
 }




}

operator fun Matrix.set(ix:Int,iy:Int,int:Int){
    this.arr[ix][iy] = int
}
operator fun Matrix.get(ix:Int,iy:Int):Int {
    return this.arr[ix][iy]
}



infix operator fun Matrix.times(int:Int): Int {
    var total = 1
    for (i in 0 until this.size()) {
        for (j in 0 until this.size()){
            total = this[i, i] *total
    }
}

 return total * int

}