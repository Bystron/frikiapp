package com.example.frikiapp
import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.frikiapp.databinding.FragmentMapBinding
//import com.example.frikiapp.databinding.FragmentMapFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import java.lang.Math.log


class AuthenticationFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    private lateinit var binding: FragmentMapBinding
    var email: String = ""
    var password: String = ""
// DADES FIREBASE
    // private val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.R)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentMapBinding.inflate(layoutInflater)
        return binding.root

    }

    private fun UserCreate(){
        FirebaseAuth.getInstance().
        createUserWithEmailAndPassword( email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val emailLogged = it.result?.user?.email
                    goToHome(emailLogged!!)
                }
                else{
                    showError("Error al registrar l'usuari")
                }
            }
    }

    private fun UserLogin(){
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

    private fun LogOut(){
        FirebaseAuth.getInstance().signOut()

    }
}