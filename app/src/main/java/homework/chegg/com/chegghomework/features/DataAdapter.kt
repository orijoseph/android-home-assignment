package homework.chegg.com.chegghomework.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import homework.chegg.com.chegghomework.R
import homework.chegg.com.chegghomework.data.DetailsTiDisplay
import homework.chegg.com.chegghomework.data.IDetailsTiDisplay

class DataAdapter : ListAdapter<IDetailsTiDisplay, DataAdapter.ViewHolder>(CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position).convertToDisplayObject())
    }

    class ViewHolder(rowLayout: View) : RecyclerView.ViewHolder(rowLayout) {

        private val glideRequest = Glide.with(rowLayout.context)

        private val icon = rowLayout.findViewById<ImageView>(R.id.imageView_card_item)
        private val title = rowLayout.findViewById<TextView>(R.id.textView_card_item_title)
        private val subTitle = rowLayout.findViewById<TextView>(R.id.textView_card_item_subtitle)

        fun bind(data: DetailsTiDisplay) {
            glideRequest.load(data.imageUrl).into(icon)
            title.text = data.title
            subTitle.text = data.subtitle
        }
    }

    companion object {
        private val CALLBACK = object : DiffUtil.ItemCallback<IDetailsTiDisplay>() {
            override fun areItemsTheSame(oldItem: IDetailsTiDisplay, newItem: IDetailsTiDisplay): Boolean {
                return areContentsTheSame(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem: IDetailsTiDisplay, newItem: IDetailsTiDisplay): Boolean {
                return oldItem.convertToDisplayObject().title == newItem.convertToDisplayObject().title &&
                        oldItem.convertToDisplayObject().subtitle == newItem.convertToDisplayObject().subtitle
            }
        }
    }
}