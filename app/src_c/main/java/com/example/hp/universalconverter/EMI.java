package com.example.hp.universalconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Debabrata Mukherjee on 02-03-2019.
 */
public class EMI extends Activity {
    EditText name;
    EditText outStanding;
    EditText rateOfInterest;
    EditText tenureYears;
    EditText tenureMonths;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);
        name = (EditText) findViewById(R.id.editText7);
        outStanding = (EditText) findViewById(R.id.editText8);
        rateOfInterest = (EditText) findViewById(R.id.editText11);
        tenureYears = (EditText) findViewById(R.id.editText9);
        tenureMonths = (EditText) findViewById(R.id.editText10);
        result = (TextView) findViewById(R.id.textView18);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
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


    public void calculateEmi(View view) {
        Integer tenureYear, tenureMonth;
        Double tenure, emi;
        String sName = name.getText().toString();
        String os = outStanding.getText().toString();
        String tYear = tenureYears.getText().toString();
        String tMonth = tenureMonths.getText().toString();
        String sROI = rateOfInterest.getText().toString();
        if (!os.isEmpty() && (!tYear.isEmpty() || !tMonth.isEmpty()) && !sROI.isEmpty()) {
            Double outStand = Double.valueOf(os);
            if (tYear.isEmpty()) {
                tenureYear = 0;
                tenure=Integer.valueOf(tMonth)*1.0;
            } else if (tMonth.isEmpty()) {
                tenureMonth = 0;
                tenure=Integer.valueOf(tYear)*1.0;
            }else {
                tenureYear = Integer.valueOf(tYear);
                tenureMonth = Integer.valueOf(tMonth);
                tenure = tenureYear * 12.0 + tenureMonth;
            }
                Double roi = Double.valueOf(sROI);
                roi = roi / 12 / 100;
                emi = outStand * roi * Math.pow(1 + roi, tenure) / (Math.pow(1 + roi, tenure) - 1);
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                result.setText("Hi " + sName + " your emi is: " + df.format(emi));
                result.setVisibility(View.VISIBLE);
                Toast t = Toast.makeText(this, "The emi is " + df.format(emi), Toast.LENGTH_LONG);
                t.show();

        } else if (os.isEmpty()) {
            Toast t = Toast.makeText(this, "Please enter the outstanding amount", Toast.LENGTH_LONG);
            t.show();
        } else if (sROI.isEmpty()) {
            Toast t = Toast.makeText(this, "Please enter the rate of interest", Toast.LENGTH_LONG);
            t.show();
        } else if (tYear.isEmpty() && tMonth.isEmpty()) {
            Toast t = Toast.makeText(this, "Please enter the tenure", Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void clear(View view) {
        name.setText("");
        outStanding.setText("");
        tenureYears.setText("");
        tenureMonths.setText("");
        rateOfInterest.setText("");
        result.setText("");
    }
}
