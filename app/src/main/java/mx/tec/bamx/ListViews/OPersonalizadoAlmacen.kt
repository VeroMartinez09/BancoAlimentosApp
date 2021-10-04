package mx.tec.bamx.ListViews

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamx.R

class OPersonalizadoAlmacen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_personalizado2)
        val lstOperador = findViewById<ListView>(R.id.LstAlmacen)
        val datos = listOf(
            Operador(
                "Tlahualpan",
                R.drawable.almacen,
                "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.",
                "Activo"
            ),
            Operador(
                "Refrigerados",
                R.drawable.almacen,
                "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.",
                "Activo"
            ),
            Operador(
                "Tlahualpan",
                R.drawable.almacen,
                "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.",
                "Activo"
            ),
            Operador(
                "Tlahualpan",
                R.drawable.almacen,
                "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.",
                "Activo"
            ),

            )
        val adaptador = Adapter(
            this@OPersonalizadoAlmacen,
            R.layout.operador_tienda,
            datos
        )

        lstOperador.adapter = adaptador

    }
}