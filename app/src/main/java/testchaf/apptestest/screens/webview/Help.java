package testchaf.apptestest.screens.webview;

import android.net.Uri;
import android.os.Environment;
import android.webkit.ValueCallback;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Help {
    static ValueCallback<Uri> mUploadMessage;
    static Uri mCapturedImageURI = null;
    static ValueCallback<Uri[]> mFilePathCallback;
    static String mCameraPhotoPath;
    static final int INPUT_FILE_REQUEST_CODE = 1;
    static final int FILECHOOSER_RESULTCODE = 1;



    static File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return imageFile;
    }
}
