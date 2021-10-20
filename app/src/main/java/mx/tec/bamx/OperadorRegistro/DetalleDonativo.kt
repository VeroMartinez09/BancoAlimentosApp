package mx.tec.bamx.OperadorRegistro


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detalle_donativo.*
import mx.tec.bamx.DonativoRegistrado
import mx.tec.bamx.R
import mx.tec.bamx.RegistrarDonativo


class DetalleDonativo : AppCompatActivity() {

    lateinit var lista: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_donativo)

        lista = intent.getStringArrayListExtra("lista") as ArrayList<String>

        val sharedPreferences = getSharedPreferences("donativo",
            Context.MODE_PRIVATE)

        val nombre = sharedPreferences.getString("strName", "@")
        val ubicacion = sharedPreferences.getString("strLocation", "@")
        val id = sharedPreferences.getString("strId", "@")
        val responsable = sharedPreferences.getString("strResponsable", "@")
        val puesto = sharedPreferences.getString("strPuesto", "@")

        if (lista.size != 0) {
            txtAbarroteCant.setText(lista.get(3))
            txtFrutaVCant.setText(lista.get(4))
            txtPanCant.setText(lista.get(5))
            txtNoComerCant.setText(lista.get(6))
        } else {
            lista.add(0, id.toString())
            lista.add(1, responsable.toString())
            lista.add(2, puesto.toString())
            for(i in 3 until 6)
            lista.add(i, "0")
        }

        btnContinuar.setOnClickListener {

            if (txtAbarroteCant.text.isNullOrEmpty()) {
                lista.add(3, "0")
                txtAbarroteCant.setText("0")
            } else
                lista.add(3, txtAbarroteCant.text.toString())

            if (txtFrutaVCant.text.isNullOrEmpty()) {
                lista.add(4, "0")
                txtFrutaVCant.setText("0")
            } else
                lista.add(4, txtFrutaVCant.text.toString())

            if (txtPanCant.text.isNullOrEmpty()) {
                lista.add(5, "0")
                txtPanCant.setText("0")
            } else
                lista.add(5, txtPanCant.text.toString())

            if (txtNoComerCant.text.isNullOrEmpty()) {
                lista.add(6, "0")
                txtNoComerCant.setText("0")
            } else
                lista.add(6, txtNoComerCant.text.toString())

            if (lista.get(3) == "0" && lista.get(4) == "0" && lista.get(5) == "0" && lista.get(6) == "0") {
                Toast.makeText(
                    this,
                    "Introduce cantidades válidas, por favor.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, DonativoRegistrado::class.java)
                intent.putExtra("nombre", nombre)
                intent.putExtra("ubicacion", ubicacion)
                intent.putExtra("id", id)
                intent.putExtra("puesto", puesto)
                intent.putExtra("responsable", responsable)
                intent.putExtra("abarrote", txtAbarroteCant.text.toString())
                intent.putExtra("fruta", txtFrutaVCant.text.toString())
                intent.putExtra("pan", txtPanCant.text.toString())
                intent.putExtra("nocomestible", txtNoComerCant.text.toString())

                intent.putStringArrayListExtra("lista", lista)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

            }
        }

        ic_Close.setOnClickListener {
            val intent = Intent(this, RegistrarDonativo::class.java)
            intent.putStringArrayListExtra("lista", lista)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }
}