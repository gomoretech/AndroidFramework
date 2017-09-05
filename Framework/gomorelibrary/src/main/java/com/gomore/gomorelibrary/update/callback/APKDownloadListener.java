package com.gomore.gomorelibrary.update.callback;

import java.io.File;

public interface APKDownloadListener {
     void onDownloading(int progress);
    void onDownloadSuccess(File file);
    void onDownloadFail();
}
