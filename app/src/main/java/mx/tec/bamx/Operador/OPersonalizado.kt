package mx.tec.bamx.Operador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.operador_tienda.*
import mx.tec.bamx.MainActivity
import mx.tec.bamx.R

class OPersonalizado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizado)

        val lstPizza = findViewById<ListView>(R.id.lstStore)

        val datos = listOf(
            Operador("Walmart", R.drawable.walmart, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("Oxxo", R.drawable.oxxo, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("Sanborns", R.drawable.sanborns, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),
            Operador("El globo", R.drawable.elglobo, "Blvrd Luis Donaldo Colosio 2009, Los Jales, Coscotitlán, 42064 Pachuca de Soto, Hgo.", "Activo"),

            )
        val adaptador = Adapter(this@OPersonalizado,
            R.layout.operador_tienda,
            datos
        )

        lstPizza.adapter = adaptador
        lstPizza.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@OPersonalizado, MainActivity::class.java)
            intent.putExtra("nombre", datos[position].name)
            intent.putExtra("descripcion", datos[position].location)
            intent.putExtra("precio", datos[position].precio)
            startActivity(intent)
        }
    }
}