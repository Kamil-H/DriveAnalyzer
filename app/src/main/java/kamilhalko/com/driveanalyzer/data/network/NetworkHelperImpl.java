package kamilhalko.com.driveanalyzer.data.network;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import kamilhalko.com.driveanalyzer.data.models.Trip;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkHelperImpl implements NetworkHelper {
    private static NetworkHelperImpl instance = new NetworkHelperImpl();
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public static NetworkHelperImpl getInstance() {
        return instance;
    }

    private NetworkHelperImpl() {}

    @Override
    public Observable<Long> synchronize(final Context context, final Trip trip) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                File file = createFile(context, trip);
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_JSON, file))
                        .build();

                Request request = new Request.Builder()
                        .url("http://104.238.177.72:8000/")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();
                file.deleteOnExit();

                if (response.isSuccessful()) {
                    return 10L;
                } else {
                    return null;
                }
            }
        });
    }

    private File createFile(Context context, Trip trip) {
        File file = new File(context.getFilesDir(), String.format("%s_%s.json", trip.getDeviceId(), trip.getTime().toDateTimeISO()));
        FileOutputStream outputStream;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(new Gson().toJson(trip).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
