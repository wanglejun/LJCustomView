package com.lj.piechart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lj.common.Utils

class PieChartView:View {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val RADIUS = Utils.instance.dp2px(150f)
    val LENGTH = 20

    val rectF = RectF()
    val angles = arrayOf(60,100,120,80)
    val colors = arrayOf(Color.parseColor("#2979FF"),
        Color.parseColor("#C2185B"),
        Color.parseColor("#009688"),
        Color.parseColor("#FF8F00"))

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(width/2-RADIUS,height/2-RADIUS,width/2+RADIUS,height/2+RADIUS)

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var currAngle = 0
//        canvas?.drawArc(rectF,currAngle.toFloat(),120f,true,paint)

        for ((i,angle) in angles.withIndex()) {
            paint.color = colors[i]
            canvas?.save()
            if(i == 2){
                canvas?.translate((Math.cos(Math.toRadians((currAngle+angle/2).toDouble()))*LENGTH).toFloat(),
                    (Math.sin(Math.toRadians((currAngle+angle/2).toDouble()))*LENGTH).toFloat()
                )
            }
            canvas?.drawArc(rectF,currAngle.toFloat(),angle.toFloat(),true,paint)
            canvas?.restore()
            currAngle += angle
        }

    }

}