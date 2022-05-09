package com.example.frikiapp.firebase

import com.google.firebase.auth.FirebaseAuth

class usercreation {
fun createUser(){

    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                val emailLogged = it.result?.user?.email
                goToHome(emailLogged!!)
            } else {
                showError("Error al registrar l'usuari")
            }
        }
}

    fun loginUser(){
        FirebaseAuth.getInstance().
        signInWithEmailAndPassword(email, binding.etPassword.text.toString())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val emailLogged = it.result?.user?.email
                    goToHome(emailLogged!!)
                }
                else{
                    showError("Error al fer login")
                }
            }

    }

    fun signOut(){
        FirebaseAuth.getInstance().signOut()

    }

}