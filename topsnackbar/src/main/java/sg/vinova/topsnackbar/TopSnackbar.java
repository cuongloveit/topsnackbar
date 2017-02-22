package sg.vinova.topsnackbar;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;

import vn.eazy.core.helper.snackbar.ClickActionListener;
import vn.eazy.core.helper.snackbar.SnackbarColor;
import vn.eazy.core.helper.snackbar.SnackbarIcon;
import vn.eazy.core.helper.snackbar.SnackbarInterface;

/**
 * Created by cuong on 2/20/17.
 */

public class TopSnackbar implements SnackbarInterface {

    @Override
    public void show(View viewParent, String message) {
        TSnackbar.make(viewParent, message, TSnackbar.LENGTH_LONG).show();
    }

    @Override
    public void show(View viewParent, String message, String actionText) {
        show(viewParent, message, actionText, null);
    }

    @Override
    public void show(View viewParent, String message, int actionIcon) {
        SnackbarIcon icon = new SnackbarIcon();
        icon.setRightIconRes(actionIcon);
        show(viewParent, message, null, null, icon, null);
    }

    @Override
    public void show(View viewParent, String message, int actionIcon, SnackbarColor snackbarColor) {
        SnackbarIcon icon = new SnackbarIcon();
        icon.setRightIconRes(actionIcon);
        show(viewParent, message, null, snackbarColor, icon, null);
    }

    @Override
    public void show(View viewParent, String message, SnackbarColor snackbarColor) {
        show(viewParent, message, null, snackbarColor);
    }

    @Override
    public void show(View viewParent, String message, String actionText, SnackbarColor snackbarColor) {
        show(viewParent, message, actionText, snackbarColor, null, null);
    }

    @Override
    public void show(final View view, String message, final String actionText, SnackbarColor snackbarColor, final SnackbarIcon snackbarIcon, final ClickActionListener clickActionListener) {
        //left and right icon
        final TSnackbar snackbar = TSnackbar.make(view, message, clickActionListener == null ? TSnackbar.LENGTH_LONG : TSnackbar.LENGTH_INDEFINITE);

        //show icon
        int sizeClick = 0;
        if (snackbarIcon != null) {

            // left icon
            if (snackbarIcon.getLeftIconRes() > 0) {
                int leftIconSize = snackbarIcon.getLeftIconSize();
                if (leftIconSize <= 0) {
                    leftIconSize = 24;
                }
                snackbar.setIconLeft(snackbarIcon.getLeftIconRes(), leftIconSize); //Size in dp - 24 is great!
            }

            // right icon
            if (snackbarIcon.getRightIconRes() > 0) {
                int rightIconSize = snackbarIcon.getRightIconSize();
                if (rightIconSize <= 0) {
                    rightIconSize = 24;
                }
                sizeClick = rightIconSize + (snackbarIcon.getIconPadding() * 2);
                snackbar.setIconRight(snackbarIcon.getRightIconRes(), rightIconSize); //Resize to bigger dp
            }

            //padding icon
            snackbar.setIconPadding(snackbarIcon.getIconPadding());
        }
        snackbar.setMaxWidth(3000); //if you want fullsize on tablets
        View snackbarView = snackbar.getView();
        TextView tvMessage = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        //click right icon
        tvMessage.setOnTouchListener(new RightDrawableOnTouchListener(tvMessage, SnackbarUtils.convertDpToPixel(sizeClick, view.getContext())) {
            @Override
            public boolean onDrawableTouch(MotionEvent event) {
                if (snackbarIcon.getRightIconRes() > 0 && clickActionListener != null) {
                    clickActionListener.onClick();
                    snackbar.dismiss();
                } else {
                    snackbar.dismiss();
                }

                return false;
            }
        });

        //click action button
        if (snackbarIcon == null ||(snackbarIcon!=null&& snackbarIcon.getRightIconRes() == 0)) {
            snackbar.setAction(actionText, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(actionText) && clickActionListener != null) {
                        clickActionListener.onClick();
                        snackbar.dismiss();
                    } else {
                        snackbar.dismiss();
                    }
                }
            });
        }


        //set color
        if (snackbarColor != null) {
            snackbar.setActionTextColor(snackbarColor.getActionColor());
            tvMessage.setTextColor(snackbarColor.getMessageColor());
            snackbarView.setBackgroundColor(snackbarColor.getBackgroundColor());
        }

        snackbar.show();
    }


}
