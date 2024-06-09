package SingleLinkedList.Company_Najvazhniot_Task_Od_ToDo_Na_Pochetok_Kaj_InProgress_I_Najnevazhniot_Task_Od_InProgress_Na_Kraj_Kaj_ToDo;

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

class Task {
    private int id;
    private int hours;
    private int priority;

    public Task(int id, int hours, int priority) {
        this.id = id;
        this.hours = hours;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

public class Company_Najvazhniot_Task_Od_ToDo_Na_Pochetok_Kaj_InProgress_I_Najnevazhniot_Task_Od_InProgress_Na_Kraj_Kaj_ToDo {
    public static void work(SLL<Task> toDo, SLL<Task> inProgress) {
        SLLNode<Task> tmp = toDo.getFirst();
        SLLNode<Task> mostImportantTask = toDo.getFirst();
        while (tmp != null) {
            int importance = 2 * tmp.element.getHours() * tmp.element.getPriority();
            int maxImportance = 2 * mostImportantTask.element.getHours() * mostImportantTask.element.getPriority();
            if (importance > maxImportance) {
                mostImportantTask = tmp;
            }
            tmp = tmp.succ;
        }
        toDo.delete(mostImportantTask);
        inProgress.insertFirst(mostImportantTask.element);

        tmp = inProgress.getFirst();
        SLLNode<Task> leastImportantTask = inProgress.getFirst();
        while (tmp != null) {
            int importance = 2 * tmp.element.getHours() * tmp.element.getPriority();
            int minImportance = 2 * leastImportantTask.element.getHours() * leastImportantTask.element.getPriority();
            if (importance < minImportance) {
                leastImportantTask = tmp;
            }
            tmp = tmp.succ;
        }
        inProgress.delete(leastImportantTask);
        toDo.insertLast(leastImportantTask.element);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numToDo = Integer.parseInt(scanner.nextLine());
        int numInProgress = Integer.parseInt(scanner.nextLine());

        SLL<Task> toDo = new SLL<Task>();
        SLL<Task> inProgress = new SLL<Task>();

        for (int i = 0; i < numToDo; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            toDo.insertLast(new Task(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < numInProgress; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            inProgress.insertLast(new Task(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        work(toDo, inProgress);
        System.out.println(toDo.toString());
        System.out.println(inProgress.toString());
    }
}
