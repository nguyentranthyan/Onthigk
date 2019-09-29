package com.example.onthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText filename1, content1;
    Button save1, open1;
    String filename, content;
    Spinner sp_filename;
    ArrayList<String> filenameList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filename1 = (EditText) findViewById(R.id.filename);
        content1 = (EditText) findViewById(R.id.content);
        save1 = findViewById(R.id.Savefile);
        open1 = findViewById(R.id.open);
        sp_filename = (Spinner) findViewById(R.id.spinner_files);

        save();
        spinner();
        open();

    }

    private void spinner() {
        filenameList.add("");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, filenameList);
        sp_filename.setAdapter(adapter);
        sp_filename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filename1.setText(filenameList.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Item nothing selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void save() {
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename = filename1.getText().toString();
                content = content1.getText().toString();
                filenameList.add(filename);
                try {
                    FileOutputStream fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    fileOutputStream.write(content.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(MainActivity.this, "save success", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "save success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void open() {
        open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = filename1.getText().toString();
                StringBuffer buffer = new StringBuffer();
                String line = null;
                try {
                    FileInputStream fin = openFileInput(filename);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fin));
                    while ((line = bufferedReader.readLine()) != null) {
                        buffer.append(line).append("\n");
                        content1.setText(buffer);
                        Toast.makeText(MainActivity.this, "save success", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "save success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
