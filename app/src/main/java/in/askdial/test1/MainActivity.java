package in.askdial.test1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import in.askdial.test1.Data.Database;
import in.askdial.test1.Data.MyDatabase;
import in.askdial.test1.RecylerAdapter.Adapter;

import static in.askdial.test1.R.id.editText;
import static in.askdial.test1.R.id.imageview;
import static in.askdial.test1.R.id.recyler_view;


public class MainActivity extends AppCompatActivity {

    EditText search;
    Button find;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Database myDatabase = new Database(this);
        myDatabase.open();

         ArrayList<POJO> arrayListData= new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(recyler_view);
        Adapter recyclerAdapter = new Adapter(this, arrayListData);

         search= (EditText) findViewById(R.id.editText);
        find= (Button) findViewById(R.id.button_find);

        int[] img= new int[]{R.drawable.image3, R.drawable.images4,R.drawable.images5, R.drawable.images6, R.drawable.images7,R.drawable.image8, R.drawable.image9,R.drawable.images12};
        String[] message = new String[]{"image1", "image2","image3", "image4", "image5","image6","image7","image8"};


        for (int i = 0; i < img.length; i++) {
            POJO content = new POJO(img[i], message[i], "image");
            arrayListData.add(content);
        }

        Cursor c= myDatabase.search1();
        if (c.getCount()==0) {
            for (int i = 0; i < img.length; i++) {
                myDatabase.insertSearchPath(message[i], String.valueOf(img[i]));
            }

        }

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        // Items show default animation even if we do not set this
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setAdapter(recyclerAdapter);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String s= search.getText().toString();
                Cursor c1= myDatabase.search(s);
                if(c1.getCount() > 0){

                    c1.moveToNext();
                    String name=c1.getString(c1.getColumnIndex("image_name"));
                    String img= c1.getString(c1.getColumnIndex("image_text"));

                    Intent i= new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("msg", name);
                    i.putExtra("image", Integer.valueOf(img));
                    startActivity(i);

                }else {

                    Toast.makeText(MainActivity.this, "No Value Found", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}
