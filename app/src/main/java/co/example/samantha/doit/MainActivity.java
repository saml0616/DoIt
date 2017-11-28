package co.example.samantha.doit;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    static final int ADD_WAGER = 1337;
    private FeedCursorAdapter adapter;
    private DatabaseHelper handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = MainActivity.this;
        this.handler = new DatabaseHelper(context);
        this.adapter = new FeedCursorAdapter(context, handler.getCursor());
        handler.addEntry(new DataItem(1, 2, 3, "30 Jumps", "Tomorrow", "My Room",30));

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.findViewById(R.id.action_settings).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent inte = new Intent(MainActivity.this, AddBet.class);
//                startActivity(inte);
//            }
//        } );
    }

    public void setView() {
        adapter.swapCursor(handler.getCursor());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent inte = new Intent(this, AddBet.class);
            startActivityForResult(inte,ADD_WAGER);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent betinfo){
        if(requestCode == ADD_WAGER){
            if(resultCode == RESULT_OK){
                handler.addEntry(new DataItem(1, 2, 3, betinfo.getStringExtra("descrip"), betinfo.getStringExtra("endDate"), betinfo.getStringExtra("location"),Integer.parseInt(betinfo.getStringExtra("wager"))));
                adapter.swapCursor(handler.getCursor());
            }
        }
    }
}
