package mx.edu.utng.verbos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.edu.utng.verbos.db.DbVerbs


class InsertActivity : AppCompatActivity() {

    private lateinit var txtVerb: EditText
    private lateinit var text1: EditText
    private lateinit var text2: EditText
    private lateinit var text3: EditText
    private lateinit var text4: EditText
    private lateinit var text5: EditText
    private lateinit var text6: EditText
    private lateinit var text7: EditText
    private lateinit var text8: EditText
    private lateinit var text9: EditText
    private lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)


        txtVerb = findViewById(R.id.et_verb)
        text1= findViewById(R.id.Ind_Yo)
        text2= findViewById(R.id.Ind_Tu)
        text3= findViewById(R.id.Ind_El)
        text4= findViewById(R.id.Ind_Nosotros)
        text5= findViewById(R.id.Ind_Vosotros)
        text6= findViewById(R.id.Ind_Ellos)
        text7= findViewById(R.id.Imp_Tu)
        text8= findViewById(R.id.Imp_Nosotros)
        text9= findViewById(R.id.Imp_ellos)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val verb = txtVerb.text.toString()
            val indYo = text1.text.toString()
            val indTu = text2.text.toString()
            val indEl = text3.text.toString()
            val indNosotros = text4.text.toString()
            val indVosotros = text5.text.toString()
            val indEllos = text6.text.toString()
            val impTu = text7.text.toString()
            val impNosotros = text8.text.toString()
            val impEllos = text9.text.toString()

            if (isValidText(verb) && isValidText(indYo) && isValidText(indTu) && isValidText(indEl)
                && isValidText(indNosotros) && isValidText(indVosotros) && isValidText(indEllos)
                && isValidText(impTu) && isValidText(impNosotros) && isValidText(impEllos)
            ) {
                val dbVerbs = DbVerbs(this@InsertActivity)
                val id = dbVerbs.InsertV(
                    verb,
                    indYo,
                    indTu,
                    indEl,
                    indNosotros,
                    indVosotros,
                    indEllos,
                    impTu,
                    impNosotros,
                    impEllos
                )

                if (id > 0) {
                    Toast.makeText(this@InsertActivity, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                    limpiar()
                } else {
                    Toast.makeText(this@InsertActivity, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@InsertActivity, "Ingrese datos válidos en todos los campos", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun limpiar() {
        txtVerb.text.clear()
        text1.text.clear()
        text2.text.clear()
        text3.text.clear()
        text4.text.clear()
        text5.text.clear()
        text6.text.clear()
        text7.text.clear()
        text8.text.clear()
        text9.text.clear()
    }
    private fun isValidText(text: String): Boolean {
        val pattern = Regex("^[a-zA-ZáéíóúñÁÉÍÓÚÑ]+$")
        return text.isNotBlank() && pattern.matches(text)
    }



}