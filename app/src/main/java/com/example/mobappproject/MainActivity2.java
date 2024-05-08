package com.example.mobappproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private LibraryDatabaseHelper dbHelper;
    private EditText editTextName, editTextCardNo, editTextDeleteName, editTextDeleteCardNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new LibraryDatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextCardNo = findViewById(R.id.editTextCardNo);
        editTextDeleteName = findViewById(R.id.editTextDeleteName);
        editTextDeleteCardNo = findViewById(R.id.editTextDeleteCardNo);

        // Add OnClickListener for adding a new member
        Button btnAddMember = findViewById(R.id.btnAddMemeber);
        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String cardNoStr = editTextCardNo.getText().toString().trim();

                if (!name.isEmpty() && !cardNoStr.isEmpty()) {
                    int cardNo = Integer.parseInt(cardNoStr);
                    long id = dbHelper.addMember(name, cardNo);
                    if (id != -1) {
                        Toast.makeText(MainActivity2.this, "Member added successfully with ID: " + id, Toast.LENGTH_SHORT).show();
                        editTextName.setText("");
                        editTextCardNo.setText("");
                    } else {
                        Toast.makeText(MainActivity2.this, "Failed to add member", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Call the viewMembers method to display the list of members
        viewMembers();

        // Add OnClickListener for deleting a member
        Button btnDeleteMember = findViewById(R.id.btnDeleteMember);
        btnDeleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextDeleteName.getText().toString().trim();
                String cardNoStr = editTextDeleteCardNo.getText().toString().trim();

                if (!name.isEmpty() && !cardNoStr.isEmpty()) {
                    int cardNo = Integer.parseInt(cardNoStr);
                    dbHelper.deleteMember(name, cardNo);
                    Toast.makeText(MainActivity2.this, "Member deleted successfully", Toast.LENGTH_SHORT).show();
                    editTextDeleteName.setText("");
                    editTextDeleteCardNo.setText("");
                } else {
                    Toast.makeText(MainActivity2.this, "Please enter name and card number to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Home
        Button btnHomePage = findViewById(R.id.btnHomePage);
        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the MembersActivity
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to display the list of members
    private void viewMembers() {
        Button btnViewMembers = findViewById(R.id.btnViewMembers);
        btnViewMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Member> members = dbHelper.getAllMembers();
                StringBuilder sb = new StringBuilder();
                for (Member member : members) {
                    sb.append("ID: ").append(member.getId()).append("\n");
                    sb.append("Name: ").append(member.getName()).append("\n");
                    sb.append("Card Number: ").append(member.getBookId()).append("\n\n");
                }
                if (sb.length() > 0) {
                    Toast.makeText(MainActivity2.this, sb.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity2.this, "No members found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
