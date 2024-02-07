package com.banklannister.dictionaryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.banklannister.dictionaryapp.data.Meaning
import com.banklannister.dictionaryapp.databinding.DictionaryItemsBinding

class DictionaryAdapter(private var meaningList: List<Meaning>) :
    RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder>() {

    inner class DictionaryViewHolder(private val binding: DictionaryItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meaning: Meaning) {

            binding.partOfSpeechTv.text = meaning.partOfSpeech
            binding.definationTv.text = meaning.definitions.joinToString("\n\n") {
                val currentIndex = meaning.definitions.indexOf(it)
                (currentIndex + 1).toString() + ". " + it.definition
            }

            if (meaning.synonyms.isEmpty()) {
                binding.synonymTitleTv.visibility = View.GONE
                binding.synonymTv.visibility = View.GONE
            } else {
                binding.synonymTitleTv.visibility = View.VISIBLE
                binding.synonymTv.visibility = View.VISIBLE
                binding.synonymTv.text = meaning.synonyms.joinToString(", ")
            }


            if (meaning.antonyms.isEmpty()) {
                binding.antonymTitleTv.visibility = View.GONE
                binding.antonymTv.visibility = View.GONE
            } else {
                binding.antonymTitleTv.visibility = View.VISIBLE
                binding.antonymTv.visibility = View.VISIBLE
                binding.antonymTv.text = meaning.antonyms.joinToString(", ")
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        val binding =
            DictionaryItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DictionaryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }

    fun updateWord(newWord: List<Meaning>) {
        meaningList = newWord
        notifyDataSetChanged()

    }
}