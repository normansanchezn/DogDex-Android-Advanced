package dev.norman.dogdex.dogdetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import dev.norman.dogdex.model.Dog
import dev.norman.dogdex.R
import dev.norman.dogdex.databinding.ActivityDogDetailBinding

class DogDetailActivity : AppCompatActivity() {
    companion object {
        const val DOG_KEY = "dog"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dog = intent?.extras?.getParcelable<Dog>(DOG_KEY)

        if (dog == null) {
            Toast.makeText(this, getString(R.string.error_showing_dog_not_found), Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        with(binding) {
            dogIndex.text = getString(R.string.dog_index_format, dog.index)
            lifeExpectancy.text = getString(R.string.dog_life_expectancy_format, dog.lifeExpectancy)
            this.dog = dog
            dogImage.load(dog.imageUrl)

            closeButton.setOnClickListener {
                finish()
            }
        }
    }
}