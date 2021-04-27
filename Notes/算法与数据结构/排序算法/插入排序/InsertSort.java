package com.wuwei.yanyi.test;

/**
 * @ClassName InsertSort
 * @Description: TODO 插入排序（类似斗地主码牌）
 * @Author beyondyanyi@gmail.com
 * @Date 2021/4/26
 * @Version V1.0
 **/
public class InsertSort {
    private int[] array;
    public InsertSort(int[] array) {
        this.array = array;
    }
    public void sort() {
        insertSort(array);
    }
    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private void insertSort(int[] array){
        for(int i=1;i< array.length;i++){
            //基准数index
            int index=i;
            //对比基准数以前的所有数，寻找合适的位置
            for (int j = index-1;j>=0; j--) {
                if(array[index]>=array[j]){
                    int mid=array[index];
                    array[index]=array[j];
                    array[j]=mid;
                    index--;
                }
            }
        }
    }
}
