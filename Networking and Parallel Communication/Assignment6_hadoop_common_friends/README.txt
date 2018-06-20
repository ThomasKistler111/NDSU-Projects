Assignment 6 readme

11/28/17

Thomas Kistler

----------------------------------------------------------------------------------

		--How to run the program--
The following can be used to run the program (I followed the bigram example)

The only alteration that needs to be made is changing "username" to the 
name of 
the directory you have the assignment files in 


#setup
export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar

hadoop com.sun.tools.javac.Main *.java

jar cf commonfriends.jar *.class



#Creating a directory for input 

hadoop fs -mkdir /user/username/commonfriends

hadoop fs -mkdir /user/username/commonfriends/input

hadoop fs -copyFromLocal file01 /user/username/commonfriends/input/file01

hadoop fs -copyFromLocal file02 /user/username/commonfriends/input/file02

hadoop fs -copyFromLocal file03 /user/username/commonfriends/input/file03



#Running the code

hadoop jar commonfriends.jar CommonFriends -input /user/username/commonfriends/input -numReducers 2 -output /user/username/commonfriends/output



#Output

hadoop fs -ls /user/username/commonfriends/output/

hadoop fs -cat /user/username/commonfriends/output/part-r-00000

hadoop fs -rmdir /user/username/commonfriends/output

hadoop fs -rmr /user/username/commonfriends/output

----------------------------------------------------------------------------------