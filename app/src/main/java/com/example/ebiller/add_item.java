package com.example.ebiller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_item extends AppCompatActivity {

    Button additem_b_additem;
    EditText additem_et_productid,additem_et_productname,
    additem_et_price,additem_et_priceunit,
            additem_et_stock,additem_et_stockunit;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);
        additem_b_additem = findViewById(R.id.additem_b_additem);
        additem_et_productid = findViewById(R.id.additem_et_productid);
        additem_et_productname = findViewById(R.id.additem_et_productname);
        additem_et_price = findViewById(R.id.additem_et_price);
        additem_et_priceunit = findViewById(R.id.additem_et_priceunit);
        additem_et_stock = findViewById(R.id.additem_et_stock);
        additem_et_stockunit = findViewById(R.id.additem_et_stockunit);
        additem_b_additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    String id;// name, price, stock, price_unit, stock_unit;
                    id = String.valueOf(additem_et_productid.getText());
                    DatabaseReference myRef = database.getReference(id);

                    myRef.child("NAME").setValue(String.valueOf(additem_et_productname.getText()));
                    myRef.child("PRICE").setValue(String.valueOf(additem_et_price.getText()));
                    myRef.child("PRICEUNIT").setValue(String.valueOf(additem_et_priceunit.getText()));
                    myRef.child("STOCK").setValue(String.valueOf(additem_et_stock.getText()));


                    finish();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
