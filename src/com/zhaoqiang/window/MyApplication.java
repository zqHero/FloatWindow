package com.zhaoqiang.window;

import android.app.Application;
import android.view.WindowManager;

/**
 * Project_name : FloatWindow
 * Author : zhaoQiang
 * Create_time : 2015/10/23 | 19:17
 */
public class MyApplication extends Application {
    //全局应用中初始化自定义视图：

    /**
     * 创建全局变量：
     *  在Application中 添加数据的方法实现全局变量
     *  注意需要在AndroidManifest.xml中的Application节点中
     *  添加android:name = ".MyApplication"属性
     *  并添加权限
     */

    //初始化 WindowManager.LayoutParams
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    //创建方法调用参数：
    public WindowManager.LayoutParams getMywmParams()
    {
        return wmParams;
    }
}
