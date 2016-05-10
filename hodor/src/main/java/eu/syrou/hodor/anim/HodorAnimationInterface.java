package eu.syrou.hodor.anim;

import android.view.View;
import android.view.animation.Animation;

/**
 * Created by Syrou on 2016-05-08.
 */
public interface HodorAnimationInterface {
    void inAnimation(View view, long milliseconds);
    void outAnimation(View view, long milliseconds);
}
