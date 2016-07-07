package eu.syrou.hodor.anim;

import android.view.View;

/**
 * Created by Syrou on 2016-05-08.
 */
public class HodorAnimationSlideInFromRight extends HodorAnimation {
    @Override
    public void inAnimation(View view, long milliseconds) {
        HodorMoves.slideInFromRight(view, milliseconds);
    }

    @Override
    public void outAnimation(View view, long milliseconds) {
        HodorMoves.slideOutToLeft(view,milliseconds);
    }
}
