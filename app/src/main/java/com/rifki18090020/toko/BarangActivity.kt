package com.rifki18090020.toko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhe18090088.toko.R
import com.rifki18090020.toko.adapters.BarangAdapter
import com.adhe18090088.toko.autilities.Utils
import com.rifki18090020.toko.contracts.BarangActivityContract
import com.rifki18090020.toko.models.Barang
import com.rifki18090020.toko.presenters.BarangActivityPresenter
import kotlinx.android.synthetic.main.activity_barang.*

class BarangActivity : AppCompatActivity(), BarangActivityContract.View{
    private  var presenter =
        BarangActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang)
        checkIsLoggedIn()
        presenter =
            BarangActivityPresenter(this)
    }

    private fun getData(){
        Utils.getToken(this)?.let { presenter.allUser(it) }
    }

    override fun attachToRecycle(barang: List<Barang>) {
        rvBarang.apply {
            layoutManager = LinearLayoutManager(this@BarangActivity)
            adapter = BarangAdapter(
                barang,
                this@BarangActivity
            )
        }
    }

    override fun toast(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {
    }

    override fun notConnect(message: String?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
    private fun checkIsLoggedIn(){
        val token = Utils.getToken(this)
        if (token == null || token.equals("UNDEFINED")){
            startActivity(Intent(this, LoginActivity::class.java)).also { finish() }
        }
    }
    override fun onResume() {
        super.onResume()
        getData()
    }
}