package space.novium.nebula.graphics.gui.transitions;

import space.novium.nebula.graphics.gui.parts.RectPart;
import space.novium.nebula.graphics.gui.transitions.enums.TransitionType;
import space.novium.nebula.graphics.renderer.Renderer;
import space.novium.utils.math.Vector2f;

public class FadeTransition implements ITransition {
    private float time;
    private float alpha;
    private TransitionType transitionType;
    private boolean fadingIn;
    private boolean fadingOut;
    private RectPart fadePart;

    public FadeTransition(float time){
        this(time, true);
    }

    public FadeTransition(float time, boolean faded){
        this(time, faded, TransitionType.LINEAR);
    }

    public FadeTransition(float time, TransitionType type){
        this(time, true, type);
    }

    public FadeTransition(float time, boolean faded, TransitionType type){
        this.time = time;
        this.alpha = faded ? 1.0f : 0.0f;
        this.transitionType = type;
        this.fadingIn = false;
        this.fadingOut = false;
        this.fadePart = new RectPart(new Vector2f(-1.0f, -1.0f), new Vector2f(2.0f, 2.0f), Renderer.OVERLAY_Z);
        fadePart.setColor(0.0f, 0.0f, 0.0f, alpha);
    }

    @Override
    public void setTime(float t) {
        alpha = (alpha / time) * t;
        time = t;
    }

    @Override
    public void fadeIn() {
        fadingIn = true;
        fadingOut = false;
    }

    @Override
    public void fadeOut() {
        fadingIn = false;
        fadingOut = true;
    }

    public boolean isFadingIn() {
        return fadingIn;
    }

    public boolean isFadingOut(){
        return fadingOut;
    }

    @Override
    public void tick(float dt) {
        float step = dt / time;
        switch(transitionType){
            case LINEAR -> {
                if(fadingIn){
                    alpha -= step;
                    finishFadeOut();
                }
                if(fadingOut){
                    alpha += step;
                    finishFadeIn();
                }
            }
            case QUICK_FADE -> {
                float timePassed = (float)Math.pow(2, alpha) - 1.0f;
                if(fadingIn){
                    alpha = (float)(Math.log((timePassed - step) + 1) / Math.log(2));
                    finishFadeIn();
                }
                if(fadingOut){
                    alpha = (float)(Math.log((timePassed + step) + 1) / Math.log(2));
                    finishFadeOut();
                }
            }
            case EXPONENTIAL -> {
                float timePassed = (float)(Math.log(alpha + 1) / Math.log(2));
                if(fadingIn){
                    alpha = (float)Math.pow(2, (timePassed - step)) - 1.0f;
                    finishFadeIn();
                }
                if(fadingOut){
                    alpha = (float)Math.pow(2, (timePassed + step)) - 1.0f;
                    finishFadeOut();
                }
            }
        }
    }

    private void finishFadeIn(){
        if(alpha <= 0.0f){
            alpha = 0.0f;
            fadingIn = false;
        }
        fadePart.setAlpha(alpha);
    }

    private void finishFadeOut(){
        if(alpha >= 1.0f){
            alpha = 1.0f;
            fadingOut = false;
        }
        fadePart.setAlpha(alpha);
    }
}
