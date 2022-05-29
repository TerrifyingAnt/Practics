package it.mirea.practic_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    Button btn;
    ImageView btn1;
    EditText editText, holiday, place, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editTextText);
        holiday = (EditText) findViewById(R.id.holiday);
        place = (EditText) findViewById(R.id.place);
        description = (EditText) findViewById(R.id.description);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
        btn1 = (ImageView) findViewById(R.id.imageView);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

    }

    public void cal(View v){
        String title = holiday.getText().toString();
        if (title != "") {
            String place_str = place.getText().toString();
            String description_str = description.getText().toString();
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra(CalendarContract.Events.TITLE, title);
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, place_str);
            intent.putExtra(CalendarContract.Events.DESCRIPTION, description_str);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View v) {
        Random r = new Random();
        double latitude = -90 + r.nextDouble() * 180;
        double longitude = -180 + r.nextDouble() * 360;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + String.valueOf(latitude) + ","  + String.valueOf(longitude)));
        startActivity(intent);
    }
}