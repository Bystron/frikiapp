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
import java.lang.Math.log


class MapFragment(contentLayoutId: Int) : Fragment(contentLayoutId), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapBinding

// DADES FIREBASE
    // private val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.R)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentMapBinding.inflate(layoutInflater)
        Toast.makeText( getContext(),"I MADE A MARKER",  Toast.LENGTH_SHORT).show()
        createMap()
        return binding.root

    }

    fun createMap(){

        val mapFragment = childFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    fun createMarker(){

        val coordinates = LatLng(41.4534227,2.1841046)
        val myMarker = MarkerOptions().position(coordinates).title("ITB")
        mMap.addMarker(myMarker)
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f),
            5000, null)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        createMarker()
        enableLocation()
    }



    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun enableLocation(){
        if(!::mMap.isInitialized) return
        if(isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = true
        }
        else{
            requestLocationPermission()
        }

    }

    private fun requestLocationPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(requireContext(), "Ves a la pantalla de permisos de l’aplicació i habilita el de Geolocalització", Toast.LENGTH_SHORT).show()
        }
        else{
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mMap.isMyLocationEnabled = true
            }
            else{
                Toast.makeText(
                    requireContext(), "Accepta els permisos de geolocalització",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        if(!::mMap.isInitialized) return
        if(!isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = false
            Toast.makeText(
                requireContext(), "Accepta els permisos de geolocalització",
                Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val REQUEST_CODE_LOCATION = 100
    }
}