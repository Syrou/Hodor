package eu.syrou.hodor;

import android.support.annotation.Nullable;

/**
 * Created by Syrou on 2016-04-24.
 */
public class Condition {

    public static boolean isEqual(@Nullable Object obj, @Nullable Object obj2)
    {
        if(isBothNull(obj, obj2)) {
            return true;
        } else {
            return obj.equals(obj2);
        }
    }

    public static boolean isNotEqual(@Nullable Object obj, @Nullable Object obj2)
    {
        if(isBothNull(obj, obj2)) {
            return false;
        } else {
            return !obj.equals(obj2);
        }
    }

    private static boolean isBothNull(@Nullable Object obj, @Nullable Object obj2)
    {
        if(obj == null && obj2 == null) {
            return true;
        }else{
            return false;
        }
    }
}
