package mx.edu.utng.verbos

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import mx.edu.utng.verbos.adapters.ListCitasAdapter
import mx.edu.utng.verbos.db.DbCitas
import mx.edu.utng.verbos.entidades.Citas

import java.util.ArrayList

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var txtBuscar: SearchView
    private lateinit var listCitas: RecyclerView
    private lateinit var listArrayCitas: ArrayList<Citas>
    private lateinit var adapter: ListCitasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Video 3
        txtBuscar = findViewById(R.id.txtSearch)
        listCitas = findViewById(R.id.listCitas)
        listCitas.layoutManager = LinearLayoutManager(this)
        val dbCitas = DbCitas(this)

        listArrayCitas = ArrayList()

        adapter = ListCitasAdapter(dbCitas.showCitas())
        listCitas.adapter = adapter

        txtBuscar.setOnQueryTextListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_s, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuNuevo -> {
                goToInsert()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun goToInsert() {
        val intent = Intent(this, InsertActivity::class.java)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        adapter.filtrado(s)
        return false
    }
}