package testchaf.apptestest.screens.webview

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import testchaf.apptestest.R


class MainFragment : Fragment(R.layout.fragment_main) {
    private var mCM: String? = null
    private var mUM: ValueCallback<Uri>? = null
    private var mUMA: ValueCallback<Array<Uri>>? = null
    private var FCR = 1
    private var FILECHOOSER_RESULTCODE = 1


    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val site = arguments?.getString("URL")
        webview.settings.javaScriptEnabled = true
        webview.settings.allowFileAccess = true
        webview.settings.allowContentAccess = true
        webview.webChromeClient = MyWebChromeClient(this)

        webview.loadUrl(site!!)

    }

    val REQUEST_SELECT_FILE = 100

    companion object {
        var uploadMessage: ValueCallback<Array<Uri>>? = null
        var mCameraPhotoPath: Uri? = null

    }

    val INPUT_FILE_REQUEST_CODE = 1;

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (uploadMessage == null) return
            uploadMessage?.onReceiveValue(
                WebChromeClient.FileChooserParams.parseResult(
                    resultCode,
                    data
                )
            )
            uploadMessage = null
        }



    }


}