package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_introduction.*

class Introduction : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)
        auth = FirebaseAuth.getInstance()

        btnregister.setOnClickListener {
            userReg()
        }
    }
        private fun userReg() {
            if( signusername.text.toString().isEmpty()){
                signusername.error="Please enter mail"
                signusername.requestFocus()
                return
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(signusername.text.toString()).matches()){
                signusername.error="Enter valid email"
                signusername.requestFocus()
                return
            }
            if( signpassword.text.toString().isEmpty()){
                signpassword.error="Please password"
                signpassword.requestFocus()
                return
            }
            auth.createUserWithEmailAndPassword(signusername.text.toString(), signpassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Sign up failed", Toast.LENGTH_SHORT).show()
                    }

                }
        }
}