import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cachingproject.data.model.Cocktail
import com.example.cachingproject.databinding.ListItemCocktailBinding

class CocktailAdapter(private var items: List<Cocktail>) :
    RecyclerView.Adapter<CocktailAdapter.MyViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Cocktail>) {
        items = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: ListItemCocktailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemCocktailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cocktail = items[position]

        Glide.with(holder.itemView.context)
            .load(cocktail.image)
            .into(holder.binding.cocktailImageView)

        holder.binding.cocktailNameTextView.text = cocktail.name
//        holder.binding.cocktailCategoryTextView.text = cocktail.category

    }

    override fun getItemCount(): Int {
        return items.size
    }
}
