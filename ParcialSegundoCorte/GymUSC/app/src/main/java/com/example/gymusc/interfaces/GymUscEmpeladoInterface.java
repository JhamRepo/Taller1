package com.example.gymusc.interfaces;

import com.example.gymusc.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GymUscEmpeladoInterface {

    @GET("/consultarAll")
    Call<List<Empleado>> getAll();

    //MEtodo para guardar directo
    @POST("/guardar")
    Call<Empleado> createEmployee(@Body Empleado empleado);
//metodo para consultar por id en la API
    @GET("user/consultar/{id}")
    Call<Empleado> getById(@Path("id") Long id);
//metodo para actualizar los datos
    @PUT("user/actualizar")
    Call<Empleado> updateEmployee(@Body Empleado empleado);
//metodo para eliminar empleado por el ID
    @DELETE("/user/{id}")
    Call<Void> deleteEmployee(@Path("id") Long id);


}
