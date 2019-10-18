package com.saucefan.stuff.enteramatrix

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction

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
*
*
*
*
 */



class MainActivity : AppCompatActivity() {
    private lateinit var router: Router
    private lateinit var routerTwo: Router
    private val container: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.first)
    }

    private val containerTwo: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.second)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        router = Conductor.attachRouter(this, container, savedInstanceState)
        if(!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(QuestionController("string from main")))
        }
        routerTwo = Conductor.attachRouter(this, containerTwo, savedInstanceState)
        if(!routerTwo.hasRootController()) {
            routerTwo.setRoot(RouterTransaction.with(QuestionController("a different string")))
        }













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
            else -> super.onOptionsItemSelected(item)
        }
    }
}
