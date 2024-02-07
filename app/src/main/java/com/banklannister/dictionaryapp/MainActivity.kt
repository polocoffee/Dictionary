package com.banklannister.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.banklannister.dictionaryapp.adapter.DictionaryAdapter
import com.banklannister.dictionaryapp.data.Dictionary
import com.banklannister.dictionaryapp.databinding.ActivityMainBinding
import com.banklannister.dictionaryapp.network.Instance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var dictionaryAdapter: DictionaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchBtn.setOnClickListener {
            val word = binding.searchInput.text.toString()
            getMeaning(word)
        }


        dictionaryAdapter = DictionaryAdapter(emptyList())
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = dictionaryAdapter
    }

    private fun getMeaning(word: String) {
        settingProgress(true)
        GlobalScope.launch {
            try {
                val response = Instance.api.getMeaning(word)
                if (response.body() == null) {
                    throw (Exception())
                }
                runOnUiThread {
                    settingProgress(false)
                    response.body()?.first()?.let {
                        setUi(it)
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    settingProgress(false)
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }
    }

    private fun setUi(response: Dictionary) {
        binding.wordTv.text = response.word
        binding.phoneticTv.text = response.phonetic
        dictionaryAdapter.updateWord(response.meanings)
    }

    private fun settingProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.searchBtn.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.searchBtn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}