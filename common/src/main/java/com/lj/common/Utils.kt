package com.lj.common

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

class Utils private constructor(){
    companion object{
        val instance : Utils by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED)  {
            Utils()
        }
    }

    fun dp2px(dp:Float):Float{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,Resources.getSystem().displayMetrics)
    }

}