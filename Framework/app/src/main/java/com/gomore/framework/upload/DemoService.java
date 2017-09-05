package com.gomore.framework.upload;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.gomore.framework.R;
import com.gomore.gomorelibrary.update.core.VersionService;

public class DemoService extends VersionService {
    public DemoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onResponses(VersionService service, String response) {
        Log.e("DemoService", response);
        //可以在判断版本之后在设置是否强制更新或者VersionParams
        // versionParams.isForceUpdate=true;
        showVersionDialog("https://www.pgyer.com/apiv1/app/install?aId=c0f37477baeb637f37affbf02e0cfe43&_api_key=1c1acff227f9dea3eafd531ba369680d", "检测到新版本", getString(R.string.updatecontent));
//        or
//        showVersionDialog("http://www.apk3.com/uploads/soft/guiguangbao/UCllq.apk", "检测到新版本", getString(R.string.updatecontent),bundle);

    }
}
