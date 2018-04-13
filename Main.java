package me.kamili.rachid.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("B2,E5,F6", "A1,B2,C3,D4", "D4,G7,I9", "G7,H8");


        TreeNode tree = null;

        List<TreeNode> roots = new ArrayList<>();
        for (String strings : stringList) {
            String[] s = strings.split(",");
            if (tree == null) {
                tree = new TreeNode(s[0], null, null);
                roots.add(tree);
            }

//            TreeNode parent = search(s[0], tree.getParent());
            TreeNode parent = get(s[0],roots);
            if (parent == null){
                parent = new TreeNode(s[0],null,null);
                roots.add(parent);
            }
            //22
            for (int i = 1; i < s.length; i++) {
//                TreeNode test = search(s[i], tree.getParent());
                TreeNode test = get(s[i], roots);
                if (test == null) {
                    if( parent.children == null )
                        parent.children = new ArrayList<>();
                    TreeNode tt = new TreeNode(s[i], null, parent);
                    roots.add(tt);
                    parent.children.add(tt);
                }else{
                    test.parent = parent;
//                    parent.children.add(test);
                    if( parent.children == null )
                        parent.children = new ArrayList<>();
//                    TreeNode tt = new TreeNode(s[i], null, test);
//                    roots.add(tt);
                    parent.children.add(test);
                }
            }

        }
        tree.getParent().print();
    }

    static TreeNode get(String str, List<TreeNode>array){
        for (TreeNode tn:array){
            if (tn.name.equals(str))
                return tn;
        }
        return null;
    }

    private static TreeNode search(String name, TreeNode node) {
        if (node != null) {
            if (node.name.equals(name)) {
                return node;
            } else {
                if (node.children != null)
                    for (TreeNode n : node.children) {
                        TreeNode foundNode = search(name, n);
                        if (foundNode == null) {
                            continue;
                        } else
                            return foundNode;
                    }
                return null;
            }
        } else {
            return null;
        }
    }

    public static class TreeNode {

        final String name;
        List<TreeNode> children;
        TreeNode parent;

        public TreeNode(String name, List<TreeNode> children, TreeNode parent) {
            this.name = name;
            this.children = children;
            this.parent = parent;
        }
        public TreeNode getParent(){
            if(parent==null)
                return this;
            return getParent();
        }

        public void print() {
            print("", true);
        }

        private void print(String prefix, boolean isTail) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + name);
            if (children != null) {
                for (int i = 0; i < children.size() - 1; i++) {
                    children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
                }
                if (children.size() > 0) {
                    children.get(children.size() - 1)
                            .print(prefix + (isTail ? "    " : "│   "), true);
                }
            }
        }
    }

}
