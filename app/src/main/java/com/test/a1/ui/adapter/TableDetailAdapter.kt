package com.test.a1.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
 import com.test.a1.databinding.TableItemBinding
import com.test.a1.databinding.TableItemTitleBinding
import com.test.a1.domain.CommandDetailInfo
import com.test.a1.ui.entities.TableDetail
import com.test.a1.ui.entities.TableTitle

class TableDetailAdapter(
    private val inflater: LayoutInflater,
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<TableDetail>()

    fun updateList(newList: List<TableDetail>) {
        items.clear()
        items.addAll(newList)

        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is TableTitle -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            0 -> TableDetailTitleViewHolder(
                TableItemTitleBinding.inflate(inflater,parent,false)
            )
            else -> TableDetailAdapterViewHolder(
                TableItemBinding.inflate(inflater,parent,false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(val model = items[position]){
            is TableTitle -> (holder as TableDetailTitleViewHolder)
            is CommandDetailInfo -> (holder as TableDetailAdapterViewHolder).bind(model,position)
        }
     }

    override fun getItemCount(): Int = items.size

}

class TableDetailAdapterViewHolder(
    private val binding: TableItemBinding
): ViewHolder(binding.root){
    fun bind(commandDetailInfo: CommandDetailInfo,position: Int){
        with(binding){
            if(position % 2 == 0)
            container.setBackgroundColor(Color.parseColor("#BEBEBE"))

            tvNumber.text = position.toString()
            tvCommand.text = commandDetailInfo.command
            tvI.text = commandDetailInfo.games.toString()
            tvV.text = commandDetailInfo.V.toString()
            tvN.text = commandDetailInfo.N.toString()
            tvP.text = commandDetailInfo.P.toString()
            tvBalls.text = commandDetailInfo.balls
            tvO.text = commandDetailInfo.point.toString()
        }
    }
}

class TableDetailTitleViewHolder(
    private val binding: TableItemTitleBinding
): ViewHolder(binding.root)