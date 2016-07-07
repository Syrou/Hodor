package eu.syrou.hodor.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Syrou on 2016-05-07.
 */
public class HodorMoves {

    public static void slideInFromLeft(final View view, long ms)
    {
        TranslateAnimation slideInFromLeftTranslation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,0.0f);
        slideInFromLeftTranslation.setDuration(ms);
        slideInFromLeftTranslation.setFillAfter(true);
        if(view != null) {
            view.setDrawingCacheEnabled(true);
            view.setAnimation(slideInFromLeftTranslation);
        }

    }

    public static void slideOutToRight(final View view, long ms)
    {
        TranslateAnimation slideOutToRightTranslation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,0.0f);
        slideOutToRightTranslation.setDuration(ms);
        slideOutToRightTranslation.setFillAfter(true);
        view.setDrawingCacheEnabled(true);
        view.setAnimation(slideOutToRightTranslation);
    }

    public static void slideInFromRight(final View view, long ms)
    {
        TranslateAnimation slideInFromLeftTranslation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,0.0f);
        slideInFromLeftTranslation.setDuration(ms);
        slideInFromLeftTranslation.setFillAfter(true);
        view.setDrawingCacheEnabled(true);
        view.setAnimation(slideInFromLeftTranslation);
    }

    public static void slideOutToLeft(final View view, long ms)
    {
        TranslateAnimation slideOutToRightTranslation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,0.0f);
        slideOutToRightTranslation.setDuration(ms);
        slideOutToRightTranslation.setFillAfter(true);
        view.setDrawingCacheEnabled(true);
        view.setAnimation(slideOutToRightTranslation);
    }

    public static void slideInFromTop(final View view, long ms)
    {
        TranslateAnimation a = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF,-1.0f, Animation.RELATIVE_TO_SELF,0.0f);
        a.setDuration(ms);
        a.setFillAfter(true);
        view.setDrawingCacheEnabled(true);
        view.setAnimation(a);
    }

    public static void slideOutToBottom(final View view, long ms)
    {
        TranslateAnimation a = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,1.0f);
        a.setDuration(ms);
        a.setFillAfter(true);
        view.setDrawingCacheEnabled(true);
        view.setAnimation(a);
    }



}
