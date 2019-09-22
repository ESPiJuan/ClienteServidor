package com.example.cliente

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*

import kotlinx.android.synthetic.main.activity_agregar_deudor.*
import kotlinx.android.synthetic.main.content_agregar_deudor.*
import org.json.JSONArray
import org.json.JSONObject

class agregarDeudor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_deudor)
        setSupportActionBar(toolbar)




        val enviarButtonAction = findViewById<Button>(R.id.enviarButton)
        enviarButtonAction.setOnClickListener {
            val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
            val network = BasicNetwork(HurlStack())

            val requestQueue = RequestQueue(cache, network).apply {
                start()
            }

            var nuevoNombre = nuevoNombreEditText.text.toString()

            var nuevaDeuda = deudaNuevaEdit.text.toString()

            val jsonObject = JSONObject()

            jsonObject.put("Nombre",nuevoNombre)
            jsonObject.put("Deuda",nuevaDeuda)

            val url = "http://192.168.0.169:40/crearcliente"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,jsonObject,Response.Listener
            { _ ->
                Toast.makeText(this,"Datos creados correctamente", Toast.LENGTH_SHORT).show()
            },Response.ErrorListener { _ ->
                Toast.makeText(this,"No se ha podido hacer el creado de datos",Toast.LENGTH_SHORT).show()
            })

            requestQueue.add(jsonObjectRequest)
        }
    }

}
