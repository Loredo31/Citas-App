package mx.edu.utng.verbos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.edu.utng.verbos.db.DbVerbs
import mx.edu.utng.verbos.entidades.Verbs

class EditActivity : AppCompatActivity() {

    private lateinit var et_verb: EditText
    private lateinit var Ind_Yo: EditText
    private lateinit var Ind_Tu: EditText
    private lateinit var Ind_El_Ella_Usted: EditText
    private lateinit var Ind_Nosotros: EditText
    private lateinit var Ind_Vosotros: EditText
    private lateinit var Ind_Ellos: EditText
    private lateinit var Imp_Tu: EditText
    private lateinit var Imp_Nosotros: EditText
    private lateinit var Imp_Ellos_Ellas_Ustedes: EditText
    private lateinit var btnSave: Button

    private lateinit var verb: Verbs
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        et_verb = findViewById(R.id.et_verb)
        Ind_Yo = findViewById(R.id.Ind_Yo)
        Ind_Tu = findViewById(R.id.Ind_Tu)
        Ind_El_Ella_Usted = findViewById(R.id.Ind_El)
        Ind_Nosotros = findViewById(R.id.Ind_Nosotros)
        Ind_Vosotros = findViewById(R.id.Ind_Vosotros)
        Ind_Ellos = findViewById(R.id.Ind_Ellos)
        Imp_Tu = findViewById(R.id.Imp_Tu)
        Imp_Nosotros = findViewById(R.id.Imp_Nosotros)
        Imp_Ellos_Ellas_Ustedes = findViewById(R.id.Imp_ellos)
        btnSave = findViewById(R.id.btnSave)

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
        }

        btnSave.setOnClickListener {
            val verbText = et_verb.text.toString()
            val indYoText = Ind_Yo.text.toString()
            val indTuText = Ind_Tu.text.toString()
            val indElText = Ind_El_Ella_Usted.text.toString()
            val indNosotrosText = Ind_Nosotros.text.toString()
            val indVosotrosText = Ind_Vosotros.text.toString()
            val indEllosText = Ind_Ellos.text.toString()
            val impTuText = Imp_Tu.text.toString()
            val impNosotrosText = Imp_Nosotros.text.toString()
            val impEllosText = Imp_Ellos_Ellas_Ustedes.text.toString()

            if (isValidText(verbText) && isValidText(indYoText) && isValidText(indTuText) && isValidText(
                    indElText
                ) && isValidText(indNosotrosText) && isValidText(indVosotrosText) && isValidText(
                    indEllosText
                ) && isValidText(impTuText) && isValidText(impNosotrosText) && isValidText(
                    impEllosText
                )
            ) {
                val correcto = dbVerbs.editVerb(
                    id, verbText, indYoText, indTuText, indElText, indNosotrosText,
                    indVosotrosText, indEllosText, impTuText, impNosotrosText, impEllosText
                )

                if (correcto) {
                    Toast.makeText(this@EditActivity, "Registro Modificado", Toast.LENGTH_LONG)
                        .show()
                    goToShow()
                } else {
                    Toast.makeText(
                        this@EditActivity,
                        "Error al modificar registro",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@EditActivity,
                    "Ingrese datos válidos en todos los campos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


    }

    private fun goToShow() {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }

    private fun isValidText(text: String): Boolean {
        val pattern = Regex("^[a-zA-ZáéíóúñÁÉÍÓÚÑ]+$")
        return text.isNotBlank() && pattern.matches(text)
    }


}