package com.example.dell.contactapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Main3Activity extends AppCompatActivity {
Button sa;
    EditText name,number;
    database dbb;
    String na;
    String nu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
         sa= (Button) findViewById(R.id.save);
        name= (EditText) findViewById(R.id.namee);
        number= (EditText) findViewById(R.id.numberr);
        na=name.getText().toString();
        nu=number.getText().toString();
        sa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean issinsert= dbb.isinsert(na,nu);
            if(issinsert==true)
            {
                Toast.makeText(Main3Activity.this, "Inseted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Main3Activity.this, "not", Toast.LENGTH_SHORT).show();
            }
          //  Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
            //intent.putExtra("insertdata",isinsert);
            //startActivity(intent);
        }
    });
       // Bundle extras = getIntent().getExtras();
        //final String value1 = extras.getString("Cnnn");
        //final String value2 = extras.getString("Cnuu");
        //name.setText(""+value1);
        //number.setText(""+value2);
    }
}
