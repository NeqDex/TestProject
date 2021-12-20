package com.dexmemore.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dexmemore.testproject.databinding.ActivityMainBinding
import com.dexmemore.testproject.databinding.ListItemBinding
import com.dexmemore.testproject.repository.Repository
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var textView:TextView
    private var check : String = ""
    private val listOfNumbers: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textView = findViewById(R.id.text_view)

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



}
