package mx.tec.bamx

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_donativo_registrado.*
import kotlinx.android.synthetic.main.fragment_registrar_donativo_a.*
import kotlinx.android.synthetic.main.toolbar_operador.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toolbar_operador)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        //Utiliza transacciones para cargar todos los elementos en pantalla
        // 1. id del contenedor
        // 2. Instancia del fragment que quiero cargar en el contenedor
        fragmentTransaction.replace(R.id.contenedor, RegistrarDonativo())
        fragmentTransaction.commit()

       /* btnProductos.setOnClickListener {
            fragmentTransaction.replace(R.id.contenedor, DonativoRegistrado())
            fragmentTransaction.commit()
        }*/

    }

}