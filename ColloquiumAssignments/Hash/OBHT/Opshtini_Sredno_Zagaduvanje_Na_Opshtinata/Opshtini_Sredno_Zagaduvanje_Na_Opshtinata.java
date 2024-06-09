package Hash.OBHT.Opshtini_Sredno_Zagaduvanje_Na_Opshtinata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

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

class OBHT<K extends Comparable<K>, E> {

    // An object of class OBHT is an open-bucket hash table, containing entries
    // of class MapEntry.
    public MapEntry<K, E>[] buckets;

    // buckets[b] is null if bucket b has never been occupied.
    // buckets[b] is former if bucket b is formerly-occupied
    // by an entry that has since been deleted (and not yet replaced).

    static final int NONE = -1; // ... distinct from any bucket index.

    private static final MapEntry former = new MapEntry(null, null);
    // This guarantees that, for any genuine entry e,
    // e.key.equals(former.key) returns false.

    private int occupancy = 0;
    // ... number of occupied or formerly-occupied buckets in this OBHT.

    @SuppressWarnings("unchecked")
    public OBHT(int m) {
        // Construct an empty OBHT with m buckets.
        buckets = (MapEntry<K, E>[]) new MapEntry[m];
    }


    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }


    public int search(K targetKey) {
        // Find which if any bucket of this OBHT is occupied by an entry whose key
        // is equal to targetKey. Return the index of that bucket.
        int b = hash(targetKey);
        int n_search = 0;
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return NONE;

            }
        }
    }


    public void insert(K key, E val) {
        // Insert the entry <key, val> into this OBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        int n_search = 0;
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            } else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return;

            }
        }
    }


    @SuppressWarnings("unchecked")
    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key);
        int n_search = 0;
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];

            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;//(MapEntry<K,E>)former;
                return;
            } else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return;

            }
        }
    }


    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K, E> clone() {
        OBHT<K, E> copy = new OBHT<K, E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K, E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<K, E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
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
        int h = -1;

        s = bufferedReader.readLine();
        n = Integer.parseInt(s);
        OBHT<String, ArrayList<Float>> tabela = new OBHT<String, ArrayList<Float>>(n * 2 + 1);

        for (i = 0; i < n; i++) {
            s = bufferedReader.readLine();
            pom = s.split(" ");
            opshtina = pom[0];
            merenje = Float.parseFloat(pom[1]);
            h = tabela.search(opshtina);
            if (h == -1) {
                ArrayList<Float> arrayList = new ArrayList<Float>();
                arrayList.add(merenje);
                tabela.insert(opshtina, arrayList);
            } else {
                ArrayList<Float> arrayList = tabela.buckets[h].value;
                arrayList.add(merenje);
                tabela.insert(opshtina, arrayList);
            }
        }
        opshtina = bufferedReader.readLine();
        h = tabela.search(opshtina);
        if (h == -1) {
            System.out.println("Nema merenja za opshtinata: " + opshtina);
        } else {
            ArrayList<Float> arrayList = tabela.buckets[h].value;
            float sum = 0;
            int golemina = arrayList.size();
            for (int j = 0; j < golemina; j++) {
                sum += arrayList.get(j);
            }
            merenje = sum / golemina;
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
        }
    }
}
