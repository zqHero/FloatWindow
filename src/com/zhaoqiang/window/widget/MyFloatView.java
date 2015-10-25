package com.zhaoqiang.window.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.zhaoqiang.window.MyApplication;

/**
 * Project_name : FloatWindow
 * Author : zhaoQiang
 * Create_time : 2015/10/23 | 18:55
 */

/**
 * 调用WindowManager，用来管理窗口的一些状态、属性、view增加、删除、更新、窗口顺序、消息收集和处理等。
 * 并设置WindowManager.LayoutParams的相关属性，
 * 通过WindowManager的addView方法创建View，
 * 这样产生出来的View根据WindowManager.LayoutParams属性不同，
 * 效果也就不同了。比如创建系统顶级窗口，实现悬浮窗口效果！
 * 然后通过覆写悬浮View中onTouchEvent方法来改变windowMananager.LayoutParams
 * 中x和y的值来实现自由移动悬浮窗口。
 */

//自定义  ImageView控件
public class MyFloatView extends ImageView {

    private float mTouchStartX;//定义手指触摸时的  x的坐标
    private float mTouchStartY;//
    private float x;
    private float y;

    private Context context;//上下文对象

    /**
     *1, 获取系统对窗口的服务：整个Android的窗口机制是基于一个叫做 WindowManager，
     * 这个接口可以添加view到屏幕，也可以从屏幕删除view。它面向的对象一端是屏幕，
     * 另一端就是View，直接忽略我们以前的Activity或者Dialog之类的东东。
     * 其实我们的Activity或者Dialog底层的实现也是通过WindowManager，
     * 这个 WindowManager是全局的，整个系统就是这个唯一的东东。它是显示View的最底层了。
     */
    private WindowManager wm = (WindowManager)getContext()
            .getSystemService(Context.WINDOW_SERVICE);

    //此wmParams变量  为获取的全局变量，用以保存悬浮窗口的属性   该对象通过全局应用中getMywmParams方法实例化。
    private WindowManager.LayoutParams wmParams = (
            (MyApplication)getContext().getApplicationContext()).getMywmParams();

    //添加  构造参数   以下构造参数是必须要实现的否则报错
    public MyFloatView(Context context) {
        super(context);//访问父类的构造参数
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY()-25; //25是系统状态栏的高度
        Log.i("currP", "currX" + x + "====currY" + y);

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: //捕获手指触摸的按下动作

                //获取相对View的坐标，即以此View左上角为原点
                mTouchStartX = event.getX();
                mTouchStartY = event.getY();

                Toast.makeText(getContext(),"手指触摸上去了",Toast.LENGTH_LONG).show();
                Log.i("startP", "startX" + mTouchStartX + "====startY" + mTouchStartY);
                break;

            case MotionEvent.ACTION_MOVE: //捕获手指触摸的移动动作
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP: //捕获手指触摸的离开动作
                updateViewPosition();
                mTouchStartX=mTouchStartY=0;
                Toast.makeText(getContext(),"手指离开",Toast.LENGTH_LONG).show();

                break;
            }
          return true;
        }

    //更新    视图位置：
    private void updateViewPosition(){
        //更新浮动图标的   位置参数
        wmParams.x=(int)( x-mTouchStartX);
        wmParams.y=(int)(y-mTouchStartY);
        //刷新显示  的坐标位置
        wm.updateViewLayout(this, wmParams);
    }

}
