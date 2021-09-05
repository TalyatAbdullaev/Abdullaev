package com.example.abdullaev

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.abdullaev.database.DevelopersDatabase
import com.example.abdullaev.networkutils.api.ApiFactory
import com.example.abdullaev.networkutils.pojo.DeveloperInfo
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db: DevelopersDatabase =
        DevelopersDatabase.getInstance(application.applicationContext)
    var developerId: MutableLiveData<Int> = MutableLiveData()
    val connectionResponce = MutableLiveData<Boolean>()

    init {
        loadData()
    }

    fun loadData() {
        ApiFactory.apiService.getData()
            .subscribeOn(Schedulers.io())
            .subscribe({ developer ->
                Log.d(Constants.TAG, developer.id.toString())
                db.developersDao().insertDeveloper(developer)
                developerId.postValue(developer.id)
                connectionResponce.postValue(true)
            }, {
                connectionResponce.postValue(false)
                Log.d(Constants.TAG, it.message.toString())
            })
    }

    fun getDeveloperInfoById(id: Int): DeveloperInfo {
        val developerInfo = db.developersDao().getDeveloperInfoById(id)
        Log.d(Constants.TAG, developerInfo.toString())
        return developerInfo
    }

    fun setGif(gifURL: String?, imageView: ImageView, progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        Glide.with(getApplication<Application>().applicationContext)
            .asGif()
            .load(gifURL)
            .addListener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d(Constants.TAG, "Gif false")
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d(Constants.TAG, "Gif true")
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(imageView)
    }
}