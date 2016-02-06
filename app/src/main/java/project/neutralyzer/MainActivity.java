package project.neutralyzer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText uname;
    private EditText pass;
    private Button login;
    private TextView fp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        uname = (EditText)findViewById(R.id.uname);
        pass = (EditText)findViewById(R.id.pass);
        login = (Button)findViewById(R.id.login);
        fp = (TextView)findViewById(R.id.fp);
        createDB();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT pass FROM LOG WHERE uname = '" + uname.getText().toString() + "'", null);
                c.moveToFirst();
                if (c.getString(c.getColumnIndex("pass")).equals(pass.getText().toString())) {
                    c = db.rawQuery("SELECT Name, Phone, Nationality FROM DATA WHERE uname = '" + uname.getText().toString() + "'", null);
                    Toast.makeText(MainActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                    c.moveToFirst();
                    Intent i = new Intent(MainActivity.this, Dashboard.class);
                    Bundle b = new Bundle();
                    b.putString("Uname", uname.getText().toString());
                    b.putString("Name", c.getString(0));
                    b.putString("Phone", c.getString(1));
                    b.putString("Nationality", c.getString(2));
                    i.putExtra("val", b);
                    startActivity(i);
                } else {
                    uname.setText("");
                    pass.setText("");
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createDB(){
        db = openOrCreateDatabase("Login.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        final String CREATE_TABLE_LOG = "CREATE TABLE IF NOT EXISTS LOG(uname TEXT PRIMARY KEY, pass TEXT)";
        final String CREATE_TABLE_DATA = "CREATE TABLE IF NOT EXISTS DATA(uname TEXT PRIMARY KEY, Name TEXT, Phone TEXT, Nationality TEXT)";
        db.execSQL(CREATE_TABLE_LOG);
        db.execSQL(CREATE_TABLE_DATA);
        //db.execSQL("INSERT INTO LOG VALUES('kunal94', 'neutralyzer')");
        //db.execSQL("INSERT INTO DATA VALUES('kunal94', 'Kunal Kukreja', '+919810000000', 'Indian')");
    }


    private void ForgotPass(View v){

    }
}
