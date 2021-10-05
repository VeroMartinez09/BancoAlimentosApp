package mx.tec.bamx

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.fragment_registrar_donativo_a.*
import kotlinx.android.synthetic.main.fragment_registrar_donativo_a.view.*

class RegistrarDonativo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_registrar_donativo_a, container, false)

        view.btnProductos.setOnClickListener {
            println("Diste click en el boton")
            requireActivity().supportFragmentManager.beginTransaction().apply{
                replace(R.id.contenedor, DonativoRegistrado())
                commit()
            }
        }

        return view
    }
}