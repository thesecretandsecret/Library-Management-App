package com.example.mobappproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LibraryDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "library_manager";
    // Table names
    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_MEMBERS = "members";
    private static final String TABLE_LENDINGS = "lendings";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_PUBLISHER = "publisher";
    private static final String KEY_NAME = "name";
    private static final String KEY_BOOK_ID = "book_id";
    // Lendings Table - column names
    private static final String KEY_LENDING_BOOK_ID = "book_id";
    private static final String KEY_LENDING_MEMBER_ID = "member_id";
    private static final String KEY_LENDING_DATE = "lending_date";
    private static final String KEY_LENDING_RETURN_DATE = "return_date";
    public LibraryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_AUTHOR + " TEXT,"
                + KEY_PUBLISHER + " TEXT" + ")";
        db.execSQL(CREATE_BOOKS_TABLE);

        String CREATE_MEMBERS_TABLE = "CREATE TABLE " + TABLE_MEMBERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_BOOK_ID + " INTEGER" + ")";
        db.execSQL(CREATE_MEMBERS_TABLE);

        // Create Lendings table SQL query

        // Create Lendings table SQL query
        String CREATE_TABLE_LENDINGS = "CREATE TABLE " + TABLE_LENDINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_LENDING_BOOK_ID + " INTEGER,"
                + KEY_LENDING_MEMBER_ID + " INTEGER,"
                + KEY_LENDING_DATE + " TEXT,"
                + KEY_LENDING_RETURN_DATE + " TEXT"
                // Add more columns if needed
                + ")";
        db.execSQL(CREATE_TABLE_LENDINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LENDINGS);
        onCreate(db);
    }

    public long addBook(String title, String author, String publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_AUTHOR, author);
        values.put(KEY_PUBLISHER, publisher);
        long id = db.insert(TABLE_BOOKS, null, values);
        db.close();
        return id;
    }
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_BOOKS;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add books to the list
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex(KEY_AUTHOR));
                @SuppressLint("Range") String publisher = cursor.getString(cursor.getColumnIndex(KEY_PUBLISHER));
                Book book = new Book(id, title, author, publisher);
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        // Close cursor and database connection
        cursor.close();
        db.close();

        // Return the list of books
        return bookList;
    }
    //delete book function
    public void deleteBook(int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKS, KEY_ID + " = ?", new String[]{String.valueOf(bookId)});
        db.close();
    }

    // Implement other CRUD operations as needed
    //Memeber code
    public long addMember(String name, int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_BOOK_ID, bookId);
        long id = db.insert(TABLE_MEMBERS, null, values);
        db.close();
        return id;
    }

    public List<Member> getAllMembers() {
        List<Member> memberList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_MEMBERS;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                @SuppressLint("Range") int bookId = cursor.getInt(cursor.getColumnIndex(KEY_BOOK_ID));
                Member member = new Member(id, name, bookId);
                memberList.add(member);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return memberList;
    }

    public void updateMember(int memberId, String newName, int newBookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newName);
        values.put(KEY_BOOK_ID, newBookId);
        db.update(TABLE_MEMBERS, values, KEY_ID + " = ?", new String[]{String.valueOf(memberId)});
        db.close();
    }

    public void deleteMember(String name, int memberId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEMBERS, KEY_ID + " = ?", new String[]{String.valueOf(memberId)});
        db.close();
    }

}
