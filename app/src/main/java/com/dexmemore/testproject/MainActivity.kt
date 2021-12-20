package com.dexmemore.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dexmemore.testproject.adapters.NumbAdapter
import com.dexmemore.testproject.databinding.ActivityMainBinding
import com.dexmemore.testproject.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var textView: TextView
    private val listOfNumbers: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textView = findViewById(R.id.text_view)

        setupGridView()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Response", response.body()?.numbers.toString())
                textView.text = response.body()?.numbers.toString()
                for (numb in response.body()!!.numbers) {
                    listOfNumbers.add(numb.toString().substringAfter("=").substringBefore(")"))
                    Log.d("Numb", numb.toString().substringAfter("=").substringBefore(")"))
                    Log.d("list", listOfNumbers.toString())
                }
            } else {
                Log.d("Response", response.errorBody().toString())
                textView.text = response.code().toString()
            }
        })

    }
    private fun setupGridView() {
        val adapter = NumbAdapter(this, R.layout.list_item, listOfNumbers)
        binding.gridView.adapter = adapter

        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                Toast.makeText(
                    this@MainActivity, " Clicked Position: " + (position + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


}
