package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int priceInitial=5;
    private Object TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if(quantity>=100){
            Toast.makeText(this,"You cannot Go Beyond this!!",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        display(quantity);
        int priceInitial = 5;
        displayPrice((quantity * priceInitial));
    }

    public void decrement(View view) {
        if (quantity <= 0) {
            Toast.makeText(this, "You cannot Go Below this!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (quantity > 0) {
            quantity = quantity - 1;
            display(quantity);
            int priceInitial = 5;
            displayPrice((quantity * priceInitial));

        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void submitOrder(View view) {
        String message="THANKS FOR ORDERING :)";
        String message2="THANKS FOR VISITING :)";
        if(quantity > 0) {
            displayMessage(message);
        }
        else{
            displayMessage(message2);
        }

        CheckBox whippedCreamObj=(CheckBox) findViewById(R.id.checkboxWhipped);
        boolean hasWhippedCream=false;
        hasWhippedCream=whippedCreamObj.isChecked();

        CheckBox chocolateCreamObj=(CheckBox) findViewById(R.id.checkboxChocolate);
        boolean hasChocolateCream=false;
        hasChocolateCream=chocolateCreamObj.isChecked();

        EditText NameObj=(EditText) findViewById(R.id.name);
        String name=NameObj.getText().toString();

        int price=calculatePrice(hasWhippedCream,hasChocolateCream);
        String priceMessage=orderDetails(quantity,price,hasWhippedCream,hasChocolateCream,name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);  //ALL CAPS MEANS CONSTANT
        String[] to={name};
        intent.setData(Uri.parse("mailto: "));
        intent.putExtra(Intent.EXTRA_SUBJECT,"THANKS FOR ORDERING COFFEE :) ");
        intent.putExtra(Intent.EXTRA_TEXT,"YOUR ORDER DETAILS ARE AS FOLLOWS....\n\n" + priceMessage);
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent); //ONLY EMAIL APPS WILL HANDLE IT
        }
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(" " + number);
        priceTextView.setText((NumberFormat.getCurrencyInstance().format(number)));
    }

    private void displayMessage(String message) {
        TextView messageTextView = (TextView) findViewById(R.id.message_text_view);
        messageTextView.setText(message);
    }

    public int calculatePrice(boolean hasWhippedCream,boolean hasChocolateCream){
        int price=5;
        if(hasWhippedCream){
            price+=1;
            }
        if(hasChocolateCream){
            price+=2;
            }

        displayPrice(price*quantity);
        return (price*quantity);
    }
    public String orderDetails(int quantity,int price, boolean hasWhippedCream, boolean hasChocolateCream, String name){
        if(price==0)
            return "You haven't ordered yet!!!!";
        String actualMessage="Order Details";       //DO  BOT FORGET THE += SIGN TO CONCATENATE STRINGS
        actualMessage+="\n\nMail id: " + name;
        actualMessage+="\nQuantity: "+ quantity;
        actualMessage+="\nIs Whipped Cream added ? "+ hasWhippedCream;
        actualMessage+="\nIs Chocolate Topping added ? "+ hasChocolateCream;
        actualMessage+="\nPrice: "+ price;
//        if (hasWhippedCream){
//            actualMessage+="\n\nPlease pay for "+ quantity + " Coffees (with Whipped Cream): Rs. " + price;
//        }
//       else if (hasChocolateCream){
//            actualMessage+="\n\nPlease pay for "+ quantity + " Coffees (with Chocolate Toppings is): Rs. " + price;
//       }
//       else if(!hasWhippedCream && !hasChocolateCream){
//           actualMessage+= "\n\nPlease pay for "+ quantity + "Coffees (with Whipped Cream & Chocolate Toppings is): Rs. " + price;
//        }
        actualMessage+="\n\nYou need to pay Rs. "+price+" for "+quantity+" Coffee Cups!!";
        actualMessage+="\n\nTHANK YOU";
        return actualMessage;

    }

}