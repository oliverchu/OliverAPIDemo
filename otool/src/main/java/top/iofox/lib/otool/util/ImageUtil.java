package top.iofox.lib.otool.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.PostProcessor;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import java.io.IOException;

import androidx.annotation.NonNull;

/**
 * Created by [Oliver Chu] on 2018/11/26 10:00
 */
public class ImageUtil {

    public Drawable encodePicture(ImageDecoder.Source resources) throws IOException {

        return ImageDecoder.decodeDrawable(resources, new ImageDecoder.OnHeaderDecodedListener() {
            @Override
            public void onHeaderDecoded(@NonNull final ImageDecoder decoder, @NonNull ImageDecoder.ImageInfo info, @NonNull ImageDecoder.Source source) {
                decoder.setTargetSampleSize(2);
                decoder.setPostProcessor(new PostProcessor() {
                    @Override
                    public int onPostProcess(@NonNull Canvas canvas) {
//                            Path path = new Path();

//                            path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
                        int width = canvas.getWidth();
                        int height = canvas.getHeight();
//                            path.addRoundRect(0, 0, width, height, 20, 20, Path.Direction.CW);
                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setColor(Color.TRANSPARENT);
                        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
                        canvas.drawOval(new RectF(0, 0, width, height), paint);
//                            canvas.drawPath(path, paint);
                        return PixelFormat.TRANSLUCENT;
                    }
                });
            }
        });


    }


}
