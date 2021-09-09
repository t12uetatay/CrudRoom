package id.t12uetatay.room.view.adapter

import android.animation.ObjectAnimator

import com.github.aakira.expandablelayout.ExpandableLinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.github.aakira.expandablelayout.ExpandableLayout

import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import android.util.SparseBooleanArray
import android.view.View
import android.widget.ImageView
import com.github.aakira.expandablelayout.Utils
import id.t12uetatay.room.R
import id.t12uetatay.room.model.Notes


class NoteAdapter(context: Context, listener: AdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mContext: Context
    private var list: List<Notes>? = null
    private val listener: AdapterListener
    private val expandState = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        view = LayoutInflater.from(parent.context).inflate(R.layout.row_notes, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val itemViewHolder = holder as ItemViewHolder
        val notes: Notes = list!![position]
        itemViewHolder.note_title.text = notes.getTitle()
        itemViewHolder.note_description.text = notes.getDescription()

        itemViewHolder.expandableLayout.setInRecyclerView(true)
        itemViewHolder.expandableLayout.isExpanded = expandState[position]
        itemViewHolder.expandableLayout.setListener(object : ExpandableLayoutListenerAdapter() {
            override fun onPreOpen() {
                createRotateAnimator(itemViewHolder.action_more, 0f, 180f).start()
                expandState.put(position, true)
            }

            override fun onPreClose() {
                createRotateAnimator(itemViewHolder.action_more, 180f, 0f).start()
                expandState.put(position, false)
            }
        })
        itemViewHolder.action_more.setRotation(if (expandState[position]) 180f else 0f)
        itemViewHolder.action_more.setOnClickListener { View->
            onClickButton(itemViewHolder.expandableLayout)
        }
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    fun setDataList(dataList: List<Notes>?) {
        list = dataList
        notifyDataSetChanged()
    }

    private fun onClickButton(expandableLayout: ExpandableLayout) {
        expandableLayout.toggle()
    }

    interface AdapterListener {
        fun onClick(note: Notes?)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var note_title: TextView
        var note_create: TextView
        var note_description: TextView
        var action_more: ImageView
        var expandableLayout: ExpandableLinearLayout

        init {
            note_title = itemView.findViewById(R.id.note_title)
            action_more = itemView.findViewById(R.id.action_more)
            note_create = itemView.findViewById(R.id.note_create)
            note_description = itemView.findViewById(R.id.note_description)
            expandableLayout = itemView.findViewById(R.id.expandableLayout)
        }
    }

    fun createRotateAnimator(target: View?, from: Float, to: Float): ObjectAnimator {
        val animator = ObjectAnimator.ofFloat(target, "rotation", from, to)
        animator.duration = 300
        animator.interpolator = Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR)
        return animator
    }

    init {
        mContext = context
        this.listener = listener
        for (i in 0 until itemCount) {
            expandState.append(i, false)
        }
    }
}