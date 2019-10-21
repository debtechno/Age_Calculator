package com.example.hp.universalconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class UnitConversion extends Activity {
    private Spinner spinner1,spinner2;
    private EditText length, output;
    private TextView textView;
    DecimalFormat df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);
        length=(EditText) findViewById(R.id.editText12);
        output= (EditText) findViewById(R.id.editText13);
        textView= (TextView) findViewById(R.id.textView04);
        df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unit_conversion, menu);
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


    public void lengthUnitConverter(View view) {
        output.setText("");
        textView.setVisibility(View.INVISIBLE);
        spinner1= (Spinner) findViewById(R.id.spinner2);
        spinner2= (Spinner) findViewById(R.id.spinner3);
        String unit1=String.valueOf(spinner1.getSelectedItem());
        String unit2=String.valueOf(spinner2.getSelectedItem());
        String value= length.getText().toString();
        double input=Double.valueOf(value);
        if(unit1.equalsIgnoreCase("feet") && unit2.equalsIgnoreCase("metres"))
            output.setText(df.format(input*0.3048));
        else if(unit1.equalsIgnoreCase("metres") && unit2.equalsIgnoreCase("feet"))
            output.setText(df.format(input*3.28));
        else if(unit1.equalsIgnoreCase("miles") && unit2.equalsIgnoreCase("km"))
            output.setText(df.format(input*1.609344));
        else if(unit1.equalsIgnoreCase("km") && unit2.equalsIgnoreCase("miles"))
            output.setText(df.format(input*0.621371));
        else if(unit1.equalsIgnoreCase("cm") && unit2.equalsIgnoreCase("inches"))
            output.setText(df.format(input*0.3937));
        else if(unit1.equalsIgnoreCase("inches") && unit2.equalsIgnoreCase("cm"))
            output.setText(df.format(input*2.54));
        else {
            textView.setText("This conversion is not supported yet");
            textView.setVisibility(View.VISIBLE);
        }

    }

    public void clear(View view) {
        length.setText("");
        output.setText("");
        textView.setText("");
    }
}
