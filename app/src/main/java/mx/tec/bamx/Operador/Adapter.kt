package mx.tec.bamx.Operador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import mx.tec.bamx.MainActivity
import mx.tec.bamx.R


class Adapter (val context: Context,
               val layout: Int,
               val dataSource: List <Operador> ): BaseAdapter(){

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(p0: Int,
                         convertView: View?,
                         parent: ViewGroup?): View {
        val view = inflater.inflate(layout, parent, false)
        // Cargar los datos del dataSource en el elemento cargado

        val imagen = view.findViewById(R.id.imgStore) as ImageView
        val nombre = view.findViewById(R.id.txtNameStore) as TextView
        val direccion = view.findViewById(R.id.txtLocation) as TextView
        val precio = view.findViewById(R.id.txtPrecio) as TextView
      //  var btn = view.findViewById(R.id.btnRegistro) as Button



        //Extraer elemento del D/S
        val elemento = getItem(p0) as Operador

        nombre.text =  elemento.name
        direccion.text = elemento.location
        imagen.setImageResource(elemento.logo)
        precio.text = elemento.precio


        return view;
    }

}