package com.example.ejercicio1.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio1.BBDD.SQLOH;
import com.example.ejercicio1.R;
import com.example.ejercicio1.core.Contacto;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if (comprobar(nombre.getText().toString(), apellido1.getText().toString(), apellido2.getText().toString(), telefono.getText().toString(), email.getText().toString())){
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

            }
        });

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               comprobarNombre(nombre.getText().toString());
            }
        });

        apellido1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                comprobarApellido1(apellido1.getText().toString());
            }
        });

        apellido2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               comprobarApellido2(apellido2.getText().toString());
            }
        });

        telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                comprobarTelefono(telefono.getText().toString());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               comprobarEmail(email.getText().toString());
            }
        });

    }

    private boolean comprobarNombre(String nombre){
        final TextView errorNombre = (TextView) Subactividad.this.findViewById(R.id.errorNombre);
        if (nombre.length()>16){
            errorNombre.setText("Nombre demasiado (16 máx)");
            return false;
        } else if (nombre.length()<2){
            errorNombre.setText("Nombre demasiado corto (2 min)");
            return false;
        } else {
            errorNombre.setText("");
        }
        return true;
    }

    private boolean comprobarApellido1 (String apellido1){
        final TextView errorApellido1 = (TextView) Subactividad.this.findViewById(R.id.errorApellido1);
        if (apellido1.length()>20){
            errorApellido1.setText("Apellido demasiado largo (20 máx)");
            return false;
        } else if (apellido1.length()<2){
            errorApellido1.setText("Apellido demasiado corto (2 min)");
            return false;
        } else {
            errorApellido1.setText("");
        }
        return true;
    }

    private boolean comprobarApellido2 (String apellido2){
        final TextView errorApellido2 = (TextView) Subactividad.this.findViewById(R.id.errorApellido2);
        if (apellido2.length()>20){
            errorApellido2.setText("Apellido demasiado largo (20 máx)");
            return false;
        } else {
            errorApellido2.setText("");
        }
        return true;
    }

    private boolean comprobarEmail(String email) {
        final TextView errorEmail = (TextView) Subactividad.this.findViewById(R.id.errorEmail);
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if(email.isEmpty()){
            errorEmail.setText("Email requerido");
        } else if (!mather.find()){
            errorEmail.setText("Email no válido");
            return false;
        } else {
            errorEmail.setText("");
        }
        return true;
    }

    private boolean comprobarTelefono (String telefono){
        final TextView errorTelefono = (TextView) Subactividad.this.findViewById(R.id.errorTelefono);
        if (telefono.length()<9){
            errorTelefono.setText("Teléfono demasiado corto (9 min)");
            return false;
        } else {
            errorTelefono.setText("");
        }
        return true;
    }

    private boolean comprobar(String nombre, String apellido1, String apellido2, String telefono, String email){
        boolean toret = true;
        if (!comprobarNombre(nombre)){
            toret=false;
        }
        if (!comprobarApellido1(apellido1)){
            toret=false;
        }
        if (!comprobarApellido2(apellido2)){
            toret=false;
        }
        if (!comprobarEmail(email)){
            toret=false;
        }
        if (!comprobarTelefono(telefono)){
            toret=false;
        }
        return toret;
    }
}
