package com.testytest.cr_lifehack_1.base;


import java.util.List;

public interface RecyclerViewAdapter<T> {

    void addAll(List<T> items);

    void replaceAll(List<T> items);

    T getItem(int position);

    List<T> getItems();
}
