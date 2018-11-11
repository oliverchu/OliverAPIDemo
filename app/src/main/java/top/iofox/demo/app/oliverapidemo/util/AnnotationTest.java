package top.iofox.demo.app.oliverapidemo.util;

import android.Manifest;
import android.os.Build;
import androidx.annotation.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by [Oliver Chu] on 2018/11/11 23:32
 */
public class AnnotationTest {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_LONG_ONE = 1;
    public static final int TYPE_LONG_TWO = 2;
    public static final String TYPE_STRING_ONE = "1";
    public static final String TYPE_STRING_TWO = "2";
    private int state;
    private long longState;
    private String stringState;
    public void setState(@State int state) {
        this.state = state;
    }
    public void setLongState(@StateLong long longState) {
        this.longState = longState;
    }

    public void setStringState(@StateString String stringState) {
        this.stringState = stringState;
    }

    /*表示整数类型的带注释元素表示逻辑类型，并且其值应该是显式命名的常量之一*/
    @IntDef({TYPE_ONE,TYPE_TWO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State{}

    @LongDef({TYPE_LONG_ONE,TYPE_LONG_TWO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StateLong{}

    @StringDef({TYPE_STRING_ONE,TYPE_STRING_TWO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StateString{}

    public void AnimatorRes(@AnimatorRes int AnimatorRes){}

    public void AnimRes(@AnimRes int AnimRes){}

    public void AnyRes(@AnyRes int AnyRes){}

    @AnyThread
    public void AnyThread(){}

    public void ArrayRes(@ArrayRes int ArrayRes){}

    @BinderThread
    public void BinderThread(){}

    public void BoolRes(@BoolRes int BoolRes){}

    @CallSuper
    public void CallSuper(){}

    @CheckResult
    public int CheckResult(){
        return 1;
    }

    public void ColorInt(@ColorInt int ColorInt){}

    public void ColorLong(@ColorLong long ColorLong){}

    public void ColorRes(@ColorRes int ColorRes){}
    public void DimenRes(@DimenRes int DimenRes){}
    public void Dimension(@Dimension int Dimension){}
    public void DrawableRes(@DrawableRes int DrawableRes){}
    public void FloatRange(@FloatRange(from = 0.0f,to = 2.0f) float FloatRange){}
    public void FontRes(@FontRes int FontRes){}
    public void FractionRes(@FractionRes int FractionRes){}

    /**
     * @GuardedBy(lock) 有以下几种使用形式：
     *
     * 1、@GuardedBy( "this" ) 受对象内部锁保护
     * 2、@GuardedBy( "fieldName" ) 受 与fieldName引用相关联的锁 保护。
     * 3、@GuardedBy( "ClassName.fieldName" ) 受 一个类的静态field的锁 保存。
     * 4、@GuardedBy( "methodName()" ) 锁对象是 methodName() 方法的返值，受这个锁保护。
     * 5、@GuardedBy( "ClassName.class" ) 受 ClassName类的直接锁对象保护。而不是这个类的某个实例的锁对象。
     */
    @GuardedBy("ColorLong")
    public void GuardedBy(){}

    public void HalfFloat(@HalfFloat short HalfFloat){}

    public void IdRes(@IdRes int IdRes){}


    public void IntegerRes(@IntegerRes int IntDef){}

    public void InterpolatorRes	(@InterpolatorRes int InterpolatorRes){}

    public void IntRange(@IntRange(from = 0,to = 100) int IntRange){}

    @Keep /*表示在构建时混淆代码时不应删除带注解的元素*/
    public void Keep(int Keep){}

    public void LayoutRes(@LayoutRes int LayoutRes){}

    public void MenuRes(@MenuRes int MenuRes){}
    public void NavigationRes(@NavigationRes int NavigationRes){}
    /*表示整数参数，字段或方法返回值应该是复数资源引用*/
    public void PluralsRes(@PluralsRes int PluralsRes){}

    public void RawRes(@RawRes int PluralsRes){}

    /*表示预期整数参数，字段或方法返回值表示像素维度*/
    public @Px int Px(@Px int Px){
        return Px;

    }

    @MainThread
    public void MainThread(){}
    @UiThread
    public void UiThread(){}
    @WorkerThread
    public void WorkerThread(){}

    /*表示参数，字段或方法返回值永远不能为空*/
    public @NonNull String NonNull(@NonNull String NonNull){
        return NonNull;
    }

    /*表示参数，字段或方法返回值可以为null*/
    public @Nullable String Nullable(@Nullable String Nullable){
        return Nullable;
    }

    @RequiresApi(value = Build.VERSION_CODES.P)
    public void RequiresApi(){ }

    @RequiresFeature(name = "android.hardware.touchscreen.multitouch",enforcement = "RequiresFeature")
    public void RequiresFeature(){}

    @RequiresPermission(Manifest.permission.CAMERA)
    public void RequiresPermission(){}

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP,RestrictTo.Scope.LIBRARY,RestrictTo.Scope.LIBRARY_GROUP,RestrictTo.Scope.SUBCLASSES})
    public void RestrictTo(){}

    public void Size(@Size int Size){}
    public void StringRes(@StringRes int StringRes){}
    public void StyleableRes(@StyleableRes int StyleableRes){}
    public void StyleRes(@StyleRes int StyleRes){}
    public void TransitionRes(@TransitionRes int TransitionRes){}
    public void XmlRes(@XmlRes int XmlRes){}

    /*表示类，方法或字段的可见性是放宽的，因此它比可以使代码可测试的其他必要条件更加可见*/
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public void VisibleForTesting(){}


}
