package co.example.samantha.doit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

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
        EditText description = (EditText) findViewById(R.id.descripinput);
        EditText endDate = (EditText) findViewById(R.id.dueDateInput);
        EditText wager = (EditText) findViewById(R.id.wagerinput);

        //creates calender event
        Intent calenderIntent = new Intent(Intent.ACTION_EDIT);
        calenderIntent.setType("vnd.android.cursor.item/event");
        calenderIntent.putExtra(CalendarContract.Events.TITLE, betName.getText().toString());
        calenderIntent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
        calenderIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
        //startActivity(calenderIntent);

        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(calenderIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(calenderIntent);
        } else {
            Toast.makeText(this, "You're calender app is not set up correctly.", Toast.LENGTH_SHORT).show();
        }

        //Returns entry information to list view
        setContentView(R.layout.activity_main);
        Intent returnIntent = new Intent(this, MainActivity.class);
        returnIntent.putExtra("betName",betName.getText().toString());
        returnIntent.putExtra("descrip",description.getText().toString());
        returnIntent.putExtra("endDate",endDate.getText().toString());
        returnIntent.putExtra("wager",wager.getText().toString());
        returnIntent.putExtra("location",location.getText().toString());
        setResult(Activity.RESULT_OK,returnIntent);

        finish();



    }

}
