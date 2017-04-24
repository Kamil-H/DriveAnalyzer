package kamilhalko.com.driveanalyzer.data.network;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kamilhalko.com.driveanalyzer.data.storage.FileHelper;
import kamilhalko.com.driveanalyzer.utils.AppConstants;
import kamilhalko.com.driveanalyzer.utils.NetworkUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Singleton
public class NetworkHelperImpl implements NetworkHelper {
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private FileHelper fileHelper;
    private Context context;

    @Inject
    public NetworkHelperImpl(Context context, FileHelper fileHelper) {
        this.context = context;
        this.fileHelper = fileHelper;
    }

    @Override
    public void synchronize() {
        if (!NetworkUtils.isInternetConection(context)) {
            return;
        }
        Observable.fromIterable(fileHelper.getNotSynchronizedFiles())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) throws Exception {
                        OkHttpClient client = new OkHttpClient.Builder()
                                .build();

                        RequestBody requestBody = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE_JSON, file))
                                .build();

                        Request request = new Request.Builder()
                                .url(AppConstants.SERVER_ADDRESS)
                                .post(requestBody)
                                .build();

                        Response response = client.newCall(request).execute();
                        if (response.isSuccessful()) {
                            file.delete();
                        }
                    }
                });
    }
}
