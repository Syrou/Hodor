package eu.syrou.hodor.anim;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by Syrou on 2016-05-22.
 */
public class HodorSaveState {
    private static final String TAG = HodorSaveState.class.getSimpleName();

    public static void save(Bundle outState, Object classInstance, Class<?> baseClass) {
        if (outState == null) {
            return;
        }
        Class<?> clazz = classInstance.getClass();
        while (baseClass.isAssignableFrom(clazz)) {
            String className = clazz.getName();
            for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    String key = className + "#" + field.getName();
                    try {
                        Object value = field.get(classInstance);
                        if (value instanceof Parcelable) {
                            outState.putParcelable(key, (Parcelable) value);
                        } else if (value instanceof Serializable) {
                            outState.putSerializable(key, (Serializable) value);
                        }
                    } catch (Throwable t) {
                        Log.d(TAG, "The field '" + key + "' was not added to the bundle");

                    }
            }
            clazz = clazz.getSuperclass();
        }
    }

    public static void save(Bundle outState, Object classInstance) {
        save(outState, classInstance, classInstance.getClass());
    }
}
