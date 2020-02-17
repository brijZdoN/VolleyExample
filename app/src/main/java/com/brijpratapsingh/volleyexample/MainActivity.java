package com.brijpratapsingh.volleyexample;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.VolleyLog;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.StringReader;
        import java.io.UnsupportedEncodingException;
        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG=MainActivity.class.getName();
    //private Button requestBtn;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ProgressDialog pDialog;
    private List<Model> modelList = new ArrayList();
    private ListView listView;
    private CustomAdapter adapter;
    //any json data url
    private String url="http://mobileappdatabase.in/demo/smartnews/app_dashboard/jsonUrl/single-article.php?article-id=71";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestBtn=findViewById(R.id.requestbtn);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(this, modelList);
        listView.setAdapter(adapter);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        sendandrequestResponse();
        /*requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
            }
        });*/

    }

    private void sendandrequestResponse()
    {
        /*requestQueue= Volley.newRequestQueue(this);
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
        requestQueue.add(stringRequest);*/

        JsonArrayRequest movieReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>()
        {
            @Override
                    public void onResponse(JSONArray response)
            {
                        Log.d(TAG, response.toString());
                        hideDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Model model = new Model();
                                    model.setTitle(obj.getString("title"));
                                    model.setCategory(obj.getString("category"));
                                    model.setThumbnailUrl(obj.getString("image"));

                                    modelList.add(model);

                                } catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }


                        // notifying list adapter about data changes so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideDialog();

            }
        });
        //Adding request to queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }

    private void hideDialog()
    {
        if (pDialog != null)
        {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}