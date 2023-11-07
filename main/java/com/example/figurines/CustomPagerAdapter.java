package com.example.figurines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> images;

    // Constructeur de la classe CustomPagerAdapter qui prend en paramètre le contexte de l'application et une liste d'images
    public CustomPagerAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.images = images;
    }

    // Retourne le nombre d'éléments dans la liste d'images
    @Override
    public int getCount() {
        return images.size();
    }


    // Méthode appelée lors de la création de chaque page du ViewPager
    // Elle crée une vue à partir du layout item_pager, ajoute l'image correspondante à l'ImageView et retourne la vue créée
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pager, container, false);

        ImageView imageView = view.findViewById(R.id.image_view);
        imageView.setImageResource(images.get(position));

        container.addView(view);
        return view;
    }


    // Méthode appelée lors de la suppression d'une page du ViewPager
    // Elle supprime la vue correspondante du container
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    // Méthode qui permet de savoir si une vue est liée à un objet
    // Ici, on vérifie si la vue passée en paramètre est bien l'objet (la page) correspondant
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
