package com.shenme.mvp_kotlin.mvp.presenter

import com.shenme.mvp_kotlin.app.data.response.ResponseGank
import com.shenme.mvp_kotlin.mvp.contract.AndroidContract
import com.shenme.mvp_kotlin.mvp.model.api.ApiService
import com.shenme.mvp_kotlin.net.BaseResult
import com.shenme.mvp_kotlin.net.CodeHandledSubscriber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by CANC on 2017/8/29.
 */
class AndroidPresenter : AndroidContract.Presenter {

    var apiService: ApiService
    var view: AndroidContract.View

    @Inject
    constructor(view: AndroidContract.View, apiService: ApiService) {
        this.view = view
        this.apiService = apiService
    }

    override fun getAndroidInfo(pageSize: String) {
        apiService.getAndroidInfo(pageSize)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CodeHandledSubscriber<ResponseGank>() {

                    override fun onNextSuccess(result: ResponseGank) {
                        view.setData(result.results!!)
                    }

                    override fun onNextError(baseResult: BaseResult?) {
                        view.showMessage(baseResult!!.message!!)
                    }

                    override fun onComplete() {
                        view.refreshComplete()
                    }
                })
    }
}