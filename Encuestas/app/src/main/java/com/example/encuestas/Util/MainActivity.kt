package com.example.encuestas.Util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.encuestas.Data.ListaUsuarios
import com.example.encuestas.Data.ListaUsuarios.Companion.usuario_registrado
import com.example.encuestas.Entity.EntityUsuario
import com.example.encuestas.R
import kotlinx.android.synthetic.main.activity_authentication.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var user = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = intent.extras?.getString("usuario").toString()
        //txvNombreAutenticado.setText("$user")
        //intent.putExtra("usuario", "$user");
        txvNombreAutenticado.setText(usuario_registrado.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.itmRealizar -> {
                val intent = Intent(this@MainActivity, MakeActivity::class.java)
                startActivity(intent)
            }

            R.id.itmRealizadas -> {
                val intent = Intent(this@MainActivity, MadeActivity::class.java)
                startActivity(intent)
            }

            R.id.itmEliminar -> {
                val intent = Intent(this@MainActivity, DeleteActivity::class.java)
                startActivity(intent)
            }

            R.id.itmEditar -> {
                val intent = Intent(this@MainActivity, SelectEditActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
