package as.bwei.com.show.model;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/15.
 */

public class ShowModel {
    private OkHttpClient okHttpClient;

    public ShowModel(){
        okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public void show(final ShowCallback showCallback){
        FormBody formBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/product/getCarts?uid=71")
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showCallback.failure("失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                showCallback.success(string);
            }
        });
    }

    public interface ShowCallback {
        void failure(String msg);

        void success(String msg);
    }
}
