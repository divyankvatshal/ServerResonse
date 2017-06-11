package barcode.com.servercon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
 import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
   TextView greeting;
    Button btn;
    private final static String url="http://192.168.0.100:80/a.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greeting =(TextView)findViewById(R.id.gret);
        btn= (Button)findViewById(R.id.add);
        btn.setOnClickListener(this);
    }
    public void onClick(View view){
        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             greeting.setText(response);
                queue.stop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                greeting.setText("error ");
                queue.stop();
            }
        });

        queue.add(request);
    }

}
