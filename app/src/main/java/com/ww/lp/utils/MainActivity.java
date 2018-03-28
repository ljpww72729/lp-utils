package com.ww.lp.utils;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ww.lp.lputils.LogUtils;
import com.ww.lp.lputils.SPUtils;
import com.ww.lp.utils.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.setDebug(true);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.tSp.setOnClickListener(v -> {
            LogUtils.w("测试...");
            SPUtils.putString(this, "sp_key", "sp_value");
            LogUtils.d(SPUtils.getString(this, "sp_key", ""));
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String imageURL = "http://www.liantu.com/images/2013/liantu.png";

                        Bitmap bitmap;
                        try {
                            // Download Image from URL
                            InputStream input = new URL(imageURL).openStream();
                            // Decode Bitmap
                            bitmap = BitmapFactory.decodeStream(input);
                            File imgFileDir = new File(MainActivity.this.getFilesDir().getAbsolutePath() + "/shared/images");
                            if (!imgFileDir.exists()) {
                                imgFileDir.mkdirs();
                            }
                            String suffix = ".jpg";
                            if (imageURL.contains(".png")) {
                                suffix = ".png";
                            }
                            File imgFile = new File(imgFileDir, "share" + suffix);
                            if (!imgFile.exists()) {
                                imgFile.createNewFile();
                            } else {
                                imgFile.delete();
                                imgFile.createNewFile();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(imgFile);
                            Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
                            if (suffix.equals(".png")) {
                                compressFormat = Bitmap.CompressFormat.PNG;
                            }
                            bitmap.compress(compressFormat, 100, fileOutputStream);
                            input.close();
                            fileOutputStream.close();
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Intent share = new Intent(Intent.ACTION_SEND);
                                    share.setType("image/*");
                                    Uri imageUri = FileProvider.getUriForFile(MainActivity.this,
                                            MainActivity.this.getApplicationContext().getPackageName() + ".fileprovider",
                                            imgFile);
                                    share.putExtra(Intent.EXTRA_STREAM, imageUri);
                                    share.putExtra(Intent.EXTRA_SUBJECT, "好运购分享");
                                    share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    MainActivity.this.startActivity(Intent.createChooser(share, "分享到"));
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

}
