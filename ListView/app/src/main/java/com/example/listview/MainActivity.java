package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private String[] paises = { "Argentina", "Chile", "Paraguay", "Bolivia", "Peru", "Ecuador", "Brasil", "Colombia", "Venezuela", "Uruguay" };
    private String[] habitantes = { "40000000", "17000000", "6500000", "10000000", "30000000", "14000000", "183000000", "44000000", "29000000", "3500000" };

    private TextView tv1;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    tv1=findViewById(R.id.tv1);
    lv1=findViewById(R.id.list1);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, paises);
    lv1.setAdapter(adapter);


    lv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView adapterView, View view, int i, long l) {
            tv1.setText("Población de "+ lv1.getItemAtPosition(i) + " es "+ habitantes[i]);}
        });
   }
}