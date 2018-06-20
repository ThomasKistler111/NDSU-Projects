import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CommonFriendsMapper extends Mapper<LongWritable, Text, TwoNodeWritable, ListWritable> {
    private static final ListWritable LW = new ListWritable();
    private static final TwoNodeWritable TWONODE = new TwoNodeWritable();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String prev = null;
	ArrayList<String> all = new ArrayList<>();
	String mainperson = null;//mainperson is the first node
            StringTokenizer itr = new StringTokenizer(line);
	    //add all values to list except first
	    while(itr.hasMoreTokens()){
		if(mainperson != null)
		   all.add(itr.nextToken());
		else//if first 
		   mainperson = itr.nextToken();
	    }
	    itr = new StringTokenizer(line);
            while (itr.hasMoreTokens()) {
                String cur = itr.nextToken();
		//create key
		if(mainperson.compareTo(cur) < 0){ //if mainperson0 < cur
		  TWONODE.set(new Text(mainperson),new Text(cur));
		}else{
		  TWONODE.set(new Text(cur),new Text(mainperson));
		}
		//create listWritable 
		for(String a : all){
		   if(!a.equals(cur)){LW.add(new Text(a));}
		}
                context.write(TWONODE, LW);
            }
    }   
}
