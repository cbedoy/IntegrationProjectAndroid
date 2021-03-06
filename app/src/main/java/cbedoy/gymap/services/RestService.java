package cbedoy.gymap.services;

import android.os.AsyncTask;
import android.os.Build;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import cbedoy.gymap.interfaces.IRestService;

public class RestService implements IRestService {

    @Override
    @SuppressWarnings("unchecked")
    public void request(String url, IRestCallback callback) {
        HashMap<String, Object> request = new HashMap<String, Object>();
        request.put("url", url);
        request.put("callback", callback);

        AsyncCall call = new AsyncCall();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            call.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
        } else {
            call.execute(request);
        }
    }

    private class AsyncCall extends AsyncTask<HashMap<String, Object>, Void, HashMap<String, Object>> {

        @Override
        @SuppressWarnings("unchecked")
        protected HashMap<String, Object> doInBackground(HashMap<String, Object>... params) {
            HashMap<String, Object> request = params[0];
            String url = request.get("url").toString();

            HttpResponse httpResponse;
            HttpUriRequest httpUriRequest;
            HttpClient defaultHttpClient = new DefaultHttpClient();
            HashMap<String, Object> response;

            try {
                httpUriRequest = new HttpGet(url);
                httpResponse = defaultHttpClient.execute(httpUriRequest);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
                StringBuilder builder = new StringBuilder();
                for (String line; (line = bufferedReader.readLine()) != null; ) {
                    builder.append(line).append("\n");
                }

                JSONTokener jsonTokener = new JSONTokener(builder.toString());
                JSONArray jsonArray = new JSONArray(jsonTokener);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", true);
                jsonObject.put("random_locations", jsonArray);
                response = (HashMap<String, Object>) Utils.toMap(jsonObject);
                LogService.logResponse(response);
            }
            catch (UnsupportedEncodingException ex)
            {
                response = new HashMap<>();
                response.put("status", false);
                response.put("error", "char_encoding");
                response.put("message", "char_encoding");
            }
            catch (ClientProtocolException ex)
            {
                response = new HashMap<>();
                response.put("status", false);
                response.put("error", "http_protocol");
                response.put("message", "http_protocol");
            }
            catch (IOException ex)
            {
                response = new HashMap<>();
                response.put("status", false);
                response.put("error", "no_network");
                response.put("message", "no_network");
            }
            catch (JSONException ex)
            {
                response = new HashMap<>();
                response.put("status", false);
                response.put("error", "error_communication");
                response.put("message", "error_communication");
            }

            HashMap<String, Object> result = new HashMap<>();
            result.put("callback", request.get("callback"));
            result.put("response", response);
            return result;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void onPostExecute(HashMap<String, Object> result) {
            IRestCallback callback = (IRestCallback) result.get("callback");
            HashMap<String, Object> response = (HashMap<String, Object>) result.get("response");
            callback.run(response);
            super.onPostExecute(result);
        }

    }


}