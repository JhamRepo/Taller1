package com.example.gymusc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymusc.interfaces.GymUscEmpeladoInterface;
import com.example.gymusc.model.Empleado;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private EditText editTextId;
        private Button buttonEliminar;
        private Button buttonGuardar;
        private Button buttonConsultarID, buttonConsultar, buttonActualizar;
        private EditText editTextNombre;
        private EditText editTextPassword;
        private EditText editTextEmail;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getAll();
            //create();


            // Pasar valor de los elementos en la interfaz
            editTextId = findViewById(R.id.editText_id);
            buttonEliminar = findViewById(R.id.button_eliminar);
            editTextNombre = findViewById(R.id.editText_nombre);
            editTextPassword = findViewById(R.id.editText_password);
            editTextEmail = findViewById(R.id.editText_email);
            buttonGuardar = findViewById(R.id.button_guardar);
            buttonConsultarID = findViewById(R.id.button_con_id);
            buttonConsultar = findViewById(R.id.button_consulta);
            buttonActualizar = findViewById(R.id.button_actualiza);

            // Realizar accion
            buttonEliminar.setOnClickListener(this);
            buttonGuardar.setOnClickListener(this);
            buttonConsultarID.setOnClickListener(this);
            buttonConsultar.setOnClickListener(this);
            buttonActualizar.setOnClickListener(this);
        }

        //METODO PARA EL BOTON DE ELIMINAR POR ID
        @Override
        public void onClick(View v) {
            if (v == buttonEliminar) {
                // Obtener el ID ingresado por el usuario
                Long id = Long.parseLong(editTextId.getText().toString());

                // Eliminar el empleado correspondiente
                delete(id);
            } else if (v == buttonGuardar) {
                // Obtener el ID ingresado por el usuario
                String nom = editTextNombre.getText().toString();
                String pass = editTextPassword.getText().toString();
                String ema = editTextEmail.getText().toString();

                // Eliminar el empleado correspondiente
                create(nom,pass,ema);
            } else if (v == buttonConsultarID) {
                // Obtener el ID ingresado por el usuario
                Long id = Long.parseLong(editTextId.getText().toString());

                // Buscar el empleado correspondiente
                getById(id);
            }else if (v == buttonConsultar) {
                // Buscar el empleado correspondiente
                getAll();
            }else if ( v== buttonActualizar){
                // Obtener el ID ingresado por el usuario
                String nom = editTextNombre.getText().toString();
                String pass = editTextPassword.getText().toString();
                String ema = editTextEmail.getText().toString();
                Long id = Long.parseLong(editTextId.getText().toString());

                // Eliminar el empleado correspondiente
                update(id,nom,pass,ema);
            }
        }

         // METODO PARA CONSULTAR
         private void getAll() {
             //nuevo paquete JSon para aceptar los mal formados
             //Gson gson = new GsonBuilder().setLenient().create();
             Retrofit retrofit = new Retrofit.Builder()
                     // .baseUrl("http://localhost:8080/")
                     .baseUrl("http://10.10.11.205:8081/")
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
             GymUscEmpeladoInterface crudempelado = retrofit.create(GymUscEmpeladoInterface.class);
             Call<List<Empleado>> call = crudempelado.getAll();

             call.enqueue(new Callback<List<Empleado>>()
             {
                 @Override
                 public void onResponse
                         (Call < List < Empleado >> call, Response < List < Empleado >> response){
                     if (!response.isSuccessful()) {
                         //System.out.println(response.message());
                         Log.e("Response err:,", response.message());
                         return;
                     }
                     List<Empleado> listEmpleado = response.body();
                     //listEmpleado.forEach(p -> System.out.println(p.toString()));
                     listEmpleado.forEach(p-> Log.i("Empleados: ", p.toString()));
                 }
                 @Override
                 public void onFailure (Call < List < Empleado >> call, Throwable t ){
                     //System.out.println(t.getMessage());
                     Log.e("Throw error:",t.getMessage());
                 }
             });
         }

    //METODO PARA GUARDAR O CREAR NUEVO OBJETO DIRECTAMENTE
    private void create(String nombre, String password, String email) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.11.205:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GymUscEmpeladoInterface crudempelado = retrofit.create(GymUscEmpeladoInterface.class);
        Empleado empleado = new Empleado(null, nombre, password, email);
        Call<Empleado> call = crudempelado.createEmployee(empleado);
        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err:", response.message());
                    return;
                }
                Empleado empleado = response.body();
                Log.i("Empleado creado:", empleado.toString());
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Throw error:", t.getMessage());
            }
        });
    }

    //METOO PARA ELIMINAR POR ID
    private void delete(Long id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.11.205:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GymUscEmpeladoInterface crudempelado = retrofit.create(GymUscEmpeladoInterface.class);

        Call<Void> call = crudempelado.deleteEmployee(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err:", response.message());
                    return;
                }
                Log.i("Empleado eliminado:", "Empleado eliminado con éxito");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Throw error:", t.getMessage());
            }
        });
    }
    //METODO PARA CONSULTAR POR ID
    private void getById(Long id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.11.205:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GymUscEmpeladoInterface crudempelado = retrofit.create(GymUscEmpeladoInterface.class);
        Call<Empleado> call = crudempelado.getById(id);

        call.enqueue(new Callback<Empleado>()
        {
            @Override
            public void onResponse(Call < Empleado > call, Response < Empleado > response){
                if (!response.isSuccessful()) {
                    Log.e("Response err:", response.message());
                    return;
                }
                Empleado empleado = response.body();
                Log.i("Empleado encontrado:", empleado.toString());
            }
            @Override
            public void onFailure (Call < Empleado > call, Throwable t ){
                Log.e("Throw error:",t.getMessage());
            }
        });
    }

    private void update(Long id, String nombre, String password, String email) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.11.205:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GymUscEmpeladoInterface crudempelado = retrofit.create(GymUscEmpeladoInterface.class);
        Empleado updatedEmpleado = new Empleado(id, nombre, password, email);
        Call<Empleado> call = crudempelado.updateEmployee(updatedEmpleado);

        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (!response.isSuccessful()) {
                    // mensaje de error
                    Toast.makeText(MainActivity.this, "Error al actualizar el empleado", Toast.LENGTH_SHORT).show();
                    return;
                }

                // correcto
                Toast.makeText(MainActivity.this, "Empleado actualizado correctamente", Toast.LENGTH_SHORT).show();
                getAll();
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                // Si ocurrió algún error en la petición, mostramos un mensaje de error
                Toast.makeText(MainActivity.this, "Error al actualizar el empleado: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
