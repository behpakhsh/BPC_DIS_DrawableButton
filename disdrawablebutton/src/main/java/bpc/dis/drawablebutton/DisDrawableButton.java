package bpc.dis.drawablebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DisDrawableButton extends FrameLayout {

    private ConstraintLayout clDrawableButton;
    private AppCompatImageView imgDrawableLeft;
    private AppCompatImageView imgDrawableRight;
    private AppCompatTextView txtDrawableButton;
    private AppCompatButton btnDrawableButton;
    private View vUnderline;

    private int backgroundColor;
    private int disableColor;
    private boolean enableClickAnimation;

    public DisDrawableButton(Context context) {
        super(context);
        init(context, null, 0);
    }

    public DisDrawableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public DisDrawableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = inflate(context, R.layout.dis_drawable_button, this);
        clDrawableButton = view.findViewById(R.id.cl_dis_drawable_button);
        btnDrawableButton = view.findViewById(R.id.btn_dis_drawable_button);
        imgDrawableLeft = view.findViewById(R.id.img_drawable_left);
        imgDrawableRight = view.findViewById(R.id.img_drawable_right);
        txtDrawableButton = view.findViewById(R.id.txt_drawable_button);
        vUnderline = view.findViewById(R.id.v_underline);
        setupView(context, attrs);
    }

    private void setupView(Context context, AttributeSet attrs) {
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DisDrawableButton);
        setupLayout(context, styledAttributes);
        setupDrawable(styledAttributes);
        setupText(context, styledAttributes);
        setupUnderline(context, styledAttributes);
        styledAttributes.recycle();
    }

    private void setupUnderline(Context context, TypedArray styledAttributes) {
        //enable

        boolean underlineEnable = styledAttributes.getBoolean(R.styleable.DisDrawableButton_disUnderlineEnable, false);
        setUnderlineEnable(underlineEnable);

        //color

        int underlineColor = styledAttributes.getColor(R.styleable.DisDrawableButton_disUnderlineColor, context.getResources().getColor(R.color.disUnderlineColor));
        setUnderlineColor(underlineColor);

        //heightSize

        float underlineHeight = styledAttributes.getDimension(R.styleable.DisDrawableButton_disUnderlineHeight, context.getResources().getDimension(R.dimen.disUnderlineHeight));
        setUnderlineHeight(underlineHeight);

    }

    private void setupLayout(Context context, TypedArray styledAttributes) {
        //background

        int backgroundColor = styledAttributes.getColor(R.styleable.DisDrawableButton_disBackground, context.getResources().getColor(R.color.disBackground));
        this.backgroundColor = backgroundColor;
        setBackground(backgroundColor);


        //background

        int disableColor = styledAttributes.getColor(R.styleable.DisDrawableButton_disDisableBackgroundColor, backgroundColor);
        setDisableColor(disableColor);

        //enable

        boolean enable = styledAttributes.getBoolean(R.styleable.DisDrawableButton_disEnable, true);
        setEnable(enable);

        //enable

        boolean enableClickAnimation = styledAttributes.getBoolean(R.styleable.DisDrawableButton_disEnableClickAnimation, true);
        setEnableClickAnimation(enableClickAnimation);
    }

    private void setupDrawable(TypedArray styledAttributes) {
        //leftDrawable

        int leftDrawable = styledAttributes.getResourceId(R.styleable.DisDrawableButton_disDrawableLeft, -1);
        setLeftDrawable(leftDrawable);


        //leftTintColor

        int leftTintColor = styledAttributes.getColor(R.styleable.DisDrawableButton_disLeftTintColor, -1);
        setLeftTintColor(leftTintColor);

        //rightDrawable

        int rightDrawable = styledAttributes.getResourceId(R.styleable.DisDrawableButton_disDrawableRight, -1);
        setRightDrawable(rightDrawable);


        //rightTintColor

        int rightTintColor = styledAttributes.getColor(R.styleable.DisDrawableButton_disRightTintColor, -1);
        setRightTintColor(rightTintColor);
    }

    private void setupText(Context context, TypedArray styledAttributes) {
        //text

        String text = styledAttributes.getString(R.styleable.DisDrawableButton_disText);
        setText(text);


        //textColor

        int textColor = styledAttributes.getColor(R.styleable.DisDrawableButton_disTextColor, context.getResources().getColor(R.color.disTextColor));
        setTextColor(textColor);


        //textSize

        float textSize = styledAttributes.getDimension(R.styleable.DisDrawableButton_disTextSize, context.getResources().getDimension(R.dimen.disTextSize));
        setTextSize(textSize);


        //textStyle

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


    public void setBackground(int color) {
        clDrawableButton.setBackgroundColor(color);
    }

    public void setBackgroundDrawable(int res) {
        clDrawableButton.setBackgroundResource(res);
    }

    public void setEnable(boolean enable) {
        clDrawableButton.setEnabled(enable);
        btnDrawableButton.setEnabled(enable);
        if (enable) {
            setBackground(backgroundColor);
        } else {
            setBackground(disableColor);
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

    public void setDisableColor(int disableColor) {
        this.disableColor = disableColor;
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


    @Override
    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        if (enableClickAnimation) {
            btnDrawableButton.setOnClickListener(onClickListener);
        } else {
            clDrawableButton.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void setOnLongClickListener(@Nullable OnLongClickListener onLongClickListener) {
        if (enableClickAnimation) {
            btnDrawableButton.setOnLongClickListener(onLongClickListener);
        } else {
            clDrawableButton.setOnLongClickListener(onLongClickListener);
        }
    }


}