package bpc.dis.drawablebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DisDrawableButton extends FrameLayout {

    private ConstraintLayout clDrawableButton;
    private LinearLayout llDisDrawable;
    private AppCompatImageView imgDrawableLeft;
    private AppCompatImageView imgDrawableRight;
    private AppCompatTextView txtDrawableButton;
    private AppCompatButton btnDrawableButton;
    private View vUnderline;

    private int backgroundRes;
    private int disableBackgroundRes;
    private boolean enableClickAnimation;

    public DisDrawableButton(Context context) {
        super(context);
        init(null, 0);
    }

    public DisDrawableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DisDrawableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clDrawableButton.setOnClickListener(null);
        llDisDrawable.setOnClickListener(null);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        clDrawableButton.setBackgroundColor(backgroundColor);
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        clDrawableButton.setBackgroundDrawable(background);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        if (enableClickAnimation) {
            btnDrawableButton.setOnClickListener(onClickListener);
        } else {
            clDrawableButton.setOnClickListener(onClickListener);
        }
        llDisDrawable.setOnClickListener(onClickListener);
    }

    @Override
    public void setOnLongClickListener(@Nullable OnLongClickListener onLongClickListener) {
        if (enableClickAnimation) {
            btnDrawableButton.setOnLongClickListener(onLongClickListener);
        } else {
            clDrawableButton.setOnLongClickListener(onLongClickListener);
        }
        llDisDrawable.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public boolean callOnClick() {
        llDisDrawable.callOnClick();
        return super.callOnClick();
    }


    public void init(AttributeSet attrs, int defStyleAttr) {
        View view = inflate(getContext(), R.layout.dis_drawable_button, this);
        clDrawableButton = view.findViewById(R.id.cl_dis_drawable_button);
        llDisDrawable = view.findViewById(R.id.ll_dis_drawable);
        btnDrawableButton = view.findViewById(R.id.btn_dis_drawable_button);
        imgDrawableLeft = view.findViewById(R.id.img_drawable_left);
        imgDrawableRight = view.findViewById(R.id.img_drawable_right);
        txtDrawableButton = view.findViewById(R.id.txt_drawable_button);
        vUnderline = view.findViewById(R.id.v_underline);
        setupView(attrs);
    }

    private void setupView(AttributeSet attrs) {
        TypedArray styledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.DisDrawableButton);
        setupLayout(styledAttributes);
        setupDrawable(styledAttributes);
        setupText(styledAttributes);
        setupUnderline(styledAttributes);
        styledAttributes.recycle();
    }

    private void setupUnderline(TypedArray styledAttributes) {
        //enable

        boolean underlineEnable = styledAttributes.getBoolean(R.styleable.DisDrawableButton_disUnderlineEnable, false);
        setUnderlineEnable(underlineEnable);

        //color

        int underlineColor = styledAttributes.getColor(R.styleable.DisDrawableButton_disUnderlineColor, getResources().getColor(R.color.disUnderlineColor));
        setUnderlineColor(underlineColor);

        //heightSize

        float underlineHeight = styledAttributes.getDimension(R.styleable.DisDrawableButton_disUnderlineHeight, getResources().getDimension(R.dimen.disUnderlineHeight));
        setUnderlineHeight(underlineHeight);

    }

    private void setupLayout(TypedArray styledAttributes) {
        //background

        int backgroundRes = styledAttributes.getResourceId(R.styleable.DisDrawableButton_disBackground, -1);
        if (backgroundRes == -1) {
            backgroundRes = R.color.disBackground;
        }
        this.backgroundRes = backgroundRes;
        setBackgroundResource(backgroundRes);


        //background

        int disableBackgroundRes = styledAttributes.getResourceId(R.styleable.DisDrawableButton_disDisableBackground, -1);
        if (disableBackgroundRes == -1) {
            disableBackgroundRes = R.color.disDisableBackground;
        }
        setDisableBackgroundRes(disableBackgroundRes);

        //enable

        boolean enable = styledAttributes.getBoolean(R.styleable.DisDrawableButton_disEnable, true);
        setEnable(enable);

        //enable

        boolean enableClickAnimation = styledAttributes.getBoolean(R.styleable.DisDrawableButton_disEnableClickAnimation, true);
        setEnableClickAnimation(enableClickAnimation);
    }

    private void setupDrawable(TypedArray styledAttributes) {
        setLeftDrawable(styledAttributes.getResourceId(R.styleable.DisDrawableButton_disDrawableLeft, -1));
        setLeftTintColor(styledAttributes.getColor(R.styleable.DisDrawableButton_disLeftTintColor, -1));
        setLeftDrawableHeight(styledAttributes.getDimension(R.styleable.DisDrawableButton_disDrawableLeftHeight, -1));
        setLeftDrawableWidth(styledAttributes.getDimension(R.styleable.DisDrawableButton_disDrawableLeftWidth, -1));
        setRightDrawable(styledAttributes.getResourceId(R.styleable.DisDrawableButton_disDrawableRight, -1));
        setRightTintColor(styledAttributes.getColor(R.styleable.DisDrawableButton_disRightTintColor, -1));
        setRightDrawableHeight(styledAttributes.getDimension(R.styleable.DisDrawableButton_disDrawableRightHeight, -1));
        setRightDrawableWidth(styledAttributes.getDimension(R.styleable.DisDrawableButton_disDrawableRightWidth, -1));
    }

    private void setLeftDrawableHeight(float height) {
        if (height != -1) {
            ViewGroup.LayoutParams params = imgDrawableLeft.getLayoutParams();
            params.height = (int) height;
            imgDrawableLeft.requestLayout();
        }
    }

    private void setLeftDrawableWidth(float width) {
        if (width != -1) {
            ViewGroup.LayoutParams params = imgDrawableLeft.getLayoutParams();
            params.width = (int) width;
            imgDrawableLeft.requestLayout();
        }
    }

    private void setRightDrawableHeight(float height) {
        if (height != -1) {
            ViewGroup.LayoutParams params = imgDrawableRight.getLayoutParams();
            params.height = (int) height;
            imgDrawableRight.requestLayout();
        }
    }

    private void setRightDrawableWidth(float width) {
        if (width != -1) {
            ViewGroup.LayoutParams params = imgDrawableRight.getLayoutParams();
            params.width = (int) width;
            imgDrawableRight.requestLayout();
        }
    }

    private void setupText(TypedArray styledAttributes) {
        setText(styledAttributes.getString(R.styleable.DisDrawableButton_disText));
        setTextColor(styledAttributes.getColor(R.styleable.DisDrawableButton_disTextColor, getResources().getColor(R.color.disTextColor)));
        setTextSize(styledAttributes.getDimension(R.styleable.DisDrawableButton_disTextSize, getResources().getDimension(R.dimen.disTextSize)));
        int textStyle = styledAttributes.getInteger(R.styleable.DisDrawableButton_disTextStyle, DisTextStyle.NORMAL.getValue());
        switch (textStyle) {
            case 0:
                setTextStyle(DisTextStyle.NORMAL);
                break;
            case 1:
                setTextStyle(DisTextStyle.BOLD);
                break;
            case 2:
                setTextStyle(DisTextStyle.ITALIC);
                break;
            case 3:
                setTextStyle(DisTextStyle.BOLD_ITALIC);
                break;
        }
    }

    public AppCompatTextView getTextView() {
        return txtDrawableButton;
    }

    public void setBackgroundResource(int res) {
        clDrawableButton.setBackgroundResource(res);
    }

    public void setEnable(boolean enable) {
        clDrawableButton.setEnabled(enable);
        btnDrawableButton.setEnabled(enable);
        if (enable) {
            setBackgroundResource(backgroundRes);
        } else {
            setBackgroundResource(disableBackgroundRes);
        }
    }

    public void setEnableClickAnimation(boolean enableClickAnimation) {
        this.enableClickAnimation = enableClickAnimation;
        if (enableClickAnimation) {
            btnDrawableButton.setVisibility(View.VISIBLE);
        } else {
            btnDrawableButton.setVisibility(View.GONE);
        }
    }

    public void setDisableBackgroundRes(int disableBackgroundRes) {
        this.disableBackgroundRes = disableBackgroundRes;
    }

    public void setText(String text) {
        txtDrawableButton.setText(text);
    }

    public void setTextSize(float textSize) {
        textSize = textSize / getResources().getDisplayMetrics().density;
        txtDrawableButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    public void setTextStyle(DisTextStyle disTextStyle) {
        switch (disTextStyle) {
            case NORMAL:
                txtDrawableButton.setTypeface(txtDrawableButton.getTypeface(), Typeface.NORMAL);
                break;
            case BOLD:
                txtDrawableButton.setTypeface(txtDrawableButton.getTypeface(), Typeface.BOLD);
                break;
            case ITALIC:
                txtDrawableButton.setTypeface(txtDrawableButton.getTypeface(), Typeface.ITALIC);
                break;
            case BOLD_ITALIC:
                txtDrawableButton.setTypeface(txtDrawableButton.getTypeface(), Typeface.BOLD_ITALIC);
                break;
        }
    }

    public void setTextColor(int textColor) {
        txtDrawableButton.setTextColor(textColor);
    }

    public void setRightDrawable(int rightDrawable) {
        if (rightDrawable != -1) {
            imgDrawableRight.setImageResource(rightDrawable);
        }
    }

    public void setRightTintColor(int rightTintColor) {
        if (rightTintColor != -1) {
            imgDrawableRight.setColorFilter(rightTintColor);
        }
    }

    public void setLeftDrawable(int leftDrawable) {
        if (leftDrawable != -1) {
            imgDrawableLeft.setImageResource(leftDrawable);
        }
    }

    public void setLeftTintColor(int leftTintColor) {
        if (leftTintColor != -1) {
            imgDrawableLeft.setColorFilter(leftTintColor);
        }
    }

    public void setUnderlineHeight(float underlineHeight) {
        underlineHeight = underlineHeight / getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams params = vUnderline.getLayoutParams();
        params.height = (int) underlineHeight;
        vUnderline.requestLayout();
    }

    public void setUnderlineColor(int underlineColor) {
        vUnderline.setBackgroundColor(underlineColor);
    }

    public void setUnderlineEnable(boolean underlineEnable) {
        if (underlineEnable) {
            vUnderline.setVisibility(VISIBLE);
        } else {
            vUnderline.setVisibility(GONE);
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface == null) {
            return;
        }
        txtDrawableButton.setTypeface(typeface);
    }


}