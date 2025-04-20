package com.li.algorithm.linkedlist;

//环形数组的增删改查
public class CycleArray {
    private int[] array;
    // 左闭右开 [start,end)
    // start 指向第一个元素
    // end 指向最后一个元素的下一位
    private int start;
    private int end;
    private int size; // 数组容量
    private int count; // 元素个数

    public CycleArray(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size 不能小于 0");
        }
        this.size = size;
        this.array = new int[size];
        this.start = 0;
        this.end = 0;
        this.count = 0;
    }

    // 自动扩缩容辅助函数
    private void resize(int newSize) {
        int[] tempArray = new int[newSize];
        // 把旧数组的元素移到新数组中
        for (int i = 0; i < count; i++) {
            // 从start开始，共count个元素
            tempArray[i] = array[(i + start) % size];
        }
        array = tempArray;
        size = newSize;
        start = 0;
        // end 指向最后一个元素的下一位，索引从0开始
        end = count;
    }


    // 在数组头部添加元素，时间复杂度 O(1)
    public void addFirst(int val) {
        // 使用率达75%则2倍扩容
        if (size * 0.75 <= count) {
            resize(size * 2);
        }
        // 先计算新的start
        start = (start - 1 + size) % size;
        array[start] = val;
        count++;
    }

    // 删除数组头部元素，时间复杂度 O(1)
    public void removeFirst() {
        if (count == 0) {
            return;
        }
        // 置空
        array[start] = -999;
        // 重新计算start值
        start = (start + 1) % size;
        count--;
        if (count <= size * 0.25) {
            resize(size / 2);
        }
    }

    // 在数组尾部添加元素，时间复杂度 O(1)
    public void addLast(int val) {
        if (size * 0.75 <= count) {
            resize(size * 2);
        }
        // 先赋值再移动end
        array[end] = val;
        end = (end + 1) % size;
        count++;
    }

    // 删除数组尾部元素，时间复杂度 O(1)
    public void removeLast() {
        if (count == 0) {
            return;
        }
        end = (end - 1 + size) % size;
        // 置空
        array[end] = -999;
        count--;
        // 缩容
        if (count <= size * 0.25) {
            resize(size / 2);
        }
    }

    // 获取数组头部元素，时间复杂度 O(1)
    public int getFirst() {
        if (count == 0) {
            return -999;
        }
        return array[start];
    }

    // 获取数组尾部元素，时间复杂度 O(1)
    public int getLast() {
        if (count == 0) {
            return -999;
        }
        return array[(end - 1 + size) % size];
    }

    public void displayArr() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CycleArray arr = new CycleArray(6);

        arr.addLast(1);
        arr.addLast(2);
        arr.displayArr();
        // [1, 2, _, _, _, _]

        arr.addFirst(3);
        arr.displayArr();
        // [1, 2, _, _, _, 3]

        arr.addFirst(4);
        arr.displayArr();
        // [1, 2, _, _, 4, 3]

        int first = arr.getFirst(); // 4
        System.out.println(first);
        int last = arr.getLast(); // 2
        System.out.println(last);

        arr.addFirst(5);
        arr.displayArr();
        // [1, 2, _, 5, 4, 3]

        arr.removeLast();
        arr.displayArr();
        // [1, _, _, 5, 4, 3]

        arr.removeFirst();
        arr.displayArr();
        // [1, _, _, _, 4, 3]

        arr.removeLast();
        arr.displayArr();
        // [_, _, _, _, 4, 3]

        arr.removeLast();
        arr.displayArr();
        // [_, 4, _]
    }

}
