package com.example.ejericio1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        final EditText edNombre = (EditText) this.findViewById( R.id.edNombre );
        final EditText edEmail = (EditText) this.findViewById( R.id.edEmail );
        final EditText edDireccion = (EditText) this.findViewById( R.id.edDireccion );
        final EditText apellidos = (EditText) this.findViewById(R.id.apellidos);
        final EditText edad = (EditText) this.findViewById(R.id.edad);

        edNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.datosPersonales.setNombre( edNombre.getText().toString() );
            }
        });

        edDireccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.datosPersonales.setDireccion( edDireccion.getText().toString() );
            }
        });

        edEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.datosPersonales.setEmail( edEmail.getText().toString() );
            }
        });
        apellidos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.this.datosPersonales.setApellidos(apellidos.getText().toString());
            }
        });
        edad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    MainActivity.this.datosPersonales.setEdad(edad.getText().toString());
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();

        SharedPreferences prefs = this.getPreferences( Context.MODE_PRIVATE );
        String nombre = prefs.getString( "nombre", "" );
        String apellidos = prefs.getString("apellidos","");
        String edad = prefs.getString("edad","");
        String direccion = prefs.getString("direccion", "");
        String email = prefs.getString("email", "");
        this.datosPersonales = new DatosPersonalesExtendidos( nombre, direccion, email, apellidos,edad);

        final EditText edNombre = (EditText) this.findViewById( R.id.edNombre );
        final EditText edEmail = (EditText) this.findViewById( R.id.edEmail );
        final EditText edDireccion = (EditText) this.findViewById( R.id.edDireccion );
        final EditText edapellidos = (EditText) this.findViewById(R.id.apellidos);
        final EditText ededad = (EditText) this.findViewById(R.id.edad);

        edNombre.setText( this.datosPersonales.getNombre() );
        edDireccion.setText( this.datosPersonales.getDireccion() );
        edEmail.setText( this.datosPersonales.getEmail() );
        edapellidos.setText(this.datosPersonales.getApellidos());
        ededad.setText(String.valueOf(this.datosPersonales.getEdad()));
    }

    @Override
    public void onPause()
    {
        super.onPause();

        SharedPreferences.Editor edit = this.getPreferences( Context.MODE_PRIVATE ).edit();

        edit.putString( "nombre", this.datosPersonales.getNombre() );
        edit.putString("apellidos", this.datosPersonales.getApellidos());
        edit.putString("edad", String.valueOf(this.datosPersonales.getEdad()));
        edit.putString("direccion",this.datosPersonales.getDireccion());
        edit.putString("email", this.datosPersonales.getEmail());
        edit.apply();
    }

    private DatosPersonalesExtendidos datosPersonales;
}