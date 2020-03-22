package com.example.basedatos.Util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.R

class MainActivity : AppCompatActivity() {

    val studentsDb = StudentsDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Prueba insertar y mostrar todos
        /*var values =StudentsEntity(-1,"Yolanda", "Martínez", 0, "1986/11/16")
        var id = studentsDb.studentAdd(values)
        Log.d("UDELP","El elemento guardado es $id")

        values =StudentsEntity(-1,"Moisés", "Piñeiro", 0, "1998/05/16")
        id = studentsDb.studentAdd(values)
        Log.d("UDELP","El elemento guardado es $id")

        values =StudentsEntity(-1,"Ángel", "Estrada", 0, "1989/11/16")
        id = studentsDb.studentAdd(values)
        Log.d("UDELP","El elemento guardado es $id")

        studentsDb.studentsGetAll()*/

        //Prueba mostrar uno
        /*studentsDb.studentsGetAll()
        studentsDb.studentsGetOne(3)*/

        //Prueba borrar
        /*studentsDb.studentsGetAll()
        studentsDb.studentDelete(3)
        Log.d("UDELP", "Después de eliminar")
        studentsDb.studentsGetAll()*/


        //Prueba Editar
        /*studentsDb.studentsGetAll()
        val values = StudentsEntity(2, "Moisés", "Pineiro",1, "1998/05/19")
        studentsDb.studentEdit(values)
        Log.d("UDELP", "Después de editar")
        studentsDb.studentsGetAll()*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.itmAlta -> {
                val intent = Intent(this@MainActivity, MakeActivity::class.java)
                startActivity(intent)
            }

            R.id.itmMostrar -> {
                val intent = Intent(this@MainActivity, MadeActivity::class.java)
                startActivity(intent)
            }

            R.id.itmEditar -> {
                val intent = Intent(this@MainActivity, EditSelectActivity ::class.java)
                startActivity(intent)
            }

            R.id.itmEliminar -> {
                val intent = Intent(this@MainActivity, DeleteActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
