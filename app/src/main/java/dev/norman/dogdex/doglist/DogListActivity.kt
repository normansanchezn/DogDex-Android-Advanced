package dev.norman.dogdex.doglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.norman.dogdex.databinding.ActivityDogListBinding
import dev.norman.dogdex.getFakeDogs

class DogListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogList = getFakeDogs()

        val recycler = binding.dogRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = DogAdapter()
        recycler.adapter = adapter
        adapter.submitList(dogList)

    }
}