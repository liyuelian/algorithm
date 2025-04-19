package com.li.algorithm.linkedlist;

//动态数组的增删改查
public class DynamicArrays<E> {
    private E[] data;//存储元素的数组
    private int size;//当前数组存储的元素个数
    private static final int DEFAULT_CAPACITY = 8;//默认的初始容量

    public DynamicArrays() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArrays(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal init capacity:" + capacity);
        }
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 当前元素个数
     *
     * @return
     */
    private int size() {
        return size;
    }

    /**
     * 向数组增加元素
     *
     * @param insertPos 插入的位置，从0开始，非数组元素下标
     * @param element   插入的元素
     */
    public void add(int insertPos, E element) {
        //判断插入位置是否合法
        checkInsertPos(insertPos);

        //向右移动位置 data[insertPos] = data[insertPos-1]
        for (int i = size; i > insertPos; i--) {
            data[i] = data[i - 1];
        }
        data[insertPos] = element;
        size++;

        //当元素的个数达到容量的0.75时，2倍扩容
        if (size >= data.length * 0.75f) {
            E[] tempData = (E[]) new Object[data.length * 2];
            for (int i = 0; i < size; i++) {
                tempData[i] = data[i];
            }
            //更新引用
            data = tempData;
        }
    }

    private void addLast(E element) {
        add(size, element);
    }

    private void addFirst(E element) {
        add(0, element);
    }


    /**
     * 删除数组元素
     *
     * @param index 删除元素的索引下标，从0开始
     */
    public E delete(int index) {
        //判断索引是否越界
        checkElementIndex(index);

        E removeElement = get(index);
        //从index起，后面的元素依次往左挪 data[index] = data[index+1]
        for (int i = index; i < size - 1; i++) {
            //size=3,index=1
            //1,2,3
            //1,3
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;

        //缩容：当前数组的元素个数<容量的0.25倍，则容量/2
        if (size <= data.length / 4) {
            E[] tempData = (E[]) new Object[data.length / 2];
            for (int i = 0; i < size; i++) {
                tempData[i] = data[i];
            }
            //更新引用
            data = tempData;
        }

        return removeElement;
    }

    private E deleteLast() {
        return delete(size - 1);
    }


    /**
     * 修改数组元素
     *
     * @param index   修改元素的索引，从0开始
     * @param element 修改的新值
     */
    public E set(int index, E element) {
        //判断索引是否越界
        checkElementIndex(index);

        E oldElement = data[index];
        data[index] = element;
        return oldElement;

    }

    /**
     * 查询数组元素
     *
     * @param index 查询数组索引，从0开始
     */
    public E get(int index) {
        //判断索引是否越界
        checkElementIndex(index);
        return data[index];
    }

    /**
     * 检查索引是否越界
     *
     * @param index 数组索引，从0开始
     */
    private void checkElementIndex(int index) {
        //size为当前数据存储的元素个数，如果index>=size或index<0,则越界
        //比如 size=3，合法index=0,1,2
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("MyArrayIndexOutOfBounds,index:" + index + ",size:" + size);
        }
    }

    /**
     * 检查插入位置是否合法
     *
     * @param insertPos 插入位置，从0开始，这里的位置是指元素之间的空隙，非元素下标索引
     */
    private void checkInsertPos(int insertPos) {
        if (insertPos < 0 || insertPos > size) {
            throw new ArrayIndexOutOfBoundsException("MyArrayIndexOutOfBounds,insertPos:" + insertPos + ",size:" + size);
        }
    }

    public static void main(String[] args) {
        DynamicArrays<Integer> arr = new DynamicArrays<>(3);

        // 添加 5 个元素
        for (int i = 1; i <= 5; i++) {
            arr.addLast(i);
        }

        arr.delete(3);
        arr.add(1, 9);
        arr.addFirst(100);
        int val = arr.deleteLast();
        System.out.println(val);

        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
    }

}