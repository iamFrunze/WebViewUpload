package testchaf.apptestest.screens.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import testchaf.apptestest.R
import testchaf.apptestest.screens.second.Main1Fragment


class SplashFragment : MvpAppCompatFragment(R.layout.fragment_splash), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.result()
    }

    override fun openWebView(url: String) {
        val bundle = Bundle()
        bundle.putString("URL", url)
        Thread.sleep(1000)
//        val intent = Intent(requireContext(), Main1Fragment::class.java)
//        intent.putExtra("URL", url)
//        startActivity(intent)
        findNavController().navigate(R.id.main1Fragment)
    }

    override fun openCustom() {
        Thread.sleep(2000)
        findNavController().navigate(R.id.authFragment)

    }


}