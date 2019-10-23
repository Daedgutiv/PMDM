package com.example.sinboton.ui;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.area1.ui.R;
public class MainActivity extends AppCompatActivity {
    private TextView result;
    private EditText largo;
    private  EditText ancho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        largo = (EditText) this.findViewById(R.id.num1);
        largo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                MainActivity.this.calcula();
                return false;
            }
        });
        ancho = (EditText) this.findViewById(R.id.num2);
        ancho.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                MainActivity.this.calcula();
                return false;
            }
        });
    }
    public void calcula(){
        result = (TextView) this.findViewById(R.id.result);
        try {
            float resultado = Float.parseFloat(largo.getText().toString())*Float.parseFloat(ancho.getText().toString());
            result.setText(String.valueOf(resultado)+ " m²");
        } catch(NumberFormatException fmt){
            result.setText(R.string.error);
        }
        return;
    }
}
