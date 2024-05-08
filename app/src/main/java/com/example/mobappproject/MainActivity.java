package com.example.mobappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LibraryDatabaseHelper dbHelper = new LibraryDatabaseHelper(this);
    private EditText editTextTitle, editTextAuthor, editTextPublisher, editTextBookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextPublisher = findViewById(R.id.editTextPublisher);

        Button btnAddBook = findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString().trim();
                String author = editTextAuthor.getText().toString().trim();
                String publisher = editTextPublisher.getText().toString().trim();

                if (!title.isEmpty() && !author.isEmpty() && !publisher.isEmpty()) {
                    long id = dbHelper.addBook(title, author, publisher);
                    if (id != -1) {
                        Toast.makeText(MainActivity.this, "Book added successfully with ID: " + id, Toast.LENGTH_SHORT).show();
                        editTextTitle.setText("");
                        editTextAuthor.setText("");
                        editTextPublisher.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to add book", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add OnClickListener for other buttons like View Books, Update, Delete, etc.
        Button btnViewBooks = findViewById(R.id.btnViewBooks);
        btnViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = dbHelper.getAllBooks();
                StringBuilder sb = new StringBuilder();
                for (Book book : books) {
                    sb.append("ID: ").append(book.getId()).append("\n");
                    sb.append("Title: ").append(book.getTitle()).append("\n");
                    sb.append("Author: ").append(book.getAuthor()).append("\n");
                    sb.append("Publisher: ").append(book.getPublisher()).append("\n\n");
                }
                if (sb.length() > 0) {
                    Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "No books found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //delete book function
        // Add OnClickListener for the delete button
        Button btnDeleteBook = findViewById(R.id.btnDeleteBook);
        btnDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the ID of the book to delete (assuming you have an editText for this)
                String idString = editTextBookId.getText().toString().trim();
                if (!idString.isEmpty()) {
                    int id = Integer.parseInt(idString);
                    dbHelper.deleteBook(id);
                    Toast.makeText(MainActivity.this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid book ID", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button btnmemberspage = findViewById(R.id.btnMemberspage);
        btnmemberspage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the MembersActivity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }});

        Button btnlendingpage = findViewById(R.id.btnLendingPage);
        btnlendingpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the MembersActivity
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }});




    }
}
