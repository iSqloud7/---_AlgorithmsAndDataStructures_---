package DoubleLinkedList.Naizmenichno_Popolnuvanje_Po_Tri_Elementa_Eden_Po_Drug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            deleteFirst();
            return node.element;
        }
        if (node == last) {
            deleteLast();
            return node.element;
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp + "<->";
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void izvadiDupliIPrebroj() {

    }
}

public class Naizmenichno_Popolnuvanje_Po_Tri_Elementa_Eden_Po_Drug {
    public static void Naizmenichno_Popolnuvanje_Po_Tri(DLL<Integer> list, int N) {
        DLLNode<Integer> tmp = list.getFirst();
        list.insertLast(1);
        int counter;
        int i = 2;
        while (i < N) {
            counter = 1;
            while (counter <= 3 && i <= N) {
                list.insertLast(i);
                i++;
                counter++;
            }
            tmp = list.getFirst();
            counter = 1;
            while (tmp != null && counter <= 3 && i < N) {
                list.insertAfter(i, tmp);
                i++;
                counter++;
                tmp = tmp.succ.succ;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int i;
        int n;
        int broj;
        String[] pom;

        s = bufferedReader.readLine();
        n = Integer.parseInt(s);

        DLL<Integer> lista1 = new DLL<Integer>();

        Naizmenichno_Popolnuvanje_Po_Tri(lista1, n);
        System.out.println("Листата после вметнувањата на елементите до N:" + lista1);
    }
}
