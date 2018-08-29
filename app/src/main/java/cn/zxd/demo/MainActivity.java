package cn.zxd.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            Uri uri = data.getData();
            if (uri != null) {
                String path = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                File file = new File(path);
                try {
                    AVFile avFile = AVFile.withFile(file.getName(), file);
                    avFile.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                Log.e(TAG, "done: ");
                            } else {
                                Log.e(TAG, "error: ", e);
                            }
                        }
                    }, new ProgressCallback() {
                        @Override
                        public void done(Integer integer) {
                            Log.e(TAG, "progress: " + integer);
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
