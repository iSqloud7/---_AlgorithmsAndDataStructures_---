package SingleLinkedList.Spojuvanje_Na_Dve_Sortirani_Listi_Vo_Rezultantna_I_Brishenje_Duplikati_If_Else;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    public SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + "->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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

    public Iterator<E> iterator() {
        // Return an iterator that visits all elements of this list, in left-to-right order.
        return new LRIterator<E>();
    }

    // //////////Inner class ////////////

    private class LRIterator<E> implements Iterator<E> {

        private SLLNode<E> place, curr;

        private LRIterator() {
            place = (SLLNode<E>) first;
            curr = null;
        }

        public boolean hasNext() {
            return (place != null);
        }

        public E next() {
            if (place == null)
                throw new NoSuchElementException();
            E nextElem = place.element;
            curr = place;
            place = place.succ;
            return nextElem;
        }

        public void remove() {
            //Not implemented
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (tmp != null) {
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }

    }

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        } else {
            first = in.getFirst();
        }
    }
}

public class Spojuvanje_Na_Dve_Sortirani_Listi_Vo_Rezultantna_I_Brishenje_Duplikati_If_Else {
    public static void SLLJoinLists(SLL<Integer> list1, SLL<Integer> list2) {
        SLLNode<Integer> firstList = list1.getFirst();
        SLLNode<Integer> secondList = list2.getFirst();
        SLLNode<Integer> start = null;
        SLLNode<Integer> end = null;
        while (firstList != null && secondList != null) {
            if (firstList.element < secondList.element) {
                if (start == null) {
                    start = end = firstList;
                } else {
                    end.succ = firstList;
                    end = firstList;
                }
                firstList = firstList.succ;
            } else {
                if (start == null) {
                    start = end = secondList;
                } else {
                    end.succ = secondList;
                    end = secondList;
                }
                secondList = secondList.succ;
            }
        }
        if (firstList != null) {
            end.succ = firstList;
        }
        if (secondList != null) {
            end.succ = secondList;
        }
        list1.first = start;
        list2.first = null;
        SLLNode<Integer> tmp = list1.getFirst();
        while (tmp != null && tmp.succ != null) {
            if (tmp.element == tmp.succ.element) {
                list1.delete(tmp);
            }
            tmp = tmp.succ;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        int i;
        int n;
        int broj;
        String[] pom;

        SLL<Integer> lista1 = new SLL<Integer>();
        SLL<Integer> lista2 = new SLL<Integer>();

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

        System.out.println("Листа 1: " + lista1);
        System.out.println("Листа 2: " + lista2);

        SLLJoinLists(lista1, lista2);
        System.out.println("Листата после подредувањето со слевање: " + lista1);
    }
}
