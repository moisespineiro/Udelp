package com.example.encuestas.Entity

class EntityUsuario(
    var nombre:String,
    var apellidoPaterno:String,
    var apellidoMaterno:String,
    var telefono:String,
    var genero:String,
    var delegacion:Int,
    var direccion:String,
    var estadoCivil:Int,
    var correo:String,
    var password:String
){constructor():this("","","","","",0,"",0,"","")}