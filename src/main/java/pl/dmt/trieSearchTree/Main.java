package pl.dmt.trieSearchTree;

public class Main {

    public static void main(String... args) {
        final TrieI trie = new Trie();
        trie.insert("capacity");
        trie.insert("java");
        trie.insert("cpp");
        trie.insert("ada");
        trie.insert("vanish");
        trie.insert("ship");

        System.out.println(trie.search("ship"));
        System.out.println(trie.startsWith("d"));

        System.out.println(trie);
    }
}

interface TrieI {
    void insert(String word);
    boolean search(String word);
    boolean startsWith(String prefix);
}

class Trie implements TrieI {
    private final TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    @Override
    public void insert(final String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (p.arr[index] == null) {
                final TrieNode temp = new TrieNode();
                p.arr[index] = temp;
                p = temp;
            } else {
                p = p.arr[index];
            }
        }
        p.isEnd = true;
    }

    // Returns if the word is in the trie.
    @Override
    public boolean search(final String word) {
        final TrieNode p = searchNode(word);
        if (p == null) {
            return false;
        } else {
            return p.isEnd;
        }

    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    @Override
    public boolean startsWith(final String prefix) {
        final TrieNode p = searchNode(prefix);
        return p != null;
    }

    private TrieNode searchNode(final String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            int index = character - 'a';
            if (p.arr[index] != null) {
                p = p.arr[index];
            } else {
                return null;
            }
        }

        if (p == root)
            return null;

        return p;
    }

    private class TrieNode {

        private static final int CAPACITY = 26;

        final TrieNode[] arr;
        boolean isEnd;

        // Initialize your data structure here.
        private TrieNode() {
            this.arr = new TrieNode[CAPACITY];
        }
    }
}
