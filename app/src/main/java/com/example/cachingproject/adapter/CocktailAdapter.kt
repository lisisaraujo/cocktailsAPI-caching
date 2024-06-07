import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.example.cachingproject.R
import com.example.cachingproject.data.model.Cocktail
import com.example.cachingproject.databinding.ListItemCocktailBinding
import com.example.cachingproject.ui.HomeFragmentDirections

class CocktailAdapter(
    private var items: List<Cocktail>,
    private val itemClickedCallback: (Cocktail) -> Unit
) :
    RecyclerView.Adapter<CocktailAdapter.MyViewHolder>() {

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

        holder.binding.listIV.load(cocktail.image){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
        }

        holder.binding.listNameTV.text = cocktail.name
        holder.binding.listCategoryTV.text = cocktail.category
        holder.binding.listAlcoholicTV.text = cocktail.alcoholic

        holder.binding.listCV.setOnClickListener {
            itemClickedCallback(cocktail)
            Log.d("ClickedCocktail", cocktail.name.toString())
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(cocktail.id))
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}
