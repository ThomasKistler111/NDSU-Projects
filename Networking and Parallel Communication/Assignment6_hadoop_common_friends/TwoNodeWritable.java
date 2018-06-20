import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TwoNodeWritable implements WritableComparable<TwoNodeWritable> {

    private Text leftNode;
    private Text rightNode;

    public TwoNodeWritable() {
    }

    public TwoNodeWritable(Text left, Text right) {
        this.leftNode = left;
        this.rightNode = right;
    }

    public void readFields(DataInput in) throws IOException {
        leftNode = new Text(in.readUTF());
        rightNode = new Text(in.readUTF());
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(leftNode.toString());
        out.writeUTF(rightNode.toString());
    }

    public void set(Text prev, Text cur) {
        leftNode = prev;
        rightNode = cur;
    }

    @Override
    public String toString() {
        return leftNode.toString() + ", " + rightNode.toString();
    }

    @Override
    public int hashCode() {
        return leftNode.hashCode() + rightNode.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TwoNodeWritable) {
            TwoNodeWritable Node = (TwoNodeWritable) o;
            return leftNode.equals(Node.leftNode)
                    && rightNode.equals(Node.rightNode);
        }
        return false;
    }

    public int compareTo(TwoNodeWritable tp) {
        int cmp = leftNode.compareTo(tp.leftNode);
        if (cmp != 0) {
            return cmp;
        }
        return rightNode.compareTo(tp.rightNode);
    }

}

