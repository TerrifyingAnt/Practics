package it.mirea.practic_6_2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        EditText editTextName = findViewById(R.id.editText);
        EditText editTextAge = findViewById(R.id.age);
        if (editTextName.getText().toString() != "") {
            String person_name = editTextName.getText().toString();
            int person_age = 0;
            try {
                person_age = Integer.parseInt(editTextAge.getText().toString());
            }
            catch (NumberFormatException e) {
                Toast.makeText(this, "Неправильный возраст", Toast.LENGTH_SHORT);
                return;
            }
            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("my_db.db", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, UNIQUE(name))");
            db.execSQL("INSERT OR IGNORE INTO users VALUES ($0, $1)",
                    new String[]{person_name, String.valueOf(person_age)});


            Cursor query = db.rawQuery("SELECT * FROM users;", null);
            TextView textView = findViewById(R.id.textView);
            textView.setText("");
            while (query.moveToNext()) {
                String name = query.getString(0);
                int age = query.getInt(1);
                textView.append("Имя: " + name + ", возраст: " + age + "\n");
            }
            query.close();
            db.close();
        }
        else
            Toast.makeText(this, "Неправильное имя", Toast.LENGTH_SHORT);
    }

}