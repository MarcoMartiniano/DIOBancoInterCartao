package br.com.marco.appbusinesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.marco.appbusinesscard.R
import br.com.marco.appbusinesscard.data.BusinessCard
import br.com.marco.appbusinesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter : ListAdapter<BusinessCard,BusinessCardAdapter.ViewHolder>(DiffCallback()){

    var listenerShare: (View) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind (item: BusinessCard){
            binding.tvNome.text = item.nome
            binding.tvTelefone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvEspecialidade.text = item.especialidade
            binding.tvSenioridade.text = item.senioridade
            binding.imgHabilidade1.setBackgroundResource(setImage(item.habilidade1));
            binding.imgHabilidade2.setBackgroundResource(setImage(item.habilidade2));
            binding.mcvContent.setCardBackgroundColor(Color.parseColor(item.fundoPersonalizado))
            binding.mcvContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }
}


class DiffCallback: DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem.id == newItem.id
}

    private fun setImage(item: Int): Int {
        when(item){
            0 -> return R.drawable.imagemjava
            1 -> return R.drawable.imagemkotlin
            2 -> return R.drawable.imagemcsharp
            3 -> return R.drawable.imagemsql
            else -> return R.drawable.imagemjava
        }

    }
