package sg.vinova.topsnackbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public abstract class RightDrawableOnTouchListener implements View.OnTouchListener {
    Drawable drawable;
    private int fuzz = 10;
    private Context context;
    private float sizeClick;


    public RightDrawableOnTouchListener(TextView view, float sizeClick) {
        super();
        final Drawable[] drawables = view.getCompoundDrawables();
        context = view.getContext();
        this.sizeClick = sizeClick;
        if (drawables != null && drawables.length == 4)
            this.drawable = drawables[2];
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
     */
    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && drawable != null) {
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            final Rect bounds = drawable.getBounds();
            if (x>v.getWidth()-sizeClick) {
                return onDrawableTouch(event);
            }
        }
        return false;
    }

    public abstract boolean onDrawableTouch(final MotionEvent event);

    int getWidthScreen(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

}