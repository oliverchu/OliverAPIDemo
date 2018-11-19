//package top.iofox.app.easytools.tool;
//
//import android.os.Handler;
//import android.os.Looper;
//import android.util.JsonReader;
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONStringer;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//
//public class NetTool {
//    private OkHttpClient client;
//    private Handler handler = null;
//    private static final String TAG = "NetTool";
//    public abstract static class ResultCallback{
//        public void onPreExecute(){
//
//        }
//
//        public boolean doInBackground(){
//
//            return true;
//        }
//        public void onPostExecute(String json, JSONObject object, JSONArray array) throws JSONException{
//
//        }
//        public void onError(){
//
//        }
//
//    }
//
//    private NetTool(){
//        client = new OkHttpClient();
//        handler = new Handler(Looper.getMainLooper());
//    }
//
//
//    public void get(final String url, final ResultCallback callback){
//        Request request = new Request.Builder().url(url).get().build();
//        onPreExecute(callback);
//        LogUtil.d(TAG,url+" Request:\n"+request.toString());
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//                onError(callback);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                ResponseBody body = response.body();
//
//                if(body!=null){
//                    String string = body.string().trim();
//                    if(string.startsWith("{\"")&& string.endsWith("}")){
//                        try {
//                            postResult(callback,string,new JSONObject(string),null);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            postResult(callback,string,new JSONObject(),null);
//                        }
//                        LogUtil.dJson(TAG,url+" Response:\n"+string);
//                    }else if(string.startsWith("[")&&string.endsWith("]")){
//                        try {
//                            postResult(callback,string,null,new JSONArray(string));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            postResult(callback,string,null,new JSONArray());
//                        }
//                        LogUtil.dJson(TAG,url+" Response:\n"+string);
//                    }else {
//                        postResult(callback,string,null,null);
//                        LogUtil.dLong(TAG,url+" Response:\n"+string);
//                    }
//                }else {
//                    onError(callback);
//                    LogUtil.eLong(TAG,url+" Response:\n"+"Response body is NULL!");
//                }
//
//            }
//        });
//    }
//    private void postResult(final ResultCallback callback, final String json, final JSONObject object, final JSONArray array){
//        if(doInBackground(callback)){
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    onPostExecute(callback,json,object,array);
//                }
//            });
//        }
//    }
//
//    private void onError(final ResultCallback callback){
//        if(callback!=null){
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    callback.onError();
//                }
//            });
//
//        }
//    }
//
//    private void onPreExecute(ResultCallback callback){
//        if(callback!=null){
//            callback.onPreExecute();
//        }
//    }
//
//    private boolean doInBackground(ResultCallback callback){
//        if(callback!=null){
//            return callback.doInBackground();
//        }
//        return false;
//    }
//
//    private void onPostExecute(ResultCallback callback,String json, JSONObject object, JSONArray array){
//        if(callback!=null){
//            try {
//                callback.onPostExecute(json,object,array);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static NetTool getInstance() {
//        return InternClz.INSTANCE;
//    }
//
//
//    private static class InternClz{
//         final static NetTool INSTANCE = new NetTool();
//    }
//}
