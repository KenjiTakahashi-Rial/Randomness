package com.kenjitakahashirial.randomness.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.kenjitakahashirial.randomness.R
import com.kenjitakahashirial.randomness.utilities.MainMenuCard

class MainMenuAdapter(
    private val mainMenuCards: Array<MainMenuCard>,
    val onClick: (Int) -> Unit,
    private val colorRange: IntRange? = null
) : RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>() {

    inner class MainMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.main_menu_card)
        val imageView: ImageView = itemView.findViewById(R.id.main_menu_card_image)
        val textView: TextView = itemView.findViewById(R.id.main_menu_card_text)
        val topShadow: View = itemView.findViewById(R.id.main_menu_card_above_shadow)
        val bottomShadow: View = itemView.findViewById(R.id.main_menu_card_below_shadow)

        init {
            cardView.setOnClickListener { onClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_menu_card, parent, false)
        return MainMenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        val card = mainMenuCards[position]

        holder.apply {
            with(colorRange) {
                if (this != null) {
                    val ratio = position.toFloat() / (mainMenuCards.size - 1)
                    val nextColor = ColorUtils.blendARGB(first, last, ratio)
                    cardView.setCardBackgroundColor(nextColor)
                }
            }

            textView.text = card.name

            imageView.apply {
                contentDescription = card.name
                setImageResource(card.imageId)
            }

            topShadow.visibility = if (adapterPosition == 0) View.GONE else View.VISIBLE
            bottomShadow.visibility = if (adapterPosition == mainMenuCards.lastIndex) View.VISIBLE else View.GONE
        }
    }

    override fun getItemCount() = mainMenuCards.size
}