package android.com.customertextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyTextView myTextView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        myTextView01 = (MyTextView)findViewById(R.id.firestTV);
        myTextView01.setBackground(getResources().getDrawable(R.drawable.ticket_intercity_light_bg));
        myTextView01.setFirstTitle(getResources().getString(R.string.bus_name));
        myTextView01.setSecondTitle(getResources().getString(R.string.warmly_hint));
        myTextView01.setSecondTitleSize(20);
        myTextView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Click First",Toast.LENGTH_SHORT).show();
            }
        });
        myTextView01.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(),"Long Click First",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}