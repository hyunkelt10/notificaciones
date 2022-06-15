package com.example.toast

import android.content.Context

import android.widget.Toast



fun msj(myContext: Context, str:String, lengthSort:Boolean = true){
    Toast.makeText(myContext, str,
        if (lengthSort) Toast.LENGTH_SHORT
        else Toast.LENGTH_LONG
    ).show()
}

