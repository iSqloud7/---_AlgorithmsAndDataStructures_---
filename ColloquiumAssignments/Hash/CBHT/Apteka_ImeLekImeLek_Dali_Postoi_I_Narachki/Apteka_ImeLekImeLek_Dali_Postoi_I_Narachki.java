package Hash.CBHT.Apteka_ImeLekImeLek_Dali_Postoi_I_Narachki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

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

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class ImeLek implements Comparable<ImeLek> {
    String name;
    int pos_neg;
    int price;
    int quantity;

    public ImeLek(String name, int pos_neg, int price, int quantity) {
        this.name = name.toUpperCase();
        this.pos_neg = pos_neg;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public int compareTo(ImeLek imeLek) {
        return name.compareTo(imeLek.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ImeLek imeLek = (ImeLek) object;
        return Objects.equals(name, imeLek.name);
    }

    @Override
    public int hashCode() {
        int result = 0;
//        h(w)=(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780
        result = (29 * (29 * (29 * 0 + name.charAt(0)) + name.charAt(1)) + name.charAt(2)) % 102780;
        return result;
    }

    @Override
    public String toString() {
        String s;
        s = name + "\n";
        /*
        if(pos_neg == 1){
            s += "positive\n"
        }
        else{
            s += "negative\n"
        }
        */
        s += (pos_neg == 1) ? "positive\n" : "negative\n";
        s += price + "\n";
        s += quantity + "\n";
        return s;
    }
}

public class Apteka_ImeLekImeLek_Dali_Postoi_I_Narachki {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int i;
        int n;
        int positive_negative;
        int price;
        int quantity;
        String s;
        String name;
        String[] pom;
        ImeLek imeLek = null;
        SLLNode<MapEntry<ImeLek, ImeLek>> tmp = null;

        s = bufferedReader.readLine();
        n = Integer.parseInt(s);
        CBHT<ImeLek, ImeLek> tabela = new CBHT<ImeLek, ImeLek>(2 * n + 1);

        for (i = 0; i < n; i++) {
            s = bufferedReader.readLine();
            pom = s.split(" ");
            name = pom[0];
            positive_negative = Integer.parseInt(pom[1]);
            price = Integer.parseInt(pom[2]);
            quantity = Integer.parseInt(pom[3]);

            imeLek = new ImeLek(name, positive_negative, price, quantity);
            tabela.insert(imeLek, imeLek);
        }

        while (true) {
            name = bufferedReader.readLine();
            if (name.equals("KRAJ")) {
                break;
            }
            s = bufferedReader.readLine();
            quantity = Integer.parseInt(s);
            imeLek = new ImeLek(name, 0, 0, 0);

            tmp = tabela.search(imeLek);
            if (tmp == null) {
                System.out.println("Nema takov lek");
            } else {
                ImeLek found = tmp.element.value;
                System.out.println(found); // ЗА toString()
                /* НАМЕСТО toString()
                System.out.println(found.name);

//                System.out.println(found.pos_neg == 1 ? "positive" : "negative");
                if (found.pos_neg == 1) {
                    System.out.println("positive");
                } else {
                    System.out.println("negative");
                }

                System.out.println(found.price);
                System.out.println(found.quantity);
                */
                if (quantity <= found.quantity) {
                    found.quantity -= quantity;
                    tabela.insert(found, found);
                    System.out.println("Napravena naracka");
                } else {
                    System.out.println("Nema dovolno lekovi");
                }
            }
        }
    }
}
