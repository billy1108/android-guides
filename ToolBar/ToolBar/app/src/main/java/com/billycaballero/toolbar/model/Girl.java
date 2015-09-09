package com.billycaballero.toolbar.model;

import com.billycaballero.toolbar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by billycaballero on 9/9/15.
 */
public class Girl {

    private String name;
    private int idDrawable;

    public Girl(String name, int idDrawable) {
        this.name = name;
        this.idDrawable = idDrawable;
    }

    public Girl(String name) {
        this.name = name;
        this.idDrawable = getRandomGirlDrawable();
    }

    public String getName() {
        return name;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    /**
     * Asigna un drawable en forma aleatoria
     *
     * @return id del drawable
     */
    private int getRandomGirlDrawable() {
        Random rnd = new Random();
        switch (rnd.nextInt(8)) {
            default:
            case 0:
                return R.drawable.girl1;
            case 1:
                return R.drawable.girl2;
            case 2:
                return R.drawable.girl3;
            case 3:
                return R.drawable.girl4;
            case 4:
                return R.drawable.girl5;
            case 5:
                return R.drawable.girl6;
            case 6:
                return R.drawable.girl7;
            case 7:
                return R.drawable.girl8;
        }
    }


    public static final String[] girlsNamesDummy = {
            "Catherine", "Evelyn", "Phyllis", "Beverly", "Michelle",
            "Denise", "Virginia", "Ruth", "Barbara", "Amanda", "Anna",
            "Catherine", "Melissa", "Sandra", "Julia", "Paula", "Kimberly",
            "Diane", "Betty", "Sharon", "Ruby", "Barbara", "Ann", "Phyllis",
            "Linda", "Marie", "Deborah", "Sara", "Gloria", "Karen", "Patricia",
            "Donna", "Catherine", "Louise", "Catherine", "Joyce", "Katherine",
            "Janice", "Cheryl", "Lisa", "Andrea", "Elizabeth", "Nicole",
            "Cynthia", "Angela", "Donna", "Deborah", "Sandra", "Cheryl", "Jane"
    };

    /**
     * Genera una lista de objetos {@link Girl} con un tamaño determinado
     *
     * @param count Tamaño
     * @return Lista aleatoria
     */
    public static List<Girl> randomList(int count) {
        Random random = new Random();
        List<Girl> items = new ArrayList<>();

        // Restricción de tamaño
        count = Math.min(count, girlsNamesDummy.length);

        while (items.size() < count) {
            items.add(new Girl(girlsNamesDummy[random.nextInt(girlsNamesDummy.length)]));
        }

        return new ArrayList<>(items);
    }
}
