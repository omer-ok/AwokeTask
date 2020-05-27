package com.kampen.riksSe.api.remote_api;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class OfflineMockInterceptor implements Interceptor {

    private static final MediaType MEDIA_JSON = MediaType.parse("application/json");
    private Context mContext;

    public OfflineMockInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        /* http://sample.com/hello will return "/hello" */
        String path = request.url().encodedPath();


        /* I put a 'hello' file in debug/assets/mockData */
        InputStream stream = mContext.getAssets().open("data.json");

        /* Just read the file. */
        String json = parseStream(stream);

        Response response = new Response.Builder()
                .body(ResponseBody.create(MEDIA_JSON, json))
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(200)
                .build();

        return response;
    }

    private String parseStream(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
        }
        in.close();
        return builder.toString();
    }
}