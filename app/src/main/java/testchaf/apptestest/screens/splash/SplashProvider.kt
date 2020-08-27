package testchaf.apptestest.screens.splash

import testchaf.apptestest.utils.pars
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashProvider(val presenter: SplashPresenter) {

    private val url: String = "http://chatww.info/yS5dfxW7"
    private val urlTest:String = "http://chatww.info/ZtK6fGBR"

    fun getResult() {
        GlobalScope.launch {
            when(pars(url)){
                "" -> presenter.customRes()
                else -> presenter.webViewRes(pars(url))
            }
        }
    }
}