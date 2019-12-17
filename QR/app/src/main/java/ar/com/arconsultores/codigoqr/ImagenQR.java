package ar.com.arconsultores.codigoqr;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImagenQR extends AppCompatActivity {
    Bitmap bitmapFinal;
    String ImagePath;
    Uri URI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_qr);



        String nombre,apellido,Dni,Telefono,GrupoSanguineo,obraSocial,Alergias,sexo;
        nombre = getIntent().getStringExtra("nombre");
        apellido= getIntent().getStringExtra("apellido");
        Dni= getIntent().getStringExtra("Dni");
        Telefono = getIntent().getStringExtra("Telefono");
        GrupoSanguineo = getIntent().getStringExtra("GrupoSanguineo");
        obraSocial = getIntent().getStringExtra("ObraSocial");
        Alergias = getIntent().getStringExtra("Alergias");
        sexo = getIntent().getStringExtra("opcionSexo");

        String qrData = "Nombre : "+nombre+"<br>" + " apellido: "+apellido+ "<br>" + "DNI: "+Dni+"<br>"+" Telefono: "+Telefono+"<br>"+" Grupo Sanguineo: "+GrupoSanguineo+"<br>"+"Sexo:"+sexo+"<br>"+ "alergias:"+ Alergias
                +"<br>"+"obra Social :" +obraSocial;
        int qrCodeDimention = 500;


        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null,
                Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);


        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            ImageView mainImage = (ImageView) findViewById(R.id.ImagenQR);
            mainImage.setImageBitmap(bitmap);
            bitmapFinal = bitmap;

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void Guardar(View view) throws Exception {
        ImagePath = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                bitmapFinal,
                "ACAIMAGEN ",
                "ACAIMAGEN"
        );

        URI = Uri.parse(ImagePath);

        Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_LONG).show();

    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
