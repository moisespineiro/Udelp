package com.example.encuestas.Util

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestas.Data.ListaEncuestas
import com.example.encuestas.R
import kotlinx.android.synthetic.main.activity_delete.*

class DeleteActivity : AppCompatActivity(){
    var listaEncuestas = ListaEncuestas()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        if (listaEncuestas.devuelveListEncuesta().size > 0) {

            val miAdaptador = ArrayAdapter<String>(this@DeleteActivity, android.R.layout.simple_list_item_1, listaEncuestas.devuelveListEncuestaString())
            ltvEncuestas.adapter = miAdaptador

            ltvEncuestas.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val itemSeleccionado = adapterView.getItemAtPosition(position)
                Toast.makeText(this@DeleteActivity,"$position $id $itemSeleccionado",Toast.LENGTH_SHORT).show()
                miDialogo("Eliminar a $itemSeleccionado",id.toInt()).show()
            }
        }
    }

    private fun miDialogo(texto:String , id:Int): AlertDialog {
        val miAlerta = AlertDialog.Builder(this@DeleteActivity)
        miAlerta.setTitle("Estar seguro que deseas eliminar?").setMessage(texto)
        miAlerta.setPositiveButton("SI") { dialog, which ->
            listaEncuestas.eliminarEncuesta(id)
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