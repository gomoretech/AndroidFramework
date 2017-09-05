package com.gomore.gomorelibrary.update.core;

import android.os.Parcel;
import android.os.Parcelable;

import com.gomore.gomorelibrary.update.core.http.HttpHeaders;
import com.gomore.gomorelibrary.update.core.http.HttpParams;
import com.gomore.gomorelibrary.update.core.http.HttpRequestMethod;
import com.gomore.gomorelibrary.utils.FileTools;


public class VersionParams implements Parcelable {
    private String requestUrl;
    private String downloadAPKPath;
    private HttpHeaders httpHeaders;
    private long pauseRequestTime;
    private HttpRequestMethod requestMethod;
    private HttpParams requestParams;
    private Class<? extends VersionDialogActivity> customDownloadActivityClass;
    //    public boolean isForceUpdate;
    public boolean isForceRedownload;
    public boolean isSilentDownload;
    private Class<? extends VersionService> service;

    private VersionParams() {
    }

    public Class<? extends VersionService> getService() {
        return service;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getDownloadAPKPath() {
        return downloadAPKPath;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public long getPauseRequestTime() {
        return pauseRequestTime;
    }

    public HttpRequestMethod getRequestMethod() {
        return requestMethod;
    }

    public HttpParams getRequestParams() {
        return requestParams;
    }

    public Class getCustomDownloadActivityClass() {
        return customDownloadActivityClass;
    }

//    public boolean isForceUpdate() {
//        return isForceUpdate;
//    }

    public boolean isForceRedownload() {
        return isForceRedownload;
    }

    public boolean isSilentDownload() {
        return isSilentDownload;
    }

    public static class Builder {

        VersionParams params;

        public Builder() {
            params = new VersionParams();
            params.downloadAPKPath = FileTools.getDownloadApkCachePath();
            params.pauseRequestTime = 1000 * 30;
            params.requestMethod = HttpRequestMethod.GET;
            params.customDownloadActivityClass = VersionDialogActivity.class;
//            this.isForceUpdate = false;
            params.isForceRedownload = false;
            params.isSilentDownload = false;
        }

        public Builder setRequestUrl(String requestUrl) {
            params.requestUrl = requestUrl;
            return this;
        }

        public Builder setDownloadAPKPath(String downloadAPKPath) {
            params.downloadAPKPath = downloadAPKPath;
            return this;
        }

        public Builder setHttpHeaders(HttpHeaders httpHeaders) {
            params.httpHeaders = httpHeaders;
            return this;
        }

        public Builder setPauseRequestTime(long pauseRequestTime) {
            params.pauseRequestTime = pauseRequestTime;
            return this;
        }

        public Builder setRequestMethod(HttpRequestMethod requestMethod) {
            params.requestMethod = requestMethod;
            return this;
        }

        public Builder setRequestParams(HttpParams requestParams) {
            params.requestParams = requestParams;
            return this;
        }

        public Builder setCustomDownloadActivityClass(Class customDownloadActivityClass) {
            params.customDownloadActivityClass = customDownloadActivityClass;
            return this;
        }

//        public Builder setForceUpdate(boolean forceUpdate) {
//            isForceUpdate = forceUpdate;
//            return this;
//        }

        public Builder setForceRedownload(boolean forceRedownload) {
            params.isForceRedownload = forceRedownload;
            return this;
        }

        public Builder setSilentDownload(boolean silentDownload) {
            params.isSilentDownload = silentDownload;
            return this;
        }

        public Builder setService(Class<? extends VersionService> service) {
            params.service = service;
            return this;
        }

        public VersionParams build() {
            return params;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.requestUrl);
        dest.writeString(this.downloadAPKPath);
        dest.writeSerializable(this.httpHeaders);
        dest.writeLong(this.pauseRequestTime);
        dest.writeInt(this.requestMethod == null ? -1 : this.requestMethod.ordinal());
        dest.writeSerializable(this.requestParams);
        dest.writeSerializable(this.customDownloadActivityClass);
        dest.writeByte(this.isForceRedownload ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSilentDownload ? (byte) 1 : (byte) 0);
        dest.writeSerializable(this.service);
    }

    protected VersionParams(Parcel in) {
        this.requestUrl = in.readString();
        this.downloadAPKPath = in.readString();
        this.httpHeaders = (HttpHeaders) in.readSerializable();
        this.pauseRequestTime = in.readLong();
        int tmpRequestMethod = in.readInt();
        this.requestMethod = tmpRequestMethod == -1 ? null : HttpRequestMethod.values()[tmpRequestMethod];
        this.requestParams = (HttpParams) in.readSerializable();
        this.customDownloadActivityClass = (Class<? extends VersionDialogActivity>) in.readSerializable();
        this.isForceRedownload = in.readByte() != 0;
        this.isSilentDownload = in.readByte() != 0;
        this.service = (Class<? extends VersionService>) in.readSerializable();
    }

    public static final Creator<VersionParams> CREATOR = new Creator<VersionParams>() {
        @Override
        public VersionParams createFromParcel(Parcel source) {
            return new VersionParams(source);
        }

        @Override
        public VersionParams[] newArray(int size) {
            return new VersionParams[size];
        }
    };
}
