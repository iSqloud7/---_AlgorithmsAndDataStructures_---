package SingleLinkedList.Cakes_Brishenje_Torti_Poskapi_Od_Prosecna_Cena.Cake;

import java.util.Scanner;

class SLLNode {
    String name;
    int price;
    SLLNode succ;

    public SLLNode(String name, int price, SLLNode succ) {
        this.name = name;
        this.price = price;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return name;
    }
}

class SLL {
    SLLNode first;

    public SLL() {
        this.first = null;
    }

    public void insertFirst(String name, int price) {
        SLLNode ins = new SLLNode(name, price, first);
        first = ins;
    }

    public void insertAfter(String name, int price, SLLNode node) {
        if (node != null) {
            SLLNode ins = new SLLNode(name, price, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(String name, int price, SLLNode before) {

        if (first != null) {
            SLLNode tmp = first;
            if (first == before) {
                this.insertFirst(name, price);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode ins = new SLLNode(name, price, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(String name, int price) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(name, price, null);
            tmp.succ = ins;
        } else {
            insertFirst(name, price);
        }
    }

    public SLLNode deleteFirst() {
        if (first != null) {
            SLLNode tmp = first;
            first = first.succ;
            return tmp;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public SLLNode delete(SLLNode node) {
        if (first != null) {
            SLLNode tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode find(String name, int price) {
        if (first != null) {
            SLLNode tmp = first;
            while (!(tmp.price == price && tmp.name.equals(name)) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.price == price && tmp.name.equals(name)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (first != null) {
            SLLNode tmp = first;
            ret.append(tmp).append("\n");
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret.append(tmp).append("\n");
            }
        } else
            ret = new StringBuilder("NO ELEMENTS");
        return ret.toString();
    }
}

public class Cake_Brishenje_Torti_Poskapi_Od_Prosecna_Cena {
    public static void removeCakes(SLL cakes) {
        SLLNode tmp = cakes.first;
        int sum = 0;
        int counter = 0;
        float average;
        while (tmp != null) {
            sum += tmp.price;
            counter++;
            tmp = tmp.succ;
        }
        average = (float) sum / counter;
        tmp = cakes.first;
        while (tmp != null) {
            if (tmp.price > average) {
                cakes.delete(tmp);
            }
            tmp = tmp.succ;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        SLL cakes = new SLL();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            cakes.insertLast(parts[0], Integer.parseInt(parts[1]));
        }

        removeCakes(cakes);
        System.out.println(cakes.toString());
    }
}
