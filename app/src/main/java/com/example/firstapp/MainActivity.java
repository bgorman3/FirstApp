package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextItemPrice;
    RadioGroup radioGroupSalePercent;
    RadioButton radioButtonSalePercent10;
    RadioButton radioButtonSalePercent15;
    RadioButton radioButtonSalePercent18;
    RadioButton radioButtonSalePercentCustom;

    SeekBar seekBar;
    TextView percentCalc, discountCalc, finalCalc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextItemPrice = findViewById(R.id.editTextItemPrice);
        radioGroupSalePercent = findViewById(R.id.radioGroupSalePercent);
        seekBar = findViewById(R.id.seekBar);
        percentCalc = findViewById(R.id.percentCalc);
        discountCalc = findViewById(R.id.discountCalc);
        finalCalc = findViewById(R.id.finalCalc);



        // Initialize RadioButton variables
        radioButtonSalePercent10 = findViewById(R.id.radioButtonSalePercent10);
        radioButtonSalePercent15 = findViewById(R.id.radioButtonSalePercent15);
        radioButtonSalePercent18 = findViewById(R.id.radioButtonSalePercent18);
        radioButtonSalePercentCustom = findViewById(R.id.radioButtonSalePercentCustom);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Update percentCalc TextView with current SeekBar progress
                percentCalc.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });


        // Reset Button Logic
        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the app state
                editTextItemPrice.setText("");
                radioGroupSalePercent.check(R.id.radioButtonSalePercent10);
                seekBar.setProgress(25);
                discountCalc.setText("0.00");
                finalCalc.setText("0.00");
            }
        });

        // Calculate Button Logic
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Your calculation logic
                // Check for empty item price
                String itemPriceStr = editTextItemPrice.getText().toString();
                if (itemPriceStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Item Price", Toast.LENGTH_SHORT).show();
                    discountCalc.setText("");
                    finalCalc.setText("");
                    return;
                }

                // Retrieve selected sale % value from the radio group
                int selectedSalePercentId = radioGroupSalePercent.getCheckedRadioButtonId();
                double salePercent;
                if (selectedSalePercentId == R.id.radioButtonSalePercent10) {
                    salePercent = 10;
                } else if (selectedSalePercentId == R.id.radioButtonSalePercent15) {
                    salePercent = 15;
                } else if (selectedSalePercentId == R.id.radioButtonSalePercent18) {
                    salePercent = 18;
                } else if (selectedSalePercentId == R.id.radioButtonSalePercentCustom) {
                    salePercent = seekBar.getProgress();
                } else {
                    salePercent = 0;
                }

                // Calculate discount
                double itemPrice = Double.parseDouble(itemPriceStr);
                double discount = (salePercent / 100) * itemPrice;

                // Update discount and final price values
                discountCalc.setText(String.format("%.2f", discount));
                finalCalc.setText(String.format("%.2f", itemPrice - discount));
            }
        });
    }
}

