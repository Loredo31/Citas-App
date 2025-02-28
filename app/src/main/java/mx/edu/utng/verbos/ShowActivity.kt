package mx.edu.utng.verbos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import mx.edu.utng.verbos.db.DbCitas
import mx.edu.utng.verbos.entidades.Citas

//import com.udb.testjava.db.DbContactos
///import com.udb.testjava.entidades.Contactos

class ShowActivity : AppCompatActivity() {

    private lateinit var Consulta: TextView
    private lateinit var Doctor: TextView
    private lateinit var Paciente: TextView
    private lateinit var Fecha: TextView
    private lateinit var Hora_Inicio: TextView
    private lateinit var Hora_Fin: TextView
    private lateinit var Diagnostico: TextView
    private lateinit var Receta: TextView
    private lateinit var Pago: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button

    private lateinit var cita: Citas
    private var id = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        Consulta = findViewById(R.id.Consulta)
        btnEdit = findViewById(R.id.btngoToEdit)
        btnDelete = findViewById(R.id.btngoToDelete)
        Doctor = findViewById(R.id.Doctor)
        Paciente = findViewById(R.id.Paciente)
        Fecha = findViewById(R.id.Fecha)
        Hora_Inicio = findViewById(R.id.Hora_Inicio)
        Hora_Fin = findViewById(R.id.Hora_Fin)
        Diagnostico = findViewById(R.id.Diagnostico)
        Receta = findViewById(R.id.Receta)
        Pago = findViewById(R.id.Pago)

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
            builder.setMessage("¿Desea eliminar esta cita?")
                .setPositiveButton("SI") { dialog, which ->
                    if (dbCitas.deleteCita(id)) {
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