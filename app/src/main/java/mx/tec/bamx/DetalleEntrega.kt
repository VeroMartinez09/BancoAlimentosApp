package mx.tec.bamx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detalle_donativo.*
import kotlinx.android.synthetic.main.detalle_entrega.*
import kotlinx.android.synthetic.main.detalle_entrega.ic_Close
import mx.tec.bamx.ListViews.EntregasPendientes

class DetalleEntrega : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_entrega)

        ic_Close.setOnClickListener{
            val intent = Intent(this@DetalleEntrega, EntregasPendientes::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}