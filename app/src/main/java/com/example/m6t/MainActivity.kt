package com.example.m6t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnLog: Button
    lateinit var btnToReg: Button


    lateinit var tracks:ArrayList<track>
    lateinit var users:ArrayList<user>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUsername=findViewById(R.id.et_username_log)
        etPassword=findViewById(R.id.etp_password_log)
        btnLog=findViewById(R.id.btn_login)
        btnToReg=findViewById(R.id.btn_toRegister)

        tracks= ArrayList()
        users=ArrayList()

        val currentTracks=intent.getParcelableArrayListExtra<track>("tracks")
        if (currentTracks!=null){
            tracks=currentTracks
        }
        val currentUsers=intent.getParcelableArrayListExtra<user>("users")
        if (currentUsers!=null) {
            users=currentUsers
        }

        btnLog.setOnClickListener{
            if (etUsername.text.toString()=="adminnihbos"&&etPassword.text.toString()=="adminnihbos"){
                finish()
                val intent= Intent(this,AdminActivity::class.java)
                intent.putExtra("tracks",tracks)
                intent.putExtra("users",users)
                startActivity(intent)
            }else{
                var check=false
                var initialusername=""
                for(user in users){
                    if(user.username==etUsername.text.toString()){
                        if(user.password==etPassword.text.toString()){
                            check=true
                            initialusername=user.username
                            break
                        }
                    }
                }
                if (check){
                    finish()
                    val intent= Intent(this,UserActivity::class.java)
                    intent.putExtra("tracks",tracks)
                    intent.putExtra("users",users)
                    intent.putExtra("current",initialusername)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "username/password salah!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnToReg.setOnClickListener {
            finish()
            val intent= Intent(this,RegisterAcitivty::class.java)
            intent.putExtra("tracks",tracks)
            intent.putExtra("users",users)
            startActivity(intent)
        }
    }
}