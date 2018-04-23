package at.teamgotcha.helpers;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import at.teamgotcha.tamagotchi.R;

public class AnimHelper {

    public static Animation AddAlphaAnimation(Context context){

        // generate alpha Animation
        return AnimationUtils.loadAnimation(context, R.anim.alpha);
    }

    public static Animation AddBounceAnimation(Context context){

        // generate Bounce Animation
        Animation resultAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        resultAnim.setInterpolator(interpolator);

        return resultAnim;
    }

    public static Animation AddRotateAnimation(Context context){

        // generate Rotate Animation
        return AnimationUtils.loadAnimation(context, R.anim.rotate);
    }

    public static Animation AddScaleAnimation(Context context){

        // generate Scale Animation
        return AnimationUtils.loadAnimation(context, R.anim.scale);
    }

    public static Animation AddShakeOneAnimation(Context context){

        // generate Shake One Animation
        return  AnimationUtils.loadAnimation(context, R.anim.shake_01);
    }

}
