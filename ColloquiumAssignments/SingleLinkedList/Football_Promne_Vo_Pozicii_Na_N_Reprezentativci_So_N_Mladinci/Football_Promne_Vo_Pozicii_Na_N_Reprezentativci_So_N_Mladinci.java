package SingleLinkedList.Football_Promne_Vo_Pozicii_Na_N_Reprezentativci_So_N_Mladinci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    private SLLNode<E> first;

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


}

class Player implements Comparable<Player> {
    int number;
    int rating;
    int years;

    public Player(int number, int rating, int years) {
        this.number = number;
        this.rating = rating;
        this.years = years;
    }


    @Override
    public int compareTo(Player o) {
        if (o.rating > this.rating)
            return -1;
        if (o.rating < this.rating)
            return 1;
        if (o.rating == this.rating) {
            if (o.years > this.years)
                return 1;
            if (o.years < this.years)
                return -1;
        }
        return 0;

    }
}


public class Football_Promne_Vo_Pozicii_Na_N_Reprezentativci_So_N_Mladinci {
    public static void sort(SLL<Player> list) {
        SLLNode<Player> tmp1 = list.getFirst();
        while (tmp1 != null) {
            SLLNode<Player> tmp2 = tmp1.succ;
            while (tmp2 != null) {
                if (tmp1.element.compareTo(tmp2.element) < 0) {
                    Player pom = tmp1.element;
                    tmp1.element = tmp2.element;
                    tmp2.element = pom;
                }
                tmp2 = tmp2.succ;
            }

            tmp1 = tmp1.succ;
        }
    }

    public static void changePlayers(SLL<Player> representative_team, SLL<Player> under_21_team, int N) {
        //Вашиот код тука

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        SLL<Player> representative_team = new SLL<Player>();
        SLL<Player> under_21_team = new SLL<Player>();
        for (int i = 0; i < 11; i++) {
            String[] pom = bf.readLine().split(" ");
            Player p = new Player(Integer.parseInt(pom[0]), Integer.parseInt(pom[1]), Integer.parseInt(pom[2]));
            representative_team.insertLast(p);
        }

        for (int i = 0; i < 11; i++) {
            String[] pom = bf.readLine().split(" ");
            Player p = new Player(Integer.parseInt(pom[0]), Integer.parseInt(pom[1]), Integer.parseInt(pom[2]));
            under_21_team.insertLast(p);
        }

        sort(under_21_team);
        int N = Integer.parseInt(bf.readLine());


        changePlayers(representative_team, under_21_team, N);
        SLLNode<Player> tmp = representative_team.getFirst();

        while (tmp != null) {
            System.out.print(tmp.element.number + " ");
            tmp = tmp.succ;
        }


    }
}
