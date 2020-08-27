package testchaf.apptestest.screens.webview

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import testchaf.apptestest.screens.webview.MainFragment.Companion.mCameraPhotoPath
import testchaf.apptestest.screens.webview.MainFragment.Companion.uploadMessage
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MyWebChromeClient(val fr: Fragment) : WebChromeClient() {


//    override fun onShowFileChooser(
//        webView: WebView?,
//        filePathCallback: ValueCallback<Array<Uri>>?,
//        fileChooserParams: FileChooserParams?
//    ): Boolean {
//        if (uploadMessage != null) {
//            uploadMessage?.onReceiveValue(null)
//            uploadMessage = null
//        }
//        uploadMessage = filePathCallback
//        val intent = fileChooserParams?.createIntent()
//        try {
//            fr.startActivityForResult(intent, 100)
//        } catch (e: ActivityNotFoundException) {
//            uploadMessage = null
//            Toast.makeText(fr.requireContext(), "Cannot open file chooser", Toast.LENGTH_SHORT)
//                .show()
//            return false
//        }
//        return true
//    }

    val INPUT_FILE_REQUEST_CODE = 1;

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        if (uploadMessage != null) uploadMessage?.onReceiveValue(null)
        uploadMessage = filePathCallback
        var takePictureIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        Log.i("WEB0", "${takePictureIntent?.resolveActivity(fr.requireActivity().packageManager)}")
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
                Log.i("WEB", photoFile.toString())
                takePictureIntent?.putExtra("PhotoPath", mCameraPhotoPath)
            } catch (e: IOException) {
                Log.i("WEB", e.localizedMessage)

            }
            if (photoFile != null) {
                mCameraPhotoPath = photoFile.absolutePath.toUri()
                Log.i("WEB", "${mCameraPhotoPath.toString()} ")
                takePictureIntent?.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(photoFile)
                );
            } else {
                takePictureIntent = null
            }
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = "image/*"

        val intentArray: Array<Intent?>
        intentArray = if (takePictureIntent != null) {
            arrayOf(takePictureIntent)
        } else {
            arrayOfNulls(0)
        }

        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)

        fr.startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE)

        return true


    }


    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir: File = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        return File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
    }


}