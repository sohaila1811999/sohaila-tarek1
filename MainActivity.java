package com.example.todo0;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
DB_Sqlite db = new DB_Sqlite(this);
EditText note , ID;
 FloatingActionButton delete , add;
ListView list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
note=(EditText) findViewById(R.id.editTextTextPersonName);
list =(ListView) findViewById(R.id.list);

delete = (FloatingActionButton) findViewById(R.id.btn_delete);
ID = (EditText) findViewById(R.id.id);
add= (FloatingActionButton  ) findViewById(R.id.btn_add_data);
showData();
    }

    public  void btn_add_data(View view) {
  String Note =note.getText().toString();
  Boolean result = db.insertData(Note);
  if(result==true){
      Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_SHORT).show();
      note.setText("");
      showData();
  }
else {Toast.makeText(MainActivity.this,"NOT Saved",Toast.LENGTH_SHORT).show();
}
    }
public  void showData(){
    ArrayList<String> listData = db.getAllrecord();
    ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listData);
list.setAdapter(arrayAdapter);

    }

    public void btn_delete(View view) {
        String id =ID.getText().toString();
        Integer result =db.Delete(id);
        if(result>0){
            Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
showData();
        }
        else {Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_SHORT).show();}
    }

    }
