package e.roman.apikudagtried1;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class RequestAsynctask extends AsyncTask<Void,Void,Response> {

    private AsyncResponse delegate;

    public RequestAsynctask(AsyncResponse delegate){
        this.delegate= delegate;
    }
    @Override
    protected Response doInBackground(Void... params) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://kudago.com/public-api/v1.4/events/?lang=&fields=title,images&actual_since=1557187200")
                .build();

        Response response = null;

        try {
             response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response result) {
//        super.onPostExecute(result);
        delegate.processFinish(result);
    }
}
