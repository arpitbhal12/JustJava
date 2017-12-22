package com.example.arpit.justjava;


/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int noc=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //int noc=2;
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name      =  nameField.getText().toString();
        Log.v("MainActicity","Name: " + name);
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActicity","Has whipped cream? " + hasWhippedCream);
        Log.v("MainActicity","Has chocolate? " + hasChocolate);
        int price= calculatePrice(hasWhippedCream,hasChocolate);

        String priceMessage=createOrderSummary(price,hasWhippedCream,hasChocolate,name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: "));
       // intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + name );
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(priceMessage);
        //displayPrice(noc*5);

    }

    private String createOrderSummary(int price,boolean hasWhippedCream,boolean hasChocolate,String name){
       // int price= calculatePrice();

        String priceMessage="Name: " + name;
        priceMessage += "\nHas Whipped cream? " + hasWhippedCream;
        priceMessage += "\nHas Chocolate? " + hasChocolate;
        priceMessage = priceMessage + "\nNumber of cups: " + noc;
        priceMessage = priceMessage + "\nTotal: Rs." + price;
        priceMessage = priceMessage + "\nThank You!!";
        return priceMessage;
       // displayMessage(priceMessage);
    }

    private int calculatePrice(boolean hasWhippedCream,boolean hasChocolate){
        int price=noc*5;
        if(hasWhippedCream==true)
            price+=noc*1;

        if(hasChocolate==true)
            price+=noc*2;

        return price;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView)  findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }

//method for increment
    public void increment(View view) {
        if(noc<100)
         noc=noc+1;

        display(noc);
        //displayPrice(noc*10);
    }

//method for decrement
    public void decrement(View view) {
       // int noc=2;
        if(noc>1)
            noc--;

        display(noc);
        //displayPrice(noc*10);
    }

    /**
     * This method displays the given quantity value on the screen.
     */




    /**
     * This method displays the given price on the screen.
     */
    /*
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/
}