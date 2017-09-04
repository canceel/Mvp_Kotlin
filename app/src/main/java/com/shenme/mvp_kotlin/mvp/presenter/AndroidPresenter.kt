package com.shenme.mvp_kotlin.mvp.presenter

import android.Manifest
import android.util.Log
import com.shenme.mvp_kotlin.app.data.response.ResponseGank
import com.shenme.mvp_kotlin.app.net.BaseResult
import com.shenme.mvp_kotlin.app.net.CodeHandledSubscriber
import com.shenme.mvp_kotlin.app.utils.PermissionUtils
import com.shenme.mvp_kotlin.app.utils.RxUtils
import com.shenme.mvp_kotlin.mvp.contract.AndroidContract
import com.shenme.mvp_kotlin.mvp.model.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by CANC on 2017/8/29.
 */
class AndroidPresenter : AndroidContract.Presenter, PermissionUtils.PermissionLisenter {
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
                .compose(RxUtils().bindToLifecycle(view))//与view的生命周期绑定，当view销毁时，结束取消网络请求
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

    override fun getIosInfo(pageSize: String) {
        apiService.getIosInfo(pageSize)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils().bindToLifecycle(view))//与view的生命周期绑定，当view销毁时，结束取消网络请求
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

    override fun getVideoInfo(pageSize: String) {
        apiService.getVideoInfo(pageSize)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils().bindToLifecycle(view))//与view的生命周期绑定，当view销毁时，结束取消网络请求
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

    override fun getFuliInfo(pageSize: String) {
        apiService.getFuliInfo(pageSize)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils().bindToLifecycle(view))//与view的生命周期绑定，当view销毁时，结束取消网络请求
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

    override fun getExpandInfo(pageSize: String) {
        apiService.getExpandInfo(pageSize)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils().bindToLifecycle(view))//与view的生命周期绑定，当view销毁时，结束取消网络请求
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

    override fun getFrontInfo(pageSize: String) {
        apiService.getFrontInfo(pageSize)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils().bindToLifecycle(view))//与view的生命周期绑定，当view销毁时，结束取消网络请求
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


    /**
     * 权限申请使用示例
     */
    fun getLocationPermission() {
        PermissionUtils().requierPermission(view.getRxpermissions(), this, Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.RECORD_AUDIO)
    }

    override fun requirePermissionSuccess(permissionName: String) {
        Log.d("Permission", "Agree " + permissionName)
    }

    override fun refusePermission(permissionName: String) {
        Log.d("Permission", "Refuse " + permissionName)
    }

    override fun forbiddenPermission(permissionName: String) {
        Log.d("Permission", "Refuse & don't require Again " + permissionName)
    }

}