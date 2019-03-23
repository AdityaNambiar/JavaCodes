import java.io.*;
import java.util.*;
import java.lang.*;

class KMeans {
       public static float avg(List<Float> arr){
              float average = 0, sum=0;
              for(int p = 0; p < arr.size(); p++){
                     sum = sum + arr.get(p);
              }
              average = sum/arr.size();
              return average;
       }
       public static void compute(int n_clus, float[][] dataNmeans,List<Float> data,
        List<Float> means, List<ArrayList<Float>> clustersMat){
              for(int i = 0; i < n_clus; i++){
                     clustersMat.set(i,new ArrayList<Float>());
              }


              // Prepare data x means matrix for evaluation phase
              for(int i = 0; i < data.size();i++){
                     for(int j = 0; j < means.size(); j++){
                            dataNmeans[i][j] = Math.abs(data.get(i) - means.get(j));
                     }
              }
              // Traverse and fetch minimum of the two distances ( differences )
              int mean_size = means.size();
              int clusIndex = 0;

              for(int i = 0; i < data.size() || i < clustersMat.size();i++){
                     for(int j = 0; j < means.size(); j++){
                            if(dataNmeans[i][j] < dataNmeans[i][(j+1)%mean_size]){
                                   clusIndex = j;
                                   clustersMat.get(clusIndex).add(data.get(i));
                                   break;
                            } else {
                                   clusIndex = (j+1)%mean_size;
                                   clustersMat.get(clusIndex).add(data.get(i));
                                   break;
                            }
                            
                     }
              }
       }
       public static void main(String args[]){
              List<Float> data = new ArrayList<Float>();
              List<Float> means = new ArrayList<Float>();

              Scanner sc = new Scanner(System.in);
              // Ask for Data:
              System.out.println("How many data elements?: ");
              int de = sc.nextInt();
              System.out.println("Enter data: ");
              for(int i = 0; i<de; i++){
                     System.out.println(String.format("Data item %d: ", i));
                     data.add(sc.nextFloat());
              }
              // Ask for initial means, num of clusters.
              List<ArrayList<Float>> clustersMat = new ArrayList<ArrayList<Float>>();
              System.out.println("How many clusters?");
              int n_clus = sc.nextInt(); // Initially allow user to pick random values from cluster.
              System.out.println(String.format("Enter %d means below:",n_clus));
              for(int j = 0; j < n_clus; j++){
                     clustersMat.add(j,new ArrayList<Float>());
                     System.out.println(String.format("Enter mean %d", j));
                     means.add(sc.nextFloat());
              }
              System.out.println("First means: "+means);
              float[][] dataNmeans = new float[data.size()][means.size()];
              List<Float> previousMean = new ArrayList<Float>(means);
              compute(n_clus,dataNmeans,data,means,clustersMat);
              for(int i = 0; i < clustersMat.size(); i++){
                     System.out.println(String.format("Cluster #%d: ",i)+clustersMat.get(i));
              }
              int m = 1;
              // Iteration of actual steps below:
              while(m++ < 20){
                     // Set new means:
                     for(int j = 0; j < n_clus; j++){
                            means.set(j,avg(clustersMat.get(j)));
                     }
                     //System.out.println("MEANS: "+means);
                     //System.out.println("PREVMEANS: "+previousMean);
                     if(means.containsAll(previousMean)){
                            break;
                     }
                     System.out.println("New means: "+means);
                     previousMean = null;
                     previousMean = new ArrayList<Float>(means);

                     compute(n_clus,dataNmeans,data,means,clustersMat);
                     for(int i = 0; i < clustersMat.size(); i++){
                            System.out.println(String.format("Cluster #%d: ",i)+clustersMat.get(i));
                     }
              }
              System.out.println("================================================");
              for(int i = 0; i < clustersMat.size(); i++){
                     System.out.println(String.format("Final Cluster #%d: ",i)+clustersMat.get(i));
              }
       }
}
