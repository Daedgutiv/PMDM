package com.example.ejercicio1.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio1.BBDD.SQLOH;
import com.example.ejercicio1.R;
import com.example.ejercicio1.core.Contacto;

import java.util.concurrent.ConcurrentNavigableMap;

public class Subactividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactividad_layout);

        ImageButton guardar = (ImageButton) this.findViewById(R.id.btGuardar);
        ImageButton cancelar = (ImageButton) this.findViewById(R.id.btCancelar);

        final EditText nombre = (EditText) this.findViewById(R.id.edNombre);
        final EditText apellido1 = (EditText) this.findViewById(R.id.edApellido1);
        final EditText apellido2 = (EditText) this.findViewById(R.id.edApellido2);
        final EditText email = (EditText) this.findViewById(R.id.edEmail);
        final EditText telefono = (EditText) this.findViewById(R.id.edTelefono);

        final Intent datosEnviados = this.getIntent();
        final int id = datosEnviados.getExtras().getInt("id");

        if (id>0){
            SQLOH sql = new SQLOH(this.getApplicationContext());

            Contacto contacto = sql.getContacto(id);

            nombre.setText(contacto.getNombre());
            apellido1.setText(contacto.getApellido1());
            apellido2.setText(contacto.getApellido2());
            email.setText(contacto.getEmail());
            telefono.setText(contacto.getTelefono());
        }

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subactividad.this.setResult(Activity.RESULT_CANCELED);
                Subactividad.this.finish();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLOH db = new SQLOH(Subactividad.this.getApplicationContext());
                Contacto contacto = new Contacto(nombre.getText().toString(), apellido1.getText().toString(), apellido2.getText().toString(), telefono.getText().toString(), email.getText().toString());
                contacto.setId(id);
                final Intent retData = new Intent();

                if (id<0){
                    db.insertarContacto(contacto);
                } else {
                    db.modificarContacto(contacto);
                }

                Subactividad.this.setResult(Activity.RESULT_OK);
                Subactividad.this.finish();
            }
        });

    }
}
