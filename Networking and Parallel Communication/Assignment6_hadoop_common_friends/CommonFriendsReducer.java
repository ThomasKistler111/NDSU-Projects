import java.io.IOException;
import java.util.Iterator;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CommonFriendsReducer extends Reducer<TwoNodeWritable, ListWritable, Text, ListWritable> {
 
    
    @Override
    public void reduce(TwoNodeWritable key, Iterable<ListWritable> values, Context context) throws IOException, InterruptedException {
 
	ListWritable prev = null;
        Iterator<ListWritable> iter = values.iterator();
        while (iter.hasNext() ){
            ListWritable current = iter.next();
	    if(prev != null){
	        ListWritable result = prev.intersect(current);
		Text k = new Text(key.toString());
		context.write(k, result);
	    }
	    prev = current;
	    
        }

    }
    
}
