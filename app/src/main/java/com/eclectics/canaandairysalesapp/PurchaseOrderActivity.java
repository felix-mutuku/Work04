package com.eclectics.canaandairysalesapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PurchaseOrderActivity extends AppCompatActivity {

    ListView items_list_view;
    ArrayList<String> DairyProductsList;
    ArrayList<String> SelectedProductsList;
    TextView delivery_date_txt;
    Calendar myCalendar = Calendar.getInstance();
    ListView selected_items_list_view;
    RelativeLayout relative_orders;
    String selectedProduct;
    int items_quantity;
    Button place_order_btn;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);

        items_list_view = findViewById(R.id.items_list_view);
        delivery_date_txt = findViewById(R.id.delivery_date_txt);
        relative_orders = findViewById(R.id.relative_orders);
        selected_items_list_view = findViewById(R.id.selected_items_list_view);
        place_order_btn = findViewById(R.id.place_order_btn);
        back = findViewById(R.id.back);

        relative_orders.setVisibility(View.GONE);

        DairyProductsList = new ArrayList<String>();
        SelectedProductsList = new ArrayList<String>();

        //initialize the list for products
        getProducts();

        //Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                DairyProductsList);

        //Set The Adapter
        items_list_view.setAdapter(arrayAdapter);

        //register onClickListener to handle click events on each item
        items_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                selectedProduct = DairyProductsList.get(position);

                final Dialog dialog = new Dialog(PurchaseOrderActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_order_quantity);

                TextView item_name = dialog.findViewById(R.id.item_name);
                Button reduce_quantity = dialog.findViewById(R.id.reduce_quantity);
                Button increase_quantity = dialog.findViewById(R.id.increase_quantity);
                Button done = dialog.findViewById(R.id.done);

                item_name.setText(selectedProduct);

                reduce_quantity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //add quantity

                        TextView item_quantity = dialog.findViewById(R.id.item_quantity);

                        //items_quantity = Integer.parseInt(item_quantity.getText().toString());

                        if (items_quantity != 1) {
                            items_quantity = items_quantity - 1;

                            item_quantity.setText(String.valueOf(items_quantity));

                            //Toast.makeText(getApplicationContext(), String.valueOf(items_quantity), Toast.LENGTH_LONG).show();

                            item_quantity.setText(String.valueOf(items_quantity));
                        }
                    }
                });

                increase_quantity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //add quantity

                        TextView item_quantity = dialog.findViewById(R.id.item_quantity);

                        String itemCount = item_quantity.getText().toString();

                        items_quantity = Integer.parseInt(itemCount);

                        items_quantity = items_quantity + 1;

                        //Toast.makeText(getApplicationContext(), String.valueOf(items_quantity), Toast.LENGTH_LONG).show();

                        item_quantity.setText(String.valueOf(items_quantity));
                    }
                });

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //close dialog and add product to list
                        Toast.makeText(getApplicationContext(), "Added : " + selectedProduct + " to order", Toast.LENGTH_LONG).show();

                        getSelectedProducts();

                        final ArrayAdapter<String> arraySelectedAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                SelectedProductsList);
                        selected_items_list_view.setAdapter(arraySelectedAdapter);

                        relative_orders.setVisibility(View.VISIBLE);

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go backwards
                onBackPressed();
            }
        });

        delivery_date_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show date picker
                showDatePicker();
            }
        });

        place_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to back
                onBackPressed();
            }
        });
    }

    private void getSelectedProducts() {
        SelectedProductsList.add(String.format("%s   %d", selectedProduct, items_quantity));
    }

    private void showDatePicker() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                delivery_date_txt.setText(sdf.format(myCalendar.getTime()));
                delivery_date_txt.setTextColor(getResources().getColor(R.color.blackColor));
            }

        };

        new DatePickerDialog(PurchaseOrderActivity.this,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void getProducts() {
        DairyProductsList.add("Yoghurt             45.00      100ml      Cup");
        DairyProductsList.add("Yoghurt             45.00      150ml      Cup");
        DairyProductsList.add("Yoghurt             45.00      250ml      Cup");
        DairyProductsList.add("Yoghurt             45.00      500ml      Cup");
        DairyProductsList.add("Yoghurt             45.00      500ml      Pouch");
        DairyProductsList.add("Yoghurt             45.00      2l         Bucket");
        DairyProductsList.add("Yoghurt             45.00      4l         Bucket");
        DairyProductsList.add("Yoghurt Drink       45.00      250ml      Bottle");
        DairyProductsList.add("Yoghurt Drink       45.00      500ml      Bottle");
        DairyProductsList.add("Yoghurt Drink       45.00      1l         Jerrycan");
        DairyProductsList.add("Yoghurt Drink       45.00      2l         Jerrycan");
        DairyProductsList.add("Yoghurt Drink       45.00      3l         Jerrycan");
        DairyProductsList.add("Maziwa Lala         45.00      250ml      Pouch");
        DairyProductsList.add("Maziwa Lala         45.00      500ml      Pouch");
        DairyProductsList.add("Maziwa Lala         45.00      500ml      Bottle");
        DairyProductsList.add("Maziwa Lala         45.00      1l         Jerrycan");
        DairyProductsList.add("Maziwa Lala         45.00      2l         Jerrycan");
        DairyProductsList.add("Maziwa Lala         45.00      3l         Jerrycan");
    }
}
