package autoComplete;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A prefix tree used for autocompletion. The root of the tree just stores links to child nodes (up to 26, one per letter).
 * Each child node represents a letter. A path from a root's child node down to a node where isWord is true represents the sequence
 * of characters in a word.
 */
public class PrefixTree {
    private TreeNode root; 

    // Number of words contained in the tree
    private int size;

    public PrefixTree(){
        root = new TreeNode();
    }

    /**
     * Adds the word to the tree where each letter in sequence is added as a node
     * If the word, is already in the tree, then this has no effect.
     * @param word
     */
    public void add(String word){
        if (contains(word)) {
            // Do nothing
        } else {
            TreeNode current = root;
            for (int i=0; i < word.length(); i++){
                if (current.children.containsKey(word.charAt(i))){
                    current = current.children.get(word.charAt(i));
                    if (i == word.length()-1){
                        current.isWord = true;
                        size++;
                    }
                } else {
                    TreeNode newNode = new TreeNode();
                    newNode.letter = word.charAt(i);
                    current.children.put(word.charAt(i), newNode);
                    current = newNode;
                    if (i == word.length()-1){
                        newNode.isWord = true;
                        size++;
                    }

                }
            }
        }
    }

    /**
     * Checks whether the word has been added to the tree
     * @param word
     * @return true if contained in the tree.
     */
    public boolean contains(String word){
        TreeNode current = root; 
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.children.containsKey(c)) {
                return false; 
            }
            current = current.children.get(c); 
        }
        return current.isWord; 

    }

    /**
     * Finds the words in the tree that start with prefix (including prefix if it is a word itself).
     * The order of the list can be arbitrary.
     * @param prefix
     * @return list of words with prefix
     */
    public ArrayList<String> getWordsForPrefix(String prefix){
        TreeNode current = root; 
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!current.children.containsKey(c)) {
                return new ArrayList<>(); 
            }
            current = current.children.get(c); 
        }

        ArrayList<String> result = new ArrayList<>();
        collectWords(current, new StringBuilder(prefix), result);
        return result;
    }

    /**
     * Helper method to collect all words in the tree starting from the given node.
     * @param node
     * @param prefix
     * @param result
     */
    private void collectWords(TreeNode node, StringBuilder prefix, List<String> result) {
        if (node.isWord) {
            result.add(prefix.toString()); 
        }
        for (Map.Entry<Character, TreeNode> entry : node.children.entrySet()) {
            prefix.append(entry.getKey()); 
            collectWords(entry.getValue(), prefix, result); 
            prefix.deleteCharAt(prefix.length() - 1); 
        }
    }

    /**
     * @return the number of words in the tree
     */
    public int size(){
        return size;
    }
    
}
