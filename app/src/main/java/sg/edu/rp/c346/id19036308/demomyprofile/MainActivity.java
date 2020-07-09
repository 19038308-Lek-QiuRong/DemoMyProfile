package sg.edu.rp.c346.id19036308.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Step 1a: Get the user input from the EditText and store it in a variable
                String strName = etName.getText().toString();
                String strGPA = etGPA.getText().toString();
                float gpa = Float.parseFloat(strGPA);
                int GenderId = rgGender.getCheckedRadioButtonId();

                // Step 1b: Obtain an instance of the SharedPreferences
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                // Step 1c: Obtain an instance of the SharedPreference Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();

                // Step 1d: Add the key-value pair
                prefEdit.putString("Name", strName);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.putInt("genderId", GenderId);

                // Step 1e: Call commit() to save the changes into SharedPreferences
                prefEdit.commit();

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        String strGPA = etGPA.getText().toString();
        float gpa = Float.parseFloat(strGPA);
        int genderId = rgGender.getCheckedRadioButtonId();

        // Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 1d: Add the key-value pair
        prefEdit.putString("Name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putInt("genderId", genderId);

        // Step 1e: Call commit() to save the changes into SharedPreferences
        prefEdit.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data with the key "greeting" from the SharedPreference object.
        String name = prefs.getString("Name", "No Name!");
        float gpa = prefs.getFloat("gpa", 0.0f);
        int id = prefs.getInt("GenderId", R.id.radioGroupGender);


        etName.setText(name);
        etGPA.setText(gpa + "");
        rgGender.check(id);


    }
}
