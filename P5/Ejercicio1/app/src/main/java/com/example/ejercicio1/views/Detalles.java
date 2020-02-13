package com.example.ejercicio1.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio1.BBDD.SQLOH;
import com.example.ejercicio1.R;
import com.example.ejercicio1.core.Contacto;

public class Detalles extends AppCompatActivity {

    protected static final int CODIGO_EDICION_CONTACTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_layout);

        ImageButton btBack = (ImageButton) this.findViewById(R.id.btBack);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Detalles.this.finish();
            }
        });

        ImageButton btEditar = (ImageButton) this.findViewById(R.id.btEditor);

        final Intent datosEnviados = this.getIntent();
        final int id = datosEnviados.getExtras().getInt("id");

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subactividad = new Intent(Detalles.this, Subactividad.class);

                subactividad.putExtra("id", id);

                Detalles.this.startActivityForResult(subactividad, CODIGO_EDICION_CONTACTO );
            }
        });

        SQLOH sql = new SQLOH(this.getApplicationContext());

        Contacto contacto = sql.getContacto(id);

        TextView telefono = (TextView) this.findViewById(R.id.tel);

        telefono.setText(contacto.getTelefono());

        TextView email = (TextView) this.findViewById(R.id.detalleEmail);

        email.setText(contacto.getEmail());

        String nombreCompleto = contacto.getNombre() + " " + contacto.getApellido1() + " " + contacto.getApellido2();

        TextView tvnombreCompleto = (TextView) this.findViewById(R.id.nombreCompleto);

        tvnombreCompleto.setText(nombreCompleto);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent retData){
        if (resultCode== Activity.RESULT_OK && requestCode == CODIGO_EDICION_CONTACTO){
            Detalles.this.setResult(Activity.RESULT_OK);
            Detalles.this.finish();
        }

        return;
    }
}
