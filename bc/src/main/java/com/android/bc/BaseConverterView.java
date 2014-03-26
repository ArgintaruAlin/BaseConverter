package com.android.bc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.BinaryConverter.BinaryConverter;
import com.DecimalConverter.DecimalConverter;
import com.HexaConverter.HexadecimalConverter;

//
// Created by Alin on 3/2/14.
//
public class BaseConverterView extends Activity {

    EditText focusedControl;
    EditText base16_et;
    EditText base10_et;
    EditText base2_et;

    Button b1_btn;
    Button b2_btn;
    Button b3_btn;
    Button b4_btn;
    Button b5_btn;
    Button b6_btn;
    Button b7_btn;
    Button b8_btn;
    Button b9_btn;
    Button b0_btn;
    Button bA_btn;
    Button bB_btn;
    Button bC_btn;
    Button bD_btn;
    Button bE_btn;
    Button bF_btn;
    Button bBS_btn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        base16_et = (EditText) findViewById(R.id.b16_et);
        base10_et = (EditText) findViewById(R.id.b10_et);
        base2_et = (EditText) findViewById(R.id.b2_et);

        createOnClickListeners();
        createOnTouchListeners();
        createTextChangedListeners();

        base10_et.requestFocus();
        focusedControl = base10_et;
        inputForDecimal();


