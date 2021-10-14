package mx.tec.bamx

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.tec.bamx.ListViews.TiendasPendientes
import mx.tec.bamx.OperadorRegistro.DetalleDonativo
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RegistrarDonativo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrar_donativo)

        val SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val nombre = intent.getStringExtra("nombre")
        val ubicacion = intent.getStringExtra("ubicacion")
        val id = intent.getStringExtra("id")


        edtTienda.setText(nombre)
        txtUbicacionRD.text = ubicacion
        txtFechaHoraRD.text = SimpleDateFormat.format(Date())
        val puesto = edtRol.text

        icon_Back.setOnClickListener {
            val intent = Intent(this, TiendasPendientes::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        btnProductos.setOnClickListener {
            if (edtResponsable.text.toString().length == 0)
                edtResponsable.setError("¡Este campo es requerido!")
            else if (edtRol.text.toString().length == 0)
                edtRol.setError("¡Este campo es requerido!")
            else
            {
                onClic(nombre, ubicacion, id, puesto.toString())
            }
        }
        icon_salir.setOnClickListener {
            logout()
        }
    }



    fun onClic(a: String?, b: String?, c: String?, d: String?) {
        println("AAAA: " +  c)
        val intent = Intent(this, DetalleDonativo::class.java)
        //intent.putExtra("nombre", nombre)
        intent.putExtra("responsable", edtResponsable.text.toString())
        intent.putExtra("nombre1", a)
        intent.putExtra("ubicacion1", b)
        intent.putExtra("id", c)
        intent.putExtra("puesto",d)

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
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
                    this.finish()
                }
                .show()
        }
    }
}