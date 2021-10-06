package mx.tec.bamx.ListViews

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import mx.tec.bamx.OperadorRegistro.DetalleDonativo
import mx.tec.bamx.R

class OPersonalizadoAlmacen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_personalizado2)
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
            this@OPersonalizadoAlmacen,
            R.layout.operador_tienda,
            datos
        )

        lstOperador.adapter = adaptador

        card1.setOnClickListener{
            val intent = Intent(this@OPersonalizadoAlmacen, OPersonalizado::class.java)
            startActivity(intent)
        }

    }
}