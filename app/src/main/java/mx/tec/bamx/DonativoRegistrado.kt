package mx.tec.bamx

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat.format
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.donativo_registrado.*
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.tec.bamx.ListViews.TiendasPendientes
import mx.tec.bamx.OperadorRegistro.DetalleDonativo
import org.json.JSONObject
import java.lang.String.format
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

class DonativoRegistrado : AppCompatActivity() {
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donativo_registrado)

        val SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        queue = Volley.newRequestQueue(this)
        val lista = mutableListOf<String?>()

        val nombre = intent.getStringExtra("nombre")
        val id = intent.getStringExtra("id")
        val puesto = intent.getStringExtra("puesto")
        val ubicacion = intent.getStringExtra("ubicacion")
        val responsable = intent.getStringExtra("responsable")
        val abarrote = intent.getStringExtra("abarrote")
        val fruta = intent.getStringExtra("fruta")
        val pan = intent.getStringExtra("pan")
        val nocomestible = intent.getStringExtra("nocomestible")
        val fecha : String = (SimpleDateFormat.format(Date())).toString()


        lista.add(id)
        lista.add(responsable)
        lista.add(puesto)
        lista.add(abarrote)
        lista.add(fruta)
        lista.add(pan)
        lista.add(nocomestible)
        lista.add(fecha)


        txtAbarrote.text = abarrote
        txtFrutaVerdura.text = fruta
        txtPan.text = pan
        txtNoComestible.text = nocomestible
        txtResponsable.text = responsable

        txtSucursal.text = nombre
        txtUbicacion.text = ubicacion
        txtFechaHora.text = SimpleDateFormat.format(Date())

        val total: Int
        total = abarrote.toString().toInt() + fruta.toString().toInt() + pan.toString().toInt() + nocomestible.toString().toInt()
        txtTotal.text = total.toString()


        btnEnviar.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setCancelable(false)
                .setTitle(resources.getString(R.string.titulo))
                .setMessage(resources.getString(R.string.mensaje))
                .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.si)) { dialog, which ->
                    // Respond to positive button press
                    CreateDonation(lista)
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage(resources.getString(R.string.ok))
                    builder.setIcon(R.drawable.checked)
                    builder.setCancelable(false)
                    builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                        //Do something
                        val intent = Intent(this@DonativoRegistrado, TiendasPendientes::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }

                    builder.show()
                }
                .show()
        }

        icon_Back.setOnClickListener{
            val intent = Intent(this, DetalleDonativo::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        btnEditar.setOnClickListener {
            val intent = Intent(this@DonativoRegistrado, RegistrarDonativo::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        icon_salir.setOnClickListener {
            logout()
        }
    }
    fun CreateDonation(lista: List<String?>) {
        val datos = JSONObject()
        datos.put("kg_abarrotes",lista.get(3) )
        datos.put("kg_frutas_verduras",lista.get(4) )
        datos.put("kg_pan", lista.get(5))
        datos.put("kg_no_comestibles",lista.get(6) )
        datos.put("fecha", "2021-10-14")
        datos.put("responsable", lista.get(1))
        datos.put("puesto_responsable",lista.get(2))
        datos.put("idOperador", 48)
        datos.put("idTienda", lista.get(0))


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            "http://192.168.0.11:5000/operator/registrar-donativo",
            datos,
            { response ->
                Log.e("VOLLEYRESPONSE", response.toString())
            },
            { error ->
                Log.e("VOLLEYRESPONSE", error.message!!)
            }
        )
        queue.add(jsonObjectRequest)
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