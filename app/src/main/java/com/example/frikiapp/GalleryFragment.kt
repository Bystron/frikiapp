package com.example.frikiapp
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
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
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.lang.Math.log
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class GalleryFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

   // lateinit var binding: FragmentCameraBinding
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    var imageUri: Image? = null

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
               // imageUri = data.data!!
               // binding.imageView.setImageURI(imageUri)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.R)

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        binding = FragmentCameraBinding.inflate(layoutInflater)
//        return binding.root
//    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(intent)
    }

    private fun ImageGallery(){
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storage = FirebaseStorage.getInstance().getReference("images/$fileName")
//        storage.putFile(imageUri)
//            .addOnSuccessListener {
//               // binding.imageView.setImageURI(null)
//                Toast.makeText(requireContext(), "Image uploaded!", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener {
//                Toast.makeText(requireContext(), "Image not uploaded!", Toast.LENGTH_SHORT).show()
//            }

    }

    private fun UploadImage(){
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storage = FirebaseStorage.getInstance().getReference("images/$fileName")
//        storage.putFile(imageUri)
//            .addOnSuccessListener {
//              //  binding.imageView.setImageURI(null)
//                Toast.makeText(requireContext(), "Image uploaded!", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener {
//                Toast.makeText(requireContext(), "Image not uploaded!", Toast.LENGTH_SHORT).show()
//            }

    }

    private fun DownloadImage(){
        val storage = FirebaseStorage.getInstance().reference.child("images/2022_03_20_11_47_04")
        val localFile = File.createTempFile("temp", "jpeg")
        storage.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
           // binding.imageView.setImageBitmap(bitmap)

        }.addOnFailureListener{
            Toast.makeText(requireContext(), "Error downloading image!", Toast.LENGTH_SHORT)
                .show()
        }

    }



}