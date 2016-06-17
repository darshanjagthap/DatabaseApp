package com.example.darshan.databaseapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String jnum,jname;



    EditText jtxtnum;
    EditText jtxtname;
    Button jadd;
    Button jdisplay;
    Button jdelete;
    Button jupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        jtxtnum=(EditText)findViewById(R.id.txtnum);
        jtxtname=(EditText)findViewById(R.id.txtname);
        jadd=(Button)findViewById(R.id.button);
        jdisplay=(Button)findViewById(R.id.button3);
        jdelete=(Button)findViewById(R.id.button4);
        jupdate=(Button)findViewById(R.id.button2);

        jadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAdd();
            }
        });

        jdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDisplay();
            }
        });

        jdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDelete();
            }
        });

        jupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdate();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doAdd()
    {
        boolean flag=getjnum();
        if(flag)
            flag=getjname();
        if(flag) {
            MyData md=new MyData(jnum,jname);
            MyHelper mh=new MyHelper(MainActivity.this);
            mh.addData(md);

        }
    }

    public boolean getjnum() {
        jnum = jtxtnum.getText().toString();

        if (jnum.length() == 0) {
            Toast.makeText(MainActivity.this, "plz enter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    public boolean getjname() {
        jname = jtxtname.getText().toString();

        if (jname.length() == 0) {
            Toast.makeText(MainActivity.this, "plz enter", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    public void doDisplay()
    {
        MyHelper mh=new MyHelper(MainActivity.this);
        List<MyData> lst = mh.display();

        String s="";

        for (MyData d:lst)
        {
            s=s+d.getNum()+"\t"+d.getName()+"\n";

        }
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }

    public void doDelete()
    {
        boolean flag=getjnum();
        if(flag)
        {
            MyHelper mh=new MyHelper(MainActivity.this);
            mh.doDelete(jnum);
        }
        else
        {
            Toast.makeText(MainActivity.this,"entr no. to delete",Toast.LENGTH_SHORT).show();
        }
    }

    public void doUpdate()
    {
        boolean flag=getjnum();
        if(flag)
            flag=getjname();

        if(flag) {
            MyHelper mh=new MyHelper(MainActivity.this);
            mh.doUpdate(jnum,jname);
        }
        else {
            Toast.makeText(MainActivity.this,"entr details",Toast.LENGTH_SHORT).show();
        }
    }

}