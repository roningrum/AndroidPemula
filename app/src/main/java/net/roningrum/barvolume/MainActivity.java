package net.roningrum.barvolume;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtWidht;
    private EditText edtLenght;
    private EditText edtHeight;
    private Button btnCalculate;
    private TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtWidht = findViewById(R.id.edt_width);
        edtLenght = findViewById(R.id.edt_length);
        edtHeight = findViewById(R.id.edt_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if(savedInstanceState !=null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_calculate) {
            String inputLength = edtLenght.getText().toString();
            String inputWidht = edtWidht.getText().toString();
            String inputHeight = edtHeight.getText().toString();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtLenght.setError("Field ini tidak boleng kosong");
            }
            if (TextUtils.isEmpty(inputWidht)) {
                isEmptyFields = true;
                edtWidht.setError("Field ini tidak boleng kosong");
            }
            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleng kosong");
            }
            Double length = toDouble(inputLength);
            Double widht = toDouble(inputWidht);
            Double height = toDouble(inputHeight);

            if (length == null) {
                isInvalidDouble = true;
                edtLenght.setError("Field ini harus berupa angka yang valid");
            }
            if (widht == null) {
                isInvalidDouble = true;
                edtWidht.setError("Field ini harus berupa angka yang valid");
            }
            if (height == null) {
                isInvalidDouble = true;
                edtHeight.setError("Field ini harus berupa angka yang valid");
            }
            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * widht * height;
                tvResult.setText(String.valueOf(volume));
            }

        }
    }

    private Double toDouble(String str) {
        try{
            return  Double.valueOf(str);
        } catch (NumberFormatException e){
            return  null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
}
