package com.example.monedero.models

//aunque en la api value es de tipo number, aqui en kotlin lo queremos ocmo double
data class Coin (var name: String, var value: Double)
