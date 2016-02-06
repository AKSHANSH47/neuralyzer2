package project.neutralyzer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by KaranKunal on 02-02-2016.
 */

public class Dashboard extends Activity {

    private TextView name;
    private TextView uname;
    private TextView phone;
    private TextView nationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        name = (TextView) findViewById(R.id.name);
        uname = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        nationality = (TextView) findViewById(R.id.nationality);
        name.setTextSize(30);
        uname.setTextSize(20);
        phone.setTextSize(20);
        nationality.setTextSize(20);
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("val");
        name.setText("Welcome " + b.getString("Name") +"!");
        uname.setText(b.getString("Uname"));
        phone.setText(b.getString("Phone"));
        nationality.setText(b.getString("Nationality"));
    }
}
