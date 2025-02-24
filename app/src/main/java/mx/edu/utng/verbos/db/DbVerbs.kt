package mx.edu.utng.verbos.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.annotation.Nullable
//import mx.edu.utng.verbos.DbHelper
import mx.edu.utng.verbos.entidades.Verbs
import java.util.ArrayList

class DbVerbs(@Nullable context: Context?) : DbHelper(context) {

    private val context: Context

    init {
        this.context = context!!
    }

    fun InsertV(
        Verbo: String,
        Ind_Yo: String,
        Ind_Tu: String,
        Ind_El_Ella_Usted: String,
        Ind_Nosotros: String,
        Ind_Vosostros: String,
        Ind_Ellos: String,
        Imp_Tu: String,
        Imp_Nosotros: String,
        Imp_Ellos_Ellas_Ustedes: String
    ): Long {
        var id: Long = 0

        try {
            val dbHelper = DbHelper(context)
            val db = dbHelper.getWritableDatabase()

            val values = ContentValues()
            values.put("Verbo",Verbo)
            values.put("Ind_Yo", Ind_Yo)
            values.put("Ind_Tu", Ind_Tu)
            values.put("Ind_El_Ella_Usted", Ind_El_Ella_Usted)
            values.put("Ind_Nosotros", Ind_Nosotros)
            values.put("Ind_Vosotros", Ind_Vosostros)
            values.put("Ind_Ellos", Ind_Ellos)
            values.put("Ind_Tu", Ind_Tu)
            values.put("Imp_Tu", Imp_Tu)
            values.put("Imp_Nosotros", Imp_Nosotros)
            values.put("Imp_Ellos_Ellas_Ustedes", Imp_Ellos_Ellas_Ustedes)

            id = db.insert(TABLE_VERBOS, null, values)
        } catch (ex: Exception) {
            ex.toString()
        }

        return id
    }

    fun showVerbs(): ArrayList<Verbs> {
        val dbHelper = DbHelper(context)
        val db = dbHelper.getWritableDatabase()

        val listVerbs = ArrayList<Verbs>()
        var verb: Verbs?
        var cursorVerbs: Cursor? = null

        cursorVerbs = db.rawQuery("SELECT * FROM $TABLE_VERBOS", null)

        if (cursorVerbs.moveToFirst()) {
            do {
                verb = Verbs()
                verb.id = cursorVerbs.getInt(0)
                verb.Verbo = cursorVerbs.getString(1)
                verb.Ind_Yo = cursorVerbs.getString(2)
                verb.Ind_Tu = cursorVerbs.getString(3)
                verb.Ind_El_Ella_Usted = cursorVerbs.getString(4)
                verb.Ind_Nosotros = cursorVerbs.getString(5)
                verb.Ind_Vosotros = cursorVerbs.getString(6)
                verb.Ind_Ellos = cursorVerbs.getString(7)
                verb.Imp_Tu = cursorVerbs.getString(8)
                verb.Imp_Nosotros = cursorVerbs.getString(9)
                verb.Imp_Ellos_Ellas_Ustedes = cursorVerbs.getString(10)
                listVerbs.add(verb)
            } while (cursorVerbs.moveToNext())
        }

        cursorVerbs.close()

        return listVerbs
    }

    fun showVerb(id: Int): Verbs? {
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var verb: Verbs? = null
        var cursorVerbs: Cursor? = null

        cursorVerbs = db.rawQuery("SELECT * FROM $TABLE_VERBOS WHERE id = $id LIMIT 1", null)

        if (cursorVerbs.moveToFirst()) {
            verb = Verbs()
            verb.id = cursorVerbs.getInt(0)
            verb.Verbo = cursorVerbs.getString(1)
            verb.Ind_Yo = cursorVerbs.getString(2)
            verb.Ind_Tu = cursorVerbs.getString(3)
            verb.Ind_El_Ella_Usted = cursorVerbs.getString(4)
            verb.Ind_Nosotros = cursorVerbs.getString(5)
            verb.Ind_Vosotros = cursorVerbs.getString(6)
            verb.Ind_Ellos = cursorVerbs.getString(7)
            verb.Imp_Tu = cursorVerbs.getString(8)
            verb.Imp_Nosotros = cursorVerbs.getString(9)
            verb.Imp_Ellos_Ellas_Ustedes = cursorVerbs.getString(10)
        }

        cursorVerbs.close()

        return verb
    }

    fun editVerb(id: Int, Verbo: String, Ind_Yo: String, Ind_Tu: String, Ind_El_Ella_Usted: String, Ind_Nosotros: String, Ind_Vosotros: String, Ind_Ellos: String, Imp_Tu: String, Imp_Nosotros: String, Imp_Ellos_Ellas_Ustedes: String): Boolean {
        var correcto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.getWritableDatabase()

        try {
            val values = ContentValues()
            values.put("Verbo", Verbo)
            values.put("Ind_Yo", Ind_Yo)
            values.put("Ind_Tu", Ind_Tu)
            values.put("Ind_El_Ella_Usted", Ind_El_Ella_Usted)
            values.put("Ind_Nosotros", Ind_Nosotros)
            values.put("Ind_Vosotros", Ind_Vosotros)
            values.put("Ind_Ellos", Ind_Ellos)
            values.put("Imp_Tu", Imp_Tu)
            values.put("Imp_Nosotros", Imp_Nosotros)
            values.put("Imp_Ellos_Ellas_Ustedes", Imp_Ellos_Ellas_Ustedes)

            db.update(TABLE_VERBOS, values, "id = ?", arrayOf(id.toString()))
            correcto = true
        } catch (ex: Exception) {
            ex.toString()
            correcto = false
        } finally {
            db.close()
        }

        return correcto
    }

    fun deleteVerb(id: Int): Boolean {
        var correcto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_VERBOS WHERE id = '$id'")
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