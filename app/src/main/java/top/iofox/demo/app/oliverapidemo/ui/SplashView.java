package top.iofox.demo.app.oliverapidemo.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

/***************************************
 * Description: []
 * Created by [Oliver Chu] on 2018/11/10 15:48
 * Version: [1]
 ***************************************
 * Update:
 * 1.[Oliver Chu] 创建文件;编写初始代码
 * 2.[NAME] LOG_HERE
 *
 ***************************************/
public class SplashView extends View implements Runnable{

    private Random random;
    private Paint paint;
    private static final String TAG = "SplashView";
    private SimpleDateFormat dateFormat;
    private Calendar baseCalendar,nowCalendar;

    public SplashView(Context context) {
        this(context,null);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        random = new Random();
        paint = new Paint();
        dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss",Locale.US);
        baseCalendar = Calendar.getInstance();
        nowCalendar = Calendar.getInstance();
        baseCalendar.set(2018,10,10,15,48,0);
        Log.d(TAG, "init: "+dateFormat.format(baseCalendar.getTime())+" "+dateFormat.format(nowCalendar.getTime()));
        paint.setColor(randomColor());
        paint.setTextSize(50);
        long now = nowCalendar.getTimeInMillis()/1000;
        long base = baseCalendar.getTimeInMillis()/1000;

        //4s
        delay = (int) (10000/(float)(now-base));
        Log.d(TAG, "run: "+delay+" "+now+" "+base+" "+(now-base));
        new Thread(this).start();
    }
    private  int delay;
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void run() {

        while (true){
            nowCalendar.roll(Calendar.SECOND,1);
            if(baseCalendar.compareTo(nowCalendar)>0){
                break;
            }
            postInvalidate();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(dateFormat.format(baseCalendar.getTime()),100,100,paint);


    }



    private int randomColor(){
        return Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }
}
