package com.jere.test.account;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.jere.test.R;
import com.jere.test.util.QRCodeUtil;

/**
 * @author jere
 */
public class MyQrCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr_code);

        ImageView qrCodeIv = findViewById(R.id.qrcode_iv);
        //content, 800, 800,"UTF-8","H", "1", Color.BLACK, Color.WHITE
        Bitmap qrCodeBitmap = QRCodeUtil.createQRCodeBitmap("jerechen11@gmail.com",
                500,
                500,
                "UTF-8",
                "H",
                "1",
                Color.BLACK,
                R.color.white,
                null,
                0.2F,
                null);
        qrCodeIv.setImageBitmap(qrCodeBitmap);
    }
}
