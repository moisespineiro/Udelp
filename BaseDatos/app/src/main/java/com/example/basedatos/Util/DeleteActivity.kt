package com.example.basedatos.Util

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.R
import kotlinx.android.synthetic.main.activity_delete.*

class DeleteActivity : AppCompatActivity(){

    val studentsDb = StudentsDb(this@DeleteActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        if( studentsDb.studentsGetAllString().size >0){
            val miAdaptador = ArrayAdapter<String> (this@DeleteActivity, android.R.layout.simple_list_item_1,studentsDb.studentsGetAllString())

            ltvBorrarEstudiante.adapter=miAdaptador

            ltvBorrarEstudiante.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                var studentId =  StudentsDb.listStringIDS[id.toInt()]
                Toast.makeText(this@DeleteActivity,"Seleccionaste el $studentId ",Toast.LENGTH_SHORT).show()
                miDialogo("Eliminar a ${studentId.trim().toString()}",studentId.trim().toInt()).show()
            }
        }
    }

    private fun miDialogo(texto:String , id:Int): AlertDialog {
        val miAlerta = AlertDialog.Builder(this@DeleteActivity)
        miAlerta.setTitle("Estar seguro que deseas eliminar?").setMessage(texto)
        miAlerta.setPositiveButton("SI") { dialog, which ->
            studentsDb.studentDelete(id)
            Toast.makeText(this@DeleteActivity, "Eliminado", Toast.LENGTH_SHORT).show()
            finish();
            startActivity(intent);
        }

        miAlerta.setNegativeButton("NO") { dialog, which ->
            Toast.makeText(this@DeleteActivity, "Cancelado", Toast.LENGTH_SHORT).show()
        }
        return miAlerta.create()
    }
}