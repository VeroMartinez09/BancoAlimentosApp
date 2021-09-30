package mx.tec.bamx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      /*  btnLogin.setOnClickListener {
            println("Diste click en el boton LogIn")
            // Origen, Destino
            val intent = Intent(this@MainActivity, RegistrarDonativo::class.java)

            startActivity(intent)
        }*/
    }

}