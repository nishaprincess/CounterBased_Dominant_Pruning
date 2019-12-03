/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counterbaseddp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author acer
 */
public class CounterbasedDP {

    public static int u, v, h, src =0 ;
    public static int[] array_c= new int[10000];
    public static int[] visited;
    public static int ftime=0;
    public static int visited_count;
    public static int[] visited_queue_node = new int[10000];
     public static int[] REACHED_node = new int[10000];
    public static int visited_queue_node_count = 0;
    public static int[] connected_u = new int[10000];
    public static int forward_count;
    public static int[] nu_size_bidirection;
    public static int[] n2u_size_bidirection;
    
    /**
     * ******* Queue Forward list Track **********
     */
    
     public static Queue<Integer> q_forward = new LinkedList<>();

    /**
     * ****** Queue forward list parent Track ****
     */
     
    public static Queue<Integer> q_parent_forward = new LinkedList<>();
    public static int REACHED_node_count;
    
    
     /**
     * ****** Queue forward list parent Time Track ****
     */
     
    public static Queue<Integer> q_forward_time = new LinkedList<>();
    public static int node_number=500;
    
    
    public static void main(String[] args)throws IOException {
         for(int dd=1;dd<=10;dd++)
        {
            
        forward_count=0;
       
       // System.out.println("FILE"+dd);
        visited = new int[100000];
        visited_count = 0;
        Vset[] vset;
        int i_count = 0;
         int cc=0;
        vset = new Vset[12000];
        
        FSET[] fset;
        fset = new FSET[12000];
        nu_size_bidirection=new int[12000];
        n2u_size_bidirection=new int[12000];
        
        
        File f = new File("scen-500-225-625-625-"+dd+".out");
        BufferedReader b = new BufferedReader(new FileReader(f));
        
        String readLine = "";
        
       // System.out.println("Reading file using Buffered Reader");

       // System.out.println("Length= "+tokens.length);
       
        for (int i = 0; i < n2u_size_bidirection.length; i++) {
                
                nu_size_bidirection[i]=0;
                n2u_size_bidirection[i]=0;
            }
            
       
       
       int i = -1;
       
       
     
      

       while (i <node_number) {
                i++;

                while ((readLine = b.readLine()) != null) {
                    String[] tokens = readLine.split(" ");

                    if (tokens.length == 3) {

                        u = Integer.valueOf(tokens[tokens.length - 3]);
                        v = Integer.valueOf(tokens[tokens.length - 2]);
                        h = Integer.valueOf(tokens[tokens.length - 1]);

                        int visited_flag = 0;

                        for (int l2 = 0; l2 < visited_count; l2++) {

                            if (visited[l2] == u) {
                                visited_flag = 1;
                                break;
                            }
                        }

                        if (visited_flag == 0) {
                            
                            if(nu_size_bidirection[u]==0)
                            {
                                vset[u] = new Vset();  // create each actual Person
                            }
                          
                            
                            
                            visited[visited_count] = u;
                            visited_count++;
                            int k = 0, j = 0;
                            //System.out.println("u= " + u);
                            // System.out.println("v= " + v);
                            //System.out.println("h= " + h);

                            vset[u].nu[nu_size_bidirection[u]] = u;
                           
                            nu_size_bidirection[u]=nu_size_bidirection[u]+1;
                            
                            k++;

                            File f2 = new File("scen-500-225-625-625-"+dd+".out");

                            BufferedReader b2 = new BufferedReader(new FileReader(f2));

                            String readLine2 = "";

                            //System.out.println("Reading file of UU "+u);
                            while ((readLine2 = b2.readLine()) != null) {
                                String[] tokens2 = readLine2.split(" ");
                                int u2, v2, h2;
                                if (tokens2.length == 3) {
                                    u2 = Integer.valueOf(tokens2[tokens2.length - 3]);
                                    v2 = Integer.valueOf(tokens2[tokens2.length - 2]);
                                    h2 = Integer.valueOf(tokens2[tokens2.length - 1]);
                                    
                                    if(n2u_size_bidirection[v2]==0 && nu_size_bidirection[v2]==0)
                                    {
                                        vset[v2] = new Vset();  // create each actual Person
                                    }
                                    

                                    if (u2 == u) {

                                        vset[u].u = u2;
                                       // System.out.println("vset icount ="  +i_count+" : "+  vset[i_count].u);

                                        // ******************** Neighbour **************//
                                        if (h2 == 1) {
                                            
                                            vset[u].nu[nu_size_bidirection[u]] = v2;
                                            nu_size_bidirection[u]=nu_size_bidirection[u]+1;
                                            
                                           //System.out.println("neighbour of v:" + nu_size_bidirection[u]);
                                            
                                            vset[v2].nu[nu_size_bidirection[v2]] = u;
                                            nu_size_bidirection[v2]=nu_size_bidirection[v2]+1;
                                            
                                            k++;
                                           
                                            

                                        }

                                        // ****************** Neighbour(Neighbour) *********//
                                        if (h2 == 2) {
                                             
                                            vset[u].n2u[n2u_size_bidirection[u]] = v2;
                                            n2u_size_bidirection[u]=n2u_size_bidirection[u]+1;
                                            // System.out.println("neighbour of neighbour of v:"+vset[i].n2u[j]); 
                                            j++;
                                            vset[v2].n2u[n2u_size_bidirection[v2]] = u;
                                            n2u_size_bidirection[v2]=n2u_size_bidirection[v2]+1;
                                            
                                          
                                            
                                            
                                        }
                                    }

                                }

                            }

                            vset[u].nu_size = nu_size_bidirection[u];
                            vset[u].n2u_size = n2u_size_bidirection[u];
                            vset[node_number-1].n2u_size = n2u_size_bidirection[node_number-1];
                            vset[node_number-1].nu_size = nu_size_bidirection[node_number-1];
                           
                         // System.out.println(u+": nusize: "+vset[u].nu_size);  
           
                         // System.out.println(u+": n2u size "+vset[u].n2u_size);
                            i_count++;

                        }

                    }

                }

       }      
       
        //********* For Loop Ends *********//
        
             
        
       /* for (int l2 = 0; l2 < visited_count; l2++) {

            
            int node=visited[l2];
            
            System.out.println("Node: "+node);
            System.out.println("U Node: "+vset[node].nu_size); 
            System.out.println("V Node: "+vset[node].n2u_size); 
            
            for (int j = 0; j < vset[node].n2u_size; j++) {
                
                 System.out.println("VVV Node: "+vset[node].n2u[j]);
                 
            }
            
           
           
            
        }*/
       
       
            /**
             * ********** Src Queue Push *******
             */
            int max_src = -9999999;
            
            int time=1;

            q_forward.add(src);
            q_forward_time.add(time);
            q_parent_forward.add(max_src);
            visited_count++;
            visited[visited_count-1]=node_number-1;
            /**
             * ********* Queue not empty ********
             */
            
           int u3, v3, parent;
          
            for (int ii = 0; ii < visited_queue_node.length; ii++) {
                visited_queue_node[ii] = 0;
               
            }
             for (int ii = 0; ii < REACHED_node.length; ii++) {
               REACHED_node[ii] = 0;
            }
            counter_time_property[] ctp=new counter_time_property[10000];
            
            //System.out.println("Visited count: "+visited_count);
            
            for (int ii = 0; ii <visited_count; ii++) {
                
                
                  
                  int node=visited[ii];
                  ctp[node]=new counter_time_property();
                  ctp[node_number-1]=new counter_time_property();
                  ctp[node].counter=0;
                  ctp[node_number-1].counter=0;
                   
            }
            
            
            
            
             while (!q_forward.isEmpty()) {
                 
                 
                 u3 = q_forward.remove();
                 parent = q_parent_forward.remove();
                 
               // System.out.println("parent: "+parent);
                //System.out.println("Node: "+u3);
                
               // System.out.println("Counter Node: "+ctp[u3].counter);
                 
                 int time_f=0;
                 time_f=q_forward_time.remove();
                 
               if (visited_queue_node[u3] != 1 && ctp[u3].counter<4) {
                   
                   visited_queue_node[u3] = 1;
                   
                   forward_count=forward_count+1;

                // System.out.println("Time: "+time_f);
                 
               
                
                 
                 for (int l2 = 0; l2 < vset[u3].nu_size; l2++) {
                     
                    
                             
                   //  System.out.println("ctp Node: "+ vset[u3].nu[l2]);
                  //   System.out.println("ctp counter: "+ctp[vset[u3].nu[l2]].counter );

                     if(vset[u3].nu[l2]!=u3)
                     {
                          ctp[vset[u3].nu[l2]].counter = ctp[vset[u3].nu[l2]].counter+1;
                     }
                     
   

                 }
                 
                /* for (int l2 = 0; l2 < vset[u3].nu_size; l2++) {

                     
                    // System.out.println("Counter Node: "+vset[u3].nu[l2]);
                     //System.out.println("Counter : "+ctp[vset[u3].nu[l2]].counter);
                     
   

                 }*/
                
                
                 // ************** Source B set *****************// 
                 
                
                    if(parent==-9999999)
                    {
                       System.out.print("Node"+src+": ");
                        for (int j = 0; j < vset[u3].nu_size; j++) {
                           if (REACHED_node[vset[src].nu[j]] != 1) {
                               System.out.print(vset[src].nu[j]+",");
                                     REACHED_node[vset[src].nu[j]] = 1;
                                     cc++;
                                 }
                            

                            vset[u3].b[j] = vset[u3].nu[j];

                        }
                        
                         vset[u3].b_size=vset[u3].nu_size;
                    }
                    else
                    {
                             int b_ct = 0;
                            System.out.print("Node"+u3+": ");
                             for (int j = 0; j < vset[u3].nu_size; j++) {

                                    int node = vset[u3].nu[j];
                                    
                                    if (REACHED_node[vset[u3].nu[j]] != 1) {
                                        System.out.println(vset[u3].nu[j]+",");
                                     REACHED_node[vset[u3].nu[j]] = 1;
                                     cc++;
                                 }
                                   
                                    
                                    
                                    //System.out.println("node set: "+node);

                                    int flag_b = 0;

                                    for (int k = 0; k < vset[parent].nu_size; k++) {

                                        int node2 = vset[parent].nu[k];

                                       // System.out.println("parent set: "+node2);
                                        if (node == node2) {

                                            // System.out.println("Matched--- set: "+node2); 
                                            flag_b = 1;
                                            break;
                                        }

                                    }

                                    if (flag_b == 0) {
                                        
                                            vset[u3].b[b_ct] = node;
                                            b_ct++;
                                        
                                    }

                                }

                                /**
                                 * ******** B set size ***********
                                 */
                                vset[u3].b_size = b_ct;
                    }
                 
                    
                    
                   
                   // System.out.println("THE B Size: "+vset[u3].b_size);
                    
                    /*for (int j = 0; j < vset[u3].b_size; j++) {
                        
                       System.out.println("THE B SET: "+u3+" : "+vset[u3].b[j]);
                         
                    }*/
                   /* for (int j = 0; j < vset[u3].b_size; j++) {
                        
                        System.out.println("THE B SET counter:"+ ctp[vset[u3].b[j]].counter);
                         //ctp[vset[u3].b[j]].counter=ctp[vset[u3].b[j]].counter+1;
                    }*/
                   
                   
                   //************* Need to find out U ********//
                   if(parent==-9999999)
                    {
                        for (int j = 0; j < vset[u3].n2u_size; j++) {
                            

                            vset[u3].uset[j] = vset[u3].n2u[j];

                        }
                        
                         vset[u3].u_size=vset[u3].n2u_size;
                    }
                   else
                   {
                       
                     /**
                     * ************ Uset Calculate **********
                     */
                    int u_ct = 0;

                    for (int j = 0; j < vset[u3].n2u_size; j++) {

                        int node = vset[u3].n2u[j];
                       
                        
                       // System.out.println("Node set: "+node);

                        int flag_b = 0;

                        for (int k = 0; k < vset[parent].nu_size; k++) {

                            int node2 = vset[parent].nu[k];

                           // System.out.println("parent set: "+node2);
                            if (node == node2) {

                                // System.out.println("Matched--- set: "+node2); 
                                flag_b = 1;
                                break;
                            }

                        }

                        if (flag_b == 0) {
                            vset[u3].uset[u_ct] = node;

                            u_ct++;
                        }

                    }

                    /**
                     * ************ Uset Size **********
                     */
                   
                        vset[u3].u_size = u_ct;
                   }
                   
                  /*  for (int j = 0; j < vset[u3].u_size; j++) {

                       // System.out.println("U set: "+vset[u3].uset[j]);

                    }*/
                   
                  
                   
                    for (int j = 0; j < vset[u3].b_size; j++) {

                        int g = vset[u3].b[j];
                       // System.out.println("G="+g);

                        for (int Vset_count1 = 0; Vset_count1 < visited_count; Vset_count1++) {

                            int x = 0;

                            int u31, v31;

                            u31 = visited[Vset_count1];
                           

                            if (u31 == g) {
                                //System.out.println("u31"+u31);

                                for (int k = 0; k < vset[g].nu_size; k++) {
                                   //  System.out.println("UUUUU: " + vset[g].nu[k]);

                                    // ************** Comapre Src Uset with B set Neghbour *********// 
                                    for (int p = 0; p < vset[u3].u_size; p++) {
                                        //System.out.println("vvvvvvvvvvv: " + vset[u3].uset[p]);

                                        if (vset[u3].uset[p] == vset[g].nu[k]) {

                                             // System.out.println("UUUUU: " + vset[g].nu[k]);
                                            connected_u[x] = vset[g].nu[k];

                                            x++;

                                        }

                                    }

                                }
                                // System.out.println("count"+g+"="+count_forward_set);

                               /*for (int y = 0; y < x; y++) {

                                        System.out.println("connected to "+g+"="+connected_u[y]);

                                    }*/
                                fset[g] = new FSET();

                                fset[g].u = u3;
                                fset[g].v = g;
                                fset[g].size = x;

                                for (int y = 0; y < fset[g].size; y++) {
                                    fset[g].connected[y] = connected_u[y];
                                }
                              
                            

                        }

                    }

                    
                    
              
                 
                 
             }
                    int[] array_index_sort = new int[vset[u3].b_size];
                    int[] array_size_sort = new int[vset[u3].b_size];
                    

                    for (int fset_count = 0; fset_count < vset[u3].b_size; fset_count++) {
                      
                        //if(vset[src].b[fset_count]!=error_node)
                        //{ 
                        int b_set_value = vset[u3].b[fset_count];
                      
                //System.out.println("u: " + b_set_value);
                        array_size_sort[fset_count] = fset[b_set_value].size;
                        array_index_sort[fset_count] = b_set_value;

                       //
                         //System.out.println("Size: " +fset[b_set_value].size);
                        
                       /*  for (int jj = 0; jj < fset[b_set_value].size; jj++) {

                                System.out.println("conneted nodes:" + fset[b_set_value].connected[jj]);

                            }*///}
                       
                    }

                    //********************* Array Sort***************//
                    int n = array_size_sort.length;

                    for (int l = 0; l < n - 1; l++) {

                        for (int j = 0; j < n - l - 1; j++) {
                            if (array_size_sort[j] < array_size_sort[j + 1]) {
                                // swap temp and arr[i]
                                int temp = array_size_sort[j];
                                int temp2 = array_index_sort[j];

                                array_size_sort[j] = array_size_sort[j + 1];
                                array_index_sort[j] = array_index_sort[j + 1];

                                array_size_sort[j + 1] = temp;
                                array_index_sort[j + 1] = temp2;
                            }
                        }
                    }
                    
                      for (int hi = 0; hi < array_c.length; hi++) {
                                array_c[hi] = 0;
                            }
                    
                      for (int j = 0; j < array_index_sort.length - 1; j++) {

                        int b_set_value1 = array_index_sort[j];

                       // System.out.println("value 1 : " + b_set_value1);
                               for (int k = j + 1; k < array_index_sort.length; k++) {
                          
                            int b_set_value2 = array_index_sort[k];
                            //System.out.println("Value 2: " + b_set_value2);
                           // System.out.println("Value 2 size: " + fset[b_set_value2].size);
                           
                            if (fset[b_set_value2].size != 0) {

                                //System.out.println("Value 2: " + b_set_value2);
                                int c = 0;

                                for (int l = 0; l < fset[b_set_value1].size; l++) {

                                    //System.out.println("l fset_connected: " + fset[b_set_value1].connected[l]);
                                    for (int m = 0; m < fset[b_set_value2].size; m++) {

                                        // System.out.println("m fset connected: " + fset[b_set_value2].connected[m]);
                                        if (fset[b_set_value1].connected[l] == fset[b_set_value2].connected[m]) {

                                            //  System.out.println("Match found for "+fset[b_set_value2].connected[m]+": " + fset[b_set_value1].connected[l]);
                                            c++;
                                              array_c[b_set_value2]=array_c[b_set_value2]+1;
                                        }

                                    }

                                }

                                if (array_c[b_set_value2] == fset[b_set_value2].size) {
                                    // System.out.println("counttttttttttttttttttt: " + c);
                                    //  System.out.println("Match2: " + b_set_value2);
                                    fset[b_set_value2].size = 0;
                                }

                            }

                        }
                      }
                       //System.out.print("F SET:");
                       
                       int t1=0;
                       
                       t1=time_f;
                       
                       int t2=1;
                       
                    for (int jj = 0; jj < array_index_sort.length; jj++) {
                    
                      
                      
                        if (fset[array_index_sort[jj]].size != 0) {
                          
                           // System.out.println(array_index_sort[jj]);
                            
                            int t3=0;
                            
                            t3=t1+t2;
                            
                           // System.out.println("Time time SET:"+t3);
                            
                            q_forward.add(array_index_sort[jj]);
                            q_parent_forward.add(u3);
                            q_forward_time.add(t3);
                            
                            t2++;
                            
                           
                        }

                    }
                    
                    
                     int[] node_fw = new int[10000];
                     int node_fwc = 0;
                     
                     int[] node_fp = new int[10000];
                     int node_fpc = 0;
                     
                     int[] node_ft = new int[10000];
                     int node_ftc = 0;
                     
                     int fc_count=0;
                     fc_count=q_forward.size();
                     
                     //System.out.println("Yyyy: "+fc_count);
                     
                     
                    
                    for (int j = 0; j < fc_count; j++) {
                     
                        node_fw[node_fwc]=q_forward.remove();
                        
                        node_fp[node_fwc]=q_parent_forward.remove();
                        
                        node_ft[node_fwc]=q_forward_time.remove();
                        
                        node_fwc=node_fwc+1;
                        
                    }
                    
                    
                   /* for (int j = 0; j < node_fwc; j++) {
                     
                       System.out.println("hjh: "+node_fw[j]);
                       System.out.println("hjh1: "+node_fp[j]);
                       System.out.println("hjh13: "+node_ft[j]);
                        
                    }*/
                   
                   
                    //********************* Array Sort***************//
                    
                  

                    for (int l = 0; l < node_fwc - 1; l++) {

                        for (int j = 0; j < node_fwc - l - 1; j++) {
                            if (node_ft[j] > node_ft[j + 1]) {
                                // swap temp and arr[i]
                                int temp = node_ft[j];
                                int temp2 = node_fw[j];
                                int temp3 = node_fp[j];

                                node_ft[j] = node_ft[j + 1];
                                node_fw[j] = node_fw[j + 1];
                                node_fp[j] = node_fp[j + 1];

                                node_ft[j + 1] = temp;
                                node_fw[j + 1] = temp2;
                                node_fp[j + 1] = temp3;
                            }
                        }
                    }
                    
                    for (int j = 0; j < node_fwc; j++) {
                     
                     // System.out.println("Forward Parent : "+node_fp[j]);
                      
                     // System.out.println("Forward Node : "+node_fw[j]);
                      
                     // System.out.println("Forward Time : "+node_ft[j]);
                       
                       q_forward.add(node_fw[j]);
                       q_parent_forward.add(node_fp[j]);
                       q_forward_time.add(node_ft[j]);
                       
                        
                    }
                    
                    
                    
                   
                     //System.out.println("Last Size: "+q_forward.size());
         
                  // break;
                  
                    }
            
               
               
       
    }
            //  System.out.println();
               REACHED_node_count=0;
                 for (int ii = 0; ii < REACHED_node.length; ii++) {
               if(REACHED_node[ii] == 1)
               {
                  // System.out.print(ii+",");
                   REACHED_node_count++;
               }
            }
                //System.out.println();
                  System.out.println(REACHED_node_count);
                 //  System.out.println(forward_count);
                   System.out.println(cc);

        }
    
}
}
