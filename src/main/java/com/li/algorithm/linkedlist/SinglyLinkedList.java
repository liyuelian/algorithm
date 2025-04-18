package com.li.algorithm.linkedlist;

//单链表的增删改查实现
public class SinglyLinkedList {
    // 虚拟头尾节点
    private final Node dummyHead;
    private final Node dummyTail;
    // 容量
    int size;

    // 定义Node节点
    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // 构造函数
    public SinglyLinkedList() {
        // 虚拟头节点
        this.dummyHead = new Node(-1);
        // 虚拟尾节点
        this.dummyTail = new Node(-1);

        // 链接头尾节点
        dummyHead.next = dummyTail;
        this.size = 0;
    }

    // 检查元素是否越界
    private boolean isPositionIndex(int index) {
        return index >= 0 && index < size;
    }

    //检查元素是否可以插入链表
    private boolean isVaildIndex(int index) {
        return index >= 0 && index <= size;
    }

    // 头插
    public void addAtHead(int val) {
        Node addNode = new Node(val);
        // 更改插入节点的next
        addNode.next = this.dummyHead.next;
        // 更改虚拟头结点next
        this.dummyHead.next = addNode;
        this.size++;
    }

    // 尾插
//    public void addAtTail(int val) {
//        Node addNode = new Node(val);
//        Node cur = dummyHead;
//        // 走到dummyTail尾节点前一个节点
//        for (int i = 0; i < size; i++) {
//            cur = cur.next;
//        }
//
//        // 倒数二节点的next指针
//        cur.next = addNode;
//        // 更新尾节点
//        addNode.next = dummyTail;
//        this.size++;
//    }
    public void addAtTail(int val) {
        Node addNode = new Node(val);
        Node cur = this.dummyHead;
        //找到最后一个节点
        while (cur.next != this.dummyTail) {
            cur = cur.next;
        }
        addNode.next = dummyTail;
        cur.next = addNode;
        this.size++;
    }

    // 获取链表中下标为 index 的节点的值,下标从 0 开始。
    // 如果下标无效，则返回 -1 。
    public int get(int index) {
        if (!isPositionIndex(index)) {
            return -1;
        }
        //从头结点开始
        Node cur = this.dummyHead;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }


    public void addAtIndex(int index, Integer val) {
        if (!isVaildIndex(index)) {
            return;
        }
        // 找到index节点的前一个节点
        Node cur = this.dummyHead;
        //cur 从-1开始
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        Node addNode = new Node(val);
        // 插入节点的next指向index节点
        addNode.next = cur.next;
        // 上一节点的next指向插入节点
        cur.next = addNode;
        this.size++;
    }

    //删除下标尾index的元素
    public void deleteAtIndex(int index) {
        if (!isPositionIndex(index)) {
            return;
        }
        // 找到index节点的前一个节点
        Node cur = this.dummyHead;
        // cur 从-1开始
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        //index的下一个节点赋给index的上一个节点
        cur.next = cur.next.next;
        this.size--;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = this.dummyHead.next;
        sb.append("Head->");
        while (cur != dummyTail) {
            sb.append(cur.val).append("->");
            cur = cur.next;
        }
        sb.append("Tail");
        System.out.println(sb);
        return null;
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtHead(7);
        list.toString();
        list.addAtHead(2);
        list.toString();

        list.addAtHead(1);
        list.toString();

        list.addAtIndex(3, 0);
        list.toString();

        list.deleteAtIndex(2);
        list.toString();

        list.addAtHead(6);
        list.toString();

        list.addAtTail(4);
        list.toString();

        list.get(4);
        System.out.println(list.get(4));
        list.addAtHead(4);
        list.toString();

        list.addAtIndex(5, 0);
        list.toString();
        list.addAtHead(6);
        list.toString();


        // size = 5
        // 0 <-> 1 <-> 100 <-> 2 -> 3 -> null
    }


}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
// @lc code=end

