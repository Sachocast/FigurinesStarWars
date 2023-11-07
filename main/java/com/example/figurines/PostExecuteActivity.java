package com.example.figurines;

import java.util.List;

public interface PostExecuteActivity<T> {
    void onPostExecuteCharacter(List<T> itemList);
    void runOnUiThread( Runnable runable);
}
