package it.mirea.print;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
        }

        findViewById(R.id.printBtn).setOnClickListener(v -> {
            doPhotoPrint();
        });
    }

    protected void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(MainActivity.this);

        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_channels4_profile);

        photoPrinter.printBitmap("droid.jpg - test print", bitmap);
    }
}