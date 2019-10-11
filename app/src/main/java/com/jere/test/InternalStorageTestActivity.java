package com.jere.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jere
 */
public class InternalStorageTestActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILE_NAME = "jereTest.txt";
    private EditText mInputContentEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage_test);

        Button writeBtn = findViewById(R.id.write_btn);
        Button readBtn = findViewById(R.id.read_btn);
        writeBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        mInputContentEt = findViewById(R.id.input_content_et);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.write_btn:
                writeFile(mInputContentEt.getText().toString());
                break;
            case R.id.read_btn:
//                readFile();
//                readSharedPrefData();

                break;
            default:
                break;
        }

    }

    private void writeFile(String fileContent) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(fileContent.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Write File Successful", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            int c;
            StringBuilder temp = new StringBuilder();
            while ( (c = fileInputStream.read()) != -1) {
                temp.append(Character.toString((char) c));
            }
            fileInputStream.close();
            Toast.makeText(this, temp.toString(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private void readSharedPrefData() {
//        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity, MODE_PRIVATE);
//        String content = sharedPreferences.getString("JereTest", null);
//        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
//
//    }
}
