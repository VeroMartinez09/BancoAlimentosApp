package mx.tec.bamx.ListViews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.tiendas_pendientes.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.icon_salir
import kotlinx.android.synthetic.main.toolbarnoflecha.*
import mx.tec.bamx.LogIn
import mx.tec.bamx.OperadorRegistro.DetalleEntrega
import mx.tec.bamx.R
import org.json.JSONObject

class EntregasPendientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences2 = getSharedPreferences("login",
            MODE_PRIVATE
        )
        val idOperador = sharedPreferences2.getString("idUser", "@")


        setContentView(R.layout.entregas_pendientes)

        NoHay.visibility = View.GONE
        val lstOperador = findViewById<ListView>(R.id.LstAlmacen)

        val card1 = findViewById<CardView>(R.id.CardEntregaP)

        val queue = Volley.newRequestQueue(this@EntregasPendientes)
        val url = "http://192.168.3.100:5000/operator/proximas-entregas/${idOperador}"
        val datos = mutableListOf<Entregas>()



        val listener = Response.Listener<JSONObject>{ response ->
            //Log.e("RESPONSE", response.toString())
            val array = response.getJSONArray("data")
            if (array.isNull(0)) {
                lstOperador.visibility = View.GONE
                NoHay.visibility = View.VISIBLE
            } else {
                NoHay.visibility = View.GONE
                lstOperador.visibility = View.VISIBLE
                for(i in 0 until array.length()) {
                    datos.add(
                        Entregas(array.getJSONObject(i).getString("nombre"),
                            R.drawable.almacen,
                            array.getJSONObject(i).getString("direccion"),
                            array.getJSONObject(i).getString("idBodega"),
                        )
                    )
                }
            }
            val adaptador = AdapterAlmacen(this@EntregasPendientes,
                R.layout.lst_tienda,
                datos
            )
            lstOperador.adapter = adaptador
        }


        lstOperador.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@EntregasPendientes, DetalleEntrega::class.java)
            intent.putExtra("operario", datos[position].name)
            intent.putExtra("bodega", datos[position].status)


            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        card1.setOnClickListener{
            val intent = Intent(this@EntregasPendientes, TiendasPendientes::class.java)
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