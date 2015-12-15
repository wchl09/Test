package ray.com.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by wangchunlei on 15/12/7.
 */
public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.i("Ray", String.valueOf(deltaY)+String.valueOf(scrollY)+"_____"+scrollRangeY);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, true);
    }
}
