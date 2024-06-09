package SingleLinkedList.Transformacija_So_Podelba_Vo_Dve_Novi_Listi_Preku_Promena_Na_Jazli;

import java.util.Scanner;

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
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " -> " + tmp;
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

    public void setFirst(SLLNode<E> node) {
        first = node;
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
}

class Element {
    private int id;

    public Element(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

public class Transformacija_So_Podelba_Vo_Dve_Novi_Listi_Preku_Promena_Na_Jazli {
    private static void listTransform1(SLL<Element> original, SLL<Element> odd, SLL<Element> even) {
        SLLNode<Element> tmp = original.getFirst();
        while (tmp != null) {
            if (tmp.element.getId() % 2 == 0) {
                even.insertLast(tmp.element);
            } else {
                odd.insertLast(tmp.element);
            }
            tmp = tmp.succ;
        }
    }

    private static void listTransform2(SLL<Element> original, SLL<Element> odd, SLL<Element> even) {
        SLLNode<Element> tmp = original.getFirst();
        SLLNode<Element> oddTmp = odd.getFirst();
        SLLNode<Element> evenTmp = even.getFirst();
        SLLNode<Element> startOdd = null;
        SLLNode<Element> endOdd = null;
        SLLNode<Element> startEven = null;
        SLLNode<Element> endEven = null;
        while (tmp != null) {
            if (tmp.element.getId() % 2 == 0) {
                if (startEven == null) {
                    evenTmp = startEven = endEven = tmp;
                } else {
                    endEven.succ = tmp;
                    endEven = tmp;
                }
                tmp = tmp.succ;
            } else {
                if (startOdd == null) {
                    oddTmp = startOdd = endOdd = tmp;
                } else {
                    endOdd.succ = tmp;
                    endOdd = tmp;
                }
                tmp = tmp.succ;
            }
        }
        even.first = startEven;
        odd.first = startOdd;
        endEven.succ = null;
        endOdd.succ = null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        SLL<Element> list = new SLL<Element>();
        SLL<Element> odd = new SLL<Element>();
        SLL<Element> even = new SLL<Element>();

        for (int i = 0; i < num; i++) {
            int n = scanner.nextInt();
            list.insertLast(new Element(n));
        }

        listTransform2(list, odd, even);
        System.out.println(odd.toString());
        System.out.println(even.toString());
    }
}

