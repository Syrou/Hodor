package eu.syrou.hodor.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Syrou on 2016-05-07.
 */
public class HodorMoves {

    public static void slideInFromLeft(View view, long ms)
    {
        TranslateAnimation slideInFromLeftTranslation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,0.0f);
        slideInFromLeftTranslation.setDuration(ms);
        slideInFromLeftTranslation.setFillEnabled(false);
        view.setAnimation(slideInFromLeftTranslation);
    }

    public static void slideOutToRight(View view, long ms)
    {
        TranslateAnimation slideOutToRightTranslation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,0.0f);
        slideOutToRightTranslation.setDuration(ms);
        slideOutToRightTranslation.setFillEnabled(false);
        view.setAnimation(slideOutToRightTranslation);
    }

    public static void slideInFromTop(View view, long ms)
    {
        TranslateAnimation a = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF,-1.0f, Animation.RELATIVE_TO_SELF,0.0f);
        a.setDuration(ms);
        a.setFillAfter(true); //HERE
        //animationSet.addAnimation(a);
        view.setAnimation(a);
    }

    public static void slideOutToBottom(View view, long ms)
    {
        TranslateAnimation a = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,1.0f);
        a.setDuration(ms);
        a.setFillAfter(true); //HERE
        //animationSet.addAnimation(a);
        view.setAnimation(a);
    }
}
