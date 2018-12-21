package top.iofox.lib.otool.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import androidx.annotation.Nullable;

import java.io.FileNotFoundException;

public class IntentUtil {
    private static final String TAG = "IntentUtil";
    public static final int REQUEST_GET_CONTENT = 0x01;
    public static void getBitmapFromSystem(Activity context,String immeType){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        context.startActivityForResult(intent,REQUEST_GET_CONTENT);
    }

    public static Bitmap resolveBitmapFromSystem(Context context, int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == REQUEST_GET_CONTENT && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            if(uri!=null){
                Log.d(TAG, "resolveContent: "+uri.toString());
                try {
                    return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }




}
