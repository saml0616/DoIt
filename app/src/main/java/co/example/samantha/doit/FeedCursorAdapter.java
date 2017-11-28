package co.example.samantha.doit;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Asus Rtrix on 11/27/2017.
 */

public class FeedCursorAdapter extends CursorAdapter{
    DatabaseHelper db;
    private final Context mContext;

    public FeedCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.db = new DatabaseHelper(context);
        this.mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.entry, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView description = view.findViewById(R.id.description);
        TextView startDate = view.findViewById(R.id.startDate);
        TextView endDate = view.findViewById(R.id.endDate);
        TextView wager = view.findViewById(R.id.wagerinput);
        TextView betMateID = view.findViewById(R.id.betMateID);
        Button delete = view.findViewById(R.id.delete);
        final int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));


        String descriptionString = cursor.getString(cursor.getColumnIndexOrThrow("description"));
        String startDateString = cursor.getString(cursor.getColumnIndexOrThrow("startDate"));
        String endDateString = cursor.getString(cursor.getColumnIndexOrThrow("endDate"));
        String wagerString = cursor.getString(cursor.getColumnIndexOrThrow("wager"));
        String betMateIDString = cursor.getString(cursor.getColumnIndexOrThrow("betMateID"));

        description.setText(descriptionString);
        startDate.setText(startDateString);
        endDate.setText(endDateString);
        wager.setText(wagerString);
        betMateID.setText(betMateIDString);


        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                db.deleteEntry(id);
                if(mContext instanceof MainActivity){
                    ((MainActivity)mContext).setView();
                }
            }
        });

    }
}
