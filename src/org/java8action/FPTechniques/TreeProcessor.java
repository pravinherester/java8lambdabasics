package org.java8action.FPTechniques;

public class TreeProcessor {

    public static int lookup(String key, int defautvalue, Tree t) {
        if (t == null)
            return defautvalue;
        if (t.key == key)
            return t.value;
        return lookup(key, defautvalue, key.compareTo(t.key) < 0 ? t.left : t.right);

    }

    public static void update(String key, int newValue, Tree t) {
        if (t == null) {
            new Tree(key, newValue, null, null);
        }
        if (t.key == key) {
            t.value = newValue;

        } else
            update(key, newValue, key.compareTo(t.key) < 0 ? t.left : t.right);

    }

    public static void main(String[] args) {
        Tree t = new Tree("pravin", 32, null, null);
        Tree t1 = new Tree("nandhini", 28, null, null);
        Tree t2 = new Tree("nithik", 1, null, null);
        int v = lookup("nithik", 1, t2);
        v = lookup("nandhini", 28, t1);
        v = lookup("pravin", 32, t);

        System.out.println(v);
        update("pravin", 33, t);
        v = lookup("pravin", 5, t);
        System.out.println(v);

    }
}

class Tree {

    String key;
    int value;
    Tree left, right;

    public Tree(String key, int value, Tree left, Tree right) {
        super();
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

}