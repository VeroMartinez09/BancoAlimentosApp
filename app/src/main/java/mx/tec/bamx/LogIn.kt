package mx.tec.bamx

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*
import mx.tec.bamx.ListViews.EntregasPendientes
import mx.tec.bamx.ListViews.TiendasPendientes


class LogIn : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        sharedPreferences = getSharedPreferences("login",
            Context.MODE_PRIVATE)
        if(sharedPreferences.getString("usuario", "@") != "" &&
            sharedPreferences.getString("usuario", "@") != "@") {
            // Mandar al home
            val intent = Intent(this, TiendasPendientes::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        val usuario = findViewById<EditText>(R.id.edtUsername)
        val password = findViewById<EditText>(R.id.edtPassword)


        btnLogin.setOnClickListener {
            val intent = Intent(this@LogIn, TiendasPendientes::class.java)
            println("Diste click en el boton LogIn")
            if(usuario.text.toString() == "Charles" && // <- Petición volley al API
                password.text.toString() == "mando"){
                // Usuario correcto
                with(sharedPreferences.edit()){
                    putString("usuario", usuario.text.toString())
                    commit()
                }
                startActivity(intent)
            }
            else {
                // Usuario incorrecto
                Toast.makeText(this,
                    "Usuario o contraseña incorrectas",
                    Toast.LENGTH_LONG).show()
            }

            // Origen, Destino
            //val intent = Intent(this@MainActivity, RegistrarDonativo::class.java)

        }
    }

}