package eu.syrou.hodor;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Syrou on 21/04/16.
 */
public class Hodor {



    private static final String TAG = Hodor.class.getSimpleName();
    private static int mActiveViewGroupId;
    private static ViewGroup mActiveViewGroup;
    private static final HashMap<Integer, HashMap<Integer, View>> subview = new HashMap<>();

    public Hodor(Builder builder) {

    }

    public static void setRootView(ViewGroup viewGroup)
    {
        mActiveViewGroupId = viewGroup.hashCode();
        mActiveViewGroup = viewGroup;
    }

    public static void addView(View view)
    {


    }

    public static void addView(View view, @Nullable String tag)
    {
        view.setTag(tag);
        addView(view);
    }

    public static void removeView(@Nullable String tag)
    {
        findViewAndRemove(null, tag);
    }

    private static void findViewAndRemove(@Nullable View view, @Nullable String tag)
    {
        boolean hasBeenFound=false;
        View foundView = null;
        HashMap<Integer, View> sub = subview.get(mActiveViewGroupId);
        if(Condition.isEqual(sub, null))
        {
            Log.d(TAG, "No active context bound to this viewgroup");
            return;
        }
        Iterator<Map.Entry<Integer, View>> iter = sub.entrySet().iterator();
        while (iter.hasNext() && hasBeenFound == false) {
            HashMap.Entry<Integer, View> entry = iter.next();
            //Integer key = entry.getKey();
            View value = entry.getValue();
            if(Condition.isEqual(value.getTag(),tag) || Condition.isEqual(value,view)){
                Log.d(TAG, "Found the view.. removing");
                foundView = value;
                iter.remove();
                hasBeenFound=true;
            }
        }
        subview.put(mActiveViewGroupId, sub);
        mActiveViewGroup.removeView(foundView);
        foundView=null;
        Log.d(TAG, "Should have removed");
    }

    public static void removeView(View view)
    {
        findViewAndRemove(view, null);
    }

    private static View getLastActiveView(HashMap<Integer, View> sub)
    {
        return  sub.get(sub.size()-1);

    }

    private static void PlayAnim(View v, int animationid, int StartOffset )
    {
        if( v != null )
        {
            Animation animation = AnimationUtils.loadAnimation(v.getContext(), animationid  );
            animation.setStartOffset(StartOffset);
            v.startAnimation(animation);
        }
    }

    public static class Builder{
        private static Builder builder;

        public Builder getInstance(){
            if (builder==null){
                builder=new Builder();
            }
            return builder;
        }
        public Builder addView(View view){
            HashMap<Integer, View> sub = subview.get(mActiveViewGroupId);
            if(Condition.isEqual(sub, null)){
                sub = new HashMap<>();
            }else{
                View lastActiveView = getLastActiveView(sub);
                Log.d(TAG, "Last view:"+lastActiveView.hashCode());
                PlayAnim(lastActiveView, R.anim.slide_right_out, 0);
                mActiveViewGroup.removeView(lastActiveView);
            }
            sub.put(sub.size(), view);
            subview.put(mActiveViewGroupId, sub);
            PlayAnim(view, R.anim.slide_right_in,0);
            mActiveViewGroup.addView(view);
            return this;
        }

        public Hodor build() {
            Hodor result = new Hodor(this);
            return result;
        }
    }
}
