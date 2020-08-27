package testchaf.apptestest.screens.splash

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SplashView: MvpView {
    fun openWebView(url: String)
    fun openCustom()
}