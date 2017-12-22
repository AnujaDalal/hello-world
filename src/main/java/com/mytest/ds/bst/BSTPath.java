package com.mytest.ds.bst;

import java.util.ArrayList;
import java.util.List;

/*
*  Find distance between 2 nodes.
          55
*      /      \
*     45       75
*    / \       / \
*   25 50     57 100
*  /
* 20
* InOrder: 20, 25, 45, 50, 55, 57, 75, 100
*/
public class BSTPath
{
  static class Node {
    
    int data;
    Node left;
    Node right;
    
    public Node(int data)
    {
      this.data = data;
    }
  }
  
  public static void main(String[] args)
  {
    log("begins...\n");
    
    int[] nums = {55, 45, 75, 100, 25, 50, 20, 57};
        
    Node root = null;
    
    root = createTree(root, nums);

    printInOrder(root);
    log("\n");
    
    int i = 20, j = 100;
    Node commonRoot = findCommonParent(root, i, j);
    
    Node n1 = findNode(root, i);
    Node n2 = findNode(root, j);
    
    int distance = findDistance(commonRoot, n1, n2);
    
    log(String.format("Distance between 2 nodes %d and %d is %d.", i, j, distance));
    
    log("\n \nend!");
  }
  
  private static int findDistance(Node commonRoot, Node n1, Node n2)
  {
    int distance = -1;
    
    if (n1 == null || n2 == null)
    {	return distance;	}
    
    log("Common root is " + (commonRoot == null ? null : commonRoot.data));
    Node node = commonRoot;
    while (node != null )
    {
      distance ++;
      if (node.data == n1.data)
      {
        break;
      }
      if (n1.data > node.data)
      {
        node = node.right;
      }
      else
      {
        node = node.left;
      }
    }
    
    node = commonRoot;
    while (node != null)
    {
      if (node.data == n2.data)
      {
        break;
      }
      if (n2.data > node.data)
      {
        node = node.right;
      }
      else
      {
        node = node.left;
      }
      distance ++;
    }
    return distance;
  }

  private static Node findCommonParent(Node root, int i, int j)
  { 
    Node node = root;
    Node commonParent = null;
    List<Node> traversed = new ArrayList<>();
    
    boolean leaf = (node == null) || (node.left == null && node.right == null);
    while (!leaf)
    { 
      traversed.add(node);
      
      if (node.data ==  i) {  break;  }   // first node found
      
      if (i > node.data)
      { node = node.right;  }
      else
      { node = node.left; }
      leaf = (node == null) || (node.left == null && node.right == null);
    }
    
    node = root;
    leaf = (node == null) || (node.left == null && node.right == null);
    while (!leaf)
    {
      if (traversed.contains(node))
      { commonParent = node;  }       // dont stop, last common node will be common parent
      else
      { traversed.add(node);  }
      
      if (node.data ==  j) {  break;  }   // second node found
      
      if (j > node.data)
      { node = node.right;  }
      else
      { node = node.left; }
      leaf = (node == null) || (node.left == null && node.right == null);  
    }
    
    return commonParent;
  }

  private static Node findNode(Node root, int i)
  {
    //Preorder traverse
    if (root == null || root.data == i) 
    {
      return root;
    }
    
    if (i > root.data)
    {
      return findNode(root.right, i);
    }
    
    return findNode(root.left, i);
  }

  private static Node createTree(Node root, int[] nums)
  {
    for (int i = 0; i < nums.length; i++)
    {
      Node node = new Node(nums[i]);
      root = insertNode(root, node );
    }
    return root;
  }

  private static Node insertNode(Node parent, Node child)
  {
    if (parent == null)
    { 
      parent = child; 
    }
    else
    {

      if (child.data > parent.data)
      {        
        parent.right = insertNode(parent.right, child);
      }
      else
      {
        parent.left = insertNode(parent.left, child);
      }
    }
    return parent;
  }

  private static void log(String str)
  {
    System.out.println(str);
  }  
  
  private static void printInOrder(Node node)
  {
	  if (node == null) return; 
	  
	  printInOrder(node.left);
	  System.out.print(node.data + "  ");
	  printInOrder(node.right);	  
  } 
  
}
