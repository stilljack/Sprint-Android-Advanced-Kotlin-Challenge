package com.saucefan.stuff.enteramatrix.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.saucefan.stuff.enteramatrix.*
import work.beltran.conductorviewmodel.ViewModelController


class AnswerController (bundle: Bundle) : ViewModelController(bundle) {
    companion object {
        private const val TAG = "MyViewModelController"
    }



    constructor(communicatedMatrix: Matrix? = null, communicatedMatrix2: Matrix? = null) : this(Bundle().apply {
        putSerializable(EXTRA_MATRIX, communicatedMatrix)
    })

    //this feels like a whack way to do it but whatever
    val communicatedMatrix by lazy {
        if ((args.get(EXTRA_MATRIX) != null)) {
            args.get(EXTRA_MATRIX) as Matrix
            }
        else {
            Matrix(2, 2)
        }
    }
    val communicatedMatrix2 by lazy {
        if ((args.get(EXTRA_MATRIX_TWO) != null)) {
            args.get(EXTRA_MATRIX_TWO) as Matrix
        }
        else {
            Matrix(2, 2)
        }
    }


    val horizontalChangeHandler = HorizontalChangeHandler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.answer, container, false)
        val answerMatrix = (communicatedMatrix * communicatedMatrix2)
        (view.findViewById(R.id.tv_answer_one) as TextView).text =
            " product  "
        (view.findViewById(R.id.et_answer_one) as TextView).text =
            answerMatrix[0,0].toString()
        (view.findViewById(R.id.et_answer_two) as TextView).text =
            answerMatrix[0,1].toString()
        (view.findViewById(R.id.et_answer_three) as TextView).text =
            answerMatrix[1,0].toString()
        (view.findViewById(R.id.et_answer_four) as TextView).text =
            answerMatrix[1,1].toString()

        val btnView = (view.findViewById(R.id.next) as Button)
        btnView.setOnClickListener {
            router.pushController(
                RouterTransaction.with(
                    StretchController(
                        Bundle().apply {
                            this.putSerializable(
                                EXTRA_MATRIX,
                                etMatrix(view)
                            )
                        }
                    ))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )

        }
        return view
    }
    fun etMatrix(view:View): Matrix {
        val matrix = Matrix(2, 2)
        matrix[0, 0] = view.findViewById<EditText>(R.id.et_answer_one).text.toString().toInt()
        matrix[0, 1] = view.findViewById<EditText>(R.id.et_answer_two).text.toString().toInt()
        matrix[1, 0] = view.findViewById<EditText>(R.id.et_answer_three).text.toString().toInt()
        matrix[1, 1] = view.findViewById<EditText>(R.id.et_answer_four).text.toString().toInt()
        return matrix
    }

    protected override fun onChangeEnded(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeEnded(changeHandler, changeType)
/*

        val viewModel =activity?.run {
            viewModelProvider(LiveDataVMFactory).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        setButtonsEnabled(true)



        val tv=view?.findViewById<TextView>(R.id.tv_first)
        val btnView=view?.findViewById<Button>(R.id.btn_first)
        val btnView2 =view?.findViewById<Button>(R.id.btn2_first)


        btnView?.text="2 ChildController()"
        btnView?.setOnClickListener {
            recieveMSG(6)
            router.pushController(RouterTransaction.with(ChildController(this, Bundle().apply {
                this.putString(EXTRA_STRING,"this text was communicated from home to child via bundle")
            } ))
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler())
            )
        }


        if (router.backstackSize <= 1){
            //    btnView2?.isEnabled =false
            btnView2?.text="set LV HC()"
            btnView2?.setOnClickListener {
                viewModel.select("this is set from HomeController()")
            }
        } else {
            btnView2?.setOnClickListener {
                router.popCurrentController()
            }

            val sauce =targetController
            targetController?.view?.setBackgroundColor(Color.BLUE)

        }

        viewModel.getLiveData().observe(this, Observer<String> {
            tv?.text=it
        })
        */

    }

    protected override fun onChangeStarted(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeStarted(changeHandler, changeType)


    }
}


