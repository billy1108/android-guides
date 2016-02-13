package com.caballero.billy.gg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.caballero.billy.gg.adapters.MyAdapter;
import com.caballero.billy.gg.models.Producto;
import com.caballero.billy.gg.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Object> items = new ArrayList<Object>();
        for(int i=0; i < 20; i++){
            if (i%2 == 0){
                int dni = 1234567*i;
                User user = new User("Usuario " + i, "MI APELLIDO", dni );
                items.add(user);
            }else{
                int precio = 12*i;
                Producto producto = new Producto("Producto " + i, "S/." + precio );
                items.add(producto);
            }
        }
        MyAdapter adapter = new MyAdapter(this, items);

        recyclerView = (RecyclerView) findViewById(R.id.recicler);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);



    }
}
