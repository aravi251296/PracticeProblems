import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapCode {
    static class HashMap<K, V> {//generics

        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n; //nodes
        private int N; //N-BUCKETS
        private LinkedList<Node> buckets[]; //N=BUCKETS.LENGTH

        @SuppressWarnings("unchecked")
        public HashMap() {
            this.N = 4;
            this.buckets = new LinkedList[4];

            for (int i = 0; i < 4; i++) {
                this.buckets[i] = new LinkedList<>();
            }
        }

        private int hashFunction(K key) {

            int bi = key.hashCode();
            return Math.abs(bi) % N;

        }

        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];

            for (int i = 0; i < ll.size(); i++) {
                if (ll.get(i).key == key) {
                    return i; //di
                }
            }
            return -1;
        }

        @SuppressWarnings("unchecked")

        private void rehash() {
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[N * 2];

            for (int i = 0; i < N * 2; i++) {
                buckets[i] = new LinkedList<>();
            }

            for (int i = 0; i < oldBucket.length; i++) {
                LinkedList<Node> ll = oldBucket[i];
                for (int j = 0; j < ll.size(); i++) {
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); //di=-1

            if (di == -1) {//key doesnot exists
                buckets[bi].add(new Node(key, value));
            } else {//key exists
                Node node = buckets[bi].get(di);
                node.value = value;
            }

            double lambda = (double) n/N;
            if (lambda > 2.0) {
                rehash(); //rehashing
            }

        }

        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); //di=-1

            if (di == -1) {//key doesnot exists
                return false;
            } else {//key exists
                return true;
            }

        }

        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); //di=-1

            if (di == -1) {//key doesnot exists
                return null;
            } else {//key exists
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            }
        }

        public V get(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi); //di=-1

            if (di == -1) {//key doesnot exists
                return null;
            } else {//key exists
                Node node = buckets[bi].get(di);
                return node.value;
            }
        }

        public ArrayList<K> KeySet() {
            ArrayList<K> keys = new ArrayList<>();
            for(int i=0;i< buckets.length;i++) {//bi
                LinkedList<Node> ll = buckets[i];
                for(int j=0;j<ll.size();j++) {//di
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty() {
            return n==0;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer>  map= new HashMap<>();
        map.put("India",100);
        map.put("China",200);
        map.put("USA",502);
//        map.put("USA",500);
//
        ArrayList<String> keys = map.KeySet();
        for(int i=0;i<keys.size();i++){
            System.out.println(keys.get(i)+" "+map.get(keys.get(i)));
        }

        //remove function result
//        map.remove("India");
//        System.out.println(map.get("India"));
    }
}
