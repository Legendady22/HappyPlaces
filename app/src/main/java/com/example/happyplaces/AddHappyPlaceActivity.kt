package com.example.happyplaces

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.happyplaces.databinding.ActivityAddHappyPlaceBinding
import java.text.SimpleDateFormat
import java.util.*

class AddHappyPlaceActivity : AppCompatActivity(), View.OnClickListener {

    private var cal = Calendar.getInstance()
    private var dateSetListener: DatePickerDialog.OnDateSetListener? = null

    private var binding: ActivityAddHappyPlaceBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHappyPlaceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarAddPlace)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.toolbarAddPlace?.setNavigationOnClickListener {
            onBackPressed()
        }

        dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }
        binding?.etDate?.setOnClickListener(this)
        binding?.tvAddImage?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            binding?.etDate?.id -> {
                DatePickerDialog(this@AddHappyPlaceActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
            binding?.ivPlaceImage?.id ->{
                val pictureDialog = AlertDialog.Builder(this)
                pictureDialog.setTitle("Select Action")

            }

        }

    }

    private fun updateDateInView(){
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding?.etDate?.setText(sdf.format(cal.time).toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}