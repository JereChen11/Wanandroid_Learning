package com.jere.test;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author jere
 */
public class ExternalStorageTestActivity extends AppCompatActivity implements View.OnClickListener {
    private File mExternalFile;
    private EditText mInputContentTv;
    private TextView mDisplayResponseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage_test);

        mInputContentTv = findViewById(R.id.external_storage_input_content_et);
        mDisplayResponseTv = findViewById(R.id.external_storage_display_tv);

        Button writeBtn = findViewById(R.id.external_storage_write_btn);
        Button readBtn = findViewById(R.id.external_storage_read_btn);
        writeBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);

        if (!isExternalStorageWritable()) {
            writeBtn.setEnabled(false);
            readBtn.setEnabled(false);
        } else {
            //子目录
            String subFileDirPath = "JereTestFile";
            //文件名
            String fileName = "MyExternalStorageTest.txt";
            mExternalFile = new File(getExternalFilesDir(subFileDirPath), fileName);
        }
    }

    /**
     * 检查外部存储是否可用来读写
     */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.external_storage_write_btn:
                writeFile();
                break;
            case R.id.external_storage_read_btn:
                readFile();
                break;
            default:
                break;
        }
    }

    private void writeFile() {
        try {
            FileOutputStream fos = new FileOutputStream(mExternalFile);
            fos.write(mInputContentTv.getText().toString().getBytes());
            fos.close();
            Toast.makeText(ExternalStorageTestActivity.this,
                    "saved file to External Storage successful",
                    Toast.LENGTH_SHORT)
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        StringBuilder responseData = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(mExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                responseData.append(strLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mDisplayResponseTv.setText(responseData);
    }
}
