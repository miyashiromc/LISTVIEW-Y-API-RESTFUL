package com.example.api_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide; // Importar Glide
import com.bumptech.glide.request.RequestOptions; // Para opciones adicionales

import java.util.ArrayList;

public class AdapterProduct extends ArrayAdapter<Product> {
    public AdapterProduct(Context context, ArrayList<Product> productos) {
        super(context, 0, productos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener el producto actual
        Product producto = getItem(position);

        // Inflar la vista personalizada si no está reutilizada
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_product, parent, false);
        }

        // Referencias a los elementos del layout
        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtCategory = convertView.findViewById(R.id.txtCategory);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        TextView txtDescription = convertView.findViewById(R.id.txtDescripcion);

        // Configurar los datos del producto
        txtTitle.setText(producto.getTitle()); // Asume que es "title" en JSON
        txtCategory.setText(producto.getCategoria());
        txtDescription.setText(producto.getDescription());
        txtPrice.setText("$" + producto.getPrecio());

        // Cargar la imagen con Glide
        Glide.with(getContext())
                .load(producto.getImage()) // URL de la imagen desde el JSON
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background) // Imagen que se muestra mientras se carga
                        .error(R.drawable.ic_launcher_foreground)) // Imagen que se muestra si ocurre un error
                .into(imgProduct);


        return convertView;
    }
}
