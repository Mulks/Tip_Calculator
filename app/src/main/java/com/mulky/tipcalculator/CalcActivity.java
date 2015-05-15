package com.mulky.tipcalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.Format;


public class CalcActivity extends ActionBarActivity {

    final Context context = this;

    String bill = "";
    String tipPercent = "";

    private double billTotal = 0;
    private double tip = 0;
    private double roundedTotal = 0;
    private double roundedTip = 0;
    private int split = 1;
    private boolean roundBill = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
    }



    //private EditText ogTip = (EditText)findViewById(R.id.tipText);
    //private TextView tipTextView = (TextView)findViewById(R.id.tipMoney);
    //private TextView billTextView = (TextView)findViewById(R.id.totalMoney);

    public void calcTip( View view  ){
        EditText ogBill = (EditText)findViewById(R.id.tipText);
        EditText ogTip = (EditText)findViewById(R.id.billText);
        TextView tipTextView = (TextView)findViewById(R.id.tipMoney);
        TextView billTextView = (TextView)findViewById(R.id.totalMoney);
        bill = ogBill.getText().toString();
        tipPercent = ogTip.getText().toString();

        Format format = new DecimalFormat( ".00");

        //Toast.makeText(this, bill, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, tipPercent, Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        if( !bill.isEmpty() && !tipPercent.isEmpty() ){
            tip = Double.parseDouble(bill) * (Double.parseDouble(tipPercent) / 100);
            billTotal = ( (Double.parseDouble(bill) + tip)/(split) );

            String tipString = new DecimalFormat("###.00").format(tip);
            String totalString = new DecimalFormat("###.00").format(billTotal);

            //Toast.makeText(this, tipString, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, totalString, Toast.LENGTH_SHORT).show();
            if( roundBill ){

                roundedTotal = billTotal;
                roundedTotal = Math.ceil(roundedTotal);
                roundedTip = ( tip + ( roundedTotal - billTotal ));

                String roundedTipString = new DecimalFormat("###.00").format(roundedTip);
                String roundedTotalString = new DecimalFormat("###.00").format(roundedTotal);

                tipTextView.setText( roundedTipString );
                billTextView.setText( roundedTotalString );
            }
            else if( !roundBill){
                tipTextView.setText( tipString );
                billTextView.setText( totalString);
            }

        }


    }

    public void calcTip( ){
        EditText ogBill = (EditText)findViewById(R.id.tipText);
        EditText ogTip = (EditText)findViewById(R.id.billText);
        TextView tipTextView = (TextView)findViewById(R.id.tipMoney);
        TextView billTextView = (TextView)findViewById(R.id.totalMoney);
        bill = ogBill.getText().toString();
        tipPercent = ogTip.getText().toString();

        Format format = new DecimalFormat( ".00");

        //Toast.makeText(this, bill, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, tipPercent, Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        if( !bill.isEmpty() && !tipPercent.isEmpty() ){
            tip = Double.parseDouble(bill) * (Double.parseDouble(tipPercent) / 100);
            billTotal = ( (Double.parseDouble(bill) + tip)/(split) );

            String tipString = new DecimalFormat("###.00").format(tip);
            String totalString = new DecimalFormat("###.00").format(billTotal);

            //Toast.makeText(this, tipString, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, totalString, Toast.LENGTH_SHORT).show();
            if( roundBill ){

                roundedTotal = billTotal;
                roundedTotal = Math.ceil(roundedTotal);
                roundedTip = ( tip + ( roundedTotal - billTotal ));

                String roundedTipString = new DecimalFormat("###.00").format(roundedTip);
                String roundedTotalString = new DecimalFormat("###.00").format(roundedTotal);

                tipTextView.setText( roundedTipString );
                billTextView.setText( roundedTotalString );
            }
            else if( !roundBill){
                tipTextView.setText( tipString );
                billTextView.setText( totalString);
            }

        }


    }

    public void clearScreen( MenuItem item ){
        EditText ogBill = (EditText)findViewById(R.id.tipText);
        EditText ogTip = (EditText)findViewById(R.id.billText);
        TextView tipTextView = (TextView)findViewById(R.id.tipMoney);
        TextView billTextView = (TextView)findViewById(R.id.totalMoney);

        ogBill.setText( "" );
        ogTip.setText( "" );
        tipTextView.setText( "0" );
        billTextView.setText( "0" );
    }

    public void splitBill( MenuItem item ){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final EditText editText= new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setMessage("Enter How Many People");
        alert.setTitle("Split Bill");

        alert.setView(editText);

        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    split = Integer.parseInt(editText.getText().toString());
                    if (split == 0) {
                        split = 1;
                    }
                    calcTip();
                }

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();

    }

    public void roundBill( MenuItem item ){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Round Bill");


        alert.setPositiveButton("Round", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                roundBill = true;
                calcTip();
            }
        });

        alert.setNegativeButton("Don't Round", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                roundBill = false;
                calcTip();
            }
        });

        alert.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/
        switch (item.getItemId()) {
            case R.id.action_clear:
                return true;
            case R.id.action_split:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
