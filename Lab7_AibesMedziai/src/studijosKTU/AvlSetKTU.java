package studijosKTU;

import Lab7Petkus.BookClass;
import java.util.Comparator;

/**
 * Rikiuojamos objektų kolekcijos - aibės realizacija AVL-medžiu.
 * @užduotis Peržiūrėkite ir išsiaiškinkite pateiktus metodus.
 *
 * @author darius.matulis@ktu.lt
 */
public class AvlSetKTU<Data extends Comparable<Data>> extends BstSetKTU<Data>
        implements SortedSetADT<Data> {

    public AvlSetKTU() {
    }

    public AvlSetKTU(Comparator<Data> c) {
        super(c);
        this.c = c;
    }

    /**
     * Aibė papildoma nauju elementu ir grąžinama true.
     * @return Aibė papildoma nauju elementu ir grąžinama true.
     */
    @Override
    public boolean add(Data data) {
        if (data == null) {
            return false;
        }
        setRoot(addRecursive(data, (AVLNode<Data>) getRoot()));
        if (!returned) {
            returned = true;
            return false;
        }
        setSize(size() + 1);
        return true;
    }

//==============================================================================
// Privatus rekursinis metodas naudojamas add metode;
//==============================================================================
    private AVLNode<Data> addRecursive(Data data, AVLNode<Data> n) {
        if (n == null) {
            return n = new AVLNode<Data>(data);
        }
        int cmp = (c == null)
                ? data.compareTo(n.data)
                : c.compare(data, n.data);
        if (cmp < 0) {
            n.setLeft(addRecursive(data, n.getLeft()));
            if ((height(n.getLeft()) - height(n.getRight())) == 2) {
                int cmp2 = (c == null)
                        ? data.compareTo(n.left.data)
                        : c.compare(data, n.left.data);
                n = (cmp2 < 0) ? rightRotation(n) : doubleRightRotation(n);
            }
        } else if (cmp > 0) {
            n.right = addRecursive(data, n.getRight());
            if ((height(n.getRight()) - height(n.getLeft())) == 2) {
                int cmp2 = (c == null)
                        ? n.right.data.compareTo(data)
                        : c.compare(n.right.data, data);
                n = (cmp2 < 0) ? leftRotation(n) : doubleLeftRotation(n);
            }
        } else {
            returned = false;
        }
        n.height = Math.max(height(n.getLeft()), height(n.getRight())) + 1;
        return n;
    }

    /**
     * Pašalinamas elementas data iš aibės.
     * @return Gražinama true, pašalinus elementą iš aibės.
     */
    @Override
    public boolean remove(Data data) {
        if (data == null) {
            return false;
        }
        setRoot(removeRecursive(data, (AVLNode<Data>) getRoot()));
        if (!returned) {
            returned = true;
            return false;
        }
        setSize(size() - 1);
        return true;
    }

    private AVLNode<Data> removeRecursive(Data data, AVLNode<Data> n) {
        if (n == null) {
            returned = false;
            return n;
        }       
        int cmp = (c == null)
                ? data.compareTo(n.data)
                : c.compare(data, n.data);        
        if (cmp < 0) {
            n.setLeft(removeRecursive(data, n.getLeft()));
            if (n.getRight() != null
                    && height(n.getRight()) - height(n.getLeft()) >= 2) {
                n = (height(n.getRight().getRight()) >= height(n.getRight().getLeft()))
                        ? leftRotation(n)
                        : doubleLeftRotation(n);
            }
        } else if (cmp > 0) {
            n.setRight(removeRecursive(data, n.getRight()));
            if (n.getLeft() != null
                    && height(n.getLeft()) - height(n.getRight()) >= 2) {
                n = (height(n.getLeft().getLeft()) >= height(n.getLeft().getRight()))
                        ? rightRotation(n)
                        : doubleRightRotation(n);
            }
        } else if (n.getLeft() != null && n.getRight() != null) {
            n.data = ((AVLNode<Data>) getMax(n.getLeft())).data;
            n.setLeft(removeRecursive(n.data, n.getLeft()));
            if (n.getRight() != null
                    && height(n.getRight()) - height(n.getLeft()) >= 2) {
                n = (height(n.getRight().getRight()) >= height(n.getRight().getLeft()))
                        ? leftRotation(n)
                        : doubleLeftRotation(n);
            }
        } else {
            n = n.getLeft() != null ? n.getLeft() : n.getRight();
        }
        if (n != null) {
            n.height = Math.max(height(n.getLeft()), height(n.getRight())) + 1;
        }
        return n;
    }

    
//    
//    private AVLNode<Data> removeRecursive(Data data, AVLNode<Data> n) {
//        if (n == null) {
//            returned = false;
//            return n;
//        }
//        int cmp = (c == null)
//                ? data.compareTo(n.data)
//                : c.compare(data, n.data);
//        if (cmp < 0) {
//            n.setLeft(removeRecursive(data, n.getLeft()));
//            //...
//        } else if (cmp > 0) {
//            n.setRight(removeRecursive(data, n.getRight()));
//            //..
//        } else if (n.getLeft() != null && n.getRight() != null) {
//            n.data = ((AVLNode<Data>) getMax(n.getLeft())).data;
//            n.setLeft(removeRecursive(n.data, n.getLeft()));
//            //..
//        } else {
//            n = n.getLeft() != null ? n.getLeft() : n.getRight();
//        }
//        if (n != null) {
//            //..
//        }
//        return n;
//    }

//==============================================================================
// Papildomi privatūs metodai, naudojami operacijų su aibe realizacijai
// AVL-medžiu;
//==============================================================================
//==============================================================================
//        n2
//       /                n1
//      n1      ==>      /  \
//     /                n3  n2
//    n3
//==============================================================================
    private AVLNode<Data> rightRotation(AVLNode<Data> n2) {
        AVLNode<Data> n1 = n2.getLeft();
        n2.setLeft(n1.getRight());
        n1.setRight(n2);
        n2.height = Math.max(height(n2.getLeft()), height(n2.getRight())) + 1;
        n1.height = Math.max(height(n1.getLeft()), height(n2)) + 1;
        return n1;
    }

    private AVLNode<Data> leftRotation(AVLNode<Data> n1) {
        AVLNode<Data> n2 = n1.getRight();
        n1.setRight(n2.getLeft());
        n2.setLeft(n1);
        n1.height = Math.max(height(n1.getLeft()), height(n1.getRight())) + 1;
        n2.height = Math.max(height(n2.getRight()), height(n1)) + 1;
        return n2;
    }

//==============================================================================
//        n3               n3
//       /                /                n2
//      n1      ==>      n2      ==>      /  \
//       \              /                n1  n3
//        n2           n1
//============================================================================== 
    private AVLNode<Data> doubleRightRotation(AVLNode<Data> n3) {
        n3.left = leftRotation(n3.getLeft());
        return rightRotation(n3);
    }

    private AVLNode<Data> doubleLeftRotation(AVLNode<Data> n1) {
        n1.right = rightRotation(n1.getRight());
        return leftRotation(n1);
    }

    private int height(AVLNode<Data> n) {
        return (n == null) ? -1 : n.height;
    }

    @Override
    public int MedzioDydis() {
        BstNode first = this.getRoot();
        return height(first, 1);
    }
    
     private int height(BstNode parent, int previous_height) {
        if (parent.left == null && parent.right == null) {
            return previous_height;
        }
        if (parent.left != null && parent.right != null) {
            int left = height(parent.left, previous_height + 1);
            int right = height(parent.right, previous_height + 1);
            if (left > right) {
                return left;
            } else {
                return right;
            }
        }
        if (parent.left == null) {
            return height(parent.right, previous_height + 1);
        }
        if (parent.right == null) {
            return height(parent.left, previous_height + 1);
        }
        return 0;
    }

    @Override
    public int SkoluIsmusinejimas(String data) {
        BstNode first = this.getRoot();
        return skolos(first, 0, data);
    }
    
    private int skolos(BstNode parent, int previous_anmount, String data) {
        BookClass klasė = (BookClass)parent.data;
        if (klasė.Return(data))
            previous_anmount++;
        if (parent.left == null && parent.right == null) {
            return previous_anmount;
        }
        if (parent.left != null && parent.right != null) {
            int kiekis = previous_anmount;
            int left = skolos(parent.left, previous_anmount, data);
            int right = skolos(parent.right, previous_anmount, data);
            return (kiekis + (left - kiekis) + (right - kiekis));
        }
        if (parent.left == null) {
            return skolos(parent.right, previous_anmount, data);
        }
        if (parent.right == null) {
            return skolos(parent.left, previous_anmount, data);
        }
        return previous_anmount;
    }

    



//==============================================================================
//Vidinė kolekcijos mazgo klasė
//==============================================================================
    class AVLNode<Data> extends BstNode<Data> {

        int height;

        AVLNode(Data data) {
            super(data);
            this.height = 0;
        }

        public void setLeft(AVLNode<Data> left) {
            super.left = (BstNode) left;
        }

        public AVLNode<Data> getLeft() {
            return (AVLNode<Data>) left;
        }

        public void setRight(AVLNode<Data> right) {
            super.right = (BstNode) right;
        }

        public AVLNode<Data> getRight() {
            return (AVLNode<Data>) right;
        }
    }
}