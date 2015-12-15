package ray.com.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus(true);
//        setStatusBarDarkMode(true,this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setSystemBarTintManager();


        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//// This example uses decor view, but you can use any visible view.
//        View decorView = this.getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
//        decorView.setSystemUiVisibility(uiOptions);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        mListView = (ListView) findViewById(R.id.listView);
//        mListView.setAdapter(new MyAdapter());
    }

    /**
     * 官方方法，只能让statusBar透明
     *
     * @param on
     */
    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * MIUI的方法，设置为黑暗模式
     *
     * @param darkmode
     * @param activity
     */
    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GitHub上的通用方法
     */
    public void setSystemBarTintManager() {
        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        tintManager.setTintColor(Color.parseColor("#9900FF"));
//// set a custom navigation bar resource
//        tintManager.setNavigationBarTintResource(R.mipmap.ic_launcher);
//// set a custom status bar drawable
//        tintManager.setStatusBarTintDrawable(MyDrawable);
//        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
//        map.setPadding(0, config.getPixelInsetTop(), config.getPixelInsetRight(), config.getPixelInsetBottom());
    }

    class MyAdapter extends BaseAdapter {
        ArrayList<String> strings = new ArrayList<>();

        public MyAdapter() {
            for (int i = 0; i <= 20; i++) {
                strings.add(String.format("这是第%d个", i));
            }
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            TextView textView;
            if (view == null) {
                textView = new TextView(SecondActivity.this);
                textView.setHeight(250);
            }
            else
                textView = (TextView) view;
            textView.setText(strings.get(i));
            return textView;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }
    }
}
