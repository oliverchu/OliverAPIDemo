package top.iofox.lib.otool.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by [Oliver Chu] on 2018/11/26 9:48
 */
public class FileUtil {


    public static long openSystemDownload(Context context, Uri uri, String title, String description, String mimeType) {
        return openSystemDownload((DownloadManager) context.getSystemService(DOWNLOAD_SERVICE), uri, title, description, mimeType);
    }

    /**
     * DownloadManager.EXTRA_DOWNLOAD_ID 从Broadcast中获取到的intent
     * DownloadManager.ACTION_DOWNLOAD_COMPLETE IntentFilter这样设置
     *
     * @param manager     下载管理器
     * @param uri         资源路径
     * @param title       通知栏显示“正在下载{title}”
     * @param description 下载来源
     * @param mimeType    example:application/vnd.android.package-archive
     * @return id 下载文件系统分配的ID
     */
    public static long openSystemDownload(DownloadManager manager, Uri uri, String title, String description, String mimeType) {
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(title); //正在下载+title
        request.setDescription(description); //下载来源
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setMimeType(mimeType);
        request.allowScanningByMediaScanner();
        request.setVisibleInDownloadsUi(true);
        return manager.enqueue(request);
    }


}
