package mx.edu.utng.verbos

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import mx.edu.utng.verbos.db.DbVerbs
import mx.edu.utng.verbos.entidades.Verbs

//import com.udb.testjava.db.DbContactos
///import com.udb.testjava.entidades.Contactos

class ShowActivity : AppCompatActivity() {

    private lateinit var et_verb: TextView
    private lateinit var Ind_Yo: TextView
    private lateinit var Ind_Tu: TextView
    private lateinit var Ind_El_Ella_Usted: TextView
    private lateinit var Ind_Nosotros: TextView
    private lateinit var Ind_Vosotros: TextView
    private lateinit var Ind_Ellos: TextView
    private lateinit var Imp_Tu: TextView
    private lateinit var Imp_Nosotros: TextView
    private lateinit var Imp_Ellos_Ellas_Ustedes: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button

    private lateinit var verb: Verbs
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        et_verb = findViewById(R.id.et_verb)
        btnEdit = findViewById(R.id.btngoToEdit)
        btnDelete = findViewById(R.id.btngoToDelete)
        Ind_Yo = findViewById(R.id.Ind_Yo)
        Ind_Tu = findViewById(R.id.Ind_Tu)
        Ind_El_Ella_Usted = findViewById(R.id.Ind_El_Ella_Usted)
        Ind_Nosotros = findViewById(R.id.Ind_Nosotros)
        Ind_Vosotros = findViewById(R.id.Ind_Vosotros)
        Ind_Ellos = findViewById(R.id.Ind_Ellos)
        Imp_Tu = findViewById(R.id.Imp_Tu)
        Imp_Nosotros = findViewById(R.id.Imp_Nosotros)
        Imp_Ellos_Ellas_Ustedes = findViewById(R.id.Imp_Ellos_Ellas_Ustedes)

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                id = 0
            } else {
                id = extras.getInt("ID")
            }
        } else {
            id = savedInstanceState.getSerializable("ID") as Int
        }

        val dbVerbs = DbVerbs(this)
        verb = dbVerbs.showVerb(id)!!

        if (verb != null) {
            et_verb.setText(verb.Verbo)
            Ind_Yo.setText(verb.Ind_Yo)
            Ind_Tu.setText(verb.Ind_Tu)
            Ind_El_Ella_Usted.setText(verb.Ind_El_Ella_Usted)
            Ind_Nosotros.setText(verb.Ind_Nosotros)
            Ind_Vosotros.setText(verb.Ind_Vosotros)
            Ind_Ellos.setText(verb.Ind_Ellos)
            Imp_Tu.setText(verb.Imp_Tu)
            Imp_Nosotros.setText(verb.Imp_Nosotros)
            Imp_Ellos_Ellas_Ustedes.setText(verb.Imp_Ellos_Ellas_Ustedes)
            //btnSave.visibility = View.INVISIBLE
            /* et_verb.inputType = InputType.TYPE_NULL
             Ind_Yo.inputType = InputType.TYPE_NULL
             Ind_Tu.inputType = InputType.TYPE_NULL
             Ind_El_Ella_Usted.inputType = InputType.TYPE_NULL
             Ind_Nosotros.inputType = InputType.TYPE_NULL
             Ind_Vosotros.inputType = InputType.TYPE_NULL
             Ind_Ellos.inputType = InputType.TYPE_NULL
             Imp_Tu.inputType = InputType.TYPE_NULL
             Imp_Nosotros.inputType = InputType.TYPE_NULL
             Imp_Ellos_Ellas_Ustedes.inputType = InputType.TYPE_NULL*/
        }

        btnEdit.setOnClickListener {
            goToEdit()
        }

        btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this@ShowActivity)
            builder.setMessage("¿Desea eliminar este verbo?")
                .setPositiveButton("SI") { dialog, which ->
                    if (dbVerbs.deleteVerb(id)) {
                        goToHome();
                    }
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
    }

    private fun goToEdit() {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}