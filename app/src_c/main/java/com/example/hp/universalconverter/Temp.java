package com.example.hp.universalconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/*
 Created by Debabrata Mukherjee
 10-02-2019
 */
public class Temp extends Activity {
    private Spinner spinner;
    private EditText temperature;
    private TextView text1;
    private TextView text2;
    DecimalFormat df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        temperature=(EditText) findViewById(R.id.editText4);
        text1=(TextView) findViewById(R.id.textView8);
        text2=(TextView) findViewById(R.id.textView9);
        df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void convertTemp(View view) {
        spinner = (Spinner) findViewById(R.id.spinner);
        String unit=String.valueOf(spinner.getSelectedItem());
        String value=temperature.getText().toString();
        if(unit.equals("celsius"))
            convertFromCelsius(value);
        else if(unit.equals("fahrenheit"))
             convertFromFahrenheit(value);
        else
             convertFromKelvin(value);
    }

    public void convertFromKelvin(String value) {
        double k=Double.valueOf(value);
        double c=k-273.15;
        double f=(c*9)/5+32;
        text1.setText("The temperature in celsius is: "+df.format(c));
        text1.setVisibility(View.VISIBLE);
        text2.setText("The temperature in fahrenheit is: " + df.format(f));
        text2.setVisibility(View.VISIBLE);
    }


    public void convertFromFahrenheit(String value) {
        double f=Double.valueOf(value);
        double c=(f-32)*5/9;
        double k=c+273.15;
        text1.setText("The temperature in celsius is: "+df.format(c));
        text1.setVisibility(View.VISIBLE);
        text2.setText("The temperature in kelvin is: " + df.format(k));
        text2.setVisibility(View.VISIBLE);
    }

    public void convertFromCelsius(String value) {
        double cel=Double.valueOf(value);
        double f=(cel*9)/5+32;
        double k=cel+273.15;
        text1.setText("The temperature in Fahrenheit is: " + df.format(f) + "\n");
        text2.setText("The temperature in kelvin is: " + df.format(k));
        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
    }

}
