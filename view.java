package com.example.dell.contactapp;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
public class view extends AppCompatActivity {
    TextView cnu,cnn;
    ImageButton e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        cnn= (TextView) findViewById(R.id.name);
        cnu= (TextView) findViewById(R.id.number);
        e= (ImageButton) findViewById(R.id.edit);
        Bundle extras = getIntent().getExtras();
        final String value1 = extras.getString("Cname");
        final String value2 = extras.getString("Cnumber");
        cnn.setText(""+value1);
        cnu.setText(""+value2);
       /* cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+cnu));
                startActivity(intent);
            }
        });*/
       cnu.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               Intent intent = new Intent(Intent.ACTION_DIAL);
               intent.setData(Uri.parse("tel:"+cnu));
               startActivity(intent);
               return false;
           }
       });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.this,Main3Activity.class);
                intent.putExtra("Cnnn",value1);
                intent.putExtra("Cnuu",value2);
                startActivity(intent);

            }
        });

    }
}