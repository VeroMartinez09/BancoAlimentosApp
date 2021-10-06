package mx.tec.bamx

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.donativo_registrado.*
import kotlinx.android.synthetic.main.registrar_donativo.*
import kotlinx.android.synthetic.main.toolbar.*
import mx.tec.bamx.ListViews.TiendasPendientes

class DonativoRegistrado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donativo_registrado)

        val nombre = intent.getStringExtra("nombre")
        val responsable = intent.getStringExtra("responsable")
        val abarrote = intent.getStringExtra("abarrote")
        val fruta = intent.getStringExtra("fruta")
        val pan = intent.getStringExtra("pan")
        val nocomestible = intent.getStringExtra("nocomestible")

        txtAbarrote.text = abarrote
        txtFrutaVerdura.text = fruta
        txtPan.text = pan
        txtNoComestible.text = nocomestible
        txtResponsable.text = responsable
        txtSucursal.text = nombre

        /*val total: Int
        total = abarrote.toString().toInt() + fruta.toString().toInt() + pan.toString().toInt() + nocomestible.toString().toInt()
        txtTotal.text = total.toString()*/

        icon_Back.setOnClickListener{
            val intent = Intent(this, DetalleDonativo::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        btnEnviar.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setCancelable(false)
                .setTitle(resources.getString(R.string.titulo))
                .setMessage(resources.getString(R.string.mensaje))
                .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.si)) { dialog, which ->
                    // Respond to positive button press
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage(resources.getString(R.string.ok))
                    builder.setIcon(R.drawable.checked)
                    builder.setCancelable(false)
                    builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                        //Do something
                        val intent = Intent(this@DonativoRegistrado, TiendasPendientes::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }

                    builder.show()
                }
                .show()
        }

        btnEditar.setOnClickListener {
            val intent = Intent(this@DonativoRegistrado, RegistrarDonativo::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}