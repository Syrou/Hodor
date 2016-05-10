package eu.syrou.hodor;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewGroupCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import eu.syrou.hodor.anim.HodorAnimation;

/**
 * Created by Syrou on 21/04/16.
 */
public class Hodor {



    private static final String TAG = Hodor.class.getSimpleName();
    private static final HashMap<Integer, HashMap<Integer, View>> subview = new HashMap<>();
    private static final HashMap<Integer, Builder> instanceMap = new HashMap<>();

    public Hodor(Builder builder) {

    }

    public static void removeView(ViewGroup viewGroup,@Nullable String tag)
    {
        findViewAndRemove(viewGroup,null, tag);
    }

    private static void findViewAndRemove(@Nullable ViewGroup viewGroup,@Nullable View view, @Nullable String tag)
    {
        View foundView = null;
        HashMap<Integer, View> sub = subview.get(viewGroup);
        if(Condition.isEqual(sub, null))
        {
            Log.d(TAG, "No active context bound to this viewgroup");
            return;
        }
        Iterator<Map.Entry<Integer, View>> iter = sub.entrySet().iterator();
        while (iter.hasNext() && foundView == null) {
            HashMap.Entry<Integer, View> entry = iter.next();
            //Integer key = entry.getKey();
            View value = entry.getValue();
            if(Condition.isEqual(value.getTag(),tag) || Condition.isEqual(value,view)){
                Log.d(TAG, "Found the view.. removing");
                foundView = value;
                iter.remove();
            }
        }
        subview.put(viewGroup.hashCode(), sub);
        viewGroup.removeView(foundView);
        Log.d(TAG, "Should have removed");
    }

    public static void removeView(ViewGroup viewGroup, View view)
    {
        findViewAndRemove(viewGroup, view, null);
    }

    public static Builder with(ViewGroup viewGroup)
    {
        if(instanceMap.get(viewGroup.hashCode()) != null) {
            return instanceMap.get(viewGroup.hashCode());
        }
        return null;
    }

    public static class Builder{
        private static Builder builder;
        private HodorCoordination hodorCoordination;

        public Builder with(ViewGroup viewGroup)
        {
            if(instanceMap.get(viewGroup) == null) {
                hodorCoordination = new HodorCoordination(viewGroup);
                instanceMap.put(viewGroup.hashCode(), this);
            }
            return this;
        }

        public Builder addView(View view){

            hodorCoordination.addView(view);
            return this;
        }

        public Builder removeView(View view)
        {
            hodorCoordination.removeView(view);
            return this;
        }


        public Builder addView(ViewGroup viewGroup, View view, @Nullable String tag)
        {
            view.setTag(tag);
            addView(view);
            return this;
        }
        public Builder rotate(long time,TimeUnit timeUnit, final HodorCoordination.Direction direction)
        {
            final long ms = timeUnit.toMillis(time);
            hodorCoordination.rotate(ms, timeUnit, direction);
            return this;
        }

        public Builder transform(HodorAnimation hodorAnimation)
        {
            hodorCoordination.transform(hodorAnimation);
            return this;
        }

        public void back()
        {
            hodorCoordination.onBackpress();
        }


        public Hodor build() {
            Hodor result = new Hodor(this);
            return result;
        }
    }
}
