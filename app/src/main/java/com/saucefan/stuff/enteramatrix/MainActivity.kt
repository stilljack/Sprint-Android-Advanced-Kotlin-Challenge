package com.saucefan.stuff.enteramatrix

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*


/*
*
*  0. so no data has to go backwards,
*       we'll do livedata anyway.
*   1. so lets overload first
*
*
*
*
*
*
*
*
*
 */



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
   val arr = arrayOf(arrayOf(1,2))
        var matrix: Array<IntArray> = Array(2) { IntArray(2) }
        val ourMatrix:Matrix = Matrix(2,2)
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix.size) {
                val newValue =(i + j)
                ourMatrix.set(i,j,newValue)
            }
        }
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix.size) {
                println("${ourMatrix[i,j]}  )")
            }
        }

        fab.setOnClickListener { view ->

            Snackbar.make(view, "${matrix[1][0]*1}", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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
