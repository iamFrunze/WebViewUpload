package testchaf.apptestest.screens.splash

import moxy.MvpPresenter

class SplashPresenter : MvpPresenter<SplashView>() {
    fun result() {
        SplashProvider(this).getResult()
    }

    fun customRes() {
        viewState.openCustom()
    }

    fun webViewRes(pars: String) {
        viewState.openWebView(pars)

    }
}