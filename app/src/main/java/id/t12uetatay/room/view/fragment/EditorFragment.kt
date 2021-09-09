package id.t12uetatay.room.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import id.t12uetatay.room.model.Notes
import android.util.DisplayMetrics
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.t12uetatay.room.R


class EditorFragment (/*mNote: Notes*/) : BottomSheetDialogFragment() {
    private var editext1: TextInputEditText? = null
    private var editext2:TextInputEditText? = null
    private var inputLayout1: TextInputLayout? = null
    private var inputLayout2: TextInputLayout? = null
    private var note: Notes? = null

    init {
        //note=mNote
    }

    companion object {

        const val TAG = "CustomBottomSheetDialogFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_editor, container, false)
        init(view)
        view.findViewById<View>(R.id.close).setOnClickListener { View->

        }

        return view
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val metrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(metrics)
        //getDialog().getWindow().setGravity(Gravity.BOTTOM)
        //getDialog().getWindow().setLayout(width, height)
        //getDialog().setCanceledOnTouchOutside(false)
        //getDialog().getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun init(view: View) {
        editext1 = view.findViewById(R.id.editext1)
        editext2 = view.findViewById(R.id.editext2)
        inputLayout1 = view.findViewById(R.id.textInputLayout1)
        inputLayout2 = view.findViewById(R.id.textInputLayout2)
        //viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

    }
}