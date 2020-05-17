package dev.jamile.marvelous.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import dev.jamile.marvelous.R
import dev.jamile.marvelous.data.model.HeroResult
import kotlinx.android.synthetic.main.heroes_list_item.view.*

class HeroesListAdapter(
    private val context: Context,
    private val heroes: List<HeroResult>
) : RecyclerView.Adapter<HeroesListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.heroes_list_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hero = heroes[position]
        holder.let {
            holder.bind(hero)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: HeroResult) {
            itemView.apply {
                Glide.with(context).load(hero.thumbnail.path + "." + hero.thumbnail.extension)
                    .transition(withCrossFade()).into(heroImage)
                heroName?.text = hero.name
                heroDescription?.text = hero.description
            }
        }
    }
}
