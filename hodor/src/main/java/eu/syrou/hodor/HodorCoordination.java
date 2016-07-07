package eu.syrou.hodor;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import eu.syrou.hodor.anim.HodorAnimation;

/**
 * Created by Syrou on 2016-05-08.
 */
public class HodorCoordination {
    private static final String TAG = HodorCoordination.class.getSimpleName();

    private long mRotateMilliseconds;
    private Direction mRotationDirection;
    private HodorAnimation mHodorAnimation = new HodorAnimation();
    private final Handler mHandler = new Handler();

    public enum Direction{
        BACKWARD,
        FORWARD
    }

    private HashMap<Integer, Stack<View>> viewGroupMap = new HashMap<>();
    private ViewGroup mLatestUsedViewGroup;

    public HodorCoordination(ViewGroup latestUsedViewGroup) {
        this.mLatestUsedViewGroup = latestUsedViewGroup;
    }

    public void addView(View view)
    {
        Stack<View> viewStack = viewGroupMap.get(mLatestUsedViewGroup.hashCode());
        if(Condition.isEqual(viewStack, null)){
            Log.d(TAG, "This should only be logged twice");
            viewStack = new Stack<>();
        }else{
            View lastActiveView = viewStack.peek();
            mHodorAnimation.outAnimation(lastActiveView, 1000);
            mLatestUsedViewGroup.removeView(lastActiveView);
        }
        if(view != null) {
            mHodorAnimation.inAnimation(view, 1000);
            mLatestUsedViewGroup.addView(view);
            viewStack.push(view);
            viewGroupMap.put(mLatestUsedViewGroup.hashCode(), viewStack);
        }
    }

    public void removeView(View view) {
        Stack<View> viewStack = viewGroupMap.get(mLatestUsedViewGroup.hashCode());
        if(viewStack != null) {
            for (View viewLoop : viewStack) {
                if (viewLoop.hashCode() == view.hashCode()) {
                    viewStack.remove(viewLoop);
                    mLatestUsedViewGroup.removeView(viewLoop);
                    break;
                }
            }
            if (viewStack.peek().getParent() == null) {
                mLatestUsedViewGroup.addView(viewStack.peek());
            }
            viewGroupMap.put(mLatestUsedViewGroup.hashCode(), viewStack);
        }
    }

    public void back() {
        Stack<View> viewStack = viewGroupMap.get(mLatestUsedViewGroup.hashCode());
        if(viewStack != null) {
            mLatestUsedViewGroup.removeView(viewStack.pop());
            if (viewStack.peek().getParent() == null) {
                mLatestUsedViewGroup.addView(viewStack.peek());
            }
            viewGroupMap.put(mLatestUsedViewGroup.hashCode(), viewStack);
        }
    }

    public void rotate(long time, TimeUnit timeUnit, final Direction direction)
    {
        mRotateMilliseconds = timeUnit.toMillis(time);
        mRotationDirection = direction;
        Stack<View> viewStack = viewGroupMap.get(mLatestUsedViewGroup.hashCode());
        if(isThereItemsToPerformRotation(viewStack)) {
            mHandler.post(mUpdateUI);
        }

    }

    public void transform(HodorAnimation hodorAnimation)
    {
        mHodorAnimation = hodorAnimation;
    }

    private Runnable mUpdateUI = new Runnable() {
        public void run() {
            Stack<View> viewStack = viewGroupMap.get(mLatestUsedViewGroup.hashCode());
            if(isThereItemsToPerformRotation(viewStack)) {
                if(mRotationDirection.equals(Direction.BACKWARD)) {
                    View lastActiveView = viewStack.pop();
                    mHodorAnimation.outAnimation(lastActiveView, 1000);
                    mLatestUsedViewGroup.removeView(lastActiveView);
                    //viewStack.insertElementAt(lastActiveView, 0);
                    //Add the new view
                    View newViewToDisplay = viewStack.peek();
                    mHodorAnimation.inAnimation(newViewToDisplay, 1000);
                    mLatestUsedViewGroup.addView(newViewToDisplay);
                }else if(mRotationDirection.equals(Direction.FORWARD)) {
                    View lastActiveView = viewStack.peek();
                    mHodorAnimation.outAnimation(lastActiveView, 1000);
                    mLatestUsedViewGroup.removeView(lastActiveView);
                    View viewToDisplay = viewStack.firstElement();
                    viewStack.remove(viewToDisplay);
                    viewStack.push(viewToDisplay);
                    mHodorAnimation.inAnimation(viewToDisplay, 1000);
                    mLatestUsedViewGroup.addView(viewToDisplay);
                }
                viewGroupMap.put(mLatestUsedViewGroup.hashCode(), viewStack);
            }
            mHandler.postDelayed(mUpdateUI, mRotateMilliseconds);
        }
    };

    public void onBackpress()
    {
        Stack<View> viewStack = viewGroupMap.get(mLatestUsedViewGroup.hashCode());
        if(isThereItemsToPerformRotation(viewStack)) {
            View lastActiveView = viewStack.pop();
            mHodorAnimation.outAnimation(lastActiveView, 1000);
            mLatestUsedViewGroup.removeView(lastActiveView);
            View viewToDisplay = viewStack.peek();
            mHodorAnimation.inAnimation(viewToDisplay, 1000);
            mLatestUsedViewGroup.addView(viewToDisplay);
            viewGroupMap.put(mLatestUsedViewGroup.hashCode(), viewStack);
        }
    }
    private boolean isThereItemsToPerformRotation(Stack<View> viewStack)
    {
        return viewStack != null ? viewStack.size() > 1 : false;
    }
}
