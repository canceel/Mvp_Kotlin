package com.shenme.mvp_kotlin.di.module

import com.shenme.mvp_kotlin.mvp.contract.AndroidContract
import dagger.Module
import dagger.Provides

/**
 * Created by CANC on 2017/8/29.
 */
@Module
class AndroidModule constructor(mView: AndroidContract.View) {
    var view = mView

    @Provides
    fun provideView(): AndroidContract.View {
        return view
    }
}