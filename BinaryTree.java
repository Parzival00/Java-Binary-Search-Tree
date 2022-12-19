public class BinaryTree {
    Node root;
    static int preIndex = 0;
    int size;
    BinaryTree(double[] pre, double in[], int size){
        this.size = size;
        root = buildTree(in, pre, 0, size-1);
    }

    Node buildTree(double in[], double pre[], int inStart, int inEnd)
    {
        if (inStart > inEnd)
            return null;
 
        /* Pick current node from Preorder traversal using preIndex
           and increment preIndex */
        Node tNode = new Node(pre[preIndex++]);
 
        /* If this node has no children then return */
        if (inStart == inEnd)
            return tNode;
 
        /* Else find the index of this node in Inorder traversal */
        int inIndex = search(in, inStart, inEnd, tNode.value);
 
        /* Using index in Inorder traversal, construct left and
           right subtress */
        tNode.left = buildTree(in, pre, inStart, inIndex - 1);
        tNode.right = buildTree(in, pre, inIndex + 1, inEnd);
 
        return tNode;
    }

    //Finds the index of the value in the array
    int search(double arr[], int strt, int end, double value)
    {
        int i;
        for (i = strt; i <= end; i++) {
            if (arr[i] == value)
                return i;
        }
        return i;
    }

    
    boolean isBST(Node node)
    {
        if (node == null)
            return true;
     
        /* False if left is > than node */
        if (node.left != null && node.left.value > node.value)
            return false;
     
        /* False if right is < than node */
        if (node.right != null && node.right.value < node.value)
            return false;
     
        /* False if, recursively, the left or right is not a BST */
        if (!isBST(node.left) || !isBST(node.right))
            return false;
     
        /* Passing all that, it's a BST */
        return true;
    }

    double findMax(Node node)
    {
        if (node == null)
            return Double.MIN_VALUE;
 
        double res = node.value;
        double lres = findMax(node.left);
        double rres = findMax(node.right);
 
        if (lres > res)
            res = lres;
        if (rres > res)
            res = rres;
        return res;
    }

    int depth(Node node)
    {
        if (node == null)
            return -1;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = depth(node.left);
            int rDepth = depth(node.right);
  
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
    }


    double tree_sum(Node node){
        if (node == null)
            return 0;

        return node.value + tree_sum(node.left) + tree_sum(node.right);
    }

    double tree_average(){
        return tree_sum(root) / size;
    }

    boolean tree_is_balanced(Node node){
        if(node == null)
            return true;

        int lDepth = depth(node.left);
        int rDepth = depth(node.right);
    
        if(lDepth != rDepth)
            return false;
        else return true;
    }

    void printInorder(Node node)
    {
        if (node == null){
            return;
        }
            
 
        /* first recur on left child */
        printInorder(node.left);
 
        /* then print the data of node */
        System.out.print(node.value + " ");
 
        /* now recur on right child */
        printInorder(node.right);
    }
}
