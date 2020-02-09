package com.example.postservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener(this) //ボタンを押したロードが始まる。
    }

    override fun onClick(p0: View) {
        if(p0.id == button.id){
            MyAsyncTask(this).execute("Param1")
        }
    }
}
