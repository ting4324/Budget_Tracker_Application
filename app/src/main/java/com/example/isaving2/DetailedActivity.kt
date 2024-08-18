package com.example.isaving2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_detailed.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailedActivity : AppCompatActivity() {
    private lateinit var transaction: Transaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        transaction = intent.getSerializableExtra("transaction")as Transaction

        edtLabel.setText(transaction.label)
        edtAmount.setText(transaction.amount.toString())
        edtDescription.setText(transaction.description)

        rootView.setOnClickListener{
            this.window.decorView.clearFocus()

            val inn = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inn.hideSoftInputFromWindow(it.windowToken, 0)
        }

        edtLabel.addTextChangedListener{
            btnUpdate.visibility = View.VISIBLE
            if(it!!.count() > 0)
                labelLayout.error = null
        }

        edtAmount.addTextChangedListener{
            btnUpdate.visibility = View.VISIBLE
            if(it!!.count() > 0)
                amountLayout.error = null
        }

        edtDescription.addTextChangedListener{
            btnUpdate.visibility = View.VISIBLE
        }

        btnUpdate.setOnClickListener{
            val label = edtLabel.text.toString()
            val description = edtDescription.text.toString()
            val amount = edtAmount.text.toString().toDoubleOrNull()

            if(label.isEmpty())
                labelLayout.error = "Please enter a valid label."

            else if(amount == null)
                amountLayout.error = "Please enter a valid amount."

            else{
                val transaction = Transaction(transaction.id, label, amount, description)
                update(transaction)
            }
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun update(transaction: Transaction){
        val db :AppDatabase = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transaction").build()

        GlobalScope.launch{
            db.transactionDao().update(transaction)
            finish()
        }
    }

}
