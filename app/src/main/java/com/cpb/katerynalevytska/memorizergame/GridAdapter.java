package com.cpb.katerynalevytska.memorizergame;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by katerynalevytska on 8/11/16.
 */
public class GridAdapter extends BaseAdapter {

//    private static final int CELL_CLOSE = 0;
//    private static final int CELL_OPEN = 1;
//    private static final int CELL_DELETE = -1;

    private static enum Status{CELL_CLOSE, CELL_OPEN, CELL_DELETE};
    private ArrayList<Status> arrStatus;

    private Context mContext;
    private Integer mRows, mCols;

    private ArrayList<String> arrPict; // массив картинок
    private String PictureCollection; //префикс набора картинок
    private Resources mRes; // Ресурсы приложения

    public GridAdapter(Context context, int cols, int rows){
        mCols = cols;
        mRows = rows;
        mContext = context;

        arrPict = new ArrayList<String>();
        arrStatus = new ArrayList<Status>();

        PictureCollection = "im";
        mRes = mContext.getResources();

        makePictArray();

        closeAllCells();
    }

    private void makePictArray(){
        arrPict.clear();
        for (int i = 0; i < ((mRows*mCols)/2); i++){
            arrPict.add(PictureCollection+Integer.toString(i));
            arrPict.add(PictureCollection+Integer.toString(i));
        }

        Collections.shuffle(arrPict); // перемешиваем изображения
    }

    @Override
    public int getCount() {
        return mCols*mRows;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if(view == null)
            imageView = new ImageView(mContext);
        else
            imageView = (ImageView)view;

        switch (arrStatus.get(position)){
            case CELL_OPEN:
                Integer drawableId = mRes.getIdentifier(arrPict.get(position), "drawable", mContext.getPackageName());
                imageView.setImageResource(drawableId);
                break;
            case CELL_CLOSE:
                imageView.setImageResource(R.drawable.close);
                break;
            default:
                imageView.setImageResource(R.drawable.none);
        }
        return imageView;
    }

    private void closeAllCells(){
        arrStatus.clear();
        for (int i = 0; i < mCols * mRows; i++){
            arrStatus.add(Status.CELL_CLOSE);
        }
    }

    // game process
    public void checkOpenCells() {

        int first = arrStatus.indexOf(Status.CELL_OPEN);
        int second = arrStatus.lastIndexOf(Status.CELL_OPEN);

        if(first == second){
            return;
        }
        if(arrPict.get(first).equals(arrPict.get(second))){
            arrStatus.set(first, Status.CELL_DELETE);
            arrStatus.set(second, Status.CELL_DELETE);
        }else{
            arrStatus.set(first, Status.CELL_CLOSE);
            arrStatus.set(second, Status.CELL_CLOSE);
        }
        return;
    }

    public boolean openCell(int position) {

        if(arrStatus.get(position) == Status.CELL_DELETE || arrStatus.get(position) == Status.CELL_OPEN ){
            return false;
        }
        if(arrStatus.get(position) != Status.CELL_DELETE){
            arrStatus.set(position, Status.CELL_OPEN);
        }

        notifyDataSetChanged();
        return true;
    }

    public boolean checkGameOver() {
        if(arrStatus.indexOf(Status.CELL_CLOSE) < 0){
            return true;
        }else{
            return false;
        }
    }
}
