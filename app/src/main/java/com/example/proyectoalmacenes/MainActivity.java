package com.example.proyectoalmacenes;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText ingresarTarea;
    private Button btnAgregar, btnMostrar;
    private ListView listarTareas;
    private ArrayList<String> tareas;
    private ArrayAdapter<String> adapter;
    private FirebaseHandler firebaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseHandler = new FirebaseHandler();
        ingresarTarea = findViewById(R.id.editTextTask);
        btnAgregar = findViewById(R.id.buttonAdd);
        btnMostrar = findViewById(R.id.buttonShow);
        listarTareas = findViewById(R.id.listViewTaks);

        tareas = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareas);
        listarTareas.setAdapter(adapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tarea = ingresarTarea.getText().toString();
                if (!tarea.isEmpty()) {
                    firebaseHandler.addTask(tarea);
                    ingresarTarea.setText("");
                }
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseHandler.getTasks(new TaskCallback() {
                    @Override
                    public void onCallback(ArrayList<String> tasks) {

                    }
                }); {}
            }
        })
    ;}
}