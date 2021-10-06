package mx.tec.bamx.ListViews

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import mx.tec.bamx.R

class TiendasPendientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.tiendas_pendientes)
        val lstOperador = findViewById<ListView>(R.id.lstStore)
        val card1 = findViewById<CardView>(R.id.CardAlmacenT)

        val datos = listOf(
            Operador("Walmart", R.drawable.walmart, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("Oxxo", R.drawable.oxxo, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("Sanborns", R.drawable.sanborns, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("El globo", R.drawable.elglobo, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),

            )
        val adaptador = Adapter(this@TiendasPendientes,
            R.layout.lst_tienda,
            datos
        )

        lstOperador.adapter = adaptador

        card1.setOnClickListener{
            val intent = Intent(this@TiendasPendientes, EntregasPendientes::class.java)
            startActivity(intent)
        }
    }
}