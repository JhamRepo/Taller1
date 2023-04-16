package com.example.gymusc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gymusc.interfaces.GymUscEmpeladoInterface;
import com.example.gymusc.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAll();
    }
    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudempelado = retrofit.create(GymUscEmpeladoInterface.class);
        Call<List<Empleado>> call = crudempelado.getAll();

     call.enqueue(new Callback<List<Empleado>>()
        {
            @Override
            public void onResponse
            (Call < List < Empleado >> call, Response < List < Empleado >> response){
            if (!response.isSuccessful()) {
                system.out.println(response.mesaage());
                return;
            }
             listEmpleado = response.body();
             listEmpleado.forEach(p -> System.out.println(p.toString()));
            }
        @Override
        public void onFailure (Call < List < Empleado >> call, Throwable t ){
        System.out.println(t.getMessage());
         }
        });
    }
}