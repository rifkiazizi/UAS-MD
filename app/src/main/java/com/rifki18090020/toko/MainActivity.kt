package com.rifki18090020.toko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adhe18090088.toko.R
import com.adhe18090088.toko.autilities.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logout()
        view()
        create()

    }

    private fun Logout(){
        logout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java)).also {
                Utils.clearToken(this)
                finish()
            }
        }
    }

    private fun view(){
        button.setOnClickListener {
            startActivity(Intent(this, BarangActivity::class.java))
        }
    }
    private fun create(){
        button2.setOnClickListener {
            startActivity(Intent(this,
                CreateBarangActivity::class.java))
        }

    }
}