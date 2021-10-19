package mx.tec.bamx

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.registrar_donativo_espontaneo.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.tec.bamx.ListViews.TPEspontaneas
import mx.tec.bamx.ListViews.TiendasPendientes
import mx.tec.bamx.OperadorRegistro.DetalleDonativo
import mx.tec.bamx.OperadorRegistro.DetalleDonativoEspontaneo
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RegistrarDonativoEspontaneo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrar_donativo_espontaneo)

        val sharedPreferences = getSharedPreferences("donativo_espontaneo",
            Context.MODE_PRIVATE)
        val nombre = sharedPreferences.getString("strName", "@")
        val ubicacion = sharedPreferences.getString("strLocation", "@")
        val id = sharedPreferences.getString("strId", "@")


        val SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

        //val nombre = intent.getStringExtra("nombre")
        //val ubicacion = intent.getStringExtra("ubicacion")
        //val id = intent.getStringExtra("id")




        edtTiendaDE.setText(nombre)
        txtUbicacionDE.text = ubicacion
        txtFechaHoraDE.text = SimpleDateFormat.format(Date())

        val puesto = edtRolDE.text

        icon_Back.setOnClickListener {
            val intent = Intent(this, TPEspontaneas::class.java)
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

        btnProductosDE.setOnClickListener {
            if (edtResponsableDE.text.toString().length == 0)
                edtResponsableDE.setError("¡Este campo es requerido!")
            else if (edtRolDE.text.toString().length == 0)
                edtRolDE.setError("¡Este campo es requerido!")
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
        val intent = Intent(this, DetalleDonativoEspontaneo::class.java)

        val sharedPreferences = getSharedPreferences("donativo_espontaneo",
            Context.MODE_PRIVATE)
        with(sharedPreferences.edit()){
            putString("strResponsable", edtResponsableDE.text.toString())
            putString("strPuesto", edtRolDE.text.toString())
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