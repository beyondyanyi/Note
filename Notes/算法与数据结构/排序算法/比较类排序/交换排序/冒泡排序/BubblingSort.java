package com.wuwei.yanyi.test;

/**
 * @ClassName BubblingSort
 * @Description: TODO 冒泡排序
 * @Author beyondyanyi@gmail.com
 * @Date 2021/4/23
 * @Version V1.0
 **/
public class BubblingSort {

    private int[] array;
    public BubblingSort(int[] array) {
        this.array = array;
    }

    /**
     *
     * @param sort 0 正序 1 倒叙
     */
    public void sort(int sort) {
        bubblingSort(array,sort);
    }
    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }


    private void bubblingSort(int[] array,int sort){
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(1==sort){
                    if(array[i]<array[j]){
                        int mid=array[j];
                        array[j]=array[i];
                        array[i]=mid;
                        continue;
                    }
                }else{
                    if(array[i]>array[j]){
                        int mid=array[j];
                        array[j]=array[i];
                        array[i]=mid;
                        continue;
                    }
                }

            }
        }
    }


}
