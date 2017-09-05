package com.gomore.gomorelibrary.update.callback;

import java.io.File;


public interface DownloadListener {
    void onCheckerDownloading(int progress);
    void onCheckerDownloadSuccess(File file);
    void onCheckerDownloadFail();
}
