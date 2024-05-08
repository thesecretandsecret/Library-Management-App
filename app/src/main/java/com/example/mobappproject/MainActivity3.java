package com.example.mobappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    private LibraryDatabaseHelper dbHelper;
    private EditText editTextMemberId, editTextBookId, editTextLendingDate, editTextReturnDate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new LibraryDatabaseHelper(this);

        editTextMemberId = findViewById(R.id.editTextMemberId);
        editTextBookId = findViewById(R.id.editTextBookId);
        editTextLendingDate = findViewById(R.id.editTextLendingDate);
        editTextReturnDate = findViewById(R.id.editTextReturnDate);

        // Add Lending
        Button btnAddLending = findViewById(R.id.btnAddLending);
        btnAddLending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memberIdStr = editTextMemberId.getText().toString().trim();
                String bookIdStr = editTextBookId.getText().toString().trim();
                String lendingDate = editTextLendingDate.getText().toString().trim();
                String returnDate = editTextReturnDate.getText().toString().trim();

                if (!memberIdStr.isEmpty() && !bookIdStr.isEmpty() && !lendingDate.isEmpty() && !returnDate.isEmpty()) {
                    int memberId = Integer.parseInt(memberIdStr);
                    int bookId = Integer.parseInt(bookIdStr);

                    // Add lending operation here

                    // Clear EditText fields after adding lending
                    editTextMemberId.setText("");
                    editTextBookId.setText("");
                    editTextLendingDate.setText("");
                    editTextReturnDate.setText("");

                    Toast.makeText(MainActivity3.this, "Lending added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // View Lendings
        Button btnViewLendings = findViewById(R.id.btnViewLendings);
        btnViewLendings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // View lending operation here
            }
        });

        // Update Lending
        Button btnUpdateLending = findViewById(R.id.btnUpdateLending);
        btnUpdateLending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update lending operation here
            }
        });

        // Delete Lending
        Button btnDeleteLending = findViewById(R.id.btnDeleteLending);
        btnDeleteLending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete lending operation here
            }
        });

        // Home
        Button btnHomePage = findViewById(R.id.btnHomePage);
        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the MembersActivity
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}