package com.example.cliente

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*

import kotlinx.android.synthetic.main.activity_consuta.*
import kotlinx.android.synthetic.main.content_consuta.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.log

class Consulta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consuta)
        setSupportActionBar(toolbar)

        val consultarButtonAction = findViewById<Button>(R.id.consultarButton)
        consultarButtonAction.setOnClickListener {

            val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

            val network = BasicNetwork(HurlStack())

            val requestQueue = RequestQueue(cache, network).apply {
                start()
            }

            val url = "http://192.168.0.169:40/datosclientes"
            var found = false
            var  Deuda=""
            var auxNombre = nombreEditText.text.toString().toLowerCase()
            val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener<JSONArray> { response ->
                    for (i in 0 until response.length()) {
                        var  Nombre = response.getJSONObject(i).getString("Nombre").toLowerCase()

                        if(Nombre.equals(auxNombre)){
                            found = true
                            Deuda = response.getJSONObject(i).getString("Deuda")

                        }
                    }

                    var deudores_Info = Intent(this, Deudores_Info::class.java)
                    if (found && !auxNombre.isNullOrEmpty()){
                        deudores_Info.putExtra("Nombre" ,auxNombre)
                        deudores_Info.putExtra("Deuda" ,Deuda)
                        found = false

                        startActivity(deudores_Info)}else{

                        Toast.makeText(this,"Datos no existentes",Toast.LENGTH_SHORT).show()
                    }

                },
                Response.ErrorListener { _ ->
                   Toast.makeText(this,"Error en el servidor",Toast.LENGTH_SHORT).show()
                })
            requestQueue.add(jsonObjectRequest)


        }


    }

}
