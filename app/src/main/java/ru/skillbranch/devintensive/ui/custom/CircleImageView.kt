package ru.skillbranch.devintensive.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import ru.skillbranch.devintensive.R

@SuppressLint("AppCompatCustomView")
class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr){


    companion object {
     //   private const val DEFAULT_ASPECT_RATIO = 1.78f
           private const val DEFAULT_BORDER_COLOR = Color.WHITE
           private const val DEFAULT_BORDER_WIDTH = 2
    }

     // private var aspectRatio = DEFAULT_ASPECT_RATIO
     private var cv_borderColor = DEFAULT_BORDER_COLOR
     private var cv_borderWidth = DEFAULT_BORDER_WIDTH

    init {
        if (attrs != null) {

         //   val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
           // aspectRatio = typedArray.getFloat(R.styleable.AspectRatioImageView_aspectRatio, aspectRatio)
            //typedArray.recycle()

            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            cv_borderColor = typedArray.getColor(R.styleable.CircleImageView_cv_borderColor, cv_borderColor)
            cv_borderWidth = typedArray.getInt(R.styleable.CircleImageView_cv_borderWidth, cv_borderWidth)

            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

     //   val newHeight = (measuredWidth/aspectRatio).toInt()
     //   setMeasuredDimension(measuredWidth, newHeight)

    }


 //   @Dimension
 //   fun getBorderWidth():Int {}
 //   fun setBorderWidth(@Dimension dp:Int)  {}
 //   fun getBorderColor():Int  {}
 //   fun setBorderColor(hex:String) {}
 //   fun setBorderColor(@ColorRes colorId: Int)  {}
}