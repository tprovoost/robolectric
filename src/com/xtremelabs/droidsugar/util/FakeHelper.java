package com.xtremelabs.droidsugar.util;

import com.xtremelabs.droidsugar.fakes.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class FakeHelper {
    public static <T> T newInstanceOf(Class<T> clazz) {
        try {
            Constructor<T> defaultConstructor = clazz.getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            return defaultConstructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Class<?>> getGenericProxies() {
        return Arrays.asList(
                FakeAbsSpinner.class,
                FakeActivity.class,
                FakeAdapterView.class,
                FakeContextWrapper.class,
                FakeDialog.class,
                FakeImageView.class,
                FakeGeoPoint.class,
                FakeHandler.class,
                FakeIntent.class,
                FakeItemizedOverlay.class,
                FakeListView.class,
                FakeLooper.class,
                FakeMapController.class,
                FakeMapActivity.class,
                FakeMapView.class,
                FakeOverlayItem.class,
                FakeTextView.class,
                FakeView.class,
                FakeViewGroup.class,
                FakeLayoutParams.class
        );
    }
}
