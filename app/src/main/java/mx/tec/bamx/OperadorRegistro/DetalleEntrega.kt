package mx.tec.bamx.OperadorRegistro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.detalle_entrega.*
import mx.tec.bamx.ListViews.EntregasPendientes
import mx.tec.bamx.R

class DetalleEntrega : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_entrega)
         ic_close.setOnClickListener {
             val intent = Intent(this, EntregasPendientes::class.java)
             startActivity(intent)
         }
    }
}