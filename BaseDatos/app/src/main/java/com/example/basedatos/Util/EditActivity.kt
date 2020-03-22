package com.example.basedatos.Util

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Entity.StudentsEntity
import com.example.basedatos.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    val studentsDb = StudentsDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        var student = StudentsEntity()
        val id = intent.extras?.getString("ID")
        var idEdit = -1

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        edtCumpleanosEditar.setOnClickListener {

            val dpd = DatePickerDialog(this@EditActivity,DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val mes = (monthOfYear + 1);
                edtCumpleanosEditar.setText("" + year + "-" + mes.toString() + "-" + dayOfMonth)
            }, year, month, day)
            dpd.show()
        }


        if (id != null) {
            student = studentsDb.studentsGetOne(id.toInt())
            idEdit = id.toInt()
        }

        txvNombreEditar.setText("${student.name.toString()}")

        txvApellidoEditar.setText("${student.lastName.toString()} ")

        btnEditar.setOnClickListener {

            val selectedGender = rdgGenero.checkedRadioButtonId
            var gender = 0

            if (selectedGender != -1) {
                when (selectedGender) {
                    rdbMale.id -> {
                        gender = 1 //male
                        //student.gender = gender

                    }
                    rdbFemale.id -> {
                        gender = 2 //female
                        //student.gender = gender
                    }
                }
                if (edtCumpleanosEditar.text.toString().trim().isNotEmpty()) {
                    var birthday = edtCumpleanosEditar.text.toString()
                    //student.birthday= birthday

                    var values = StudentsEntity(idEdit,student.name,student.lastName,gender,birthday)
                    var savedId = studentsDb.studentEdit(values)
                    Toast.makeText(this@EditActivity,"Guardado $savedId", Toast.LENGTH_SHORT).show()
                }else {
                    Snackbar.make(it,"Selecciona fecha de cumpleaños",Snackbar.LENGTH_LONG).show()
                }
            }else{
                Snackbar.make(it, "Selecciona el genero", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}