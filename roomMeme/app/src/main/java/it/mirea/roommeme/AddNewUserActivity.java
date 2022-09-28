package it.mirea.roommeme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class AddNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        final EditText holderName =  findViewById(R.id.holderName);
        final EditText carModel =  findViewById(R.id.carModel);
        Button saveButton =  findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser(holderName.getText().toString(), carModel.getText().toString());
            }
        });
    }

    private void saveNewUser(String name, String model) {
        AppDatabase db  = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.holderName = name;
        user.carModel = model;
        db.userDao().insertUser(user);

        finish();

    }
}