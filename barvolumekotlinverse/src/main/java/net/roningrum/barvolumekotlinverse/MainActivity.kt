package net.roningrum.barvolumekotlinverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        private const val STATE_RESULT = "state_result"
    }
    private lateinit var edtWidht: EditText
    private lateinit var edtLenght: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtWidht = findViewById(R.id.edt_width)
        edtLenght = findViewById(R.id.edt_length)
        edtHeight = findViewById(R.id.edt_height)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if(savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_calculate) {
            val inputLenght = edtLenght.text.toString().trim()
            val inputWidht = edtWidht.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false

            if (TextUtils.isEmpty(inputLenght)) {
                isEmptyFields = true
                edtLenght.error = "Field ii tidak boleh kosong"
            }

            if (TextUtils.isEmpty(inputWidht)) {
                isEmptyFields = true
                edtWidht.error = "Field ii tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true
                edtHeight.error = "Field ii tidak boleh kosong"
            }
            val length = toDouble(inputLenght)
            val widht = toDouble(inputWidht)
            val height = toDouble(inputHeight)

            if(length == null){
                isInvalidDouble = true
                edtLenght.error = "Field ini harus berupa angka yang valid"
            }
            if(widht == null){
                isInvalidDouble = true
                edtWidht.error = "Field ini harus berupa angka yang valid"
            }
            if(height == null){
                isInvalidDouble = true
                edtHeight.error = "Field ini harus berupa angka yang valid"
            }
            if(!isEmptyFields && !isInvalidDouble){
                val volume = length as Double * widht as Double * height as Double
                tvResult.text = volume.toString()
            }

        }

    }

    private fun toDouble(str: String): Double? {
        return try{
            str.toDouble()
        } catch(e: NumberFormatException){
            null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}
