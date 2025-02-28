package mx.edu.utng.verbos.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.annotation.Nullable
//import mx.edu.utng.verbos.DbHelper
import mx.edu.utng.verbos.entidades.Citas
import java.util.ArrayList

class DbCitas(@Nullable context: Context?) : DbHelper(context) {

    private val context: Context

    init {
        this.context = context!!
    }

    fun InsertC(
        Consulta: String,
        Doctor: String,
        Paciente: String,
        Fecha: String,
        Hora_Inicio: String,
        Hora_Fin: String,
        Diagnostico: String,
        Receta: String,
        Pago: String
    ): Long {
        var id: Long = 0

        try {
            val dbHelper = DbHelper(context)
            val db = dbHelper.getWritableDatabase()

            val values = ContentValues()
            values.put("Consulta",Consulta)
            values.put("Doctor", Doctor)
            values.put("Paciente", Paciente)
            values.put("Fecha", Fecha)
            values.put("Hora_Inicio", Hora_Inicio)
            values.put("Hora_Fin", Hora_Fin)
            values.put("Diagnostico", Diagnostico)
            values.put("Receta", Receta)
            values.put("Pago", Pago)

            id = db.insert(TABLE_CITAS, null, values)
        } catch (ex: Exception) {
            ex.toString()
        }

        return id
    }

    fun showCitas(): ArrayList<Citas> {
        val dbHelper = DbHelper(context)
        val db = dbHelper.getWritableDatabase()

        val listCitas = ArrayList<Citas>()
        var cita: Citas?
        var cursorCitas: Cursor? = null

        cursorCitas = db.rawQuery("SELECT * FROM $TABLE_CITAS", null)

        if (cursorCitas.moveToFirst()) {
            do {
                cita = Citas()
                cita.id = cursorCitas.getInt(0)
                cita.Consulta = cursorCitas.getString(1)
                cita.Doctor = cursorCitas.getString(2)
                cita.Paciente = cursorCitas.getString(3)
                cita.Fecha = cursorCitas.getString(4)
                cita.Hora_Inicio = cursorCitas.getString(5)
                cita.Hora_Fin = cursorCitas.getString(6)
                cita.Diagnostico = cursorCitas.getString(7)
                cita.Receta = cursorCitas.getString(8)
                cita.Pago = cursorCitas.getString(9)
                listCitas.add(cita)
            } while (cursorCitas.moveToNext())
        }

        cursorCitas.close()

        return listCitas
    }

    fun showVerb(id: Int): Citas? {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var cita: Citas? = null
        var cursorCitas: Cursor? = null

        cursorCitas = db.rawQuery("SELECT * FROM $TABLE_CITAS WHERE id = $id LIMIT 1", null)

        if (cursorCitas.moveToFirst()) {
            cita = Citas()
            cita.id = cursorCitas.getInt(0)
            cita.Consulta = cursorCitas.getString(1)
            cita.Doctor = cursorCitas.getString(2)
            cita.Paciente = cursorCitas.getString(3)
            cita.Fecha = cursorCitas.getString(4)
            cita.Hora_Inicio = cursorCitas.getString(5)
            cita.Hora_Fin = cursorCitas.getString(6)
            cita.Diagnostico = cursorCitas.getString(7)
            cita.Receta = cursorCitas.getString(8)
            cita.Pago = cursorCitas.getString(9)
        }

        cursorCitas.close()

        return cita
    }

    fun editCita(id: Int, Consulta: String, Doctor: String, Paciente: String, Fecha: String, Hora_Inicio: String, Hora_Fin: String, Diagnostico: String, Receta: String, Pago: String): Boolean {
        var correcto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.getWritableDatabase()

        try {
            val values = ContentValues()
            values.put("Consulta", Consulta)
            values.put("Doctor", Doctor)
            values.put("Paciente", Paciente)
            values.put("Fecha", Fecha)
            values.put("Hora_Inicio", Hora_Inicio)
            values.put("Hora_Fin", Hora_Fin)
            values.put("Diagnostico", Diagnostico)
            values.put("Receta", Receta)
            values.put("Pago", Pago)

            db.update(TABLE_CITAS, values, "id = ?", arrayOf(id.toString()))
            correcto = true
        } catch (ex: Exception) {
            ex.toString()
            correcto = false
        } finally {
            db.close()
        }

        return correcto
    }

    fun deleteCita(id: Int): Boolean {
        var correcto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_CITAS WHERE id = '$id'")
            correcto = true
        } catch (ex: Exception) {
            ex.toString()
            correcto = false
        } finally {
            db.close()
        }

        return correcto
    }




}