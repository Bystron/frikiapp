package com.example.frikiapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.*
import com.google.firebase.firestore.auth.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OperateDataBase.newInstance] factory method to
 * create an instance of this fragment.
 */
class OperateDataBase : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.marker_list_screen, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OperateDataBase.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OperateDataBase().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // DATABASE
    private val db = FirebaseFirestore.getInstance()

//    private fun UserSaveDocument(){
//        binding.btSave.setOnClickListener {
//            db.collection("users").document(email!!).set(
//                hashMapOf("name" to binding.etName.text.toString(),
//                    "lastname" to binding.etLastname.text.toString(),
//                    "age" to binding.etAge.text.toString())
//            )
//        }
//
//    }

//    private fun reestoreData(){
//        binding.btGet.setOnClickListener {
//            db.collection("users").document(email!!).get().addOnSuccessListener {
//                binding.etName.setText(it.get("name") as String)
//                binding.etLastname.setText(it.get("lastname") as String)
//                binding.etAge.setText(it.get("age") as String)
//            }
//        }
//    }

//    private fun deleteData(){
//        binding.btDelete.setOnClickListener {
//            db.collection("users").document(email!!).delete()
//        }
//    }
//
//    //Al métode OnViewCreated, una vegada creat el RecyclerView, cridarem a una funció que serà la que gestionarà els canvis en les dades de la col·lecció de Firestore
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupRecyclerView(arrayListOf())
//        userList = arrayListOf<User>()
//        eventChangeListener()
//    }
//
//    private fun setupRecyclerView(usersList: List<User>) {
//        userAdapter = UserAdapter(usersList, this)
//        binding.recyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(context)
//            adapter = userAdapter
//        }
//    }
////          Mostrar dades d'un recyclerview
//    private fun eventChangeListener() {
//        db.collection("users").addSnapshotListener(object: EventListener<QuerySnapshot> {
//            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
//                if(error != null){
//                    Log.e("Firestore error", error.message.toString())
//                    return
//                }
//                for(dc: DocumentChange in value?.documentChanges!!){
//                    if(dc.type == DocumentChange.Type.ADDED){
//                        val newUser = dc.document.toObject(User::class.java)
//                        newUser.email = dc.document.id
//                        userList.add(newUser)
//                    }
//                }
//                userAdapter.setUsersList(userList)
//            }
//        })
//    }
//
//    override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?){
//        if(dc.type == DocumentChange.Type.ADDED){
//            val newUser = dc.document.toObject(User::class.java)
//            newUser.email = dc.document.id
//            userList.add(newUser)
//        }
//
//    }
//
//    override fun onClick(user: User) {
//        val action = ListFragmentDirections.actionListFragmentToUserDetailFragment(user.id!!)
//        findNavController().navigate(action)
//    }


}