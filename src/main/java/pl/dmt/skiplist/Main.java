package pl.dmt.skiplist;

import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {
        final SkipList<Integer> sl = new SkipList<>();
        int[] data = {4, 2, 7, 0, 9, 1, 3, 7, 3, 4, 5, 6, 0, 2, 8};
        for (int i : data) {
            sl.insert(i);
        }

        sl.print();
        sl.search(4);

        sl.delete(4);
        sl.delete(4);
        sl.search(4);

        System.out.println("Inserting 10");
        sl.insert(10);
        sl.print();
        sl.search(10);
    }
}

interface SkippableList<T extends Comparable<? super T>> {

    int LEVELS = 5;

    boolean delete(T target);

    void print();

    void insert(T data);

    SkipNode<T> search(T data);
}

class SkipList<T extends Comparable<? super T>> implements SkippableList<T> {

    private final SkipNode<T> head = new SkipNode<>(null);
    private final SecureRandom rand = new SecureRandom();

    @Override
    public void insert(final T data) {
        final SkipNode<T> skipNode = new SkipNode<>(data);
        for (int i = 0; i < LEVELS; ++i) {
            if (rand.nextInt((int) Math.pow(2, i)) == 0) {
                //insert with prob = 1/(2^i)
                insert(skipNode, i);
            }
        }
    }

    @Override
    public boolean delete(final T target) {
        System.out.println("Deleting " + target);
        final SkipNode<T> victim = search(target, true);
        if (victim == null) {
            return false;
        }
        victim.setData(null);

        for (int i = 0; i < LEVELS; ++i) {
            head.refreshAfterDelete(i);
        }

        System.out.println("deleted...");
        return true;
    }

    @Override
    public SkipNode<T> search(final T data) {
        return search(data, true);
    }

    @Override
    public void print() {
        for (int i = LEVELS - 1; i >= 0; --i) {
            head.print(i);
        }
        System.out.println("---------------");
        System.out.println();
    }

    private void insert(final SkipNode<T> SkipNode, int level) {
        head.insert(SkipNode, level);
    }

    private SkipNode<T> search(final T data, boolean print) {
        SkipNode<T> result = null;
        for (int i = LEVELS - 1; i >= 0; --i) {
            if ((result = head.search(data, i, print)) != null) {
                if (print) {
                    System.out.println("Found " + data.toString() + " at level " + i + ", so stopped");
                    System.out.println();
                }
                break;
            }
        }

        return result;
    }
}

class SkipNode<N extends Comparable<? super N>> {

    private N data;

    @SuppressWarnings("unchecked")
    private SkipNode<N>[] next = new SkipNode[SkippableList.LEVELS];

    public SkipNode(final N data) {
        this.data = data;
    }

    public void refreshAfterDelete(int level) {
        SkipNode<N> current = this;
        while (current != null && current.getNext(level) != null) {
            if (current.getNext(level).data == null) {
                final SkipNode<N> successor = current.getNext(level).getNext(level);
                current.setNext(successor, level);
                return;
            }

            current = current.getNext(level);
        }
    }

    private void setNext(final SkipNode<N> next, int level) {
        this.next[level] = next;
    }

    private SkipNode<N> getNext(int level) {
        return this.next[level];
    }

    public N getData() {
        return data;
    }

    public void setData(N data) {
        this.data = data;
    }

    public SkipNode<N> search(final N data, int level, boolean print) {
        if (print) {
            System.out.print("Searching for: " + data + " at ");
            print(level);
        }

        SkipNode<N> result = null;
        SkipNode<N> current = this.getNext(level);
        while (current != null && current.data.compareTo(data) < 1) {
            if (current.data.equals(data)) {
                result = current;
                break;
            }

            current = current.getNext(level);
        }

        return result;
    }

    public void insert(final SkipNode<N> skipNode, int level) {
        SkipNode<N> current = this.getNext(level);
        if (current == null) {
            this.setNext(skipNode, level);
            return;
        }

        if (skipNode.data.compareTo(current.data) < 1) {
            this.setNext(skipNode, level);
            skipNode.setNext(current, level);
            return;
        }

        while (current.getNext(level) != null
                && current.data.compareTo(skipNode.data) < 1
                && current.getNext(level).data.compareTo(skipNode.data) < 1) {

            current = current.getNext(level);
        }

        final SkipNode<N> successor = current.getNext(level);
        current.setNext(skipNode, level);
        skipNode.setNext(successor, level);
    }

    public void print(int level) {
        System.out.print("level " + level + ": [ ");
        int length = 0;
        SkipNode<N> current = this.getNext(level);
        while (current != null) {
            length++;
            System.out.print(current.data + " ");
            current = current.getNext(level);
        }

        System.out.println("], length: " + length);
    }
}