package e.roman.apikudagtried1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = findViewById(R.id.im);
        RequestAsynctask newTask = new RequestAsynctask(new AsyncResponse() {
            @Override
            public void processFinish(Response result) {
                String resultString = "";
                String imagesString = "";
                try{
                    resultString = result.body().string();
                }catch (IOException e){
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                Event posts = gson.fromJson(resultString, Event.class);
                posts = gson.fromJson(posts.getResults().get(0), Event.class);

                Log.d(TAG ,   "IT IS FIRST"+"doInBackground() called with: " +"\n" + posts.getTitle());
                Event imagesElement =gson.fromJson(posts.getImages().get(0) , Event.class);
                Picasso.get().load(imagesElement.getImage()).into(im);
            }
        });
        newTask.execute();
    }

}