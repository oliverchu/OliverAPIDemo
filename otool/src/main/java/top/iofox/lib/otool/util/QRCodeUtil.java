//package top.iofox.lib.otool.util;
//
//import android.graphics.*;
//import android.graphics.drawable.Drawable;
//import android.util.Log;
//import android.view.View;
//import com.google.zxing.*;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.common.GlobalHistogramBinarizer;
//import com.google.zxing.common.HybridBinarizer;
//import com.google.zxing.qrcode.QRCodeWriter;
//import com.hand.hrms.R;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.*;
//
//import static android.graphics.Color.WHITE;
//
///**
// * Created by oliver on 17-9-4.
// */
//
//public class QRCodeUtil {
//
//    private static final String TAG = "QRCodeUtil";
//    public static JSONObject addGroupData = null;
//    public static final Map<DecodeHintType, Object> HINTS = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
//
//    public static void setAddGroupData(JSONObject data) {
//        addGroupData = data;
//    }
//
//    public static JSONObject getAddGroupData() {
//        return addGroupData;
//    }
//
//    public static final Bitmap create2DCoderBitmap(String url, int QR_WIDTH, int QR_HEIGHT) {
//        try {
//            if (url == null || url.length() == 0) {
//                return null;
//            }
//            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            BitMatrix bitMatrix = new QRCodeWriter().encode(url,
//                    BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
//            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
//            for (int y = 0; y < QR_HEIGHT; y++) {
//                for (int x = 0; x < QR_WIDTH; x++) {
//                    if (bitMatrix.get(x, y)) {
//                        pixels[y * QR_WIDTH + x] = 0xff000000;
//                    } else {
//                        pixels[y * QR_WIDTH + x] = 0xffffffff;
//                    }
//                }
//            }
//            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
//                    Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
//            return bitmap;
//        } catch (WriterException e) {
//            e.pr
//            return null;
//        }
//    }
//
//    public static Bitmap getViewBitmap(View v) {
//        v.clearFocus();
//        v.setPressed(false);
//        boolean willNotCache = v.willNotCacheDrawing();
//        v.setWillNotCacheDrawing(false);
//        int color = v.getDrawingCacheBackgroundColor();
//        v.setDrawingCacheBackgroundColor(0);
//        if (color != 0) {
//            v.destroyDrawingCache();
//        }
//        v.buildDrawingCache();
//        Bitmap cacheBitmap = v.getDrawingCache();
//        if (cacheBitmap == null) {
//            return null;
//        }
//        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
//        v.destroyDrawingCache();
//        v.setWillNotCacheDrawing(willNotCache);
//        v.setDrawingCacheBackgroundColor(color);
//        return bitmap;
//    }
//
//    public static Bitmap saveFile(String title, String subName, Drawable avatar, Bitmap barcode) {
//        int w = 500, h = 600;
//        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
//        Bitmap bmpAvatar = drawableToBitmap(avatar);
//        Canvas canvas = new Canvas(bmp);
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.WHITE);
//        canvas.drawRect(0, 0, w, h, paint);
//        canvas.drawBitmap(bmpAvatar, new Rect(0, 0, bmpAvatar.getWidth(), bmpAvatar.getHeight()), new Rect(20, 20, 70, 70), paint);
//        paint.setColor(BLACK);
//        paint.setTextSize(25);
//        canvas.drawText(title, 90, 40, paint);
//        canvas.drawBitmap(barcode, 5, 90, paint);
//        paint.setColor(Color.GRAY);
//        paint.setTextSize(20);
//        if (subName != null) {
//            canvas.drawText(subName, 90, 65, paint);
//        }
//        paint.setStrokeWidth(0.5f);
//        canvas.drawLine(10, 55, 490, 87, paint);
//        bmpAvatar.recycle();
//        return bmp;
//    }
//
//    public static Bitmap drawableToBitmap(Drawable drawable) {
//
//
//        Bitmap bitmap = Bitmap.createBitmap(
//
//                drawable.getIntrinsicWidth(),
//
//                drawable.getIntrinsicHeight(),
//
//                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
//
//                        : Bitmap.Config.RGB_565);
//
//        Canvas canvas = new Canvas(bitmap);
//
//        //canvas.setBitmap(bitmap);
//
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//
//        drawable.draw(canvas);
//
//        return bitmap;
//
//    }
//
//    private static final int BLACK = 0xff000000;
//
//    public static Bitmap createQRCode(String str, int widthAndHeight, Bitmap logo)
//            throws WriterException {
//        if ("".equals(str)) {
//            return null;
//        }
//        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//        BitMatrix matrix = new MultiFormatWriter().encode(str,
//                BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
//        matrix = deleteWhite(matrix);
//        int width = matrix.getWidth();
//        int height = matrix.getHeight();
//        int[] pixels = new int[width * height];
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (matrix.get(x, y)) {
//                    pixels[y * width + x] = BLACK;
//                } else {
//                    pixels[y * width + x] = WHITE;
//                }
//
//            }
//        }
//
//        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//        if (logo != null) {
//            bitmap = addLogo(bitmap, logo);
//            logo.recycle();
//            logo = null;
//        }
//        return bitmap;
//    }
//
//    //删除白边
//    private static BitMatrix deleteWhite(BitMatrix matrix) {
//        int[] rec = matrix.getEnclosingRectangle();
//        int resWidth = rec[2] + 1;
//        int resHeight = rec[3] + 1;
//
//        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
//        resMatrix.clear();
//        for (int i = 0; i < resWidth; i++) {
//            for (int j = 0; j < resHeight; j++) {
//                if (matrix.get(i + rec[0], j + rec[1]))
//                    resMatrix.set(i, j);
//            }
//        }
//        return resMatrix;
//    }
//
//    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
//        if (src == null) {
//            return null;
//        }
//
//        if (logo == null) {
//            return src;
//        }
//
//        //获取图片的宽高
//        int srcWidth = src.getWidth();
//        int srcHeight = src.getHeight();
//        int padding = 2;
//        int logoWidth = logo.getWidth() + padding * 2;
//        int logoHeight = logo.getHeight() + padding * 2;
//        Bitmap tempLogo = Bitmap.createBitmap(logoWidth, logoHeight, Bitmap.Config.RGB_565);
//        Canvas canvasTemp = new Canvas(tempLogo);
//        Paint paint = new Paint();
//        paint.setColor(Color.WHITE);
//        canvasTemp.drawRoundRect(new RectF(0, 0, logoWidth, logoHeight), 20, 20, paint);
//        canvasTemp.drawBitmap(logo, padding, padding, null);
//        canvasTemp.save(Canvas.ALL_SAVE_FLAG);
//        canvasTemp.restore();
//        if (srcWidth == 0 || srcHeight == 0) {
//            return null;
//        }
//
//        if (logoWidth == 0 || logoHeight == 0) {
//            return src;
//        }
//
//        //logo大小为二维码整体大小的1/5
//        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
//        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.RGB_565);
//        try {
//            Canvas canvas = new Canvas(bitmap);
//            canvas.drawBitmap(src, 0, 0, null);
//            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
//            canvas.drawBitmap(tempLogo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
//            canvas.save(Canvas.ALL_SAVE_FLAG);
//            canvas.restore();
//
//        } catch (Exception e) {
//            e.getStackTrace();
//            bitmap.recycle();
//
//            return src;
//        } finally {
//            tempLogo.recycle();
//        }
//        return bitmap;
//    }
//
//    public static JSONObject parseHtml(String url) {
//        JSONObject result = null;
//        LogUtils.e(TAG, "parseHtml: " + url);
//        if (url != null && url.toLowerCase().startsWith("http")) {
//            String QRCODE_GROUP_FORMAT = Utils.getString(R.string.QRCODE_GROUP_FORMAT);
//            String matchHeader = QRCODE_GROUP_FORMAT.substring(0, QRCODE_GROUP_FORMAT.indexOf("=") + 1);
//            LogUtils.e(TAG, "parseHtml: " + matchHeader);
//            if (url.startsWith(matchHeader)) {
//                String scheme = url.replace(matchHeader, "");
//                result = parseScheme(scheme);
//            }
//        } else if (url != null && url.toLowerCase().startsWith(Utils.getString(R.string.launch_scheme))) {
//            result = parseScheme(url);
//        }
//        return result;
//    }
//
//    public static JSONObject parseScheme(String scheme) {
//        String[] params = scheme.substring(scheme.indexOf("?") + 1).split("&");
//        String gid = null, oid = null;
//        for (int i = 0, len = params.length; i < len; i++) {
//            if (params[i].startsWith("gid=")) {
//                gid = params[i].replace("gid=", "");
//                continue;
//            }
//            if (params[i].startsWith("oid=")) {
//                oid = params[i].replace("oid=", "");
//                continue;
//            }
//            if (!"type=group".equals(params[i])) {
//                return null;
//            }
//        }
//        LogUtils.e(TAG, "scanAddIntoGroup: " + gid + " " + oid + " ");
//        return parseJSONObject(gid, Utils.getUserId(), oid);
//    }
//
//    public static JSONObject parseJSONObject(String gid, String uid, String oid) {
//        if (gid != null && oid != null && uid != null) {
//            final JSONArray array = new JSONArray();
//            array.put(uid);
//            JSONObject object = new JSONObject();
//            try {
//                object.put("userIds", array);
//                object.put("groupId", gid);
//                object.put("operatorUserId", oid);
//            } catch (Exception ex) {
//                return null;
//            }
//            return object;
//        }
//        return null;
//    }
//
//    /**
//     * RGB转YUV420sp
//     *
//     * @param yuv420sp inputWidth * inputHeight * 3 / 2
//     * @param argb     inputWidth * inputHeight
//     * @param width
//     * @param height
//     */
//    private static void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width,
//                                       int height) {
//        // 帧图片的像素大小
//        final int frameSize = width * height;
//        // ---YUV数据---
//        int Y, U, V;
//        // Y的index从0开始
//        int yIndex = 0;
//        // UV的index从frameSize开始
//        int uvIndex = frameSize;
//
//        // ---颜色数据---
////      int a, R, G, B;
//        int R, G, B;
//        //
//        int argbIndex = 0;
//        //
//
//        // ---循环所有像素点，RGB转YUV---
//        for (int j = 0; j < height; j++) {
//            for (int i = 0; i < width; i++) {
//
//                // a is not used obviously
////              a = (argb[argbIndex] & 0xff000000) >> 24;
//                R = (argb[argbIndex] & 0xff0000) >> 16;
//                G = (argb[argbIndex] & 0xff00) >> 8;
//                B = (argb[argbIndex] & 0xff);
//                //
//                argbIndex++;
//
//                // well known RGB to YUV algorithm
//                Y = ((66 * R + 129 * G + 25 * B + 128) >> 8) + 16;
//                U = ((-38 * R - 74 * G + 112 * B + 128) >> 8) + 128;
//                V = ((112 * R - 94 * G - 18 * B + 128) >> 8) + 128;
//
//                //
//                Y = Math.max(0, Math.min(Y, 255));
//                U = Math.max(0, Math.min(U, 255));
//                V = Math.max(0, Math.min(V, 255));
//
//                // NV21 has a plane of Y and interleaved planes of VU each
//                // sampled by a factor of 2
//                // meaning for every 4 Y pixels there are 1 V and 1 U. Note the
//                // sampling is every other
//                // pixel AND every other scanline.
//                // ---Y---
//                yuv420sp[yIndex++] = (byte) Y;
//                // ---UV---
//                if ((j % 2 == 0) && (i % 2 == 0)) {
//                    //
//                    yuv420sp[uvIndex++] = (byte) V;
//                    //
//                    yuv420sp[uvIndex++] = (byte) U;
//                }
//            }
//        }
//    }
//
//
//    static {
//        List<BarcodeFormat> allFormats = new ArrayList<BarcodeFormat>();
//        allFormats.add(BarcodeFormat.AZTEC);
//        allFormats.add(BarcodeFormat.CODABAR);
//        allFormats.add(BarcodeFormat.CODE_39);
//        allFormats.add(BarcodeFormat.CODE_93);
//        allFormats.add(BarcodeFormat.CODE_128);
//        allFormats.add(BarcodeFormat.DATA_MATRIX);
//        allFormats.add(BarcodeFormat.EAN_8);
//        allFormats.add(BarcodeFormat.EAN_13);
//        allFormats.add(BarcodeFormat.ITF);
//        allFormats.add(BarcodeFormat.MAXICODE);
//        allFormats.add(BarcodeFormat.PDF_417);
//        allFormats.add(BarcodeFormat.QR_CODE);
//        allFormats.add(BarcodeFormat.RSS_14);
//        allFormats.add(BarcodeFormat.RSS_EXPANDED);
//        allFormats.add(BarcodeFormat.UPC_A);
//        allFormats.add(BarcodeFormat.UPC_E);
//        allFormats.add(BarcodeFormat.UPC_EAN_EXTENSION);
//        HINTS.put(DecodeHintType.TRY_HARDER, BarcodeFormat.QR_CODE);
//        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, allFormats);
//        HINTS.put(DecodeHintType.CHARACTER_SET, "utf-8");
//    }
//
//    /**
//     * 同步解析本地图片二维码。该方法是耗时操作，请在子线程中调用。
//     *
//     * @param picturePath 要解析的二维码图片本地路径
//     * @return 返回二维码图片里的内容 或 null
//     */
//    public static String syncDecodeQRCode(String picturePath) {
//        return syncDecodeQRCode(getDecodeAbleBitmap(picturePath));
//    }
//
//    /**
//     * 同步解析bitmap二维码。该方法是耗时操作，请在子线程中调用。
//     *
//     * @param bmp 要解析的二维码图片
//     * @return 返回二维码图片里的内容 或 null
//     */
//    public static String syncDecodeQRCode(Bitmap bmp) {
//        int srcW = bmp.getWidth();
//        int srcH = bmp.getHeight();
//        float factor = 1;
//        if (srcW > 600) {
//            factor = srcW / 600f;
//        }
//        Log.d(TAG, "onResourceReady: Factor = " + factor);
//        Bitmap bitmap = Bitmap.createScaledBitmap(bmp, (int) (srcW / factor), (int) (srcH / factor), false);
//        Result result = null;
//        RGBLuminanceSource source = null;
//        try {
//            int width = bitmap.getWidth();
//            int height = bitmap.getHeight();
//            int[] pixels = new int[width * height];
//            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
//            source = new RGBLuminanceSource(width, height, pixels);
//            result = new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(source)), HINTS);
//            bitmap.recycle();
//            return result.getText();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (source != null) {
//                try {
//                    result = new MultiFormatReader().decode(new BinaryBitmap(new GlobalHistogramBinarizer(source)), HINTS);
//                    return result.getText();
//                } catch (Throwable e2) {
//                    e2.printStackTrace();
//                }
//            }
//            if (bitmap != null) {
//                bitmap.recycle();
//                bitmap = null;
//            }
//            return null;
//        }
//    }
//
//    /**
//     * 将本地图片文件转换成可解码二维码的 Bitmap。为了避免图片太大，这里对图片进行了压缩。感谢 https://github.com/devilsen 提的 PR
//     *
//     * @param picturePath 本地图片文件路径
//     * @return
//     */
//    private static Bitmap getDecodeAbleBitmap(String picturePath) {
//        try {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.decodeFile(picturePath, options);
//            int sampleSize = options.outHeight / 400;
//            if (sampleSize <= 0) {
//                sampleSize = 1;
//            }
//            options.inSampleSize = sampleSize;
//            options.inJustDecodeBounds = false;
//
//            return BitmapFactory.decodeFile(picturePath, options);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//}
