package com.example.api_listview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class detalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // Referencias a los elementos del layout
        TextView detailTitle = findViewById(R.id.detailTitle);
        ImageView detailImage = findViewById(R.id.detailImage);
        TextView detailCategoria = findViewById(R.id.detailCategoria);
        TextView detailPrecio = findViewById(R.id.detailPrecio);
        TextView detailDescripcion = findViewById(R.id.detailDescripcion);
        Button btnPagar = findViewById(R.id.btnPagar);
        Button btnRegresar = findViewById(R.id.btnRegresar);

        // Obtener los datos pasados por el Intent
        String title = getIntent().getStringExtra("title");
        String category = getIntent().getStringExtra("category");
        String description = getIntent().getStringExtra("description");
        String price = getIntent().getStringExtra("price");
        String image = getIntent().getStringExtra("image");

        // Mostrar los datos en los elementos del layout
        detailTitle.setText("Producto: " + title);
        detailCategoria.setText("Categoría: " + category);
        detailPrecio.setText("Precio: $" + price);
        detailDescripcion.setText(description);

        // Cargar la imagen con Glide
        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.ic_launcher_background) // Imagen de carga
                .error(R.drawable.ic_launcher_foreground) // Imagen en caso de error
                .into(detailImage);

        // Acción para el botón "PAGAR"
        btnPagar.setOnClickListener(v -> {
            Toast.makeText(detalle.this, "El pago se ha realizado con éxito", Toast.LENGTH_SHORT).show();
        });

        // Acción para el botón "REGRESAR"
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(detalle.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
