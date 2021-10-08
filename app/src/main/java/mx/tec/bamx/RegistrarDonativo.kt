package mx.tec.bamx

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.tec.bamx.ListViews.EntregasPendientes
import mx.tec.bamx.ListViews.TiendasPendientes
import mx.tec.bamx.OperadorRegistro.DetalleDonativo

class RegistrarDonativo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrar_donativo)

        val sharedPreferences = getSharedPreferences("login",
            Context.MODE_PRIVATE)
        val nombre = intent.getStringExtra("nombre")
        edtTienda.setText(nombre)

        icon_Back.setOnClickListener{
            val intent = Intent(this, TiendasPendientes::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        icon_salir.setOnClickListener{
            println("DISTE CLICK BRO")
            with(sharedPreferences.edit()){
                remove("usuario")
                commit()
            }
            finish()
        }

        btnProductos.setOnClickListener {
            val intent = Intent(this, DetalleDonativo::class.java)
            intent.putExtra("nombre", nombre)
            intent.putExtra("responsable", edtResponsable.text.toString())
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}