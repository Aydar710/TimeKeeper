package ru.timekeeper.ui.vk

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.widget.EditText
import ru.timekeeper.R

class PercentDialogFragment : androidx.fragment.app.DialogFragment() {

    lateinit var clickListener: OnDialogButtonClickListener
    var etPercent: EditText? = null

    override fun onAttach(activity: Activity?) {
        try {
            clickListener = activity as MainActivity
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement OnDialogButtonClickListener")
        }
        super.onAttach(activity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = activity?.let { AlertDialog.Builder(it) }
        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.fragment_dialog, null)
        etPercent = view?.findViewById(R.id.et_percent)
        builder?.setView(view)
                ?.setTitle("Популярность поста")
                ?.setPositiveButton("Ok") { dialog, which ->
                    val percentString = etPercent?.text?.toString()
                    if (percentString?.length != 0)
                        percentString?.toInt()?.let { percent ->
                            clickListener.onPositiveBtnClicked(percent)
                        }

                }
                ?.setNegativeButton("Cancel") { dialog, which ->
                }
        return buildDialog(builder)
    }

    private fun buildDialog(builder: AlertDialog.Builder?) =
            builder?.create() ?: throw NullPointerException()

    interface OnDialogButtonClickListener {
        fun onPositiveBtnClicked(percent: Int)
    }

}
