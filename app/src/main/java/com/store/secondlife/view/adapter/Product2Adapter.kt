package com.store.secondlife.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.store.secondlife.R
import com.store.secondlife.model.Producto

class Product2Adapter (val cat:String, val productListener:ProductListener):
    RecyclerView.Adapter<Product2Adapter.ViewHoldel>() {

    private var listProduct=ArrayList<Producto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHoldel(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_product_2,
        parent, false))

    override fun getItemCount()=listProduct.size

    //dar valores a los componentes
    override fun onBindViewHolder(holder: ViewHoldel, position: Int) {
        val producto=listProduct[position] as Producto

        Glide.with(holder.itemView.context)
            .load(producto.imagen)
            .apply(RequestOptions.fitCenterTransform())
            .into(holder.ivimagen)
        holder.tvcategoria.text=cat
        holder.tvmarca.text=producto.marca
        holder.tvprecio.text= "S/. "+producto.precio.toString()
        holder.tvnombre.text=producto.modelo
        holder.tvdescripcion.text=producto.descripcion
        holder.itemView.setOnClickListener {
            productListener.onProductClicked(producto, position)
        }

    }

    //actualizar los datos
    fun updateData(data: List<Producto>){
        listProduct.clear()
        listProduct.addAll(data)
        notifyDataSetChanged()
    }

    //obtener datos del item
    class ViewHoldel(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivimagen=itemView.findViewById<ImageView>(R.id.iv_imagen)
        val tvcategoria=itemView.findViewById<TextView>(R.id.tv_categoria)
        val tvnombre=itemView.findViewById<TextView>(R.id.tv_nombre)
        val tvmarca=itemView.findViewById<TextView>(R.id.tv_marca)
        val tvprecio=itemView.findViewById<TextView>(R.id.tv_precio)
        val tvdescripcion=itemView.findViewById<TextView>(R.id.tv_descripcion)
    }
}