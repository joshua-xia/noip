package com.yunlongstudio.algorithm.tree;

import java.util.ArrayList;
import java.util.Stack;


public class SimpleTree {
	public static void main(String[] args) {

        Node root= (new Node(0)).createTree();
        System.out.print("前序遍历： ");
        new Traverse().proOrder(root);
        System.out.println();
        System.out.print("前序递归遍历： ");
        new Traverse().recursiveProOrder(root);
        System.out.println();
        System.out.print("中序遍历： ");
        new Traverse().inOrder(root);
        System.out.println();
        System.out.print("中序递归遍历： ");
        new Traverse().recursiveInOrder(root);
        System.out.println();
        System.out.print("后序遍历： ");
        new Traverse().postOrder(root);
        System.out.println();
        System.out.print("后序递归遍历： ");
        new Traverse().recursivePostOrder(root);
        System.out.println();
        System.out.print("后序遍历： ");
        new Traverse().postOrderStack(root);
   }
	
  public static class Node {
    // 节点值
    public int value;

    // 左子节点
    public Node left;

    // 右子节点
    public Node right;
    
    //public Node[] children;

    Node(int value) {
      this.value = value;
    }

    Node(int value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    /**
     * 创建一颗二叉树
     *
     * @return 根节点
     */
    public Node createTree() {
      Node root = new Node(0);
      Node node1 = new Node(1);
      Node node2 = new Node(2);
      Node node3 = new Node(3);
      Node node4 = new Node(4);
      Node node5 = new Node(5);
      Node node6 = new Node(6);
      Node node7 = new Node(7);

      root.left = node1;
      root.right = node2;
      node1.left = node3;
      node1.right = node4;
      node2.left = node5;
      node2.right = node6;
      node3.left = node7;
      return root;
    }
  }

  public static class Traverse {

