package ar.com.arconsultores.codigoqr;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

   private EditText nombre,apellido,Dni,Telefono,obraSocial;
   private TextView Alergias;
   private Spinner opciones;
   private Spinner opcionesSexo;
   private String GrupoSanguineo, opcionSexo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        opciones = findViewById(R.id.GrupoSan);
        opcionesSexo = findViewById(R.id.Sexo);

        nombre = findViewById(R.id.nombre);
        apellido=findViewById(R.id.apellido);
        Dni=findViewById(R.id.Dni);
        Telefono = findViewById(R.id.Telefono);

        obraSocial = findViewById(R.id.ObraSocial);
        Alergias = findViewById(R.id.Alergias);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);



        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.OpcionesSexo, android.R.layout.simple_spinner_item);
        opcionesSexo.setAdapter(adapter2);

    }

    public void aceptar(View view) {
        GrupoSanguineo =  opciones.getSelectedItem().toString();
        opcionSexo = opcionesSexo.getSelectedItem().toString();
        Intent i = new Intent(this, ImagenQR.class);
        i.putExtra("nombre",nombre.getText().toString());
        i.putExtra("opcionSexo",opcionSexo);
        i.putExtra("apellido",apellido.getText().toString());
        i.putExtra("Dni",Dni.getText().toString());
        i.putExtra("Telefono",Telefono.getText().toString());
        i.putExtra("GrupoSanguineo",GrupoSanguineo);
        i.putExtra("ObraSocial",obraSocial.getText().toString());
        i.putExtra("Alergias",Alergias.getText().toString());

        startActivity(i);
    }
}

