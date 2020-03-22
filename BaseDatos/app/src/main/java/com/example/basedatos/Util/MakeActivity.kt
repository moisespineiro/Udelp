package com.example.basedatos.Util

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Entity.StudentsEntity
import com.example.basedatos.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_make.*
import java.util.*


class MakeActivity : AppCompatActivity(){

    val studentsDb = StudentsDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        edtCumpleanos.setOnClickListener {

            val dpd = DatePickerDialog(this@MakeActivity,DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val mes = (monthOfYear + 1);
                    edtCumpleanos.setText("" + year + "-" + mes.toString() + "-" + dayOfMonth)
                }, year, month, day)
            dpd.show()
        }

        btnEnviar.setOnClickListener {

            if (edtNombreEstudiante.text.toString().trim().isNotEmpty()){
                var name = edtNombreEstudiante.text.toString()
                if (edtApellidoEstudianteAlta.text.toString().trim().isNotEmpty()){
                    var lastname = edtApellidoEstudianteAlta.text.toString()

                    val selectedGender = rdgGenero.checkedRadioButtonId
                    var gender = 0

                    if (selectedGender != -1) {
                        when (selectedGender) {
                            rdbMale.id -> {
                                gender = 1 //male

                            }
                            rdbFemale.id -> {
                                gender = 2 //female
                            }
                        }
                        var birthday = edtCumpleanos.text.toString()
                        val student = StudentsEntity(-1, name, lastname, gender, birthday)
                        var id = studentsDb.studentAdd(student)
                        Toast.makeText(this@MakeActivity,"Guardado $id", Toast.LENGTH_SHORT).show()

                        edtNombreEstudiante.text.clear()
                        edtApellidoEstudianteAlta.text.clear()
                        rdgGenero.clearCheck()
                        edtCumpleanos.text.clear()

                    }else{
                        Snackbar.make(it, "Selecciona el genero", Snackbar.LENGTH_SHORT).show()
                    }
                }else{
                    Snackbar.make(it, "Por favor ingresa el apellido", Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(it, "Por favor ingresa el nombre", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}