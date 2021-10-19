package mx.tec.bamx.ListViews

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.donativo_espontaneo.*
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.tiendas_pendientes.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.icon_salir
import kotlinx.android.synthetic.main.toolbarnoflecha.*
import mx.tec.bamx.LogIn
import mx.tec.bamx.R
import mx.tec.bamx.RegistrarDonativo
import mx.tec.bamx.RegistrarDonativoEspontaneo
import org.json.JSONObject

class TPEspontaneas : AppCompatActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
    lateinit var sharedPreferences: SharedPreferences

    super.onCreate(savedInstanceState)
        setContentView(R.layout.donativo_espontaneo)

        sharedPreferences = getSharedPreferences("donativo_espontaneo",
           Context.MODE_PRIVATE)

        val sharedPreferences2 = getSharedPreferences("login",
            MODE_PRIVATE
        )
        val idOperador = sharedPreferences2.getString("idUser", "@")

        val queue = Volley.newRequestQueue(this@TPEspontaneas)
        val url = "http://192.168.0.8:5000/operator/tiendas-espontaneas/${idOperador}"
        val datos = mutableListOf<Espontanea>()

        val listener = Response.Listener<JSONObject>{ response ->
            //Log.e("RESPONSE", response.toString())
            val array = response.getJSONArray("data")
            for(i in 0 until array.length()) {
                datos.add(
                    Espontanea(array.getJSONObject(i).getString("nombre"),
                        array.getJSONObject(i).getString("direccion"),
                        array.getJSONObject(i).getInt("id"),
                        R.drawable.walmart
                    )
                )
            }
            val adaptador = AdapterEspontanea(this@TPEspontaneas,
                R.layout.lst_tienda,
                datos
            )
            lstEspontaneo.adapter = adaptador
        }

    val card2 = findViewById<CardView>(R.id.CardEntregaES1)
    val card1 = findViewById<CardView>(R.id.CardAlmacenES2)

        card1.setOnClickListener{
            val intent = Intent(this@TPEspontaneas, EntregasPendientes::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        card2.setOnClickListener{
            val intent = Intent(this@TPEspontaneas, TiendasPendientes::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    lstEspontaneo.setOnItemClickListener { parent, view, position, id ->
        val intent = Intent(this@TPEspontaneas, RegistrarDonativoEspontaneo::class.java)
        with(sharedPreferences.edit()){
            putString("strName", datos[position].name)
            putString("strLocation", datos[position].location)
            putString("strId", datos[position].id.toString())
            commit()
        }
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }



        icon_salir.setOnClickListener {
            logout()
        }

        val error = Response.ErrorListener { error ->
            Log.e("ERROR", error.message!!)
        }
        val peticion = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            listener,
            error
        )
        queue.add(peticion)

    }

    fun logout() {
        icon_salir.setOnClickListener{
            println("DISTE CLICK BRO")

            val sharedPreferences = getSharedPreferences("login",
                Context.MODE_PRIVATE)

            MaterialAlertDialogBuilder(this)
                .setCancelable(false)
                .setTitle(resources.getString(R.string.tituloS))
                .setMessage(resources.getString(R.string.mensajeS))
                .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.si)) { dialog, which ->

                    with(sharedPreferences.edit()){
                        remove("usuario")
                        commit()
                    }
                    val intent = Intent(this, LogIn::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                .show()
        }
    }
}