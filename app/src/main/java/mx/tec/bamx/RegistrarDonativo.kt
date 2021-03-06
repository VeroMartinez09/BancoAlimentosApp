package mx.tec.bamx

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.donativo_registrado.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.tec.bamx.ListViews.TiendasPendientes
import mx.tec.bamx.OperadorRegistro.DetalleDonativo
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RegistrarDonativo : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrar_donativo)

        val sharedPreferences = getSharedPreferences("donativo",
            Context.MODE_PRIVATE)

        val nombre = sharedPreferences.getString("strName", "@")
        val ubicacion = sharedPreferences.getString("strLocation", "@")
        val id = sharedPreferences.getString("strId", "@")


        val SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

        //val nombre = intent.getStringExtra("nombre")
        //val ubicacion = intent.getStringExtra("ubicacion")
        //val id = intent.getStringExtra("id")

        edtTienda.setText(nombre)
        txtUbicacionRD.text = ubicacion
        txtFechaHoraRD.text = SimpleDateFormat.format(Date())

        val puesto = edtRol.text

        icon_Back.setOnClickListener {
            val intent = Intent(this, TiendasPendientes::class.java)
            with(sharedPreferences.edit()){
                remove("strName")
                remove("strLocation")
                remove("strId")
                remove("strResponsable")
                remove("strPuesto")
                commit()
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        btnProductos.setOnClickListener {
            if (edtResponsable.text.toString().length == 0)
                edtResponsable.setError("??Este campo es requerido!")
            else if (edtRol.text.toString().length == 0)
                edtRol.setError("??Este campo es requerido!")
            else
            {
                onClic()
            }
        }
        icon_salir.setOnClickListener {
            logout()
        }

    }



    fun onClic() {
        val intent = Intent(this, DetalleDonativo::class.java)

        val sharedPreferences = getSharedPreferences("donativo",
            Context.MODE_PRIVATE)
        with(sharedPreferences.edit()){
            putString("strResponsable", edtResponsable.text.toString())
            putString("strPuesto", edtRol.text.toString())
            commit()
        }
        startActivity(intent)
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