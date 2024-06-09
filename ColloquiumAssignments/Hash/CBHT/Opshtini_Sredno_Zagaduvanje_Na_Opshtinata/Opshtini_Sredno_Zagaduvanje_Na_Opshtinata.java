package Hash.CBHT.Opshtini_Sredno_Zagaduvanje_Na_Opshtinata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

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

class Zagaduvanje {
    float vkupnoMerenje;
    int brojMerenja;

    public Zagaduvanje(float vkupnoMerenje, int brojMerenja) {
        this.vkupnoMerenje = vkupnoMerenje;
        this.brojMerenja = brojMerenja;
    }

    @Override
    public String toString() {
        return String.format("%.2f", vkupnoMerenje / brojMerenja);
//        DecimalFormat decimalFormat = new DecimalFormat("#.##");
//        return decimalFormat.format(vkupnoMerenje / brojMerenja);
    }
}

public class Opshtini_Sredno_Zagaduvanje_Na_Opshtinata {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int i;
        int n;
        String s;
        String[] pom;
        String opshtina;
        float merenje;
        float vkupnoMerenje;
        int brojMerenja;
        Zagaduvanje zagaduvanje = null;
        SLLNode<MapEntry<String, Zagaduvanje>> tmp = null;

        s = bufferedReader.readLine();
        n = Integer.parseInt(s);
        CBHT<String, Zagaduvanje> tabela = new CBHT<String, Zagaduvanje>(n * 2 + 1);

        for (i = 0; i < n; i++) {
            s = bufferedReader.readLine();
            pom = s.split(" ");
            opshtina = pom[0];
            merenje = Float.parseFloat(pom[1]);
            tmp = tabela.search(opshtina);
            if (tmp == null) {
                zagaduvanje = new Zagaduvanje(merenje, 1);
                tabela.insert(opshtina, zagaduvanje);
            } else {
                zagaduvanje = tmp.element.value;
                zagaduvanje.vkupnoMerenje += merenje;
                zagaduvanje.brojMerenja++;
                tabela.insert(opshtina, zagaduvanje);
            }
        }
        opshtina = bufferedReader.readLine();
        tmp = tabela.search(opshtina);
        if (tmp == null) {
            System.out.println("Nema merenja za opshtinata: " + opshtina);
        } else {
            zagaduvanje = tmp.element.value;
            merenje = zagaduvanje.vkupnoMerenje / zagaduvanje.brojMerenja;
//            1.
            System.out.printf("%.2f\n", merenje);
//            2.
            System.out.println(String.format("%.2f", merenje));
//            3.
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            System.out.println(decimalFormat.format(merenje));
//            4. 3,14567*100=314.567, -> MathRoun=>315, 315/100=3.15
            merenje = (float) Math.round(merenje * 100) / 100;
            System.out.println(merenje);
//            5. toString vo klasata
            System.out.println(zagaduvanje);
        }
    }
}
