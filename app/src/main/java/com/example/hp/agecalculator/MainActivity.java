package com.example.hp.agecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends Activity {
    private EditText editText1, editText2, editText3;
    private TextView textView1, textView2, textView3, textView4, textView5;
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        textView1=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        datePicker=(DatePicker)findViewById(R.id.datePicker);
        textView5=(TextView)findViewById(R.id.textView5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * This method is invoked when the Calculate DOB button is clicked
     * This method subtracts the age entered from the current date to
     * calculate the date of birth
     * @param view
     */
    public void calculateDob(View view){
        int noOfDays;
        //Extracting year, month and days as integers from the user inputs
        String year=String.valueOf(editText1.getText());
        int yr=Integer.parseInt(year);
        String month=String.valueOf(editText2.getText());
        int mn=Integer.parseInt(month);
        String day=String.valueOf(editText3.getText());
        int d=Integer.parseInt(day);
        /*Creating the Calendar instance to extract the current year,
        current month and current date from the instance
         */
        Calendar c=Calendar.getInstance();
        int currYr=c.get(Calendar.YEAR);
        int currMn=c.get(Calendar.MONTH);
        int currD=c.get(Calendar.DATE);
        /* Subtracting input year from current year to get the output year
          * similarly for month and date */
        int outYr=currYr-yr;
        int outMn=currMn-mn;
        int outD=currD-d;
        /*Handling the case when the output month calculated
        is the above way is less than or equal to zero
         */
        if(outMn<=0){
            outMn=12+outMn;
            outYr--;
        }/* Handling the case when output date calculated is
         less than or equal to zero */
        if(outD<=0){
            outMn--;
            Calendar cal=new GregorianCalendar(outYr, outMn, outD);
            //getting the number of days of the output month
            noOfDays=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            outD=noOfDays+outD;
        }
        datePicker.updateDate(outYr, outMn, outD);
    }
    public void calculateAge(View view){
        int noOfDays;
        /*Extracting year, month and days as integers from the user
            selected value in the DatePicker.
         */
        int inYear=datePicker.getYear();
        int inMonth=datePicker.getMonth();
        int inDate=datePicker.getDayOfMonth();
        /*Creating the Calendar instance to extract the current year,
        current month and current date from the instance
         */
        Calendar c=Calendar.getInstance();
        int currYear=c.get(Calendar.YEAR);
        int currMonth=c.get(Calendar.MONTH);
        int currDate=c.get(Calendar.DATE);
        /* Subtracting input year from current year to get the output year
          * similarly for month and date */
        int outYear=currYear-inYear;
        int outMonth=currMonth-inMonth;
        int outDate=currDate-inDate;
        /*Handling the case when the output month calculated
        is the above way is less than or equal to zero
         */
        if(outMonth<=0){
            outMonth=12+outMonth;
            outYear--;
        }/* Handling the case when output date calculated is
         less than or equal to zero */
        if(outDate<=0){
            outMonth--;
            Calendar cal=new GregorianCalendar(outYear, outMonth, outDate);
            //getting the number of days of the output month
            noOfDays=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            outDate=noOfDays+outDate;
        }
        textView5.setText("Your age is "+outYear+" years "+outMonth+" months"+" and "+outDate+" days.");
        textView5.setVisibility(View.VISIBLE);
    }
    /* This method is invoked when the Clear button is clicked
    *  This method assigns empty Strings to the three editTexts
    *   and for the textBox which is used to display age*/
    public void clear(View view) {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        textView5.setText("");
    }
}
