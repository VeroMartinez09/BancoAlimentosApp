package mx.tec.bamx.OperadorRegistro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.detalle_entrega.*
import kotlinx.android.synthetic.main.tiendas_pendientes.*
import mx.tec.bamx.ListViews.Adapter
import mx.tec.bamx.ListViews.EntregasPendientes
import mx.tec.bamx.ListViews.Operador
import mx.tec.bamx.R
import org.json.JSONObject

class DetalleEntrega : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_entrega)

        val queue = Volley.newRequestQueue(this@DetalleEntrega)
        val datos = mutableListOf<String>()
        val sharedPreferences2 = getSharedPreferences("login",
            MODE_PRIVATE
        )
        val idOperador = sharedPreferences2.getString("idUser", "@")



        val idB = intent.getStringExtra("bodega")
        val url ="http://192.168.0.8:5000/operator/producto-bodega/${idB}/${idOperador}"


        txtBodegaId.text = idB
        val listener = Response.Listener<JSONObject>{ response ->
            Log.e("RESPONSE", response.toString())
            val array = response.getJSONArray("data")
            for(i in 0 until array.length()) {
                txtAbarroteCant.text = array.getJSONObject(i).getInt("kg_abarrotes").toString()
                txtFrutaVCant.text = array.getJSONObject(i).getInt("kg_frutas_verduras").toString()
                txtPanCant.text = array.getJSONObject(i).getInt("kg_pan").toString()
                txtNoComerCant.text = array.getJSONObject(i).getInt("kg_no_comestibles").toString()
            }
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

/*
        txtAbarroteCant.text = datos[0]
        txtFrutaVCant.text = datos[1]
        txtPanCant.text = datos[2]
        txtNoComerCant.text = datos[3]

*/
         ic_close.setOnClickListener {
             val intent = Intent(this, EntregasPendientes::class.java)
             startActivity(intent)
         }
    }
}