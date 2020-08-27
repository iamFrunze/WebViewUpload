package testchaf.apptestest.screens.webview

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import testchaf.apptestest.screens.webview.MainFragment.Companion.mCameraPhotoPath
import testchaf.apptestest.screens.webview.MainFragment.Companion.uploadMessage
import java.io.File


class WebChrome(val fr: Fragment) : WebChromeClient() {
    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        openFileChooserImplForAndroid5(filePathCallback)
        return true
    }

    private fun openFileChooserImplForAndroid5(uploadMsg: ValueCallback<Array<Uri>>?) {
        uploadMessage = uploadMsg
        try {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            val externalDataDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM
            );
            val cameraDataDir = File(
                externalDataDir.getAbsolutePath() +
                        File.separator + "browser-photos"
            );
            cameraDataDir.mkdirs();
            var mCameraFilePath = cameraDataDir.getAbsolutePath() + File.separator +
                    System.currentTimeMillis() + ".jpg";
            mCameraPhotoPath = Uri.fromFile(File(mCameraFilePath));

            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCameraPhotoPath);

            val i = Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            val chooserIntent = Intent.createChooser(i, "Image Chooser");
            chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS,
                cameraIntent
            );

            fr.startActivityForResult(chooserIntent, 2888);
        } catch (e: Exception) {
            Toast.makeText(fr.requireContext(), "Camera Exception:" + e, Toast.LENGTH_LONG).show();
        }
    }
}
