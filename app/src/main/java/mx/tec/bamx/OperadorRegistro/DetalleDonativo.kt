package mx.tec.bamx.OperadorRegistro


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detalle_donativo.*
import mx.tec.bamx.DonativoRegistrado
import mx.tec.bamx.R
import mx.tec.bamx.RegistrarDonativo


class DetalleDonativo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_donativo)

        val nombre = intent.getStringExtra("nombre1")
        val ubicacion = intent.getStringExtra("ubicacion1")
        val responsable = intent.getStringExtra("responsable")
        val id = intent.getStringExtra("id")
        val puesto = intent.getStringExtra("puesto")



        btnContinuar.setOnClickListener {
            val intent = Intent(this, DonativoRegistrado::class.java)
            intent.putExtra("nombre", nombre)
            intent.putExtra("ubicacion", ubicacion)
            intent.putExtra("id", id)
            intent.putExtra("puesto", puesto)
            intent.putExtra("responsable", responsable)
            intent.putExtra("abarrote", txtAbarroteCant.text.toString())
            intent.putExtra("fruta", txtFrutaVCant.text.toString())
            intent.putExtra("pan", txtPanCant.text.toString())
            intent.putExtra("nocomestible", txtNoComerCant.text.toString())

            startActivity(intent)
        }

        /* supportActionBar?.title = "Operador"
         supportActionBar?.setDisplayHomeAsUpEnabled(true)*/
        ic_Close.setOnClickListener {
            val intent = Intent(this, RegistrarDonativo::class.java)
            startActivity(intent)
        }

    }
}