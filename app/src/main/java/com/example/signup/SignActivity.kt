package com.example.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.signup.UserData.userId
import com.example.signup.UserData.userPw

class SignActivity : AppCompatActivity() {
    lateinit var resultLauncher: ActivityResultLauncher<Intent> // Activity Result API- 전역 변수로 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)


        val id = findViewById<EditText>(R.id.etv_id)
        val pw = findViewById<EditText>(R.id.etv_password)
        val login = findViewById<Button>(R.id.btn_login)
        val signup = findViewById<Button>(R.id.btn_signup)

        // 회원가입 페이지로 이동
        signup.setOnClickListener { // Activity Result API: 콜백선언 후 값 수신받기
            val signupIntent = Intent(this@SignActivity, SignupActivity::class.java) // 인텐트: 회원가입 페이지
            resultLauncher.launch(signupIntent)   // launch는 startActivity와 유사
        }


        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->    // ActivityResultCallback 람다함수
                if (result.resultCode== RESULT_OK) {
                    val getId = result.data?.getStringExtra("myid")?: ""
                    val getPw = result.data?.getStringExtra("mypw")?: ""
                    id.setText(getId)
                    pw.setText(getPw)
                }
            }


        // 로그인 페이지로 이동
        login.setOnClickListener {
            val loginIntent = Intent(this@SignActivity, HomeActivity::class.java)
            var identifyId = false // 입력한 Id가 회원가입한 정보U 즉, UserDataO Object Class에 있는 Id와 동일한지 식별
            var identifyPw = false
            if (id.text.isBlank() || pw.text.isBlank()) {   // 문자열이 아예 없어도 안되고, 공백이어도 안됨.
                Toast.makeText(this@SignActivity, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else if (id.text.isNotBlank() && pw.text.isNotBlank()) {  // 둘다 false 즉, 다 채워져있는 경우-> for문 돌려서, 회원가입된 값과 같을 때만 로그인 시켜준다. (회원정보는 UserData 클래스에 모아두었음.)
                for (i in userId) {
                    if (i == id.text.toString()) {  // id값 비교
                        identifyId = true
                        break
                    }
                }
                for (k in userPw) {
                    if (k == pw.text.toString()) {  // pw값 비교
                        identifyPw = true
                        break
                    }
                }
                if (identifyId && identifyPw) {  // id,pw값 회원가입 정보와 모두 같은 경우
                    loginIntent.putExtra("id", id.text.toString())
                    Toast.makeText(this@SignActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                    startActivity(loginIntent)
                } else {
                    Toast.makeText(
                        this@SignActivity,
                        "정보를 다시 입력하거나 회원가입을 완료해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

// for문 연속 2개 했을 떄 문제점: when과 다르게 모든 값들을 대입해서, 실행속도느릴 뿐더러, false인 경우는 다 else에 떨어지는 바람에 toast 많이 띄어짐. 따라서 if절로 나눠서 하는 것이 좋음.



