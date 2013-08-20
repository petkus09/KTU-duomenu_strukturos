package studijosKTU;

import java.util.Comparator;

public class BstSetKTUx2<Data extends DataKTU & Comparable<Data>> extends BstSetKTUx<Data>
        implements SortedSetADT<Data> {

    public BstSetKTUx2(Data baseObj) {
        super(baseObj);
    }

    public BstSetKTUx2(Data baseObj, Comparator<Data> c) {
        super(baseObj, c);
        this.c = c;
    }

//==============================================================================
// Aibė papildoma nauju elementu. Papildymas atliekamas iteracijos į gylį būdu;
//==============================================================================
    @Override
    public boolean add(Data data) {
        if (data == null) {
            return false;
        }
        if (getRoot() == null) {
            setRoot(new BstNode<Data>(data));
            setSize(size() + 1);
            return true;
        }
        BstNode<Data> n = get(data);
        int cmp = (c == null)
                ? data.compareTo(n.data)
                : c.compare(data, n.data);
        if (cmp < 0) {
            n.left = new BstNode<Data>(data);
        } else if (cmp > 0) {
            n.right = new BstNode<Data>(data);
        } else {
            return false;
        }
        setSize(size() + 1);
        return true;
    }
}
