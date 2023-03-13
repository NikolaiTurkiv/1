package com.test.a1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.a1.data.network.response.AttackInfoResponse
import com.test.a1.databinding.AttackDefenceItemBinding
import com.test.a1.databinding.AttackTitleItemBinding
import com.test.a1.ui.entities.AttackDefence
import com.test.a1.ui.entities.AttackTitle

class AttackAdapter(
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
            is AttackTitle -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            0 -> AttackTitleViewHolder(
                AttackTitleItemBinding.inflate(inflater,parent,false)
            )
            else -> AttackViewHolder(
                AttackDefenceItemBinding.inflate(inflater,parent,false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(val model = items[position]){
            is AttackTitle -> (holder as AttackTitleViewHolder)
            is AttackInfoResponse -> (holder as AttackViewHolder).bindAttack(model)
        }
    }

    override fun getItemCount(): Int = items.size
}

class AttackTitleViewHolder(
    private val binding: AttackTitleItemBinding
): ViewHolder(binding.root)



class AttackViewHolder(
    private val binding: AttackDefenceItemBinding
) : ViewHolder(binding.root) {

    fun bindAttack(attack: AttackInfoResponse) {
        with(binding) {
            tvCommand.text = attack.command
            tvTournament.text = attack.tournament
            tvKicks.text = attack.kickZI.toString()
            tvAddInfo.text = attack.kickVSTV.toString()
        }
    }

}
