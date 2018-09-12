package mishka.mingeo.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class PermissionUtils {
    companion object {
        val REQUEST_PERMISSION_CODE = 1

        fun requestAudioPermission(activity: Activity) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_PERMISSION_CODE)

        }

        fun isAudioPermissionGranted(activity: Activity): Boolean {
            return (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
        }

        fun requestStoragePermission(activity: Activity) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_PERMISSION_CODE)

        }

        fun isStoragePermissionGranted(activity: Activity): Boolean {
            return (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        }

        fun isStoragePermissionDenied(activity: Activity): Boolean {
            return !isStoragePermissionGranted(activity)
        }

        fun isAudioPermissionDenied(activity: Activity): Boolean {
            return !isAudioPermissionGranted(activity)
        }
    }

}