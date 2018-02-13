package pl.dmt.trieSearchTree3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

    class Node {

        private final char ch;

        /**
         * Flag indicates that this node is the end of the string.
         */
        private boolean end;

        private List<Node> children;

        public Node(char ch) {
            this.ch = ch;
        }

        public void addChild(final Node node) {
            if (this.children == null) {
                this.children = new LinkedList<>();
            }
            this.children.add(node);
        }

        public Node getNode(char ch) {
            if (this.children == null) {
                return null;
            }
            for (final Node child : this.children) {
                if (child.getChar() == ch) {
                    return child;
                }
            }
            return null;
        }

        public char getChar() {
            return ch;
        }

        public List<Node> getChildren() {
            if (this.children == null) {
                return Collections.emptyList();
            }
            return this.children;
        }

        public boolean isEnd() {
            return this.end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }
    }

    Node root = new Node(' ');

    /**
     * Searches for a strings that match the prefix.
     *
     * @param prefix - prefix
     * @return - list of strings that match the prefix, or empty list of no matches are found.
     */
    public List<String> getWordsForPrefix(final String prefix) {
        if (prefix.length() == 0) {
            return Collections.emptyList();
        }
        final Node node = getNodeForPrefix(this.root, prefix);
        if (node == null) {
            return Collections.emptyList();
        }
        final List<LinkedList<Character>> chars = collectChars(node);
        final List<String> words = new ArrayList<>(chars.size());
        for (final LinkedList<Character> charList : chars) {
            words.add(combine(prefix.substring(0, prefix.length() - 1), charList));
        }
        return words;
    }

    private String combine(final String prefix, final List<Character> charList) {
        final StringBuilder sb = new StringBuilder(prefix);
        for (final Character character : charList) {
            sb.append(character);
        }
        return sb.toString();
    }

    private Node getNodeForPrefix(final Node node, final String prefix) {
        if (prefix.length() == 0) {
            return node;
        }
        final Node next = node.getNode(prefix.charAt(0));
        if (next == null) {
            return null;
        }
        return getNodeForPrefix(next, prefix.substring(1, prefix.length()));
    }

    private List<LinkedList<Character>> collectChars(final Node node) {
        final List<LinkedList<Character>> chars = new ArrayList<>();

        if (node.getChildren().size() == 0) {
            chars.add(new LinkedList<>(Collections.singletonList(node.getChar())));
        } else {
            if (node.isEnd()) {
                chars.add(new LinkedList<>(Collections.singletonList(node.getChar())));
            }
            final List<Node> children = node.getChildren();
            for (final Node child : children) {
                final List<LinkedList<Character>> childList = collectChars(child);
                for (final LinkedList<Character> characters : childList) {
                    characters.push(node.getChar());
                    chars.add(characters);
                }
            }
        }
        return chars;
    }

    private void addWord(final String word) {
        addWord(root, word);
    }

    private void addWord(final Node parent, final String word) {
        if (word.trim().length() == 0) {
            return;
        }
        Node child = parent.getNode(word.charAt(0));
        if (child == null) {
            child = new Node(word.charAt(0));
            parent.addChild(child);
        }
        if (word.length() == 1) {
            child.setEnd(true);
        } else {
            addWord(child, word.substring(1, word.length()));
        }
    }

    public static void main(String[] args) {
        final Main tree = new Main();
        tree.addWord("world");
        tree.addWord("work");
        tree.addWord("wolf");
        tree.addWord("life");
        tree.addWord("love");
        System.out.println(tree.getWordsForPrefix("l"));
    }
}
