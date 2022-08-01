package com.example.yangaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class scan1 extends AppCompatActivity {

    CodeScanner codeScanner;
    CodeScannerView ScannerView;
    TextView qrUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan1);

        ScannerView = findViewById(R.id.ScannerView);
        qrUid = findViewById(R.id.qrUid);
        codeScanner = new CodeScanner(this, ScannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        qrUid.setText(result.getText());
                        String StudentsUid = qrUid.getText().toString();
                        Intent students = new Intent(getApplicationContext(), ViewShareStudents.class);
                        students.putExtra("Student", StudentsUid);
                        startActivity(students);
                    }
                });
            }
        });

        ScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(scan1.this, Home.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }
}