package id.t12uetatay.room.view.activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.DialogBehavior
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.ModalDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.t12uetatay.room.R
import id.t12uetatay.room.model.Notes

class MainActivity : AppCompatActivity() {
    private var toolbar : MaterialToolbar? = null
    private var fab : FloatingActionButton? = null
    private var recyclerView : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recyclerView)

        fab?.setOnClickListener { view ->
            val note : Notes? = null
            val intent = Intent(this@MainActivity, EditorActivity::class.java)
            intent.putExtra("data", note)
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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

    private fun showCustomViewDialog(dialogBehavior: DialogBehavior = ModalDialog) {
        val dialog = MaterialDialog(this, dialogBehavior).show {
            title(R.string.create)
            customView(R.layout.fragment_editor, scrollable = true, horizontalPadding = true)
            positiveButton(R.string.save) { dialog ->
                // Pull the password out of the custom view when the positive button is pressed
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            negativeButton(android.R.string.cancel)
            //lifecycleOwner(this@MainActivity)
            //debugMode(debugMode)
        }

        // Setup custom view content
        val customView = dialog.getCustomView()
        //val passwordInput: TEx = customView.findViewById(R.id.password)
        //val showPasswordCheck: CheckBox = customView.findViewById(R.id.showPassword)
        /*showPasswordCheck.setOnCheckedChangeListener { _, isChecked ->
            passwordInput.inputType =
                if (!isChecked) InputType.TYPE_TEXT_VARIATION_PASSWORD else InputType.TYPE_CLASS_TEXT
            passwordInput.transformationMethod =
                if (!isChecked) PasswordTransformationMethod.getInstance() else null
        }*/
    }

}