	  /**
	  DFS(Depth-First Search)和BFS(Breadth-First Search)是两种用于访问图(graph)中节点最普遍的两种方法。
	  在我的日常编程中，使用的graph的绝大部分都是树(tree). 
	  Tree是一种connected acyclic graph - 连通(即从一个节点一定有路径到其他任何节点)的无循环图. 
	  
	  DFS 深度优先
	  假设右边的树代表一个公司的组织架构，每个node是一个部门。若要计算部门c的总收入，我们必须先知道e和f的，
	  而要知道e的总收入我们必须先知道g和h，这就是典型的深度优先。深度优先通过递归来实现，代码如下：

	  visitDFS(a); // visit from the root  
	    
	  // performs DFS from the given node  
	  public void visitDFS(Node nodeStart) {  
	    // (1) Entering node - pre-order walk  
	    for(Node child : nodeStart.children) {  
	      visitDFS(child); // recursive call  
	    }  
	    // (2) Leaving node - post-order walk  
	    System.out.println("DFS: " + nodeStart.nodeName);  
	  }  
	  在代码中，我做了两个标记(1)和(2). 在(1)处，当前节点的所有下属节点尚未被访问，而在(2)处，其所有下属节点均被处理完毕。
	  若要计算部门收入，很明显是在(2)处。上面代码打印结果是：d b g h e f c a

	  BFS 广度优先
	  假设我们要打印各部门收入报表给CEO，因为每个部门收入数据较多，CEO倾向先看各大部门(只有对某部门数据有疑惑时，才看小部门), 
	  这样情况下是先打印大部门，再打印小部门，低等级部门后打印，这就是广度优先的情况。graph的BFS算法较繁琐，但tree的BFS就非常简单了：

	  visitBFS(a); // visit from the root  
	    
	  // performs BFS from the given node  
	  public void visitBFS(Node nodeStart) {  
	    List<node> pendingExplore = new ArrayList<node>(); // list of nodes pending exploration  
	    pendingExplore.add(nodeStart);  
	    while(pendingExplore.size() > 0) {  
	      Node currentNode = pendingExplore.remove(0);  
	      System.out.println("BFS: " + currentNode.nodeName);  
	      pendingExplore.addAll(currentNode.children); // (3)  
	    }  
	  }
	  */
    /**
     * 递归前序遍历
     *
     * @param root
     */
    public void recursiveProOrder(Node root) {
      // 遍历根节点
      if (root != null) {
        System.out.print(root.value);
      }
      // 遍历左子树
      if (root.left != null) {
        recursiveProOrder(root.left);
      }
      // 遍历右子树
      if (root.right != null) {
         recursiveProOrder(root.right);
      }
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public void proOrder(Node root) {
      // 使用ArrayList作为堆栈
      ArrayList<Node> stack = new ArrayList<Node>();
      // 栈指针
      int top = -1;
      Node current = root;
      while (true) {
        if (current != null) {
          System.out.print(current.value);
        }
        // 右子节点进栈
        if (current.right != null) {
          stack.add(current.right);
          top++;
        }
        // 左子节点进栈
        if (current.left != null) {
          stack.add(current.left);
          top++;
        }
        // 如果栈内还有节点，栈顶节点出栈
        if (top > -1) {
          current = stack.get(top);
          stack.remove(top--);
        } else {
          break;
        }
      }
    }

    /**
     * 递归中序遍历
     *
     * @param root
     */
    public void recursiveInOrder(Node root) {
      if (root != null) {
        if (root.left != null) {
          recursiveInOrder(root.left);
        }
        System.out.print(root.value);
        if (root.right != null) {
          recursiveInOrder(root.right);
        }
      }
	}

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrder(Node root) {
      if (root != null) {
        ArrayList<Node> stack = new ArrayList<Node>();
        int top = -1;
        Node current = root;
        while (current != null || top > -1) {
          if (current != null) {
            // 一直深入地寻找左子节点，并将路上的节点都进栈
            stack.add(current);
            top++;
            current = current.left;
          } else {
            // 取出栈顶节点，并继续遍历右子树
            current = stack.get(top);
            stack.remove(top--);
            System.out.print(current.value);
            current = current.right;
          }
        }
      }
    }

   /**
    * 递归后续遍历
    *
    * @param root
    */
	    public void recursivePostOrder(Node root) {
	        if (root != null) {
	            if (root.left != null) {
	                recursivePostOrder(root.left);
	            }
	            if (root.right != null) {
	                recursivePostOrder(root.right);
	            }
	            System.out.print(root.value);
	        }
	    }

	    /**
	     * 后序遍历：可以被遍历的节点都要进栈出栈两次，所以添加第二个栈用来标示进栈次数
	     *
	     * @param root
	     */
	    public void postOrder(Node root) {
	        if (root != null) {
	            // 用来保存节点的栈
	            ArrayList<Node> stack = new ArrayList<Node>();
	            // 用来保存标志位的栈
	            ArrayList<Integer> stack2 = new ArrayList<Integer>();
	            // 两个栈共用的栈指针
	            int top = -1;
	            int tag;
	            Node current = root;
	            do {
	                //将所有左子节点进栈
	                while (current != null) {
	                    stack.add(current);
	                    stack2.add(0);
	                    top++;
	                    current = current.left;
	                }
	                //取出栈顶节点，并判断其标志位
	                current = stack.get(top);
	                tag = stack2.get(top);
	                stack2.remove(top);
	                if (tag == 0) {
	                    // tag为0,表明该节点第一次进栈，还需要进栈一次，同时修改标志位
	                    current = current.right;
	                    stack2.add(1);
	                } else {
	                    // tag不位0,表明非首次进栈，可以遍历了。
	                    stack.remove(top);
	                    top--;
	                    System.out.print(current.value);
	                    current = null;
	                }
	            } while (current != null || top != -1);
	        }
	    }
	    
	    //nonRecursion postOrder Traverse  
	    public void postOrderStack(Node p){  
	        Stack<Node> stack = new Stack<Node>();  
	        Node q = p;//q,is the latest Node that has been visited  
	        while(p != null) {  
	            while(p.left != null){  
	                stack.push(p);  
	                p = p.left;  
	            }  
	             
	            while(p != null && (p.right == null || p.right == q)) {  
	            	System.out.print(p.value);  
	                q = p; 
	                if (stack.isEmpty()) {
	                  return;  
	                }
	                p = stack.pop();  
	            }  
	            
	            stack.push(p);  
	            p = p.right;  
	        }  
	    }  
	}
}
