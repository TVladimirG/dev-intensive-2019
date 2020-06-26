package ru.skillbranch.devintensive.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
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
        setFrame(40, 40, 40, 40)


    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //   canvas!!.drawOval(30f,30f,30f,30f, Paint())

        //   val dr: RoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, src. as Bitmap)
        //   dr.cornerRadius = cornerRadius
        //   imageView.setImageDrawable(dr)

        //  val bitmap = (.getDrawable() as BitmapDrawable).bitmap
        //       var bitmap = ImageHelper.getRoundedCornerBitmap(bitmap, )
        //  val paint = Paint()

        //  val x = measuredWidth/2 as Float
        //  val y = measuredHeight/2 as Float
        //  val r = 20  as Float

        //  paint.isAntiAlias = true;


        //  val bitmap = Bitmap.createBitmap(
        //      measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)

        //  val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        //  paint.isAntiAlias = true
        //  paint.shader = shader

        //  val rect = RectF(10.0f, 10.0f, measuredWidth.toFloat(), measuredHeight.toFloat())

// rect contains the bounds of the shape
// radius is the radius in pixels of the rounded corners
// paint contains the shader that will texture the shape

// rect contains the bounds of the shape
// radius is the radius in pixels of the rounded corners
// paint contains the shader that will texture the shape
        // canvas!!.drawRoundRect(rect, r, r, paint)

        //  canvas
        //  if (canvas != null) {
        //      canvas.drawCircle(x, y, r, paint)
        // }

    }

    @Dimension
    fun getBorderWidth(): Int {
        return 0
    }

    fun setBorderWidth(@Dimension dp: Int) {

    }

    fun getBorderColor(): Int {
        return 0
    }

    fun setBorderColor(hex: String) {

    }

    fun setBorderColor(@ColorRes colorId: Int) {

    }

}