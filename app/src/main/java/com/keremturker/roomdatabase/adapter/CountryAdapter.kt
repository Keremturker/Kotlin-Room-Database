package com.keremturker.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.keremturker.roomdatabase.R
import com.keremturker.roomdatabase.model.Country
import com.keremturker.roomdatabase.view.MainActivity

class CountryAdapter(val list: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.country_line, parent, false)

        return ViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txt_name.text = list[position].name
        holder.txt_region.text = list[position].region
        holder.txt_capital.text = list[position].capital
        holder.txt_language.text = list[position].language


        holder.itemView.setOnClickListener {

            MainActivity.uuid = list[position].uuid
            MainActivity.edt_capital.setText(list[position].capital)
            MainActivity.edt_language.setText(list[position].language)
            MainActivity.edt_region.setText(list[position].region)
            MainActivity.edt_country.setText(list[position].name)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txt_name = view.findViewById<TextView>(R.id.txt_name)
        val txt_region = view.findViewById<TextView>(R.id.txt_region)
        val txt_capital = view.findViewById<TextView>(R.id.txt_capital)
        val txt_language = view.findViewById<TextView>(R.id.txt_language)
    }

}