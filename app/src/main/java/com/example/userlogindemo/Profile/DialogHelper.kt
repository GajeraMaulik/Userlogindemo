package com.example.userlogindemo.Profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.userlogindemo.Profile.ImagePicker.Companion.RESULT_CODE
import com.example.userlogindemo.R
import kotlinx.android.synthetic.main.dialog_choose_app.*


internal object DialogHelper:Activity() {

    fun showChooseAppDialog(context: Context, listener: ResultListener<ImageProvider>) {
        val layoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(R.layout.dialog_choose_app, null)


        val galleryPick=view.findViewById<LinearLayout>(R.id.galleryPick)
        val cameraPick = view.findViewById<LinearLayout>(R.id.cameraPick)
        val dialog = AlertDialog.Builder(context)
            .setTitle(R.string.title_choose_image_provider)
            .setView(view)
            .setOnCancelListener {
                listener.onResult(null)
            }
            .setNegativeButton(R.string.action_cancel) { _, _ ->
                listener.onResult(null)
            }
            .show()

        // Handle Camera option click
        cameraPick.setOnClickListener {
            listener.onResult(ImageProvider.CAMERA)
            dialog.dismiss()
        }

        // Handle Gallery option click
        galleryPick.setOnClickListener {


            listener.onResult(ImageProvider.GALLERY)
            dialog.dismiss()
        }
    }
}

