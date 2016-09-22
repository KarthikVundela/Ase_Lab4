package ase_karthik.doctorsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity
{


    String sourceText;
    //TextView outputTextView;
    TextView outputTextView1,outputTextView2,outputTextView3,outputTextView4,outputTextView5,outputTextView6,outputTextView7,outputTextView8;
    ImageView outputImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // outputTextView = (TextView) findViewById(R.id.txt_Result);
        outputTextView1 = (TextView) findViewById(R.id.FirstName);
        outputTextView2 = (TextView) findViewById(R.id.LastName);
        outputTextView3 = (TextView) findViewById(R.id.Street);
        outputTextView4 = (TextView) findViewById(R.id.City);
        outputTextView5 = (TextView) findViewById(R.id.State);
        outputTextView6 = (TextView) findViewById(R.id.Zip);
        outputTextView7 = (TextView) findViewById(R.id.rating);
        outputTextView8 = (TextView) findViewById(R.id.bio);
        //outputTextView7 = (TextView) findViewById(R.id.txt_Result7);
        //outputImage=(ImageView) findViewById(R.id.imageView);

    }
    public void logout(View v) {
        Intent redirect = new Intent(SearchActivity.this, LoginActivity.class);
        startActivity(redirect);
    }
    public void searchText(View v) {
        TextView sourceTextView = (TextView) findViewById(R.id.txt_Problem);

        sourceText = sourceTextView.getText().toString();
        String getURL = "https://api.betterdoctor.com/2016-03-01/doctors?query="+sourceText+"&location=37.773%2C-122.413%2C100&user_location=37.773%2C-122.413&skip=0&limit=10&user_key=a209acf0a97349970d5d7e652c439475";//The API service URL
        final String response1 = "";
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("data");
                        //final String convertedText = convertedTextArray.getJSONObject(0).getJSONObject("profile").getString("first_name");
                        final String convertedText1 = convertedTextArray.getJSONObject(0).getJSONObject("profile").getString("first_name");
                        final String convertedText2 = convertedTextArray.getJSONObject(0).getJSONObject("profile").getString("last_name");
                        final String convertedText3 = convertedTextArray.getJSONObject(0).getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street");
                        final String convertedText4 = convertedTextArray.getJSONObject(0).getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("city");
                        final String convertedText5 = convertedTextArray.getJSONObject(0).getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("state_long");
                        final String convertedText6 = convertedTextArray.getJSONObject(0).getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("zip");
                        final String convertedText7 = convertedTextArray.getJSONObject(0).getJSONArray("ratings").getJSONObject(0).getString("rating");
                        final String convertedText8 = convertedTextArray.getJSONObject(0).getJSONObject("profile").getString("bio");

                        Log.d("okHttp", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //outputTextView.setText(convertedText);
                                outputTextView1.setText(convertedText1);
                                outputTextView2.setText(convertedText2);
                                outputTextView3.setText(convertedText3);
                                outputTextView4.setText(convertedText4);
                                outputTextView5.setText(convertedText5);
                                outputTextView6.setText(convertedText6);
                                outputTextView7.setText(convertedText7);
                                outputTextView8.setText(convertedText8);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            //outputTextView.setText(ex.getMessage());
            outputTextView1.setText(ex.getMessage());
            outputTextView2.setText(ex.getMessage());
            outputTextView3.setText(ex.getMessage());
            outputTextView4.setText(ex.getMessage());
            outputTextView5.setText(ex.getMessage());
            outputTextView6.setText(ex.getMessage());
            outputTextView7.setText(ex.getMessage());
            outputTextView8.setText(ex.getMessage());

        }

    }
}

