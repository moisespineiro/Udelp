package com.example.basedatos.Util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Entity.StudentsEntity
import com.example.basedatos.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    val studentsDb = StudentsDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.extras?.getString("ID")

        var student = StudentsEntity()
        if (id != null) {
            student = studentsDb.studentsGetOne(id.toInt())
        }

        txvNumeroEstudiante.setText("${student.id.toString()}")

        txvNombreEstudiante.setText("${student.name.toString()}")

        txvApellidoEstudiante.setText("${student.lastName.toString()} ")

        when(student.gender){
            1->{
                txvGenero.setText("Male")
            }
            2->{
                txvGenero.setText("Female")
            }

        }

        txvCumpleanos.setText(student.birthday)

    }
}