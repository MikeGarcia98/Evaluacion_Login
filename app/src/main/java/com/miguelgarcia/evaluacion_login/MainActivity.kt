package com.miguelgarcia.evaluacion_login // <-- Conserva tu package name original

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        val cyanColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.synth_cyan))
        val mutedColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.synth_outline_muted))

        // Iluminar icono en cian al enfocar cada input
        etEmail.setOnFocusChangeListener { _, hasFocus ->
            tilEmail.setStartIconTintList(if (hasFocus) cyanColor else mutedColor)
        }

        etPassword.setOnFocusChangeListener { _, hasFocus ->
            val color = if (hasFocus) cyanColor else mutedColor
            tilPassword.setStartIconTintList(color)
            tilPassword.setEndIconTintList(color)
        }

        // Validación al presionar Iniciar Sesión
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            tilEmail.error = null
            tilPassword.error = null

            when {
                email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    tilEmail.error = "Ingresa un correo electrónico válido"
                }
                password.length < 6 -> {
                    tilPassword.error = "La contraseña debe tener al menos 6 caracteres"
                }
                else -> {
                    Toast.makeText(this, "Iniciando sesión con $email...", Toast.LENGTH_SHORT).show()
                    // Aquí conectarás tu llamada a API o Intent hacia la siguiente pantalla
                }
            }
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Recuperar contraseña", Toast.LENGTH_SHORT).show()
        }

        tvRegister.setOnClickListener {
            Toast.makeText(this, "Ir a pantalla de registro", Toast.LENGTH_SHORT).show()
        }
    }
}