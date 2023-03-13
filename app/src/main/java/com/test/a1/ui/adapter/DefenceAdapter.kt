package com.test.a1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.a1.data.network.response.DefenceInfoResponse
import com.test.a1.databinding.AttackDefenceItemBinding
import com.test.a1.databinding.DeffenceTitleItemBinding
import com.test.a1.ui.entities.AttackDefence
import com.test.a1.ui.entities.DefenceTitle


class DefenceAdapter(
    private val inflater: LayoutInflater,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<AttackDefence>()

    fun updateList(newList: List<AttackDefence>) {
        items.clear()
        items.addAll(newList)

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is DefenceTitle -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> DefenceTitleViewHolder(
                DeffenceTitleItemBinding.inflate(inflater,parent,false)
            )
            else -> DefenceViewHolder(
                AttackDefenceItemBinding.inflate(inflater,parent,false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val model = items[position]){
            is DefenceTitle -> (holder as DefenceTitleViewHolder)
            is DefenceInfoResponse -> (holder as DefenceViewHolder).bindDefence(model)
        }
    }

    override fun getItemCount(): Int = items.size

}

class DefenceTitleViewHolder(
    private val binding: DeffenceTitleItemBinding
): RecyclerView.ViewHolder(binding.root)

class DefenceViewHolder(
    private val binding: AttackDefenceItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindDefence(defence: DefenceInfoResponse){
        with(binding){
            tvCommand.text = defence.command
            tvTournament.text = defence.tournament
            tvKicks.text = defence.kicksZI.toString()
            tvAddInfo.text = defence.interceptionsZI.toString()
        }
    }
}
