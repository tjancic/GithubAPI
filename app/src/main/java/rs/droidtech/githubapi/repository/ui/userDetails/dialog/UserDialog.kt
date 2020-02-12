package rs.droidtech.githubapi.repository.ui.userDetails.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import rs.droidtech.githubapi.R

class UserDialog : DialogFragment() {

    lateinit var ownerEditText: EditText

    var onSaveClickListener: ((text: String) -> Unit)? = null

    var owner: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_user, null)
        ownerEditText = view.findViewById(R.id.ownerEditText)
        owner?.let {
            ownerEditText.setText(it)
        }
        return createAlertDialog(view)
    }

    private fun createAlertDialog(view: View): AlertDialog {
        val builder = AlertDialog.Builder(context).apply {
            setView(view)
            setCancelable(false)
            setTitle("Change repository owner ")
            setPositiveButton("Save") { _, _ ->
                if (ownerEditText.text.isNotEmpty()) {
                    onSaveClickListener?.let { it(ownerEditText.text.toString()) }
                    dismissAllowingStateLoss()
                }
            }
            setNegativeButton("Cancel") { _, _ ->
                dismissAllowingStateLoss()
            }
        }
        return builder.show()
    }
}
