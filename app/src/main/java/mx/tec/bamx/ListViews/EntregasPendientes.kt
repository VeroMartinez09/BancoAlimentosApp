package mx.tec.bamx.ListViews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.icon_salir
import kotlinx.android.synthetic.main.toolbarnoflecha.*
import mx.tec.bamx.DetalleEntrega
import mx.tec.bamx.R
import mx.tec.bamx.RegistrarDonativo

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

        lstOperador.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@EntregasPendientes, DetalleEntrega::class.java)
            intent.putExtra("operario", datos[position].name)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        card1.setOnClickListener{
            val intent = Intent(this@EntregasPendientes, TiendasPendientes::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        icon_salir.setOnClickListener {
            logout()
        }

    }

    fun logout() {
        icon_salir.setOnClickListener{
            println("DISTE CLICK BRO")

            val sharedPreferences = getSharedPreferences("login",
                Context.MODE_PRIVATE)

            MaterialAlertDialogBuilder(this)
                .setCancelable(false)
                .setTitle(resources.getString(R.string.tituloS))
                .setMessage(resources.getString(R.string.mensajeS))
                .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.si)) { dialog, which ->

                    with(sharedPreferences.edit()){
                        remove("usuario")
                        commit()
                    }
                    this.finish()
                }
                .show()
        }
    }
}