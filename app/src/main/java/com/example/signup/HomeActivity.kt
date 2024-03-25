package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val randomImage = findViewById<ImageView>(R.id.iv_randomimage)
        val ImageList = listOf(
            R.drawable.pizza,
            R.drawable.don,
            R.drawable.bossam,
            R.drawable.meet,
            R.drawable.chicken
        )
        var shuffledImage = ImageList.shuffled()
        randomImage.setImageResource(shuffledImage.get(0))   // 리스트 요소를 섞고 첫번째 사진을 보여준다.


        // 랜덤 다른 방법: 리스트 순서는 그대로 두고, (주사위 만들 때처럼) 랜덤하게 값을 가져오는 함수만들고 호출하기.

        val myId = findViewById<EditText>(R.id.tv_1)
        val getId = intent.getStringExtra("id")
        myId.setText(getId)

        val finish = findViewById<Button>(R.id.btn_finish)
        finish.setOnClickListener {
            finish()        //
        }
    }
}





