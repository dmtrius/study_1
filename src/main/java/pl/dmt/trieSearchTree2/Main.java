package pl.dmt.trieSearchTree2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Trie prefixTree = new Trie();

        prefixTree.insert("GOING");
        prefixTree.insert("GONG");
        prefixTree.insert("PAKISTAN");
        prefixTree.insert("SHANGHAI");
        prefixTree.insert("GONDAL");
        prefixTree.insert("GODAY");
        prefixTree.insert("GODZILLA");

        if (prefixTree.startsWith("GO")) {
            final TrieNode tn = prefixTree.searchNode("GO");
            prefixTree.wordsFinderTraversal(tn, 0);
            prefixTree.displayFoundWords();
        }

        if (prefixTree.startsWith("GOD")) {
            final TrieNode tn = prefixTree.searchNode("GOD");
            prefixTree.wordsFinderTraversal(tn, 0);
            prefixTree.displayFoundWords();
        }
    }
}

class Trie {
    private TrieNode root = new TrieNode();
    private List<String> words = new ArrayList<>();
    private TrieNode prefixRoot;
    private String curPrefix;

    // Inserts a word into the trie.
    public void insert(final String word) {
        Map<Character, TrieNode> children = root.getChildren();

        TrieNode crntparent;

        crntparent = root;

        //cur children parent = root
        for (int i = 0; i < word.length(); i++) {
            final Character c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                t.setParent(crntparent);
                children.put(c, t);
            }

            children = t.getChildren();
            crntparent = t;

            //set leaf node
            if (i == word.length() - 1) {
                t.setLeaf(true);
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(final String word) {
        final TrieNode t = searchNode(word);
        return t != null && t.isLeaf();
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(final String prefix) {
        return searchNode(prefix) != null;
    }

    public TrieNode searchNode(final String str) {
        Map<Character, TrieNode> children = root.getChildren();
        TrieNode t = null;
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.getChildren();
            } else {
                return null;
            }
        }

        this.prefixRoot = t;
        this.curPrefix = str;
        this.words.clear();
        return t;
    }

    public void wordsFinderTraversal(final TrieNode node, int offset) {
        //System.out.print(node, offset);

        if (node.isLeaf()) {
            //System.out.println("leaf node found");

            TrieNode altair;
            altair = node;

            final Stack<String> hstack = new Stack<>();

            while (altair != prefixRoot) {
                //System.out.println(altair.c);
                hstack.push(Character.toString(altair.getC()));
                altair = altair.getParent();
            }

            final StringBuilder wrd = new StringBuilder(curPrefix);

            while (!hstack.empty()) {
                wrd.append(hstack.pop());
            }

            //System.out.println(wrd);
            words.add(wrd.toString());
        }

        final Set<Character> kset = node.getChildren().keySet();
        //System.out.println(node.c);
        //System.out.println(node.isLeaf);
        //System.out.println(kset);
        final Iterator itr = kset.iterator();
        List<Character> aloc = new ArrayList<>();
        while (itr.hasNext()) {
            Character ch = (Character) itr.next();
            aloc.add(ch);
            //System.out.println(ch);
        }

        // here you can play with the order of the children
        for (final Character anAloc : aloc) {
            wordsFinderTraversal(node.getChildren().get(anAloc), offset + 2);
        }
    }

    public void displayFoundWords() {
        System.out.println("_______________");
        words.forEach(System.out::println);
        System.out.println("_______________");

    }
}

class TrieNode {
    private Character c;
    private TrieNode parent;
    private Map<Character, TrieNode> children = new HashMap<>();
    private boolean isLeaf;

    public TrieNode() {
    }

    public TrieNode(Character c) {
        this.c = c;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public Character getC() {
        return c;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}