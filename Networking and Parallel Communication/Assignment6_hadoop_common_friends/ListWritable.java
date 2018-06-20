import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.Text;

public class ListWritable implements Writable {
       // Some data     
       private ArrayList<Text> list = new ArrayList<>();
	
	public void add(Text x){
	    list.add(x);
	}
       
       public int size(){
	  return list.size();
	}
	
	public void set(ArrayList<Text> L){this.list = L;}
	

       public void write(DataOutput out) throws IOException {
	 for(Text elem: list){
	    elem.write(out);
	 }
       }
       

       public void readFields(DataInput in) throws IOException {
         Text t = new Text(in.readUTF());
	 this.add(t);
       }
       
 
       public static ListWritable read(DataInput in) throws IOException {
         ListWritable lw = new ListWritable();
         lw.readFields(in);
         return lw;
       }
	
	public ArrayList<Text> getList(){return this.list;}

	public ListWritable intersect(ListWritable other)
	{
		ArrayList<Text> temp = getList();
		temp.retainAll(other.getList());
		ListWritable lw = new ListWritable();
		lw.set(temp);
		return lw;
	} 

    public String toString() {
	StringBuilder sb = new StringBuilder();
	for(Text t : list){
	    sb.append(t).append(" ");
	}
	return sb.toString();
    }

    public int hashCode() {
        return list.hashCode();
    }

    public boolean equals(Object o) {
        if (o instanceof ListWritable) {
            ListWritable other = (ListWritable) o;
            return other.getList().equals(list);
        }
        return false;
    }

     }
 