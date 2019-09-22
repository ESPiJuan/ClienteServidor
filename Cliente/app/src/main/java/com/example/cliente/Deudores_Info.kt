package com.example.cliente

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_deudores__info.*

class Deudores_Info : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deudores__info)
        setSupportActionBar(toolbar)


        val Nombre = intent.getStringExtra("Nombre")

        val Deuda = intent.getStringExtra("Deuda")
        val deudorNombreInfoTextView= findViewById<TextView>(R.id.deudorNombreInfoTextView)
        val deudorDeudaInfoTextView= findViewById<TextView>(R.id.deudorDeudaInfoTextView)

        deudorNombreInfoTextView.text = Nombre
        deudorDeudaInfoTextView.text = Deuda


    }

}
