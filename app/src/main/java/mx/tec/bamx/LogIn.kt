package mx.tec.bamx

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.login.*
import mx.tec.bamx.ListViews.EntregasPendientes
import mx.tec.bamx.ListViews.TiendasPendientes
import org.json.JSONObject


class LogIn : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        var queue = Volley.newRequestQueue(this@LogIn)

        sharedPreferences = getSharedPreferences("login",
            Context.MODE_PRIVATE)
        if(sharedPreferences.getString("usuario", "@") != "" &&
            sharedPreferences.getString("usuario", "@") != "@") {
            // Mandar al home
            val intent = Intent(this, TiendasPendientes::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }



        btnLogin.setOnClickListener {
            val intent = Intent(this@LogIn, TiendasPendientes::class.java)
            println("Diste click en el boton LogIn")
            //    if(usuario.text.toString() == "Verito" && // <- Petición volley al API
            //        password.text.toString() == "mando")

            val datos = JSONObject()
            datos.put("username", edtUsername.text.toString())
            datos.put("contrasena", edtPassword.text.toString())

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST,
                "http://192.168.0.8:5000/operator/login",
                datos,
                { response ->
                    // Usuario correcto
                    Log.e("VOLLEYRESPONSE", response.toString())
                    val array =  response.getJSONObject("data")
                    val idOperador = array.getString("id")
                    with(sharedPreferences.edit()){
                        putString("idUser", idOperador.toString())
                        putString("usuario", edtUsername.text.toString())
                        commit()
                    }
                    startActivity(intent)
                },
                { error ->
                    // Usuario incorrecto
                    Log.e("VOLLEYRESPONSE", error.message!!)
                    Toast.makeText(this,
                        "Usuario o contraseña incorrectas",
                        Toast.LENGTH_LONG).show()
                }
            )
            queue.add(jsonObjectRequest)
        }
    }

}