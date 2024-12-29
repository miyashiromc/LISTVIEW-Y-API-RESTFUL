package com.example.api_listview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    ListView listaropa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        listaropa = (ListView) findViewById(R.id.listaropa);
        Map<String, String> datos = new HashMap<>();
        WebService ws = new WebService("https://fakestoreapi.com/products",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }




    @Override
    public void processFinish(String result) throws JSONException {


        try {
            JSONArray JSONlistaProductos = new JSONArray(result);
            ArrayList<Product> lstProducto = Product.JsonObjectsBuild(JSONlistaProductos);
            AdapterProduct adaptador = new AdapterProduct(this, lstProducto);
            listaropa.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos JSON de usuarios", Toast.LENGTH_SHORT).show();
        }
        listaropa.setOnItemClickListener((parent, view, position, id) -> {
            Product productoSeleccionado = (Product) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, detalle.class);
            // Pasar datos como extras
            intent.putExtra("title", productoSeleccionado.getTitle());
            intent.putExtra("category", productoSeleccionado.getCategoria());
            intent.putExtra("description", productoSeleccionado.getDescription());
            intent.putExtra("price", productoSeleccionado.getPrecio());
            intent.putExtra("image", productoSeleccionado.getImage()); // URL de la imagen
            startActivity(intent);
        });




    }

}