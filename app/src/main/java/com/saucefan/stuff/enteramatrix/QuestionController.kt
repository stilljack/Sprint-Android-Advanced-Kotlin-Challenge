package com.saucefan.stuff.enteramatrix

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import work.beltran.conductorviewmodel.ViewModelController


class QuestionController (bundle: Bundle) : ViewModelController(bundle){


    companion object {
        private const val TAG = "MyViewModelController"
    }


    var communicatedStringLate:String=""
    constructor(communicatedString:String? =null): this(Bundle().apply {
        putString(EXTRA_STRING,communicatedString)
    })
    val communicatedString by lazy {
        args.getString(EXTRA_STRING)
    }


    val horizontalChangeHandler = HorizontalChangeHandler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.content_main, container, false)

        (view.findViewById(R.id.tv_first) as TextView).text =
            " backstack size =${communicatedString} + ${communicatedStringLate}"
        return view
    }

    fun getMessage(string: String?):String? {
        return communicatedString
    }

    protected override fun onChangeEnded(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeEnded(changeHandler, changeType)

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
    }

    protected override fun onChangeStarted(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeStarted(changeHandler, changeType)

        setButtonsEnabled(false)
    }
    private fun setButtonsEnabled(enabled: Boolean) {
        val view = view
        if (view != null) {
            (enabled)
        }

        /*router.pushController(RouterTransaction.with(HomeController(router.backstackSize.toString()))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
                )*/
    }

