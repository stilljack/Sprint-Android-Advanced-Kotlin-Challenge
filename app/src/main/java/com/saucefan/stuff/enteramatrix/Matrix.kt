package com.saucefan.stuff.enteramatrix

import java.io.Serializable
import kotlin.random.Random

class Matrix (var height:Int,var width:Int) :Serializable {
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

    override fun toString(): String {
        return arr.toString()

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

infix operator fun Matrix.times(matrix: Matrix): Matrix {
    val h1 =this.sizeHeight()
    var h2 = matrix.sizeWidth()
    val w1  = this.sizeWidth()
    val w2 = matrix.sizeHeight()


    val total =  Matrix(h1,w2)

 // total[0,0] = (this[0,0], * matrix[0,0]) + ( * matrix[1,0]) + (this[0,0] * matrix[0,0]) + (this[0,0] * matrix[1,0])
    //this will only work on rectangular matrixicieiscies  where h1=h2
    for (i in 0 until h1) {
        for (j in 0 until w1) {
            for (k in 0 until w2) {
                total[i,j] += (this[i,k] * matrix[k,j])
            }
        }
    }

 return total

}

/*
fun main(args: Array<String>) {
    val r1 = 2
    val c1 = 3
    val r2 = 3
    val c2 = 2
    val firstMatrix = arrayOf(intArrayOf(3, -2, 5), intArrayOf(3, 0, 4))
    val secondMatrix = arrayOf(intArrayOf(2, 3), intArrayOf(-9, 0), intArrayOf(0, 4))
    // Mutliplying Two matrices
    val product = multiplyMatrices(firstMatrix, secondMatrix, r1, c1, c2)
    // Displaying the result
    displayProduct(product)
}
fun multiplyMatrices(firstMatrix: Array, secondMatrix: Array, r1: Int, c1: Int, c2: Int): Array {
    val product = Array(r1) { IntArray(c2) }
    for (i in 0..r1 - 1) {
        for (j in 0..c2 - 1) {
            for (k in 0..c1 - 1) {
                product[i][j] += firstMatrix[i][k] * secondMatrix[k][j]
            }
        }
    }
    return product
}
fun displayProduct(product: Array) {
    println("Product of two matrices is: ")
    for (row in product) {
        for (column in row) {
            print("$column    ")
        }
        println()
    }
}




 */