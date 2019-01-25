package Q1;

import java.util.*;

/*
* Double Hash Map sınıfı Map interfacinden türemiştir.
* */
public class DoubleHashMap<K,V> implements Map<K,V> {

    /**
     * Hash sınıfı key ve value tutar
     * @param <K>
     * @param <V>
     */
    public static class HashEntry<K,V>
    {
        protected K key;
        protected V value;

        public HashEntry(K k, V v)
        {
            key = k;
            value = v;
        }

        public V getValue()
        {
            return value;
        }

        public K getKey()
        { return key;
        }

        public V setValue(V val)
        {
            V oldValue = value;
            value = val;
            return oldValue;
        }

        public boolean equals(Object o)
        {
            HashEntry ent;

            try
            {
                ent = (HashEntry) o;
            }
            catch (ClassCastException ex)
            {
                return false;
            }

            return (ent.getKey() == key) && (ent.getValue() == value);
        }

        public String toString()
        {
            return "(" + key + "," + value + ")";
        }
    }

    /**
     * Private data fields
     */
    private int tableSize;
    private int size;
    private HashEntry<K,V>[] table;
    private int primeSize;

    /**
     * Constructor
     * @param ts
     */
    public DoubleHashMap(int ts){
        size=0;
        tableSize=ts;
        table=new HashEntry[tableSize];
        for (int i = 0; i <tableSize ; i++)
            table[i]=null;
        primeSize= getFirst();
    }

    /**
     * PrimeSize icin ilk degerini veriyor
     * @return int
     */
    public int getFirst(){
        for (int i = tableSize-1; i >=1 ; i--) {
            int fact=0;
            for (int j = 2; j <=(int)Math.sqrt(i) ; j++) {
                if(i%j==0)
                    fact++;
            }
            if(fact==0)
                return i;
        }
        return 3;
    }

    /**
     * Main
     * @param args
     */
    public static void main(String args[]){
        try{
            System.out.println("::::::::::::::::::::::::::::OBJE 1:");
            DoubleHashMap<String,Integer> obj1=new DoubleHashMap<>(5);
            obj1.put("burak",22);
            obj1.printHashTable();
            obj1.put("cagla",21);
            obj1.printHashTable();
            obj1.put("tahir",23);
            obj1.printHashTable();
            obj1.put("bahar",20);
            obj1.printHashTable();
            obj1.put("tuana",22);
            System.out.println("obj1.get(tuana):"+obj1.get("tuana").toString());
            obj1.remove("cagla");
            System.out.println("obj1.remove(cagla)");
            obj1.printHashTable();
            //System.out.println("obj1.get(cagla)"+obj1.get("cagla"));
            System.out.println(":::::::::::::::::::::::OBJE 2");
            DoubleHashMap<String,Integer> obj2=new DoubleHashMap<>(3);
            obj2.put("a",1);
            obj2.printHashTable();
            obj2.put("x",2);
            obj2.printHashTable();
            obj2.put("b",3);
            obj2.printHashTable();
            obj2.put("c",1);
            obj2.remove("a");
            obj2.printHashTable();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Return table size
     * @return int
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * HashTable dolu bos kontrol eder
     * @return true or false
     */
    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    /**
     * Key i girilen objenın varsa value yoksa null return eder
     * @param key
     * @return V
     */
    @Override
    public V get(Object key) {
        int hash1= hashFirst(key);
        int hash2= hashSecond(key);
        while(table[hash1]!=null && !table[hash1].key.equals(key)){
            hash1 += hash2;
            hash1 %= tableSize;
        }
        return table[hash1].value;
    }

    /**
     * HashTable e yenı bır HashEntry sınıf koyar ve koyulan objenın valuesunu return eder.
     * @param key
     * @param value
     * @return V
     */
    @Override
    public V put(K key, V value) {
        if (size == tableSize)
        {
            System.out.println("Table full");
            return null;
        }
        int hash1 = hashFirst( key );
        int hash2 = hashSecond( key );
        while (table[hash1] != null)
        {
            hash1 += hash2;
            hash1 %= tableSize;
        }
        table[hash1] = new HashEntry(key, value);
        size++;
        return table[hash1].value;
    }

    /**
     * Objenın keyıne karsılık gelen elemean varsa sıler ve valuesunu return eder yoksa null return eder.
     * @param key
     * @return V
     */
    @Override
    public V remove(Object key) {
        int hash1 = hashFirst( key );
        int hash2 = hashSecond( key );
        V val;
        while (table[hash1] != null && !table[hash1].key.equals(key))
        {
            hash1 += hash2;
            hash1 %= tableSize;
        }
        val=table[hash1].value;
        table[hash1] = null;
        size--;
        return val;
    }

    /**
     * HashTable ı temızler bosaltır
     */
    @Override
    public void clear() {
        size=0;
        for (int i = 0; i < tableSize; i++) {
            table[i]=null;
        }
    }

    /**
     * Objenın ılk hash degerını return eder
     * @param x
     * @return int
     */
    private int hashFirst(Object x )
    {
        int hashVal = x.hashCode( );
        hashVal %= tableSize;
        if (hashVal < 0)
            hashVal += tableSize;
        return hashVal;
    }

    /**
     * Objenın ıkınıc hash degerını return eder.
     * @param x
     * @return int
     */
    private int hashSecond(Object x )
    {
        int hashVal = x.hashCode( );
        hashVal %= tableSize;
        if (hashVal < 0)
            hashVal += tableSize;
        return primeSize - hashVal % primeSize;
    }

    /**
     * Hash table ı print eder.
     */
    public void printHashTable()
    {
        System.out.println("\nHash Table");
        for (int i = 0; i < tableSize; i++){
            System.out.print(i+":");
            if(table[i]!=null){
                System.out.print(table[i].key +" "+table[i].value);
            }
            System.out.println();
        }
    }
    ///////////////////////////////////////////////////////

    /**
     * Parametredkı tum map i put eder
     * @param m
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    /**
     * Key kumesını retun eder
     * @return Set
     */
    @Override
    public Set<K> keySet() {
        return null;
    }

    /**
     * Tabledekı tum valueları return eder
     * @return Collecton
     */
    @Override
    public Collection<V> values() {
        return null;
    }

    /**
     * --------
     * @return Set
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * ---------
     * @param key
     * @return true or false
     */
    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    /**
     * ----------
     * @param value
     * @return true or false
     */
    @Override
    public boolean containsValue(Object value) {
        return false;
    }

}
