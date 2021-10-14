package mx.tec.bamx.ListViews

import android.content.Context
import android.content.Intent
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
import kotlinx.android.synthetic.main.tiendas_pendientes.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.icon_salir
import kotlinx.android.synthetic.main.toolbarnoflecha.*
import mx.tec.bamx.R
import mx.tec.bamx.RegistrarDonativo
import org.json.JSONObject

class TiendasPendientes : AppCompatActivity() {
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tiendas_pendientes)

        var user: Int = 48


        val queue = Volley.newRequestQueue(this@TiendasPendientes)
        val url = "http://192.168.0.11:5000/operator/tiendas-pendientes/${user}"
        val datos = mutableListOf<Operador>()



        val listener = Response.Listener<JSONObject>{ response ->
            //Log.e("RESPONSE", response.toString())
            val array = response.getJSONArray("data")
            for(i in 0 until array.length()) {
               datos.add(
                   Operador(array.getJSONObject(i).getString("nombre"),
                       array.getJSONObject(i).getString("direccion"),
                       array.getJSONObject(i).getString("dia"),
                       array.getJSONObject(i).getInt("id"),
                       R.drawable.walmart
                   )
               )
            }
            val adaptador = Adapter(this@TiendasPendientes,
                R.layout.lst_tienda,
                datos
            )
            lstStore.adapter = adaptador
        }


        val card1 = findViewById<CardView>(R.id.CardAlmacenT)

        val sharedPreferences = getSharedPreferences("login",
            Context.MODE_PRIVATE)
        val usuario = sharedPreferences.getString("usuario", "@")



        lstStore.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@TiendasPendientes, RegistrarDonativo::class.java)
            intent.putExtra("nombre", datos[position].name)
            intent.putExtra("ubicacion", datos[position].location)
            intent.putExtra("id", datos[position].id.toString())
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        card1.setOnClickListener{
            val intent = Intent(this@TiendasPendientes, EntregasPendientes::class.java)
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
                    this.finish()
                }
                .show()
        }
    }
}