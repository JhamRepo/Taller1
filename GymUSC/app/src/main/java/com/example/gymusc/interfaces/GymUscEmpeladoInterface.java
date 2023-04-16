package com.example.gymusc.interfaces;

import com.example.gymusc.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GymUscEmpeladoInterface {

    @GET("/consultarAll")
    Call<List<Empleado>> getAll();
}
