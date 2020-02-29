package com.example.ejerciciowifi.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ejerciciowifi.R;
import com.example.ejerciciowifi.core.MetodosConexiones;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = (Button) this.findViewById(R.id.boton);

        final Switch switchWifi = (Switch) this.findViewById(R.id.switchWifi);

        final WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (manager.isWifiEnabled()){
            switchWifi.setChecked(true);
        } else {
            switchWifi.setChecked(false);
        }

        switchWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchWifi.isChecked()){
                    manager.setWifiEnabled(true);
                } else {
                    manager.setWifiEnabled(false);
                }

                comprobarConexion();
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarConexion();
            }
        });

        comprobarConexion();

    }

    public void comprobarConexion(){

        TextView conexion = (TextView) this.findViewById(R.id.conectividad);
        TextView wifi = (TextView) this.findViewById(R.id.wifi);
        TextView datos = (TextView) this.findViewById(R.id.datos);

        MetodosConexiones mc = new MetodosConexiones(this.getApplicationContext());

        final Switch switchWifi = (Switch) this.findViewById(R.id.switchWifi);

        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (mc.isConnected()){
            conexion.setBackgroundResource(R.drawable.concectado);

            if (mc.tipoConexion()){
                wifi.setBackgroundResource(R.drawable.wifi_si);
                datos.setBackgroundResource(R.drawable.no_datos);
            } else {
                wifi.setBackgroundResource(R.drawable.wifi_no);
                datos.setBackgroundResource(R.drawable.si_datos);
            }

        } else {
            conexion.setBackgroundResource(R.drawable.no_wifi);
            wifi.setBackgroundResource(R.drawable.wifi_no);
            datos.setBackgroundResource(R.drawable.no_datos);
        }

    }

}
