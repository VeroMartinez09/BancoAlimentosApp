package mx.tec.bamx.ListViews

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import mx.tec.bamx.R

class EntregasPendientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.entregas_pendientes)
        val lstOperador = findViewById<ListView>(R.id.LstAlmacen)

        val card1 = findViewById<CardView>(R.id.CardEntregaP)

        val datos = listOf(
            Entregas(
                "Tlahualpan",
                R.drawable.almacen,
                "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.",
                "Activo"
            ),
            Entregas(
                "Refrigerados",
                R.drawable.almacen,
                "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.",
                "Activo"
            ),
            Entregas(
                "Tlahualpan",
                R.drawable.almacen,
                "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.",
                "Activo"
            ),
            Entregas(
                "Tlahualpan",
                R.drawable.almacen,
                "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.",
                "Activo"
            ),

            )
        val adaptador = AdapterAlmacen(
            this@EntregasPendientes,
            R.layout.lst_tienda,
            datos
        )

        lstOperador.adapter = adaptador

        card1.setOnClickListener{
            val intent = Intent(this@EntregasPendientes, TiendasPendientes::class.java)
            startActivity(intent)
        }

    }
}