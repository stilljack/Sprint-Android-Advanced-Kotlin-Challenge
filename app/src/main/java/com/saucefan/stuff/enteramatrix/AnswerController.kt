package com.saucefan.stuff.enteramatrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import work.beltran.conductorviewmodel.ViewModelController


class AnswerController (bundle: Bundle) : ViewModelController(bundle) {
    companion object {
        private const val TAG = "MyViewModelController"
    }


    var communicatedStringLate: String = ""

    constructor(communicatedMatrix: Matrix? = null,communicatedMatrix2: Matrix? = null) : this(Bundle().apply {
        putSerializable(EXTRA_MATRIX, communicatedMatrix)
    })

    //this feels like a whack way to do it but whatever
    val communicatedMatrix by lazy {
        if ((args.get(EXTRA_MATRIX) != null)) {
            args.get(EXTRA_MATRIX) as Matrix
            }
        else {
            Matrix(1,1)
        }
    }
    val communicatedMatrix2 by lazy {
        if ((args.get(EXTRA_MATRIX_TWO) != null)) {
            args.get(EXTRA_MATRIX_TWO) as Matrix
        }
        else {
            Matrix(1,1)
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





        return view
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


