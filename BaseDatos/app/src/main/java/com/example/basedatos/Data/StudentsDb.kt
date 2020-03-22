package com.example.basedatos.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.basedatos.Entity.StudentsEntity

class StudentsDb {
    private var connectionDb:ConnectionDb
    private lateinit var sqLiteDatabase:SQLiteDatabase

    constructor(context: Context){
        connectionDb = ConnectionDb(context)
    }

    companion object{
        const val ID = "Id"
        const val NAME = "Name"
        const val LASTNAME = "Lastname"
        const val GENDER = "Gender"
        const val BIRTHDAY = "Birthday"
        private  val listString = arrayListOf<String>()
        val listStringIDS = arrayListOf<String>()
    }

    fun studentAdd(student: StudentsEntity):Long{
        sqLiteDatabase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NAME,student.name)
        values.put(LASTNAME,student.lastName)
        values.put(GENDER,student.gender)
        values.put(BIRTHDAY,student.birthday)

        return sqLiteDatabase.insert(ConnectionDb.TABLE_NAME_STUDENTS,null,values)
    }

    fun studentEdit(student: StudentsEntity):Int {
        sqLiteDatabase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NAME,student.name)
        values.put(LASTNAME,student.lastName)
        values.put(GENDER,student.gender)
        values.put(BIRTHDAY,student.birthday)
        var selection = "Id=?"
        var args = arrayOf(student.id.toString())

        return sqLiteDatabase.update(ConnectionDb.TABLE_NAME_STUDENTS,values,selection,args)
    }

    fun studentDelete(idStudent:Int):Int {
        sqLiteDatabase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        var selection = "Id=?"
        var args = arrayOf(idStudent.toString())
        //sqLiteDatabase.delete(ConnectionDb.TABLE_NAME_STUDENTS,selection,args)
        return sqLiteDatabase.delete(ConnectionDb.TABLE_NAME_STUDENTS,selection,args)
    }

    fun studentsGetAll(){
        sqLiteDatabase = connectionDb.openConnection(ConnectionDb.MODE_READ)

        val fields = arrayOf(ID, NAME, LASTNAME, GENDER, BIRTHDAY)

        val cursor = sqLiteDatabase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do{
                Log.d("UDELP", "${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}")
            }while (cursor.moveToNext())
        }
    }

    fun studentsGetAllString(): Array<String>{
        listString.clear()
        listStringIDS.clear()
        sqLiteDatabase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NAME, LASTNAME, GENDER, BIRTHDAY)

        val cursor = sqLiteDatabase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                listString.add("${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}")
                Log.d("UDELP","${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}")
                listStringIDS.add("${cursor.getInt(0)} ")
            }while (cursor.moveToNext())

        }
        val elems = listString
        return elems.toTypedArray()
    }

    fun studentsGetOne(idStudent:Int):StudentsEntity{
        sqLiteDatabase = connectionDb.openConnection(ConnectionDb.MODE_READ)

        val fields = arrayOf(ID, NAME, LASTNAME, GENDER, BIRTHDAY)
        var selection = "Id=?"
        var args = arrayOf(idStudent.toString())
        val cursor = sqLiteDatabase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,selection,args,null,null,null)
        var student = StudentsEntity()

        if(cursor.moveToFirst()){
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var lastname = cursor.getString(2)
            var gender = cursor.getInt(3)
            var birthday = cursor.getString(4)

            student = StudentsEntity(id, name, lastname, gender, birthday)

            Log.d("UDELP", "${cursor.getInt(0)} ${cursor.getString(1)} ${cursor.getString(2)} ${cursor.getInt(3)} ${cursor.getString(4)}")
        }
        return student
    }



}