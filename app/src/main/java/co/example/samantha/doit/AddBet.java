package co.example.samantha.doit;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class AddBet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void cancel(View view){
        setResult(Activity.RESULT_CANCELED, null);
        finish();
    }

    public void saveBet(View view){

        EditText betName = (EditText) findViewById(R.id.betname);
        EditText location = (EditText) findViewById(R.id.locationInput);
        EditText descripfield = (EditText) findViewById(R.id.descripinput);
        EditText endDate = (EditText) findViewById(R.id.dueDateInput);
        EditText wager = (EditText) findViewById(R.id.wagerinput);
        setContentView(R.layout.activity_main);
        Intent returnIntent = new Intent(this, MainActivity.class);
        returnIntent.putExtra("betName",betName.getText().toString());
        returnIntent.putExtra("descrip",descripfield.getText().toString());
        returnIntent.putExtra("endDate",endDate.getText().toString());
        returnIntent.putExtra("wager",wager.getText().toString());
        returnIntent.putExtra("location",location.getText().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();



    }
}
