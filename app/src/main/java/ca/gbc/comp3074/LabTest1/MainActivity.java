package ca.gbc.comp3074.LabTest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    //creating intent to start a new activity
    private void startActivity(){
        Intent intent = new Intent(getApplicationContext(), SensorActivity.class);
        startActivity(intent);
    }
    //setting main_menu as the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu,menu);
        return true;
    }
    //method for switching the pages
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.switch_page) {
            startActivity();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

}