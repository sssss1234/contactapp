package com.example.dell.contactapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class BlankFragment extends Fragment {
    Button call,on,to,th,fi,fo,six,sev,egi,nin,ze,star,has;
EditText callview;
    String calp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_blank, container, false);
        sev= (Button) root.findViewById(R.id.seven);
        egi= (Button) root.findViewById(R.id.eight);
        nin= (Button) root.findViewById(R.id.nine);
        fo= (Button) root.findViewById(R.id.four);
        fi= (Button) root.findViewById(R.id.five);
        on= (Button) root.findViewById(R.id.one);
        to= (Button) root.findViewById(R.id.two);
        th= (Button) root.findViewById(R.id.three);
        ze= (Button) root.findViewById(R.id.zero);
        six= (Button) root.findViewById(R.id.six);
      star= (Button) root.findViewById(R.id.st);
        has= (Button) root.findViewById(R.id.hash);
        call= (Button) root.findViewById(R.id.call);
        callview= (EditText) root.findViewById(R.id.EditTextPhoneNumber);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"1");
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"2");
            }
        });
        th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"3");
            }
        });
        fo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"4");
            }
        });
        fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"6");
            }
        });
        sev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"7");
            }
        });
        egi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"8");
            }
        });
        nin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"9");
            }
        });
        ze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"0");
            }
        });
        has.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"#");
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callview.setText(callview.getText()+"*");
            }
        });
        calp=callview.getText().toString();
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" +calp));
                startActivity(call);
            }
        });
 return root;
    }

}