        Button clear_btn = (Button) findViewById(R.id.clear);

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base10_et.setText("");
                base2_et.setText("");
                base16_et.setText("");
            }
        });

    }

    private void createOnTouchListeners() {

        // Code the suppresses the keyboard from showing when focusing a textEdit

        base16_et.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                focusedControl = base16_et;
                inputForHexadecimal();
                int inType = base16_et.getInputType(); // backup the input type
                base16_et.setInputType(InputType.TYPE_NULL); // disable soft input
                base16_et.onTouchEvent(event); // call native handler
                base16_et.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });

        base10_et.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                focusedControl = base10_et;
                inputForDecimal();
                int inType = base10_et.getInputType(); // backup the input type
                base10_et.setInputType(InputType.TYPE_NULL); // disable soft input
                base10_et.onTouchEvent(event); // call native handler
                base10_et.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });

        base2_et.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                focusedControl = base2_et;
                inputForBinary();
                int inType = base2_et.getInputType(); // backup the input type
                base2_et.setInputType(InputType.TYPE_NULL); // disable soft input
                base2_et.onTouchEvent(event); // call native handler
                base2_et.setInputType(inType); // restore input type
                return true; // consume touch even
            }
        });
    }

    private void createTextChangedListeners() {

        base16_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(focusedControl == base16_et) {
                    if(!base16_et.getText().toString().equals("") && base16_et.getText().length() != 0) {
                        HexadecimalConverter hc = new HexadecimalConverter(base16_et.getText().toString());

                        base16_et.setSelection(base16_et.getText().toString().length());
                        base10_et.setText(hc.getDecimal().toUpperCase());
                        base2_et.setText(hc.getBinary().toUpperCase());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        base10_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(focusedControl == base10_et) {
                    if(!base10_et.getText().toString().equals("")) {
                        DecimalConverter dc = new DecimalConverter(base10_et.getText().toString());

                        base10_et.setSelection(base10_et.getText().toString().length());
                        base2_et.setText(dc.getBinary().toUpperCase());
                        base16_et.setText(dc.getHexadecimal().toUpperCase());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        base2_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(focusedControl == base2_et) {
                    if(!base2_et.getText().toString().equals("")) {
                        BinaryConverter bc = new BinaryConverter(base2_et.getText().toString());

                        base2_et.setSelection(base2_et.getText().toString().length());
                        base10_et.setText(bc.getDecimal().toUpperCase());
                        base16_et.setText(bc.getHexadecimal().toUpperCase());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void inputForDecimal() {
        b1_btn.setEnabled(true);
        b2_btn.setEnabled(true);
        b3_btn.setEnabled(true);
        b4_btn.setEnabled(true);
        b5_btn.setEnabled(true);
        b6_btn.setEnabled(true);
        b7_btn.setEnabled(true);
        b8_btn.setEnabled(true);
        b9_btn.setEnabled(true);
        b0_btn.setEnabled(true);
        bA_btn.setEnabled(false);
        bB_btn.setEnabled(false);
        bC_btn.setEnabled(false);
        bD_btn.setEnabled(false);
        bE_btn.setEnabled(false);
        bF_btn.setEnabled(false);
    }

    private void inputForBinary() {
        b1_btn.setEnabled(true);
        b2_btn.setEnabled(false);
        b3_btn.setEnabled(false);
        b4_btn.setEnabled(false);
        b5_btn.setEnabled(false);
        b6_btn.setEnabled(false);
        b7_btn.setEnabled(false);
        b8_btn.setEnabled(false);
        b9_btn.setEnabled(false);
        b0_btn.setEnabled(true);
        bA_btn.setEnabled(false);
        bB_btn.setEnabled(false);
        bC_btn.setEnabled(false);
        bD_btn.setEnabled(false);
        bE_btn.setEnabled(false);
        bF_btn.setEnabled(false);
    }

    private void inputForHexadecimal() {
        b1_btn.setEnabled(true);
        b2_btn.setEnabled(true);
        b3_btn.setEnabled(true);
        b4_btn.setEnabled(true);
        b5_btn.setEnabled(true);
        b6_btn.setEnabled(true);
        b7_btn.setEnabled(true);
        b8_btn.setEnabled(true);
        b9_btn.setEnabled(true);
        b0_btn.setEnabled(true);
        bA_btn.setEnabled(true);
        bB_btn.setEnabled(true);
        bC_btn.setEnabled(true);
        bD_btn.setEnabled(true);
        bE_btn.setEnabled(true);
        bF_btn.setEnabled(true);
    }

    private void createOnClickListeners() {

        b1_btn = (Button) findViewById(R.id.b1);
        b1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"1");
            }
        });
        b2_btn = (Button) findViewById(R.id.b2);
        b2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"2");
            }
        });
        b3_btn = (Button) findViewById(R.id.b3);
        b3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"3");
            }
        });
        b4_btn = (Button) findViewById(R.id.b4);
        b4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"4");
            }
        });
        b5_btn = (Button) findViewById(R.id.b5);
        b5_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"5");
            }
        });
        b6_btn = (Button) findViewById(R.id.b6);
        b6_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"6");
            }
        });
        b7_btn = (Button) findViewById(R.id.b7);
        b7_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"7");
            }
        });
        b8_btn = (Button) findViewById(R.id.b8);
        b8_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"8");
            }
        });
        b9_btn = (Button) findViewById(R.id.b9);
        b9_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"9");
            }
        });
        b0_btn = (Button) findViewById(R.id.b0);
        b0_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"0");
            }
        });
        bA_btn = (Button) findViewById(R.id.bA);
        bA_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"A");
            }
        });
        bB_btn = (Button) findViewById(R.id.bB);
        bB_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"B");
            }
        });
        bC_btn = (Button) findViewById(R.id.bC);
        bC_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"C");
            }
        });
        bD_btn = (Button) findViewById(R.id.bD);
        bD_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"D");
            }
        });
        bE_btn = (Button) findViewById(R.id.bE);
        bE_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"E");
            }
        });
        bF_btn = (Button) findViewById(R.id.bF);
        bF_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusedControl.setText(focusedControl.getText()+"F");
            }
        });
        bBS_btn = (Button) findViewById(R.id.bBS);
        bBS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(focusedControl != null) {
                    Editable focusedInputValue = focusedControl.getText();
                    if (focusedInputValue.length() > 0)
                    {
                        focusedControl.setText(focusedInputValue.delete
                                (
                                    focusedInputValue.length() - 1,
                                    focusedInputValue.length()
                                ));
                    }
                    else
                    {
                        base10_et.setText("");
                        base2_et.setText("");
                        base16_et.setText("");
                    }
                }
            }
        });
    }
}
