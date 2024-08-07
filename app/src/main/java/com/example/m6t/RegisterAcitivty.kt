package com.example.m6t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterAcitivty : AppCompatActivity() {
    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnReg: Button
    lateinit var btnToLog: Button
    lateinit var initialplaylist:ArrayList<playlist>

    lateinit var tracks:ArrayList<track>
    lateinit var users:ArrayList<user>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etUsername=findViewById(R.id.et_username_reg)
        etPassword=findViewById(R.id.etp_password_reg)
        btnReg=findViewById(R.id.btn_reg)
        btnToLog=findViewById(R.id.btn_tolog)

        initialplaylist=ArrayList()
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

        btnReg.setOnClickListener {
            var check=true
            for (user in users){
                if (user.username==etUsername.text.toString()){
                    check=false
                    break
                }
            }
            if (!check){
                Toast.makeText(this, "username sudah terregistrasi!", Toast.LENGTH_SHORT).show()
            }else if (etUsername.text.toString()=="adminnihbos"){
                Toast.makeText(this, "username tidak boleh sama dengan admin!", Toast.LENGTH_SHORT).show()
            }else{
                users.add(user(etUsername.text.toString(),etPassword.text.toString(),initialplaylist,initialplaylist))
                Toast.makeText(this, "register berhasil!", Toast.LENGTH_SHORT).show()
            }
        }
        btnToLog.setOnClickListener {
            finish()
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("tracks",tracks)
            intent.putExtra("users",users)
            startActivity(intent)
        }


    }
}