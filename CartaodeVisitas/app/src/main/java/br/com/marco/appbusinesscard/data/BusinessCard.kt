package br.com.marco.appbusinesscard.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val nome:String,
    val especialidade:String,
    val senioridade: String,
    val telefone: String,
    val email: String,
    val fundoPersonalizado: String,
    val habilidade1: Int,
    val habilidade2: Int
)