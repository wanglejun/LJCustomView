package com.lj.dashboard

import android.content.Context
import android.graphics.*
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.AttributeSet
import android.view.View
import com.lj.common.Utils

class DashboardView:View{
    val RADIUS = Utils.instance.dp2px(150f)
    val ANGLE = 120
    val LENGTH = 200

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var effect: PathDashPathEffect? = null
    val dash = Path()
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = Utils.instance.dp2px(2f)
        dash.addRect(0f,0f,Utils.instance.dp2px(2f),Utils.instance.dp2px(10f),Path.Direction.CW)


        val arcPath = Path()
        arcPath.addArc(width/2-RADIUS,height/2-RADIUS,width/2+RADIUS,height/2+RADIUS,(90+ANGLE/2).toFloat(),(360-ANGLE).toFloat())
        val pathMeasure = PathMeasure(arcPath,false)
        effect = PathDashPathEffect(dash,(pathMeasure.length-Utils.instance.dp2px(2f))/20,0f,PathDashPathEffect.Style.ROTATE)

    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawArc(width/2-RADIUS,height/2-RADIUS,width/2+RADIUS,height/2+RADIUS,(90+ANGLE/2).toFloat(),(360-ANGLE).toFloat(),false,paint)
        paint.pathEffect = effect
        canvas?.drawArc(width/2-RADIUS,height/2-RADIUS,width/2+RADIUS,height/2+RADIUS,(90+ANGLE/2).toFloat(),(360-ANGLE).toFloat(),false,paint)
        paint.pathEffect = null

        canvas?.drawLine((width/2).toFloat(),(height/2).toFloat(),(Math.cos(Math.toRadians(getAngleFromMark(5).toDouble()))).toFloat()*LENGTH+width/2,
            Math.sin(Math.toRadians(getAngleFromMark(5).toDouble())).toFloat()*LENGTH+height/2,paint)
    }


    fun getAngleFromMark(mark : Int):Int{
        return (90+(ANGLE/2).toFloat()+((360-ANGLE)/20).toFloat()*mark).toInt()
    }
}