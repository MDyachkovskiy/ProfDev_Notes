package gb.com.profdev_notes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gb.com.profdev_notes.databinding.ItemNoteBinding
import gb.com.profdev_notes.model.datasource.Note

class NoteAdapter(
    private val noteList: List<Note>,
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val notes = mutableListOf<Note>()

    init {
        notes.addAll(noteList)
    }

    // Method #1
    class ViewHolder(
        private var itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note){
            val binding = ItemNoteBinding.bind(itemView)
            with(binding) {
                noteTitle.text = note.title
                noteDescription.text = note.description
                noteTag.text = note.tag

                root.setOnClickListener {
                    interaction?.onItemSelected(adapterPosition, note)
                }
            }
        }
    }

    //Method #2
    interface Interaction {
        fun onItemSelected(position: Int, item: Note)
    }

    // Method #3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)

        return ViewHolder(binding.root, interaction)
    }

    // Method #4
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    //Method #5
    override fun getItemCount() = notes.size

    // Method #6
    fun swap(notes: List<Note>) {
        val diffCallback = DiffCallback(this.notes, notes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.notes.clear()
        this.notes.addAll(notes)
        diffResult.dispatchUpdatesTo(this)
    }
}