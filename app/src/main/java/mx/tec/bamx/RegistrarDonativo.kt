package mx.tec.bamx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.tec.bamx.OperadorRegistro.DetalleDonativo

class RegistrarDonativo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrar_donativo)

       /* supportActionBar?.title = "Operador"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)*/

        val nombre = intent.getStringExtra("nombre")
        edtTienda.setText(nombre)


        icon_Back.setOnClickListener{
            val intent = Intent(this, DonativoRegistrado::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        btnProductos.setOnClickListener {
            val intent = Intent(this, DetalleDonativo::class.java)
            intent.putExtra("nombre", nombre)
            intent.putExtra("responsable", edtResponsable.text.toString())
            startActivity(intent)
        }


        //btnProductos.setOnClickListener {
        //    println("Diste click en el boton")
            //requireActivity().supportFragmentManager.beginTransaction().apply{
             //   replace(R.id.contenedor, DonativoRegistrado())
             //   commit()
            // }

            /* val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent) */
        //}
    }
}