package mx.edu.utng.verbos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.edu.utng.verbos.db.DbCitas
import mx.edu.utng.verbos.entidades.Citas

class EditActivity : AppCompatActivity() {

    private lateinit var Consulta: EditText
    private lateinit var Doctor: EditText
    private lateinit var Paciente: EditText
    private lateinit var Fecha: EditText
    private lateinit var Hora_Inicio: EditText
    private lateinit var Hora_Fin: EditText
    private lateinit var Diagnostico: EditText
    private lateinit var Receta: EditText
    private lateinit var Pago: EditText
    private lateinit var btnSave: Button

    private lateinit var cita: Citas
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        Consulta = findViewById(R.id.Consulta)
        Doctor = findViewById(R.id.Doctor)
        Paciente = findViewById(R.id.Paciente)
        Fecha = findViewById(R.id.Fecha)
        Hora_Inicio = findViewById(R.id.Hora_Inicio)
        Hora_Fin = findViewById(R.id.Hora_Fin)
        Diagnostico = findViewById(R.id.Diagnostico)
        Receta = findViewById(R.id.Receta)
        Pago = findViewById(R.id.Pago)
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

        val dbCitas = DbCitas(this)
        cita = dbCitas.showVerb(id)!!

        if (cita != null) {
            Consulta.setText(cita.Consulta)
            Doctor.setText(cita.Doctor)
            Paciente.setText(cita.Paciente)
            Fecha.setText(cita.Fecha)
            Hora_Inicio.setText(cita.Hora_Inicio)
            Hora_Fin.setText(cita.Hora_Fin)
            Diagnostico.setText(cita.Diagnostico)
            Receta.setText(cita.Receta)
            Pago.setText(cita.Pago)
        }

        btnSave.setOnClickListener {
            val consultaText = Consulta.text.toString()
            val doctorText = Doctor.text.toString()
            val pacienteText = Paciente.text.toString()
            val fechaText = Fecha.text.toString()
            val horaInicioText = Hora_Inicio.text.toString()
            val horaFinText = Hora_Fin.text.toString()
            val diagnosticoText = Diagnostico.text.toString()
            val recetaText = Receta.text.toString()
            val pagoText = Pago.text.toString()

            if (isValidText(consultaText) && isValidText(doctorText) && isValidText(pacienteText) && isValidText(
                    fechaText
                ) && isValidText(horaInicioText) && isValidText(horaFinText) && isValidText(
                    diagnosticoText
                ) && isValidText(recetaText) && isValidText(pagoText)
            ) {
                val correcto = dbCitas.editCita(
                    id, consultaText, doctorText, pacienteText, fechaText, horaInicioText,
                    horaFinText, diagnosticoText, recetaText, pagoText
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