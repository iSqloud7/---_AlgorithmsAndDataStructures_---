package DoubleLinkedList.Naizmenichno_Dodavanje_Po_Tri_Jazli_Izlez_Prva_E_Vlez_Vo_Vtora;

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

public class Naizmenichno_Dodavanje_Po_Tri_Jazli {
    public static void Naizmenichno_Dodavanje_Po_Tri_Jazli(DLL<Integer> list1, DLL<Integer> list2, DLL<Integer> listResult) {
        DLLNode<Integer> tmp1 = list1.getFirst();
        DLLNode<Integer> tmp2 = list2.getFirst();
        while (tmp1 != null || tmp2 != null) {
            if (tmp1 != null) {
                listResult.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            }
            if (tmp1 != null) {
                listResult.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            }
            if (tmp1 != null) {
                listResult.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            }
            if (tmp2 != null) {
                listResult.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
            }
            if (tmp2 != null) {
                listResult.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
            }
            if (tmp2 != null) {
                listResult.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
            }
        }
    }

    public static void Naizmenichno_Dodavanje_Po_Tri_Jazli_Prvata_Od_Pocetok_Vtorata_Od_Pozadi(DLL<Integer> list1, DLL<Integer> list2, DLL<Integer> listResult) {
        DLLNode<Integer> tmp1 = list1.getFirst();
        DLLNode<Integer> tmp2 = list2.getLast();
        while (tmp1 != null || tmp2 != null) {
            if (tmp1 != null) {
                listResult.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            }
            if (tmp1 != null) {
                listResult.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            }
            if (tmp1 != null) {
                listResult.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
            }
            if (tmp2 != null) {
                listResult.insertLast(tmp2.element);
                tmp2 = tmp2.pred;
            }
            if (tmp2 != null) {
                listResult.insertLast(tmp2.element);
                tmp2 = tmp2.pred;
            }
            if (tmp2 != null) {
                listResult.insertLast(tmp2.element);
                tmp2 = tmp2.pred;
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

        DLL<Integer> lista1 = new DLL<Integer>();
        DLL<Integer> lista2 = new DLL<Integer>();
        DLL<Integer> lista3 = new DLL<Integer>();

        s = bufferedReader.readLine();
        n = Integer.parseInt(s);
        s = bufferedReader.readLine();
        pom = s.split(" ");

        for (i = 0; i < n; i++) {
            broj = Integer.parseInt(pom[i]);
            lista1.insertLast(broj);
        }

        s = bufferedReader.readLine();
        n = Integer.parseInt(s);
        s = bufferedReader.readLine();
        pom = s.split(" ");

        for (i = 0; i < n; i++) {
            broj = Integer.parseInt(pom[i]);
            lista2.insertLast(broj);
        }

//        Naizmenichno_Dodavanje_Po_Tri_Jazli(lista1, lista2, lista3);
//        System.out.println("Листа по наизменичното додавање јазли: " + lista3);

        Naizmenichno_Dodavanje_Po_Tri_Jazli_Prvata_Od_Pocetok_Vtorata_Od_Pozadi(lista1, lista2, lista3);
        System.out.println("Првата листа додава од напред, втората листа додава од назад: " + lista3);
    }
}
