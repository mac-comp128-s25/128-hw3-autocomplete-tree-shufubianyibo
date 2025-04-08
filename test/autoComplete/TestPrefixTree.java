package autoComplete;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Class to test the tree implementation
 */
public class TestPrefixTree {
    
    @Test
    public void testAdd(){
        PrefixTree tree = new PrefixTree();
        tree.add("cat");
        tree.add("muse");
        tree.add("muscle");
        tree.add("musk");
        tree.add("po");
        tree.add("pot");
        tree.add("pottery");
        tree.add("potato");
        tree.add("possible");
        tree.add("possum");
        tree.add("pot");
        assertEquals(10, tree.size());
    }

    @Test
    public void testContains(){
        PrefixTree tree = new PrefixTree();
        tree.add("cat");
        tree.add("muse");
        tree.add("muscle");
        tree.add("musk");
        tree.add("po");
        tree.add("pot");
        tree.add("pottery");
        tree.add("potato");
        tree.add("possible");
        tree.add("possum");
        tree.add("pot");
        assertFalse(tree.contains("mu"));
        assertFalse(tree.contains("dog"));
        assertTrue(tree.contains("pot"));
        assertTrue(tree.contains("pottery"));
        assertTrue(tree.contains("possum"));
        assertEquals(10, tree.size());
    }

    @Test
    public void testPrefix(){
        PrefixTree tree = new PrefixTree();
        tree.add("cat");
        tree.add("muse");
        tree.add("muscle");
        tree.add("musk");
        tree.add("poe");
        tree.add("pot");
        tree.add("pottery");
        tree.add("potato");
        tree.add("possible");
        tree.add("possum");
        tree.add("pot");
        ArrayList<String> result = tree.getWordsForPrefix("pot");
        assertEquals(3, result.size());
        assertTrue(result.contains("pot"));
        assertTrue(result.contains("pottery"));
        assertTrue(result.contains("potato"));
        
        
        result = tree.getWordsForPrefix("mu");
        assertEquals(3, result.size());
        assertTrue(result.contains("muse"));
        assertTrue(result.contains("muscle"));
        assertTrue(result.contains("musk"));
    }
}
