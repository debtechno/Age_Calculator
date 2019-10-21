package com.example.hp.universalconverter;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/*
 Created by Debabrata Mukherjee on
 10-02-2019
 */
public class BMI extends Activity {
    private EditText EName,EWeight, EHeight;
    private TextView result;
    private String name;
    private EditText fHeight,fInches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        EName=(EditText) findViewById(R.id.editText);
        EWeight=(EditText) findViewById(R.id.editText2);
        EHeight=(EditText) findViewById(R.id.editText3);
        fHeight=(EditText) findViewById(R.id.editText5);
        fInches=(EditText) findViewById(R.id.editText6);
        result=(TextView) findViewById(R.id.textView6);
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

    public void calculateBmi(View view) {
        double ht;
        String sWeight=EWeight.getText().toString();
        double wt=Double.valueOf(sWeight);
        String sHeight=EHeight.getText().toString();
        String hFoot=fHeight.getText().toString();
        String hInches=fInches.getText().toString();
        double foot, inches;
        if(hFoot!=null && !hFoot.equals("")){
            foot=Double.valueOf(hFoot);
            if(!hInches.equals("") && hInches!=null && !hInches.equals("0")){
                inches=Double.valueOf(hInches);
                ht=foot*.3048+inches*.0254;
            }else
                ht = foot * .3048;

        }else{
            ht = Double.valueOf(sHeight);
        }
        double bmi=wt/(ht*ht);
        name=EName.getText().toString();
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        result.setText("Hi " + name + "!\n" + "Your BMI is: " + df.format(bmi));
        result.setVisibility(view.VISIBLE);
        //createPdf(result.getText());
    }

    public void clear(View view) {
        EName.setText("");
        EWeight.setText("");
        EHeight.setText("");
        fHeight.setText("");
        fInches.setText("");
    }

    /*private void createPdf(CharSequence text) {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(new Rect(0, 0, 100, 100), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        View content = getContentView();

    }*/
}
