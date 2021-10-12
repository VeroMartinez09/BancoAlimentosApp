package mx.tec.bamx.ListViews

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.tiendas_pendientes.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.icon_salir
import kotlinx.android.synthetic.main.toolbarnoflecha.*
import mx.tec.bamx.DetalleEntrega
import mx.tec.bamx.R
import mx.tec.bamx.RegistrarDonativo

class TiendasPendientes : AppCompatActivity() {
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tiendas_pendientes)

/*
        var queue = Volley.newRequestQueue(this@TiendasPendientes)
        val url = "http://192.168.0.11:5000/operator/tiendas-pendientes/48"

        val listaNombre = mutableListOf<String>() // mutableListOf para lista dinámica
        val listaDireccion = mutableListOf<String>() // mutableListOf para lista dinámica
        val listaListaStatus = mutableListOf<String>() // mutableListOf para lista dinámica


        val listener = Response.Listener<JSONObject>{ response ->
            //Log.e("RESPONSE", response.toString())
            val array = response.getJSONArray("data")
            for(i in 0 until array.length()) {
                listaNombre.add(
                    array.getJSONObject(i).getString("nombre")
                )
                listaDireccion.add(
                    array.getJSONObject(i).getString("direccion")
                )
                listaListaStatus.add(
                    array.getJSONObject(i).getString("dia")
                )
            }
        }*/



        val lstOperador = findViewById<ListView>(R.id.lstStore)
        val card1 = findViewById<CardView>(R.id.CardAlmacenT)

        val sharedPreferences = getSharedPreferences("login",
            Context.MODE_PRIVATE)
        val usuario = sharedPreferences.getString("usuario", "@")

        val datos = listOf(
            Operador("Walmart", R.drawable.walmart, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("Oxxo", R.drawable.oxxo, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("Sanborns", R.drawable.sanborns, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("El globo", R.drawable.elglobo, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),

            )
        val adaptador = Adapter(this@TiendasPendientes,
            R.layout.lst_tienda,
            datos
        )

        lstOperador.adapter = adaptador

        lstOperador.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@TiendasPendientes, RegistrarDonativo::class.java)
            intent.putExtra("nombre", datos[position].name)
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