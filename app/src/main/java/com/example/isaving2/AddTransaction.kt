package com.example.isaving2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_transaction.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTransaction : AppCompatActivity() {
    lateinit var database: DatabaseReference
    lateinit var myRef: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)


        edtLabel.addTextChangedListener{
            if(it!!.count() > 0)
                labelLayout.error = null
        }

        edtAmount.addTextChangedListener{
            if(it!!.count() > 0)
                amountLayout.error = null
        }


        database = Firebase.database.reference
        myRef = FirebaseDatabase.getInstance("https://login-app-56bfc-default-rtdb.asia-southeast1.firebasedatabase.app/")

        btnAddTransaction.setOnClickListener {

            val label = edtLabel.text.toString()
            val description = edtDescription.text.toString()
            val amount = edtAmount.text.toString().toDoubleOrNull()

            if(label.isEmpty())
                labelLayout.error = "Please enter a valid label."

            else if(amount == null)
                amountLayout.error = "Please enter a valid amount."

            else{
                val transaction = Transaction(0, label, amount, description)
                insert(transaction)

            }



            myRef.getReference(label).setValue(amount)
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun insert(transaction: Transaction){
        val db :AppDatabase = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transaction").build()

        GlobalScope.launch{
            db.transactionDao().insertAll(transaction)
            finish()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_small,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item1 -> {
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                return true
            }
            R.id.item2 -> {
                val intent = Intent(this, LoggedIn::class.java)
                startActivity(intent)
                return true
            }
            R.id.item3 -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                return true
            }else -> super.onOptionsItemSelected(item)
        }

    }

}
