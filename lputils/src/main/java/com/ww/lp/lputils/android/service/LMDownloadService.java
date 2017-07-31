package com.ww.lp.lputils.android.service;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.ww.lp.lputils.LogUtils;
import com.ww.lp.lputils.PermissionUtils;

import java.io.File;
import java.util.List;

import static com.ww.lp.lputils.android.view.LMH5Activity.DOWNLOAD_FILE_NAME;
import static com.ww.lp.lputils.android.view.LMH5Activity.DOWNLOAD_FILE_URL;


/**
 * Created by LinkedME06 on 15/01/2017.
 */

public class LMDownloadService extends Service {

    private DownloadManager dm;
    private BroadcastReceiver receiver;
    private long enqueue;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!PermissionUtils.selfPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            LogUtils.d("无读写存储卡权限，请先获取后再下载");
            stopSelf();
            return Service.START_NOT_STICKY;
        }
        final String fileName = intent.getStringExtra(DOWNLOAD_FILE_NAME);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long downloadCompletedId = intent.getLongExtra(
                        DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                if (enqueue != downloadCompletedId) {
                    return;
                }
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri;
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && getApplicationInfo().targetSdkVersion > Build.VERSION_CODES.M) {
                    String authorities = "";
                    List<ProviderInfo> providers = getPackageManager().queryContentProviders(getPackageName(), Process.myUid(), 0);
                    if (providers != null) {
                        for (ProviderInfo provider : providers) {
                            if (TextUtils.equals("android.support.v4.content.FileProvider", provider.name)) {
                                authorities = provider.authority;
                                break;
                            }
                        }
                    }
                    LogUtils.d("设置FileProvider的Authorities为：" + authorities);
                    if (TextUtils.isEmpty(authorities)) {
                        LogUtils.d("未设置FileProvider的Authorities，请在Manifest.xml配置文件中配置provider，参考：https://developer.android.com/training/secure-file-sharing/setup-sharing.html。");
                        stopSelf();
                        return;
                    }
                    try {
                        uri = FileProvider.getUriForFile(LMDownloadService.this, authorities,
                                new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + fileName));
                    } catch (Exception e) {
                        LogUtils.d("FileProvider的Authorities无正确匹配！");
                        stopSelf();
                        return;
                    }
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } else {
                    uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator + fileName));
                }
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                startActivity(intent);
                stopSelf();
            }
        };
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        startDownload(intent.getStringExtra(DOWNLOAD_FILE_URL), fileName);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }


    /**
     * 开始下载文件
     *
     * @param downloadUrl 文件下载地址
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void startDownload(String downloadUrl, String fileName) {
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(downloadUrl));
        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        enqueue = dm.enqueue(request);
    }


}