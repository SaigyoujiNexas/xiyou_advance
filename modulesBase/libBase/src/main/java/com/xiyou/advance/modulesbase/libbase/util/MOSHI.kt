package com.xiyou.advance.modulesbase.libbase.util

import com.squareup.moshi.Moshi
import java.lang.ref.WeakReference

class MOSHI {
    companion object{
        private var instance : WeakReference<Moshi>? = null
        fun getInstance(): WeakReference<Moshi>
        {
            if (instance == null || instance!!.get() == null)
                instance = WeakReference(Moshi.Builder().build())

            return instance!!
        }
    }
}