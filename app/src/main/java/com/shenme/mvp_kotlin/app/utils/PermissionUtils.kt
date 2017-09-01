package com.shenme.mvp_kotlin.app.utils

import com.tbruyelle.rxpermissions2.RxPermissions
import org.simple.eventbus.EventBus

/**
 * Created by CANC on 2017/9/1.
 */
class PermissionUtils {

    /**
     * 权限申请
     * @param  rxPermissions
     * @param  lisenter
     * @param  permissions  所申请的权限(1~n)
     */
    fun requierPermission(rxPermissions: RxPermissions, lisenter: PermissionLisenter?, vararg permissions: String) {
        for (permission in permissions) {
            var isPermissionsGranted = rxPermissions.isGranted(permission)
            if (isPermissionsGranted) {
                EventBus.getDefault()
                lisenter?.requirePermissionSuccess(permission)
            } else {
                rxPermissions.requestEach(permission)
                        .subscribe { permission ->
                            if (permission!!.granted) {
                                // 用户已经同意该权限
                                lisenter?.requirePermissionSuccess(permission.name)
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                lisenter?.refusePermission(permission.name)
                            } else {
                                //  用户拒绝了该权限，并且选中『不再询问』（Never ask again）
                                lisenter?.forbiddenPermission(permission.name)
                            }
                        }
            }
        }

    }

    /**
     * 添加权限信息，避免多个权限一起请求
     * 而无法判断具体是哪个权限成功与失败
     */
    interface PermissionLisenter {
        /**
         * 权限请求成功，
         */
        fun requirePermissionSuccess(permissionName: String)

        /**
         * 拒绝权限
         */
        fun refusePermission(permissionName: String)

        /**
         * 拒绝权限不再询问
         */
        fun forbiddenPermission(permissionName: String)
    }

}