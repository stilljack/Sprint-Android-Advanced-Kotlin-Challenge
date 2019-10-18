package com.saucefan.stuff.enteramatrix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.saucefan.stuff.enteramatrix.controllers.QuestionController

import kotlinx.android.synthetic.main.activity_main.*


/*
*
*  0. so no data has to go backwards,
*       we'll do livedata anyway.
*   1. so lets overload first
*           todo: figure out why set kinda sucks
*
*   WTF... these are the number presented in the example,
*   11 2
    32 43		69  82

    5 6
			 461 436
    7 8
*  yikes
*
* 2. matrix class is serialiazble now so we can stick it in a bundlesss
The goal of this application is to allow users to multiply matrices together and view the result on a separate page.

The requirements for this project are as follows:

Create a custom Matrix class which will.
*
*       check
Store matrixes in a 2-dimentional array
This line Array(height) { Array(width) { 0 } } will declare an array of size height x width and fill it with 0s

 *
 *  check
 *

Overload the * and index operators
This page will provide information on overloading the index operator (https://kotlinlang.org/docs/reference/operator-overloading.html)

 *
 *  check
 *

Include height and width properties with setters that will resize your matrix data array when they're changed
To resize an array, you'll need to create a second array of the bigger size, copy the old array data to it, and then replace the property the value of the new array (=)

 *
 *  check
 *

One controller which will show 2 matrices at once and a button to perform the calculation
The user must be able to edit the values in the matrices
Another controller which will show the result of the calculation as another matrix
You may have issues rebuilding your first controller after returning from your second one, a few tips:

 *
 *  check
 *

Don't do controller interactions in onChangeEnded this triggers both when the controller is being created and another one is replacing it
If you use child controllers, be sure construct them with setPopsLastView(true) and to clear them with popCurrentController to allow you to rebuild them
Use by lazy and lateinit on class properties
*
*
 *
 *  check
 *

*
Go Further (Stretch Goals)
Allow users to change the size of the matrices in the UI
*
*
* I don't understand the question and I refuse to answer it
*
*
*  -- no really tho now you can
*
*
 */



class MainActivity : AppCompatActivity() {
    private lateinit var router: Router
    private lateinit var routerTwo: Router
    private val container: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.first)
    }

   /* private val containerTwo: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.second)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        router = Conductor.attachRouter(this, container, savedInstanceState)
        if(!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(
                QuestionController(
                    "matrix one"
                )
            ))
            router.setPopsLastView(true)
        }
/*
        routerTwo = Conductor.attachRouter(this, containerTwo, savedInstanceState)
        if(!routerTwo.hasRootController()) {
            routerTwo.setRoot(RouterTransaction.with(QuestionController("a different string")))
        }
*/













        val arr = arrayOf(arrayOf(1,2))
        var matrix: Array<IntArray> = Array(2) { IntArray(2) }
        val ourMatrix:Matrix = Matrix(2,2)
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix.size) {
                val newValue =(i + j+1)
                ourMatrix.set(i,j,newValue)
            }
        }
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix.size) {
                println("${ourMatrix[i,j]}  )")
            }
        }

  /*      fab.setOnClickListener { view ->

            Snackbar.make(view, "${ourMatrix%3}", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            ourMatrix.sWidth(3)
            ourMatrix.sHeight(5)
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> router.popCurrentController()
        }
    }

    override fun onBackPressed() {
        if(!router.handleBack()) {
            super.onBackPressed()
        }
    }
}
