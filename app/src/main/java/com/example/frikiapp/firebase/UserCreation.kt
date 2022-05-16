package com.example.frikiapp.firebase

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class UserCreation(contentLayoutId: Int) : Fragment(contentLayoutId){

    lateinit var email: String
    lateinit var password: String

fun createUser(){

    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                val emailLogged = it.result?.user?.email
                //goToHome(emailLogged!!)
            } else {
               // showError("Error al registrar l'usuari")
            }
        }
}

//    fun loginUser(){
//        FirebaseAuth.getInstance().
//        signInWithEmailAndPassword(email, binding.etPassword.text.toString())
//            .addOnCompleteListener {
//                if(it.isSuccessful){
//                    val emailLogged = it.result?.user?.email
//                    goToHome(emailLogged!!)
//                }
//                else{
//                    showError("Error al fer login")
//                }
//            }
//    }

    fun signOut(){
        FirebaseAuth.getInstance().signOut()

    }

}