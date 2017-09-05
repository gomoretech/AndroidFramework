package com.gomore.gomorelibrary.update.core;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import com.gomore.gomorelibrary.R;
import com.gomore.gomorelibrary.update.callback.DownloadListener;
import com.gomore.gomorelibrary.update.core.http.MyOkHttp;
import com.gomore.gomorelibrary.update.core.http.HttpRequestMethod;
import com.gomore.gomorelibrary.utils.ALog;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public abstract class VersionService extends Service implements DownloadListener {
    protected VersionParams versionParams;
    public static final String VERSION_PARAMS_KEY = "VERSION_PARAMS_KEY";
    public static final String VERSION_PARAMS_EXTRA_KEY = "VERSION_PARAMS_EXTRA_KEY";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            versionParams = intent.getParcelableExtra(VERSION_PARAMS_KEY);
            verfiyAndDeleteAPK();
            requestVersionUrlSync();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 验证安装包是否存在，并且在安装成功情况下删除安装包
     */
    private void verfiyAndDeleteAPK() {
        //判断versioncode与当前版本不一样的apk是否存在，存在删除安装包
        String downloadPath = versionParams.getDownloadAPKPath() + getApplicationContext().getString(R.string.versionchecklib_download_apkname, getApplicationContext().getPackageName());
        if (!DownloadManager.checkAPKIsExists(getApplicationContext(), downloadPath)) {
            try {
                ALog.e("删除本地apk");
                new File(downloadPath).delete();
            } catch (Exception e) {
            }
        }
    }

    private void requestVersionUrlSync() {
        requestVersionUrl();
    }

    public abstract void onResponses(VersionService service, String response);

    Callback stringCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            pauseRequest();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                final String result = response.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        onResponses(VersionService.this, result);
                    }
                });
            } else {
                pauseRequest();
            }
        }

    };

    /**
     * 间隔请求
     */
    private void pauseRequest() {
        long pauseTime = versionParams.getPauseRequestTime();
        //不为-1 间隔请求
        if (pauseTime > 0) {
            ALog.e("请求版本接口失败，下次请求将在" + pauseTime + "ms后开始");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestVersionUrlSync();
                }
            }, pauseTime);
        }
    }

    private void requestVersionUrl() {
        OkHttpClient client = MyOkHttp.getHttpClient();
        HttpRequestMethod requestMethod = versionParams.getRequestMethod();
        Request request = null;
        switch (requestMethod) {
            case GET:
                request = MyOkHttp.get(versionParams).build();
                break;
            case POST:
                request = MyOkHttp.post(versionParams).build();
                break;
            case POSTJSON:
                request = MyOkHttp.postJson(versionParams).build();
                break;
        }
        client.newCall(request).enqueue(stringCallback);
    }


    String downloadUrl, title, updateMsg;
    Bundle paramBundle;

    public void showVersionDialog(String downloadUrl, String title, String updateMsg) {
        showVersionDialog(downloadUrl, title, updateMsg, null);
    }

    public void showVersionDialog(String downloadUrl, String title, String updateMsg, Bundle paramBundle) {
        this.downloadUrl = downloadUrl;
        this.title = title;
        this.updateMsg = updateMsg;
        this.paramBundle = paramBundle;
        if (versionParams.isSilentDownload()) {
            silentDownload();
        } else {
            goToVersionDialog();
        }
    }

    private void silentDownload() {
        DownloadManager.downloadAPK(getApplicationContext(), downloadUrl, versionParams, this);
    }

    @Override
    public void onCheckerDownloading(int progress) {

    }

    @Override
    public void onCheckerDownloadSuccess(File file) {
        goToVersionDialog();
    }

    @Override
    public void onCheckerDownloadFail() {
        stopSelf();
    }

    private void goToVersionDialog() {
        Intent intent = new Intent(getApplicationContext(), versionParams.getCustomDownloadActivityClass());
        if (updateMsg != null)
            intent.putExtra("text", updateMsg);
        if (downloadUrl != null)
            intent.putExtra("downloadUrl", downloadUrl);
        if (title != null)
            intent.putExtra("title", title);
        intent.putExtra(VERSION_PARAMS_KEY, versionParams);
        if (paramBundle != null)
            intent.putExtra(VERSION_PARAMS_EXTRA_KEY, paramBundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        stopSelf();
    }

    public void setVersionParams(VersionParams versionParams) {
        this.versionParams = versionParams;
    }
}
