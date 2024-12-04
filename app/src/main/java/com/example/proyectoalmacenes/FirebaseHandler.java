package com.example.proyectoalmacenes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHandler {
    private DatabaseReference databaseReference;

    public FirebaseHandler() {
        // Inicializa la base de datos de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("tareas");
    }

    public void addTask(String task) {
        String id = databaseReference.push().getKey(); // Genera un ID único
        databaseReference.child(id).setValue(task); // Agrega la tarea a Firebase
    }

    public void getTasks(final TaskCallback callback) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> tasks = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    tasks.add(snapshot.getValue(String.class));
                }
                callback.onCallback(tasks);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseHandler", "Error al obtener tareas", databaseError.toException());
            }
        });
    }
    }