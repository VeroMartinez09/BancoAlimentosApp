package mx.tec.bamx.OperadorRegistro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detalle_donativo.*
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.tec.bamx.DonativoRegistrado
import mx.tec.bamx.R


class DetalleDonativo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_donativo)

        val nombre = intent.getStringExtra("nombre")
        val responsable = intent.getStringExtra("responsable")



        btnDDC.setOnClickListener {
            val intent = Intent(this, DonativoRegistrado::class.java)

            intent.putExtra("nombre", nombre)
            intent.putExtra("responsable", responsable)
            intent.putExtra("abarrote", edtADD.text.toString())
            intent.putExtra("fruta", edtFVDD.text.toString())
            intent.putExtra("pan", edtPDD.text.toString())
            intent.putExtra("nocomestible", edtBDD.text.toString())

            startActivity(intent)
        }

        /* supportActionBar?.title = "Operador"
         supportActionBar?.setDisplayHomeAsUpEnabled(true)*/
    }
}