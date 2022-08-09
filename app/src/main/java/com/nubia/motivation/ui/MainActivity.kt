package com.nubia.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.nubia.motivation.infrastructure.MotivationConstants
import com.nubia.motivation.R
import com.nubia.motivation.data.Mock
import com.nubia.motivation.infrastructure.SecurityPreferences
import com.nubia.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        handleUserName()
        handleFilter(categoryId)
        handleNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in
            listOf(R.id.image_all, R.id.image_sunny, R.id.image_happy)
        ) {
            handleFilter(view.id)
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(
            MotivationConstants.KEY.USER_NAME
        )
        binding.textUserName.text = "Olá, ${name}!"
    }

    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(
            ContextCompat.getColor(this, R.color.dark_purple)
        )
        binding.imageSunny.setColorFilter(
            ContextCompat.getColor(this, R.color.dark_purple)
        )
        binding.imageHappy.setColorFilter(
            ContextCompat.getColor(this, R.color.dark_purple)
        )

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }

    }

    private fun handleNextPhrase() {
        val phrase = Mock().getPhrase(categoryId)
        binding.textPhrase.text = phrase
    }
}
