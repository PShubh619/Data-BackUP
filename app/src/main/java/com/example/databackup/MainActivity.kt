package com.example.databackup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.databackup.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSub.setOnClickListener {
            val firstName=binding.ETfirstName.text.toString()
            val lastName=binding.ETlastName.text.toString()
            val number=binding.ETnumber.text.toString()
            val age=binding.ETage.text.toString()

            database=FirebaseDatabase.getInstance().getReference("Users")
            val User= User(firstName,lastName,number,age)
            database.child(firstName).setValue(User).addOnSuccessListener {
                binding.ETfirstName.text.clear()
                binding.ETlastName.text.clear()
                binding.ETnumber.text.clear()
                binding.ETage.text.clear()

                Toast.makeText(this, "Backup Successfull", Toast.LENGTH_LONG).show()
            }.addOnFailureListener{

                Toast.makeText(this,"Backup Unsuccessfull",Toast.LENGTH_LONG).show()

            }
        }
    }
}