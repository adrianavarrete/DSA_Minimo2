package upc.dsa.dsa_minimo2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {

    List<Element> data;
    Context context;

    public Recycler(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void addElements(List<Element> elementList){

        data.addAll(elementList);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.museo_item,viewGroup,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Recycler.ViewHolder viewHolder, int i) {
        Element element = data.get(i);
        List<String> imagenes;

        imagenes = new ArrayList<>();
        imagenes.addAll(element.getImatge());

        viewHolder.nombreMuseo.setText(element.getAdrecaNom());
        Picasso.with(context).load(imagenes.get(0)).into(viewHolder.imagenMuseo);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreMuseo;
        private ImageView imagenMuseo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreMuseo = (TextView) itemView.findViewById(R.id.nombreMuseo);
            imagenMuseo = (ImageView) itemView.findViewById(R.id.imagenMuseo);
        }
    }
}
