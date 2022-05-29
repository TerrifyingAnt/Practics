package it.mirea.myapplication;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class FileSelectActivity extends Activity {
    private File mPrivateRootDir;
    private File mImagesDir;
    File[] mImageFiles;
    String[] mImageFilenames;
    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileselect_activity);
        im = (ImageView) findViewById(R.id.imageView);
        Intent mResultIntent = new Intent("storage/emulated/0/Pictures/Screenshots.ACTION_RETURN_FILE");
        mPrivateRootDir = getFilesDir();
        mImagesDir = new File("storage/emulated/0/Pictures/", "Screenshots");
        mImageFiles = mImagesDir.listFiles();
        setResult(Activity.RESULT_CANCELED, null);
        getImages(mImageFiles);
    }

    public void getImages(File[] mImageFiles){
        if(mImageFiles != null) {
            for (int i = 0; i < mImageFiles.length; i++) {
                Picasso.get()
                        .load(mImageFiles[i].getAbsolutePath())
                        //.resize(50, 50) // here you resize your image to whatever width and height you like
                        .into(im);
            }
        }
        System.out.println(mPrivateRootDir);

    }
}
