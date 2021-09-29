package mx.tec.bamx

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.operador_donativoregistrado.*
import kotlinx.android.synthetic.main.operador_registrardonativo.*

class DonativoRegistrado : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.operador_donativoregistrado)

        btnEnviar.setOnClickListener(this@DonativoRegistrado)
        btnEditar.setOnClickListener(this@DonativoRegistrado)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btnEnviar -> {
                // Origen, Destino
                val intent = Intent(this@DonativoRegistrado, DonativoEspontaneo::class.java)
            }
            R.id.btnEditar -> {
                // Origen, Destino
                val intent = Intent(this@DonativoRegistrado, RegistrarDonativo::class.java)
            }
        }
    }
}