package mx.tec.bamx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.donativo_registrado.*
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.toolbar.*

class DonativoRegistrado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donativo_registrado)

        btnEnviar.setOnClickListener {
            println("Diste click en el boton")
            //requireActivity().supportFragmentManager.beginTransaction().apply{
            //   replace(R.id.contenedor, DonativoRegistrado())
            //   commit()
            // }

            /* val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent) */
        }

        icon_Back.setOnClickListener{
            val intent = Intent(this, RegistrarDonativo::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}