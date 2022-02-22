package com.idn.notesapp.fragment.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idn.notesapp.data.model.NoteData
import com.idn.notesapp.databinding.RowLayoutItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<NoteData>()

    class MyViewHolder(val binding: RowLayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(noteData: NoteData) {
            binding.noteData = noteData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : MyViewHolder {
                // val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = RowLayoutItemBinding.inflate(
//                    LayoutInflater.from(parent.context), parent, false
//                )
                return MyViewHolder(RowLayoutItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder.from(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { holder.bind(dataList[position]) }

    override fun getItemCount(): Int = dataList.size

    fun setData(noteData: List<NoteData>) {
//        val noteDiffUtil = NoteDiffUtil(dataList, noteData)
//        val noteDiffResult = DiffUtil.calculateDiff(noteDiffUtil)
        val noteDiffResult = DiffUtil.calculateDiff(NoteDiffUtil(dataList, noteData))
        this.dataList = noteData
        noteDiffResult.dispatchUpdatesTo(this)
//        DiffUtil.calculateDiff(NoteDiffUtil(dataList, noteData))
//            .dispatchUpdatesTo(this) // kode kek gini mah ga bisa
    }

}