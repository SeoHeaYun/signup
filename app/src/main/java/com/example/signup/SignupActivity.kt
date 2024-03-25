package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.signup.UserData.userId
import com.example.signup.UserData.userPw

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signupname = findViewById<EditText>(R.id.etv_signupname)
        val signupid = findViewById<EditText>(R.id.etv_signupid)
        val signuppw = findViewById<EditText>(R.id.etv_signuppw)
        val signup = findViewById<Button>(R.id.btn_realsignup)

        signup.setOnClickListener {
            if (signupname.text.isBlank() || signupid.text.isBlank() || signuppw.text.isBlank()) {
                Toast.makeText(this@SignupActivity, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {   // 셋다 false 즉, 다 채워져 있는 경우 -> 유저정보 object 클래스 내 리스트에 넘기기  ->  나중에 로그인시 이 값과 비교할 것임
                userId.add(signupid.text.toString())
                userPw.add(signuppw.text.toString())
                Toast.makeText(this@SignupActivity, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT)
                    .show()  //   show() 꼭 넣자.. 이것 때문에 10분 날렸다.. 좋은 교훈.
                // Activity Result API: 값 전달하기     ( 회원가입창: 데이터 전달  -> 로그인창: 데이터 수신 후 콜백등록)
                intent.putExtra("myid", signupid.text.toString())
                intent.putExtra("mypw", signuppw.text.toString())   // putExtra는 string값을 인자로 받는다.
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}



