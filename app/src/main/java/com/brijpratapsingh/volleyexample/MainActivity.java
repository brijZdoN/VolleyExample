package com.brijpratapsingh.volleyexample;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;;
        import android.widget.Button;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import java.io.StringReader;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG=MainActivity.class.getName();
    private Button requestBtn;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    //any json data url
    private String url="https://api.myjson.com/bins/p64ng";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestBtn=findViewById(R.id.requestbtn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendandrequestResponse();

            }
        });

    }

    private void sendandrequestResponse()
    {
        requestQueue= Volley.newRequestQueue(this);
        stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Response:" + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.i(TAG,"Error :" + error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

}