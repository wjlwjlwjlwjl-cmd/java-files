import java.util.Random;
public class SearchTree{
    public static void main(String[] args){
        MySearchTree mst = new MySearchTree();   
        Random random = new Random();
        int tar1 = 0, tar2 = 0;
        for(int i = 0; i < 20; i++){
            if(i == 5){
                tar1 = random.nextInt(100);
                mst.insert(tar1);
            }
            else if(i == 7){
                tar2 = random.nextInt(100);
                mst.insert(tar2);
            }
            else{
                mst.insert(random.nextInt(100));
            }
        }
        mst.range();
        mst.remove(tar1);
        mst.remove(tar2);
        System.out.println("--------------------------");
        mst.range();
    }
}

class TreeNode{
    public int val;
    public TreeNode left = null;
    public TreeNode right = null;
    public TreeNode parent = null;
    TreeNode(int val){
        this.val = val;
    }
    TreeNode(){
    }
}

class MySearchTree{
    public TreeNode root;
    MySearchTree(){}
    //一共实现查找、插入、删除三种操作
    
    boolean find(int val){
        if(root == null){
            return false;
        }
        TreeNode cur = root;
        while(cur != null){
            if(val > cur.val){
                cur = cur.right;
            }
            else if(val < cur.val){
                cur = cur.left;
            }
            else{
                return true;
            }
        }
        return false;
    }

    boolean insert(int val){
        if(root == null){
            root = new TreeNode(val);
            return true;
        }
        if(find(val)){
            return false;
        }
        TreeNode cur = root;
        TreeNode prev = null;
        while(cur != null){
            prev = cur;
            if(val > cur.val){
                cur = cur.right;
            }
            else{
                cur = cur.left;
            }
        }
        TreeNode newNode = new TreeNode(val);
        if(val > prev.val){
            prev.right = newNode;
        }
        else{
            prev.left = newNode;
        }
        newNode.parent = prev;
        return true;
    }

    boolean remove(int val){
        if(root == null){
            return false;
        }
        TreeNode cur = root;
        while(cur != null){
            if(cur.val > val){
                cur = cur.left;
            }
            else if(cur.val < val){
                cur = cur.right;
            }
            else{
                break;
            }
        }
        if(cur == null){
            return false;
        }
        if(cur.right == null && cur.left == null){
            if(cur == root){
                root = null;
                return true;
            }
            if(cur.parent.left == cur){
                cur.parent.left = null;
            }
            else{
                cur.parent.right = null;
            }
        }
        else if(cur.right == null || cur.left == null){
            if(cur.right == null){
                if(cur == root){
                    root = cur.left;
                    root.parent = null;
                }
                else{
                    cur.left.parent = cur.parent;
                    if(cur.parent.left == cur){
                        cur.parent.left = cur.left;
                    }
                    else{
                        cur.parent.right = cur.left;
                    }
                }
            }
            else{
                if(cur == root){
                    root = cur.right;
                }
                else{
                    cur.right.parent = cur.parent;
                    if(cur.parent.left == cur){
                        cur.parent.left = cur.right;
                    }
                    else{
                        cur.parent.right = cur.right;
                    }
                }
            }
        }
        else{
            TreeNode newCur = cur.right;
            while(newCur.left != null){
                newCur = newCur.left;
            }
            cur.val = newCur.val;
            if(newCur.right != null){
                newCur.right.parent = newCur.parent;
            }
            if(newCur.parent.left == newCur){
                newCur.parent.left = newCur.right;
            }
            else{
                newCur.parent.right = newCur.right;
            }
        }
        return true;
    }

    public void range(){
        _range(root);
        System.out.println();
    }
    private void _range(TreeNode root){
        if(root == null){
            return;
        }
        _range(root.left);
        System.out.printf("%2d ", root.val);
        _range(root.right);
    }
}