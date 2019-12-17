package ar.com.arconsultores.codigoqr;

import android.content.Context;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class Save {

    private Context TheThis;
    private String NameOfFolder = "/Nuevacarpeta";
    private String NameOfFile = "imagen";

    public void SaveImage(Context context, Bitmap ImageToSave) {

        TheThis = context;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        String CurrentDateAndTime = "11/11/1111";
        File dir = new File(file_path);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, NameOfFile + CurrentDateAndTime + ".jpg");

        try {
            FileOutputStream fOut = new FileOutputStream(file);

            ImageToSave.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            MakeSureFileWasCreatedThenMakeAvabile(file);
            AbleToSave();
        }

        catch(FileNotFoundException e) {
            UnableToSave();
        }
        catch(IOException e) {
            UnableToSave();
        }

    }

    private void MakeSureFileWasCreatedThenMakeAvabile(File file){
        MediaScannerConnection.scanFile(TheThis,
                new String[] { file.toString() } , null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }


    private void UnableToSave() {
        Toast.makeText(TheThis, "¡No se ha podido guardar la imagen!", Toast.LENGTH_SHORT).show();
    }

    private void AbleToSave() {
        Toast.makeText(TheThis, "Imagen guardada en la galería.", Toast.LENGTH_SHORT).show();

    }
}