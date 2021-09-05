package com.example.abdullaev

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.abdullaev.Constants.TAG
import com.example.abdullaev.databinding.ActivityMainBinding
import com.example.abdullaev.networkutils.pojo.DeveloperInfo
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val developersId: ArrayList<Int> = arrayListOf()
    private var itemPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)

        viewModel.developerId.observe(this) { id ->
            developersId.add(id)
            setData(id)
            Log.d(TAG, "changes")
        }

        viewModel.connectionResponce.observe(this) {
            if (it == false) {
                setViewWithErrorResponce()
            }
        }

        binding.btnBack.setOnClickListener {
            itemPosition--
            if (itemPosition == 0) {
                binding.btnBack.visibility = View.GONE
            }
            setData(developersId[itemPosition])
        }

        binding.btnNext.setOnClickListener {
            itemPosition++
            if (itemPosition > 0) {
                binding.btnBack.visibility = View.VISIBLE
            }
            if (itemPosition == developersId.size) {
                viewModel.loadData()
            } else {
                setData(developersId[itemPosition])
            }
        }

        binding.lErrorConnection.btnRepeat.setOnClickListener {
            setViewWithCorrectResponce()
            viewModel.loadData()
        }
    }

    private fun setData(id: Int) {
        val developerInfo: DeveloperInfo = viewModel.getDeveloperInfoById(id)
        val description: String? = developerInfo.description
        val gifURL: String? = developerInfo.gifURL

        binding.lDevContent.tvDescription.text = description

        viewModel.setGif(gifURL, binding.lDevContent.ivGif, binding.lDevContent.progressBar)
    }

    private fun setViewWithCorrectResponce() {
        binding.lErrorConnection.root.visibility = View.GONE
        binding.lDevContent.root.visibility = View.VISIBLE
        binding.cLayoutButtons.visibility = View.VISIBLE
    }

    private fun setViewWithErrorResponce() {
        binding.lErrorConnection.root.visibility = View.VISIBLE
        binding.lDevContent.root.visibility = View.GONE
        binding.cLayoutButtons.visibility = View.GONE
    }
}