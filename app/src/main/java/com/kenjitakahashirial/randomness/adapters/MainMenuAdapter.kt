package com.kenjitakahashirial.randomness.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kenjitakahashirial.randomness.utilities.MainMenuCard
import com.kenjitakahashirial.randomness.R

class MainMenuAdapter(private val mainMenuCards: Array<MainMenuCard>, val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>() {

    inner class MainMenuViewHolder(cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        val imageView: ImageView = cardView.findViewById(R.id.main_menu_card_image)
        val textView: TextView = cardView.findViewById(R.id.main_menu_card_text)

        init {
            cardView.setOnClickListener { onClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.view_main_menu_card, parent, false) as CardView
        return MainMenuViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        val card = mainMenuCards[position]

        holder.apply {
            textView.text = card.name

            imageView.apply {
                contentDescription = card.name
                setImageResource(card.imageId)
            }
        }
    }

    override fun getItemCount() = mainMenuCards.size
}