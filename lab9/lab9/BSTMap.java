package lab9;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        while(p!=null){
            if(p.key.compareTo(key)>0){
                p = p.left;
            }else if(p.key.compareTo(key)<0){
                p = p.right;
            }else{
                return p.value;
            }
        }
        return null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key,root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p==null){
            p =new Node(key,value);
            size++;
            return p;
        }
        Node curr = p;
        Node parent = p;
        while(p!=null){
            parent = p;
            if(p.key.compareTo(key)>0){
                p = p.left;
            }else if(p.key.compareTo(key)<0){
                p = p.right;
            }else{
                p.value = value;
                return curr;
            }
        }
        if(parent.key.compareTo(key)>0){
            parent.left = new Node(key,value);
        }else{
            parent.right = new Node(key,value);
        }
        size++;
        return curr;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key,value,root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private Set<K> keySetHelper(Node node){
        if(node==null){
            return new HashSet<>();
        }
        Set<K> result = new HashSet<>();
        result.add(node.key);
        Set<K> leftSet = keySetHelper(node.left);
        Set<K> rightSet =keySetHelper(node.right);
        if(leftSet!=null){
            result.addAll(leftSet);
        }
        if(rightSet!=null){
            result.addAll(rightSet);
        }

        return result;
    }
    @Override
    public Set<K> keySet() {
       return keySetHelper(root);
    }
    @Test
    public void testSet(){
        BSTMap<String ,Integer> bstMap =new BSTMap<>();
        bstMap.put("hello",1);
        bstMap.put("hello",2);
        bstMap.put("hellolalal",4);
        bstMap.put("helloing",5);
        Set<String> set = new HashSet<>();
        set.add("hello");
        set.add("hellolalal");
        set.add("helloing");
        assertEquals(set,bstMap.keySet());
    }
    //根据key值找到对应的节点
    private Node findNode(K key){
        Node p =root;
        while(p!=null){
            if(p.key.compareTo(key)>0){
                p = p.left;
            }else if(p.key.compareTo(key)<0){
                p = p.right;
            }else{
                return p;
            }
        }
        return null;
    }
    private Node findParentNode(K key) {
        Node p = root;
        Node parent = null;  // 用来保存父节点
        while (p != null) {
            if (p.key.compareTo(key) > 0) {
                parent = p;  // 记录父节点
                p = p.left;
            } else if (p.key.compareTo(key) < 0) {
                parent = p;  // 记录父节点
                p = p.right;
            } else {
                return parent;  // 返回父节点
            }
        }
        return null;
    }


    private boolean isLeaf(K key){
        Node node = findNode(key);
        if(node==null){
            return false;
        }else{
            return node.left == null && node.right == null;
        }
    }
    //传入的节点是叶子节点
    //第一种情况
    private V deleteLeaf(Node node){
        Node n = findParentNode(node.key);
        V v = node.value;
        if(n.key.compareTo(node.key)>0){
            n.left = null;
        }else{
            n.right = null;
        }
        return v;
    }
    //删除只有一个子节点的节点
    private boolean isOnlyOneSubNode(K key){
        Node node = findNode(key);
        if(node==null){
            return false;
        }else{
            return node.left == null || node.right == null;
        }

    }
    private V deleteOnlyOneSubNode(Node node){
        Node n = findParentNode(node.key);
        V v = node.value;
        if(n.key.compareTo(node.key)>0){
            n.left = node.left;
        }else{
            n.right = node.right;
        }
        return v;
    }
    private boolean isTWoSubNode(K key){
        Node node = findNode(key);
        if(node==null){
            return false;
        }else{
            return node.left == null && node.right == null;
        }
    }
    private V deleteTwoSubNode(Node node) {
        // 找到节点的父节点
        Node parent = findParentNode(node.key);
        V v = node.value;

        // 找到右子树的最小节点作为后继节点
        Node successorParent = node;
        Node successor = node.right;
        while (successor.left != null) {
            successorParent = successor;
            successor = successor.left;
        }

        // 将后继节点的值替换到当前节点
        node.key = successor.key;
        node.value = successor.value;

        // 删除后继节点（注意后继节点最多有右子树，没有左子树）
        if (successorParent == node) {
            // 如果后继节点是当前节点的右子节点
            node.right = successor.right;
        } else {
            // 如果后继节点是当前节点的右子树的某个节点
            successorParent.left = successor.right;
        }

        return v;
    }




    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node node = findNode(key);
        if(node==null){
            return null;
        }
        if(isLeaf(key)){
            deleteLeaf(node);
        }
        else if(isOnlyOneSubNode(key)){
            deleteOnlyOneSubNode(node);
        }else{
            deleteTwoSubNode(node);
        }
        return node.value;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if(get(key)==value){
            return remove(key);
        }else{
            return null;
        }
    }
    public class BSTMapIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        // 构造函数，初始化栈
        public BSTMapIterator(Node root) {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node node = stack.pop();
            pushLeft(node.right);  // 遍历右子树
            return node.key;
        }
    }

    @Override
    public Iterator<K> iterator() {
       return new BSTMapIterator(root);
    }
}
