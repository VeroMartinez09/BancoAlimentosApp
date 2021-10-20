package mx.tec.bamx.OperadorRegistro


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detalle_donativo.*
import mx.tec.bamx.*


class DetalleDonativoEspontaneo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_donativo_espontaneo)

        val sharedPreferences = getSharedPreferences("donativo_espontaneo",
            Context.MODE_PRIVATE)

        val nombre = sharedPreferences.getString("strName", "@")
        val ubicacion = sharedPreferences.getString("strLocation", "@")
        val id = sharedPreferences.getString("strId", "@")
        val responsable = sharedPreferences.getString("strResponsable", "@")
        val puesto = sharedPreferences.getString("strPuesto", "@")



        btnContinuar.setOnClickListener {

                val intent = Intent(this, DonativoRegistradoEspontaneo::class.java)
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
            val intent = Intent(this, RegistrarDonativoEspontaneo::class.java)
            startActivity(intent)
        }

    }
}