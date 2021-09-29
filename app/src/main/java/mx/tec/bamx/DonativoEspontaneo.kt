package mx.tec.bamx

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.operador_donativoregistrado.*
import kotlinx.android.synthetic.main.operador_registrardonativo.*

class DonativoEspontaneo : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.operador_donativoespontaneo)

        btnProductos.setOnClickListener(this@DonativoEspontaneo)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btnProductos -> {
                // Origen, Destino
                val intent = Intent(this@DonativoEspontaneo, DonativoRegistrado::class.java)
            }
        }
    }
}