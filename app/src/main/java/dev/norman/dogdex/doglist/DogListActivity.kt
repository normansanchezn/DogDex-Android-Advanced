package dev.norman.dogdex.doglist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.norman.dogdex.api.ApiResponseStatus.Error
import dev.norman.dogdex.api.ApiResponseStatus.Loading
import dev.norman.dogdex.api.ApiResponseStatus.Success
import dev.norman.dogdex.databinding.ActivityDogListBinding
import dev.norman.dogdex.dogdetail.DogDetailActivity
import dev.norman.dogdex.dogdetail.DogDetailActivity.Companion.DOG_KEY

class DogListActivity : AppCompatActivity() {

    private val dogListViewModel: DogListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler = binding.dogRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = DogAdapter()
        adapter.setOnItemClickListener {
            val intent = Intent(this, DogDetailActivity::class.java)
            intent.apply {
                putExtra(DOG_KEY, it)
            }
            startActivity(intent)
        }
        recycler.adapter = adapter

        dogListViewModel.dogList.observe(this) { dogList ->
            adapter.submitList(dogList)
        }

        dogListViewModel.status.observe(this) { status ->
            when(status) {
                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, status.messageId, Toast.LENGTH_SHORT).show()
                }
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Estatus desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}