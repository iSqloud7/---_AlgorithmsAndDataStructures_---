package SingleLinkedList.MathCompetitors_Brishenje_Site_So_Pomalku_Poeni_Od_Prosekot_Prethodnata_Godina;

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
    private SLLNode<E> first;

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
                ret += " " + tmp;
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

class Competitor {
    private int id;
    private double points;

    public Competitor(int id, double points) {
        this.id = id;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public double getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

public class Brishenje_Site_So_Pomalku_Poeni_Od_Prosekot_Prethodnata_Godina {
    public static double competition(SLL<Competitor> prevYearCompetitors, SLL<Competitor> currYearCompetitors) {
        SLLNode<Competitor> tmp = prevYearCompetitors.getFirst();
        int sum = 0;
        int counter = 0;
        double average = 0.0;
        while (tmp != null) {
            sum += tmp.element.getPoints();
            counter++;
            tmp = tmp.succ;
        }
        average = (double) sum / counter;
        tmp = currYearCompetitors.getFirst();
        while (tmp != null) {
            if (tmp.element.getPoints() < average) {
                currYearCompetitors.delete(tmp);
            }
            tmp = tmp.succ;
        }
        return average;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int prevYearCount = Integer.parseInt(scanner.nextLine());
        int currYearCount = Integer.parseInt(scanner.nextLine());
        SLL<Competitor> prevYearCompetitors = new SLL<Competitor>();
        SLL<Competitor> currYearCompetitors = new SLL<Competitor>();

        for (int i = 0; i < prevYearCount; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            //vashiot kod tuka
            Competitor competitor = new Competitor(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
            prevYearCompetitors.insertLast(competitor);
        }

        for (int i = 0; i < currYearCount; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            //vashiot kod tuka
            Competitor competitor = new Competitor(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
            currYearCompetitors.insertLast(competitor);
        }
//        double prosek=Prosek(prevYearCompetitors);
//        System.out.println(prosek);
        double average = competition(prevYearCompetitors, currYearCompetitors);
        System.out.printf("Average: %.2f\n", average);
        competition(prevYearCompetitors, currYearCompetitors);
        System.out.println(currYearCompetitors.toString());
    }
}

