package com.example.dell.contactapp;
import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity{
    TextView con;
    ListView list;
    ImageButton dailp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        con = (TextView) findViewById(R.id.contact);
        dailp= (ImageButton) findViewById(R.id.dial);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
dailp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        android.support.v4.app.FragmentTransaction f=getSupportFragmentManager().beginTransaction();
        f.replace(R.id.fragmentcontainer,new BlankFragment());
        f.commit();

    }
});
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CALL_LOG)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            }

        } else {
//do
ArrayList<String> ca = new ArrayList<>();
            Cursor mangecal = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null, null);
            int number = mangecal.getColumnIndex(CallLog.Calls.NUMBER);
            int type = mangecal.getColumnIndex(CallLog.Calls.TYPE);
            int date = mangecal.getColumnIndex(CallLog.Calls.DATE);
            int duration = mangecal.getColumnIndex(CallLog.Calls.DURATION);
            while (mangecal.moveToNext()) {
                String phnumber = mangecal.getString(number);
                String callType = mangecal.getString(type);
                String callDate = mangecal.getString(date);
                Date callDayTime = new Date(Long.valueOf(callDate));
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
                String dateString = formatter.format(callDayTime);
                String callDuration = mangecal.getString(duration);
                String dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "Outgoing Call";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "Incoming Call";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        dir = "Missed Call";
                        break;
                }
                ca.add(" " + phnumber + "\n" + dir + " " + dateString + "  " +
                        " " + callDuration);
            }
            (list=(ListView) findViewById(R.id.listre)).setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ca){
                @Override
                public View getView(int position, View
                        convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    // Initialize a TextView for ListView each Item
                    TextView tv = (TextView) view.findViewById(android.R.id.text1);
                    // Set the text color of TextView (ListView Item)

                    tv.setTextColor(Color.WHITE);
                    // Generate ListView Item using TextView
                    return view;
                }
            });

        }
list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
        //Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phnumber));
    }
});
   /* public void dailpad() {
        FragmentManager fr = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fr.beginTransaction();
        BlankFragment bl = new BlankFragment();
        fragmentTransaction.add(R.id.fragmentcontainer, bl);
        fragmentTransaction.addToBackStack("bl");
        fragmentTransaction.commit();
    }*/

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                        ArrayList<String> ca = new ArrayList<>();
                        Cursor mangecal = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null, null);
                        int number = mangecal.getColumnIndex(CallLog.Calls.NUMBER);
                        int type = mangecal.getColumnIndex(CallLog.Calls.TYPE);
                        int date = mangecal.getColumnIndex(CallLog.Calls.DATE);
                        int duration = mangecal.getColumnIndex(CallLog.Calls.DURATION);
                        while (mangecal.moveToNext()) {
                            String phnumber = mangecal.getString(number);
                            String callType = mangecal.getString(type);
                            String callDate = mangecal.getString(date);
                            Date callDayTime = new Date(Long.valueOf(callDate));
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
                            String dateString = formatter.format(callDayTime);
                            String callDuration = mangecal.getString(duration);
                            String dir = null;
                            int dircode = Integer.parseInt(callType);
                            switch (dircode) {
                                case CallLog.Calls.OUTGOING_TYPE:
                                    dir = "Outgoing Call";
                                    break;
                                case CallLog.Calls.INCOMING_TYPE:
                                    dir = "Incoming Call";
                                    break;
                                case CallLog.Calls.MISSED_TYPE:
                                    dir = "Missed Call";
                                    break;
                            }
                            ca.add(" " + phnumber + "\n" + dir + " " + dateString + "  " +
                                    " " + callDuration);
                        }
                        (list=(ListView) findViewById(R.id.listre)).setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ca){
                            @Override
                            public View getView(int position, View
                                    convertView,  ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                // Initialize a TextView for ListView each Item
                                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                                // Set the text color of TextView (ListView Item)

                                tv.setTextColor(Color.WHITE);
                                // Generate ListView Item using TextView
                                return view;
                            }
                        });


                        Toast.makeText(this, "Permisson Granted", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "not", Toast.LENGTH_SHORT).show();
                    }
return;
                }
            }
        }


    }

}


