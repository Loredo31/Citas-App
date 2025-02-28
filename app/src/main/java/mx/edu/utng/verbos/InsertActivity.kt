package mx.edu.utng.verbos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.edu.utng.verbos.db.DbCitas


class InsertActivity : AppCompatActivity() {

    private lateinit var txtCitas: EditText
    private lateinit var text1: EditText
    private lateinit var text2: EditText
    private lateinit var text3: EditText
    private lateinit var text4: EditText
    private lateinit var text5: EditText
    private lateinit var text6: EditText
    private lateinit var text7: EditText
    private lateinit var text8: EditText
    private lateinit var btnSave: Button



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)


        txtCitas = findViewById(R.id.Consulta)
        text1= findViewById(R.id.Doctor)
        text2= findViewById(R.id.Paciente)
        text3= findViewById(R.id.Fecha)
        text4= findViewById(R.id.Hora_Inicio)
        text5= findViewById(R.id.Hora_Fin)
        text6= findViewById(R.id.Diagnostico)
        text7= findViewById(R.id.Receta)
        text8= findViewById(R.id.Pago)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val Consulta = txtCitas.text.toString()
            val Doctor = text1.text.toString()
            val Paciente = text2.text.toString()
            val Fecha = text3.text.toString()
            val Hora_Inicio = text4.text.toString()
            val Hora_Fin = text5.text.toString()
            val Diagnostico = text6.text.toString()
            val Receta = text7.text.toString()
            val Pago = text8.text.toString()

            if (isValidText(Consulta) && isValidText(Doctor) && isValidText(Paciente) && isValidText(Fecha)
                && isValidText(Hora_Inicio) && isValidText(Hora_Fin) && isValidText(Diagnostico)
                && isValidText(Receta) && isValidText(Pago)
            ) {
                val dbCitas = DbCitas(this@InsertActivity)
                val id = dbCitas.InsertC(
                    Consulta,
                    Doctor,
                    Paciente,
                    Fecha,
                    Hora_Inicio,
                    Hora_Fin,
                    Diagnostico,
                    Receta,
                    Pago
                )

                if (id > 0) {
                    Toast.makeText(this@InsertActivity, "CONSULTA GUARDADA", Toast.LENGTH_LONG).show()
                    limpiar()
                } else {
                    Toast.makeText(this@InsertActivity, "ERROR AL GUARDAR LA CONSULTA", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@InsertActivity, "Ingrese datos válidos en todos los campos", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun limpiar() {
        txtCitas.text.clear()
        text1.text.clear()
        text2.text.clear()
        text3.text.clear()
        text4.text.clear()
        text5.text.clear()
        text6.text.clear()
        text7.text.clear()
        text8.text.clear()
    }
    private fun isValidText(text: String): Boolean {
        val pattern = Regex("^[a-zA-ZáéíóúñÁÉÍÓÚÑ]+$")
        return text.isNotBlank() && pattern.matches(text)
    }



}