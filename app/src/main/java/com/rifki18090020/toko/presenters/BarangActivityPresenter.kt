package com.rifki18090020.toko.presenters

import com.rifki18090020.toko.contracts.BarangActivityContract
import com.rifki18090020.toko.models.Barang
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.rifki18090020.toko.webservices.Api
import com.rifki18090020.toko.webservices.WrappedListResponse

class BarangActivityPresenter (v: BarangActivityContract.View?):
    BarangActivityContract.Interaction{
    private var view : BarangActivityContract.View? = v
    private  var api = Api.instance()
    override fun allUser(token: String) {
        val request = api.get(token)
        request.enqueue(object : Callback<WrappedListResponse<Barang>>{
            override fun onFailure(call: Call<WrappedListResponse<Barang>>, t: Throwable) {
                println("Log: ${t.message} ")
                view?.toast("Cannot connect to server")
            }
            override fun onResponse(
                call: Call<WrappedListResponse<Barang>>,
                response: Response<WrappedListResponse<Barang>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status){
                        view?.attachToRecycle(body.data)
                    }else{
                        view?.toast("Something went wrong, try again later")
                    }
                }
            }

        })
    }

    override fun destroy() {
        view = null
    }
}