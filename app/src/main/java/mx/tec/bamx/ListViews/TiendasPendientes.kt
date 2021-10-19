package mx.tec.bamx.ListViews

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.tiendas_pendientes.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.icon_salir
import kotlinx.android.synthetic.main.toolbarnoflecha.*
import mx.tec.bamx.LogIn
import mx.tec.bamx.R
import mx.tec.bamx.RegistrarDonativo
import org.json.JSONArray
import org.json.JSONObject

class TiendasPendientes : AppCompatActivity(), LocationListener {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var locationManager: LocationManager
    lateinit var txtUbi : TextView
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tiendas_pendientes)

        locationManager =  getSystemService(Context.LOCATION_SERVICE) as LocationManager
        txtUbi = findViewById(R.id.txtUbi)
        checkPermissions(this)

        sharedPreferences = getSharedPreferences("donativo",
            MODE_PRIVATE
        )

        val sharedPreferences2 = getSharedPreferences("login",
            MODE_PRIVATE
        )
        val idOperador = sharedPreferences2.getString("idUser", "@")
        println(idOperador)

        val queue = Volley.newRequestQueue(this@TiendasPendientes)
        val url = "http://192.168.0.8:5000/operator/tiendas-pendientes/${idOperador}"
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

        lstStore.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@TiendasPendientes, RegistrarDonativo::class.java)
            with(sharedPreferences.edit()){
                putString("strName", datos[position].name)
                putString("strLocation", datos[position].location)
                putString("strId", datos[position].id.toString())
                commit()
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        val url2 = "http://192.168.0.8:5000/operator/tiendas-espontaneas/${idOperador}"

        val listener2 = Response.Listener<JSONObject> { response ->
            val array = response.getJSONArray("data")
            if (array.isNull(0)) {
                btnEnspontaneas.visibility = View.GONE
            } else {
                btnEnspontaneas.visibility = View.VISIBLE
            }
        }
        val peticion2 = JsonObjectRequest(
            Request.Method.GET,
            url2,
            null,
            listener2,
            { error -> Log.e("ERROR", error.message!!) }
        )

        queue.add(peticion2)


        btnEnspontaneas.setOnClickListener{
            val intent = Intent(this@TiendasPendientes, TPEspontaneas::class.java)
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
        //update(0,0)
    }

    override fun onLocationChanged(p0: Location) {
        //txtUbi.text = "${p0.latitude.toString()} "+" ${p0.longitude.toString()}"
        //update()
        update(p0.latitude.toString(), p0.longitude.toString())
    }

    fun logout() {
        icon_salir.setOnClickListener{
            println("DISTE CLICK BRO")

            val sharedPreferences = getSharedPreferences("login",
                MODE_PRIVATE
            )

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

    private fun checkPermissions(context: Activity){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED){
            //Si el permiso no se concedió, explicar al usuario porque se ocupa
            if(ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_FINE_LOCATION)){
                val builder = AlertDialog.Builder(context)
                builder.setMessage("El acceso a la ubicación se requiere por...")
                    .setTitle("Permiso de ubicación requerido")
                builder.setPositiveButton("OK"){ dialog, id ->
                    ActivityCompat.requestPermissions(context,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 45)
                }
                val dialogo = builder.create()
                dialogo.show()
            }else{
                // Si no se necesita mostrar una explicación al usuario
                ActivityCompat.requestPermissions(context,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 45)
            }
        }else{
            //El permiso se concedió, se pide la localización
            // 1. Proveedor: GPS
            // 2. Tiempo de actualización: en milisegundos (10000)
            // 3. Distancia en metros: 5m flotante -> 5f
            // 4. Contexto (this@MainActivity)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,
                1f,
                this)
        }

    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            45 -> {
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    // ¿Qué hacer? Cerrar la aplicación? Volver a preguntar?
                }else{
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        1000,
                        1f,
                        this)
                }
            }
        }
    }
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        //super.onStatusChanged(provider, status, extras)
    }


    fun update(a: String, b: String){
        val sharedPreferences2 = getSharedPreferences("login",
            MODE_PRIVATE
        )
        val idOperador = sharedPreferences2.getString("idUser", "@")
        val queue = Volley.newRequestQueue(this@TiendasPendientes)
        val datos = JSONObject()
        datos.put("latitud", a)
        datos.put("longitud", b)


        val jsonObjectRequest3 = JsonObjectRequest(
            Request.Method.PATCH,
            "http://192.168.0.8:5000/operator/actualizar-operador/${idOperador}",
            datos,
            { response ->
                Log.e("VOLLEYRESPONSE", response.toString())
            },
            { error ->
                Log.e("VOLLEYRESPONSE", error.message!!)
            }

        )
        queue.add(jsonObjectRequest3)
    }
}