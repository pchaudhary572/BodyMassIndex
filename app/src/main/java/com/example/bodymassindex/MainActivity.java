package com.example.bodymassindex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText heightTXT, weightTXT;
    private TextView resultTV;
    private Double height, weight, result;
    private Button calResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//returning refrence value to view////
        heightTXT = findViewById(R.id.heightID);
        weightTXT = findViewById(R.id.weightID);
        resultTV = findViewById(R.id.resultID);
        calResult = findViewById(R.id.bimID);
        calResult.setOnClickListener(this);
    }

//logic to campare user input and display the result////
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bimID:
                if (Validate()==true){
                    height = Double.parseDouble(heightTXT.getText().toString());
                    weight = Double.parseDouble(weightTXT.getText().toString());
                    height = height*0.01;
                    BMI bmi=new BMI();
                    bmi.setHeight(height);
                    bmi.setWeight(weight);
                    result = bmi.ComputeBMI();
                    resultTV.setText(Double.toString(bmi.ComputeBMI()));

                    if (result <= 18.5) {
                        Toast.makeText(this, "Under Weight", Toast.LENGTH_LONG).show();
                    } else if (result <= 18.5 || result <= 24.5) {
                        Toast.makeText(this, "Normal Weight", Toast.LENGTH_LONG).show();
                    } else if (result <= 24.5 || result <= 29.9) {
                        Toast.makeText(this, "Over Weight", Toast.LENGTH_LONG).show();
                    } else if (result > 30) {
                        Toast.makeText(this, "Obesity", Toast.LENGTH_LONG).show();
                    }
                }
        }
    }
//to validate user from mistypping in text fields///
    private boolean Validate() {
        if (TextUtils.isEmpty(heightTXT.getText())){
            heightTXT.requestFocus();
            heightTXT.setError("Height Must be Entered");
            return  false;
        }
        else if (TextUtils.isEmpty(weightTXT.getText())){
            weightTXT.requestFocus();
            weightTXT.setError("Weight Must be Entered");
            return  false;
        }
        return true;
    }
}
