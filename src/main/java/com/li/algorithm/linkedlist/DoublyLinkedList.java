package com.li.algorithm.linkedlist;

// 双链表的增删改查
public class DoublyLinkedList {

    private static class Node {
        private Node prev;
        private Node next;
        private int val;

        Node(int val) {
            this.val = val;
            prev = null;
            next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = new Node(-1);
        this.tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    //检查获取元素的下标是否合法（get、delete）
    public boolean isVaildIndex(int index) {
        return index >= 0 && index < size;
    }

    //检查插入元素的下标是否合法（add）
    public boolean isPositionCheck(int index) {
        //现存元素有 size+1 个空隙
        return index >= 0 && index <= size;
    }

    public int get(int index) {
        if (!isVaildIndex(index)) {
            return -1;
        }
        //从0开始计算第一个节点
        Node cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        Node addNode = new Node(val);
        //链接新节点
        addNode.next = head.next;
        addNode.prev = head;
        //重新链接旧节点
        head.next.prev = addNode;
        head.next = addNode;
        size++;
    }

    public void addAtTail(int val) {
        Node addNode = new Node(val);
        //链接新节点
        addNode.prev = tail.prev;
        addNode.next = tail;
        //重新链接旧节点
        tail.prev.next = addNode;
        tail.prev = addNode;
        size++;

    }

    public void addAtIndex(int index, int val) {
        if (!isPositionCheck(index)) {
            return;
        }
        Node addNode = new Node(val);
        Node cur = head;
        //获取index的前一个节点
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        //1<-->3
        //1<-->2<-->3
        //给新节点赋值
        addNode.prev = cur;
        addNode.next = cur.next;

        //temp存储cur.next位置
        Node temp = cur.next;
        //重新设置旧节点链接
        cur.next = addNode;
        temp.prev = addNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (!isVaildIndex(index)) {
            return;
        }
        Node cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        //1<-->3
        //1<-->2<-->3
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;

        size--;
    }

    public void display() {
        Node cur = head.next;

        while (cur != tail) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList dList = new DoublyLinkedList();
        dList.addAtHead(1);
        dList.display();

        dList.addAtTail(3);
        dList.display();

        dList.addAtIndex(1, 2);
        dList.display();

        dList.get(1);

        dList.deleteAtIndex(1);
        dList.display();

        dList.get(1);

        dList.deleteAtIndex(0);
        dList.display();
    }
}
