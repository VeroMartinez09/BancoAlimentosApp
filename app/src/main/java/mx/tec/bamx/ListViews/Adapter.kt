package mx.tec.bamx.ListViews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import mx.tec.bamx.R
import mx.tec.bamx.RegistrarDonativo


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
        val btnRegistro = view.findViewById(R.id.btnRegistro) as Button

        btnRegistro.setOnClickListener {
            println("CLICK")
            val intent = Intent(context, RegistrarDonativo::class.java)
            intent.putExtra("nombre", nombre.text.toString())
            context.startActivity(intent)
        }


        //Extraer elemento del D/S
        val elemento = getItem(p0) as Operador

        nombre.text =  elemento.name
        direccion.text = elemento.location
        imagen.setImageResource(elemento.logo)


        return view;
    }

}