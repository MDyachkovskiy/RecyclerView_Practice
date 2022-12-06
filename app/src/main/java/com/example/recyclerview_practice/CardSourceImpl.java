package com.example.recyclerview_practice;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class CardSourceImpl implements CardSource {

    private List<CardData> dataSource;
    private Resources resources;

    public CardSourceImpl(Resources resources){
        this.resources = resources;
        dataSource = new ArrayList<>(7);

    }

    public CardSourceImpl init() {

        String[] titles = resources.getStringArray(R.array.titles);
        String[] description = resources.getStringArray(R.array.decscriptions);
        int[] pictures = getImageArray();

        for (int i = 0; i < dataSource.size(); i++) {
            dataSource.add(new CardData(titles[i], description[i], pictures[i], false));
        }

        return this;
    }


    private int[] getImageArray(){
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int length = pictures.length();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = pictures.getResourceId(i,0);
        }
        return answer;
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }
}
