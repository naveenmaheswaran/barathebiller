package com.example.ebiller;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;



public class bill_activity extends AppCompatActivity {
    Button b_get,b_add,bill_b_bill;
    EditText productid,bill_et_quantity;
    TextView name,bil_tv_price,bill_tv_bill;
    float total = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_activity);

        b_get = findViewById(R.id.bill_b_get);
        b_add = findViewById(R.id.bill_b_add);
        bill_et_quantity = findViewById(R.id.bill_et_quantity);
        productid = findViewById(R.id.bill_et_productid) ;
        name = findViewById(R.id.bill_tv_name);
        bill_b_bill = findViewById(R.id.bill_b_bill);
        bil_tv_price = findViewById(R.id.bil_tv_price);
        bill_tv_bill  = findViewById(R.id.bill_tv_bill);

        final int[] price = {0};
        final String[] namen = new String[1];
        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        final Calendar[] calander = new Calendar[1];
        final SimpleDateFormat[] simpledateformat = new SimpleDateFormat[1];







        b_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    DatabaseReference myRef = database.getReference(String.valueOf(productid.getText()) + "/NAME");
                    DatabaseReference myRef1 = database.getReference(String.valueOf(productid.getText()) + "/PRICE");

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            String value = dataSnapshot.getValue(String.class);
                            name.setText("Name : " + value);
                            namen[0] = value;
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                } catch (Exception e) {
                    bil_tv_price.setText("Enter the Product ID");

                }
                    try {
                        DatabaseReference myRef1 = database.getReference(String.valueOf(productid.getText()) + "/PRICE");


                        myRef1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                String value = dataSnapshot.getValue(String.class);
                                bil_tv_price.setText("Price : " + value);
                                price[0] = Integer.parseInt(value);
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });


                    } catch (Exception ee) {
                        bil_tv_price.setText("Enter the Product ID");

                    }
                }

        });



        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 int quant = Integer.parseInt(String.valueOf(bill_et_quantity.getText()));
                 float ppp = quant * price[0];
                 total+=ppp;
                bill_tv_bill.setText(bill_tv_bill.getText()+"\n"+namen[0] + "\t\t\tX\t\t\t"+String.valueOf(quant)+ "\t\t\t=\t\t\t"+ppp);
                //bill_tv_bill.setText("fdsf");
            }
        });







        /////////////gen Bill



        bill_b_bill.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String st = String.valueOf(bill_tv_bill.getText());
                String path = getExternalFilesDir(null).toString()+"/bill.pdf";
                File file = new File(path);
                if(!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {

                    }
                }
                Document document = new com.itextpdf.text.Document(PageSize.A4,50,50,50,50);

                try{
                    PdfWriter.getInstance(document,new FileOutputStream(file.getAbsoluteFile()));
                }catch (Exception e){

                }
                try {
                    // creation of the different writers
                    document.open();
                    calander[0] = Calendar.getInstance();
                    simpledateformat[0] = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String Date = simpledateformat[0].format(calander[0].getTime());
                    document.add(new Paragraph("XYZ Industries \t\t\t\t\t\t Zxy yxz yyy \nContact \t\t\t : XXXXXXXXX"));
                    document.add(new Paragraph(Date));



                    document.add(new Paragraph("------------------------------------------------------- E-Biller  --------------------------------------------------------"));


//

                    document.add(new Paragraph("\n\nTotal : "+total));
                    document.add(new Paragraph(st));
                    document.add(new Paragraph("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTotal : "+total));
                    document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n-------------------------------------------------------  9500372782  ------------------------------------------------"));
                    document.add(new Paragraph("Barath"));
//
                } catch(DocumentException de) {
                    System.err.println(de.getMessage());
                }

                document.close();
                finish();








            }
        });

    }
}
