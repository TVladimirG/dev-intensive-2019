package ru.skillbranch.devintensive.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
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
           private const val DEFAULT_BORDER_COLOR = Color.WHITE
           private const val DEFAULT_BORDER_WIDTH = 2
    }

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

        //  val newHeight = (measuredWidth/aspectRatio).toInt()
        //  setMeasuredDimension(measuredWidth, newHeight)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /*
        val paint = Paint()
       // paint.color = Color.WHITE
        paint.textSize = 120F
        paint.isAntiAlias = false
        canvas!!.drawColor(Color.BLUE)
        canvas.drawText("HK", 20F, 80F, paint)
        canvas.drawOval(50f,50f,50f,50f, paint)

         */

        /*
        val output = Bitmap.createBitmap(measuredWidth/2, measuredHeight/2, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        val roundPx = pixels.toFloat()
        paint.setAntiAlias(true)
        canvas.drawARGB(0, 0, 0, 0)
        paint.setColor(color)
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, rect, rect, paint)

         */

        //    val paint = Paint()
        //  val x = measuredWidth/2 as Float
        //  val y = measuredHeight/2 as Float
        //  val r = 20  as Float
        //     paint.isAntiAlias = true;
        //      paint.color = Color.GREEN
        // canvas?.drawCircle(x, y, r, paint)

    }

    /*
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

     */

}