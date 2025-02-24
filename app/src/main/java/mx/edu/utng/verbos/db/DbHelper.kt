package mx.edu.utng.verbos.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NOMBRE, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NOMBRE = "desafio3.db"
        const val TABLE_VERBOS = "t_verbos"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE $TABLE_VERBOS (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Verbo TEXT NOT NULL," +
                    "Ind_Yo TEXT NOT NULL," +
                    "Ind_Tu TEXT NOT NULL," +
                    "Ind_El_Ella_Usted TEXT NOT NULL," +
                    "Ind_Nosotros TEXT NOT NULL," +
                    "Ind_Vosotros TEXT NOT NULL," +
                    "Ind_Ellos TEXT NOT NULL," +
                    "Imp_Tu TEXT NOT NULL," +
                    "Imp_Nosotros TEXT NOT NULL," +
                    "Imp_Ellos_Ellas_Ustedes TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_VERBOS")
        onCreate(sqLiteDatabase)
    }
}