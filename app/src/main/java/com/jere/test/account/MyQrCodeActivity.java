package com.jere.test.account;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import com.jere.test.R;
import com.jere.test.databinding.ActivityMyQrCodeBinding;
import com.jere.test.util.QRCodeUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class MyQrCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMyQrCodeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_my_qr_code);

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
        binding.qrCodeIv.setImageBitmap(qrCodeBitmap);
    }
}
