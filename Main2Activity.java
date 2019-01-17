package com.example.dell.contactapp;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    TextView re;
    Button btn;
    ListView vview;
    ImageButton list;
    String name;
    String num;
    database data;

    //String im[]={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        re = (TextView) findViewById(R.id.recentt);
        btn = (Button) findViewById(R.id.dial);
        list = (ImageButton) findViewById(R.id.set);
        registerForContextMenu(list);
        re.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(i);
            }
        });
        ArrayList<String> contacts = new ArrayList<>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER };

        String selection = null;
        String[] selectionargs = null;
        String sortorder = null;
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, projection, selection, selectionargs, sortorder);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(name + "\n" + num);

        }
        (vview=(ListView) findViewById(R.id.listview_Android_Contacts)).setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts)
        {
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

      vview.setOnItemClickListener(this);
//        fecthcontact();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        v.setLongClickable(true);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id;
        id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Toast.makeText(this, "Import/Export Setting", Toast.LENGTH_SHORT).show();
            case R.id.item2:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(Main2Activity.this, "" + position, Toast.LENGTH_SHORT).show();
        String item = (String) vview.getItemAtPosition(position);
        if (item!=null) {
            Intent intent = new Intent(Main2Activity.this, com.example.dell.contactapp.view.class);

            intent.putExtra("Cname",name);
            intent.putExtra("Cnumber", num);
            startActivity(intent);
        }
    }


        /*
        class CustomAdapter extends BaseAdapter{

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {

                view=getLayoutInflater().inflate(R.layout.contactlayout,null);
                ImageView img= (ImageView) findViewById(R.id.imageView);
                TextView namm= (TextView) findViewById(R.id.nam);
                TextView numm= (TextView) findViewById(R.id.numb);
      //          img.setImageResource();
                namm.setText(contacts.get(position));
                return null;
            }
        }
    }*/
    }

    /*public void fecthcontact() {
        ArrayList<String> contacts = new ArrayList<>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER ,String.valueOf(ContactsContract.CommonDataKinds.Photo.CAPABILITY_HAS_CAMERA)};

              String selection = null;
        String[] selectionargs = null;
        String sortorder = null;
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, projection, selection, selectionargs, sortorder);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(name + "\n" + num);

        }
        ((ListView) findViewById(R.id.listview_Android_Contacts)).setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts));
    }
}*/
