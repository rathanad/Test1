package in.askdial.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView name;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView = (ImageView) findViewById(R.id.imageview);

        name = (TextView) findViewById(R.id.name);

        Intent i= getIntent();
        Bundle b= i.getExtras();
        int image= b.getInt("image");
        String msg= (String) b.get("msg");
        imageView.setImageResource(image);
        name.setText("Your image name is " + msg );


    }
}
