### 	为了方便MvpKotlin开发，编写的模板。使用者只需按步骤添加相应的功能模块。

####			添加步骤:Contract-->Presenter-->Activity-->Module-->Component



####  1.Templates Contract

```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
import com.shenme.mvp_kotlin.app.base.IView
#parse("File Header.java")

interface ${NAME}Contract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
    }
    
    interface Presenter{
    }
}
```
#### 2.Templates Presenter

```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import com.shenme.mvp_kotlin.mvp.model.api.ApiService
import javax.inject.Inject

#parse("File Header.java")
class ${NAME}Presenter :  ${NAME}Contract.Presenter {
    var apiService: ApiService
    var view:  ${NAME}Contract.View

    @Inject
    constructor(view: ${NAME}Contract.View, apiService: ApiService) {
        this.view = view
        this.apiService = apiService
    }
}
```
#### 3.Template Activity

```
package com.shenme.mvp_kotlin.mvp.view.activity

import android.content.Intent
import android.os.Bundle
import com.shenme.mvp_kotlin.R
import com.shenme.mvp_kotlin.app.MyApplication
import com.shenme.mvp_kotlin.app.base.BaseActivity
import com.shenme.mvp_kotlin.di.component.Dagger${NAME}Component
import org.jetbrains.anko.toast
import javax.inject.Inject

class ${NAME}Activity : BaseActivity(), ${NAME}Contract.View {

    @Inject
    lateinit var presenter: ${NAME}Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_${NAME})
        setupActivityComponent()
    }

    override fun setupActivityComponent() {
        Dagger${NAME}Component.builder()
                .${NAME}Module(${NAME}Module(this))//请将${NAME}Module()第一个首字母改为小写
                .netComponent(MyApplication()[this].netComponent)
                .build()
                .inject(this)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
        toast(message)
    }
    
    override fun launchActivity(intent: Intent) {
        startActivity(intent)
    }
}
```
#### 4.Template Module

```
package com.shenme.mvp_kotlin.di.module

import dagger.Module
import dagger.Provides

@Module
class ${NAME}Module constructor(mView: ${NAME}Contract.View) {
    var view = mView

    @Provides
    fun provideView(): ${NAME}Contract.View {
        return view
    }
}
```

#### 5.Template Component

```
package com.shenme.mvp_kotlin.di.component
import com.shenme.mvp_kotlin.di.scopes.UserScope
import dagger.Component

@UserScope
@Component(modules = arrayOf(${NAME}Module::class), dependencies = arrayOf(NetComponent::class))
interface ${NAME}Component {
    fun inject(activity: ${NAME}Activity)
}
```









