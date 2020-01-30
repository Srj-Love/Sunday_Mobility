package com.example.sundaymobility.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.example.sundaymobility.R
import com.example.sundaymobility.utils.Constants.Companion.REQUEST_SUCCESS
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_photo_view.*


class PhotoViewActivity : AppCompatActivity() {


    private var url: String? = null
    private var position: Int? = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)


        url = intent.getStringExtra("photo_url")
        position = intent.getIntExtra("photo_position", -1)


        iv_phooto_view.setOnClickListener { deleteDialog() }


        Handler().postDelayed({
            initViews()
        }, 200)
    }

    private fun initViews() {


        Picasso.with(this@PhotoViewActivity)
            .load(url)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                    iv_photo_view.setImageBitmap(bitmap)
                    Palette.from(bitmap).generate(object : Palette.PaletteAsyncListener {

                        override fun onGenerated(palette: Palette?) {
                            val darkMutedSwatch = palette?.darkMutedSwatch
                                ?: palette?.darkVibrantSwatch ?: return

                            val lightMutedSwatch = palette?.lightMutedSwatch
                                ?: palette?.lightVibrantSwatch ?: return


                            ll_gallery_background.setBackgroundColor(darkMutedSwatch.rgb)
                            iv_phooto_view.setColorFilter(lightMutedSwatch.rgb)

                        }
                    })
                }

                override fun onBitmapFailed(errorDrawable: Drawable) {
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable) {
                }

            })


    }

    private fun deleteDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.warning))
            .setMessage(getString(R.string.delete_user))
            .setPositiveButton("delete") { dialog, _ ->
                dialog?.cancel()
                val i = Intent()
                i.putExtra("photo_position", position)
                setResult(REQUEST_SUCCESS, i)
                finish()
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog?.cancel()
            }
            .show()
    }

}


