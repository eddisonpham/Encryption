package com.example.simpleencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edtInput;
    TextView txtOutput;
    EditText csNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtInput=findViewById(R.id.edtInput);
        txtOutput=findViewById(R.id.txtOutput);
        csNumber=findViewById(R.id.csNumber);
    }
    //this method is used to avoid empty plain text error
    public void btn_click(View view){
        if (edtInput.getText().toString().equals("")||csNumber.getText().toString().equals("")){
            Toast.makeText(this, "Fill in the empty fields",Toast.LENGTH_SHORT).show();
        }else{
            switch(view.getId()){
                case R.id.cs:caesar(view);
                break;
                case R.id.nc:number(view);
                break;
                case R.id.pl:pig(view);
                break;
                case R.id.back:back(view);
                break;
            }
        }

    }
    //this method and number() method works for both negative and positive numbers
    public void caesar(View view){
        String word[] = edtInput.getText().toString().split("");
        String word2="";
        int shiftNumber = Integer.parseInt(csNumber.getText().toString());
        for (int i =0;i<word.length;i++){
            int modulator = (int) 'a';
            if (Character.isUpperCase(word[i].charAt(0))){
                modulator = (int)'A';
            }
            int character = ((((int)word[i].charAt(0)+shiftNumber)%modulator)%26)+modulator;
            word2+=(char)character;
            Log.i("tag",modulator+" "+character);
        }
        txtOutput.setText(word2);
    }
    // starts from 0 (a) to 25 (z), shift # = 0 for regular number encoding
    public void number(View view){
        String word[] = edtInput.getText().toString().split("");
        String word2  = "";
        int shiftNumber = Integer.parseInt(csNumber.getText().toString());
        for (String ch : word){
            word2+=((int)ch.toUpperCase().charAt(0)-65+shiftNumber)%26+" ";
        }
        txtOutput.setText(word2);
    }
    public void pig(View view){
        String sentence[] = edtInput.getText().toString().split(" ");
        for (int i = 0;i<sentence.length;i++) {
            String temp = sentence[i].charAt(0)+"ay";
            sentence[i]=sentence[i].substring(1)+temp;
        }
        String sentence2="";
        for (String word : sentence){
            sentence2+=word+" ";
        }
        txtOutput.setText(sentence2);
    }
    public void back(View view){
        String word[] = edtInput.getText().toString().split("");
        String word2 = "";
        for (int i =word.length-1;i>=0;i--){
            word2+=word[i];
        }
        txtOutput.setText(word2);
    }

}