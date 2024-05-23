package com.example.sianiodemc

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sianiodemc.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class MainActivity : AppCompatActivity() {

    private lateinit var wisataRecyclerView: RecyclerView
    private lateinit var wisataList: MutableList<Image>
    private lateinit var wisataAdapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    private var mStorage:FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wisataRecyclerView = findViewById(R.id.imageRecyclerview)
        wisataRecyclerView.setHasFixedSize(true)
        wisataRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.myDataLoaderProgressBar.visibility = View.VISIBLE
        wisataList = ArrayList()
        wisataAdapter = MyAdapter(this@MainActivity,wisataList)
        wisataRecyclerView.adapter = wisataAdapter
        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("wisata")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                wisataList.clear()
                for (teacherSnapshot in snapshot.children){
                    val upload = teacherSnapshot.getValue(Image::class.java)
                    upload!!.key = teacherSnapshot.key
                    wisataList.add(upload)
                }
                wisataAdapter.notifyDataSetChanged()
                binding.myDataLoaderProgressBar.visibility = View.GONE
            }
        })
    }
}