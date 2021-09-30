package mx.tec.bamx

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.operador_registrardonativo.*

class RegistrarDonativo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.operador_registrardonativo)

        btnProductos.setOnClickListener {
            val intent = Intent(this@RegistrarDonativo, DonativoRegistrado::class.java)
        }
    }

}