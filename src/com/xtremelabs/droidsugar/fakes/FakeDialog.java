package com.xtremelabs.droidsugar.fakes;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.xtremelabs.droidsugar.util.Implements;

import java.lang.reflect.Method;

@SuppressWarnings({"UnusedDeclaration"})
@Implements(Dialog.class)
public class FakeDialog {
    public static FakeDialog latestDialog;
    
    private boolean isShowing;
    public Context context;
    public int layoutId;
    public int themeId;
    private View inflatedView;
    private Dialog realDialog;

    public static void reset() {
        latestDialog = null;
    }

    public FakeDialog(Dialog dialog) {
        realDialog = dialog;
    }

    public void __constructor__(Context context, int themeId) {
        this.context = context;
        this.themeId = themeId;

        latestDialog = this;
    }

    public void setContentView(int layoutResID) {
        layoutId = layoutResID;
    }

    public void show() {
        isShowing = true;
        try {
            Method onCreateMethod = Dialog.class.getDeclaredMethod("onCreate", Bundle.class);
            onCreateMethod.setAccessible(true);
            onCreateMethod.invoke(realDialog, (Bundle) null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void dismiss() {
        isShowing = false;
    }

    public View findViewById(int viewId) {
        if (layoutId > 0 && context != null) {
            if (inflatedView == null) {
                inflatedView = FakeContextWrapper.resourceLoader.viewLoader.inflateView(context, layoutId);
            }
            return inflatedView.findViewById(viewId);
        }
        return null;
    }

    public void clickOn(int viewId) {
        findViewById(viewId).performClick();
    }
}
