import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HashCode {

    
    static class Projects {
        public String name;
        public int score;
        public int best_before;
        public int duration;
        public int noRolls;
        Map<String, Integer> Pskills;
        ArrayList<Contributors> Cnames = new ArrayList<Contributors>();

        public Projects(String name,int duration,int score, int best_before,  int noRolls,Map<String, Integer> Pskills ) {
            this.Pskills = Pskills;
            this.name = name;
            this.best_before = best_before;
            this.score = score;
            this.duration = duration;
            this.noRolls = noRolls;
        }

        public void printContibuters() throws FileNotFoundException {
            PrintStream out = new PrintStream(
        new FileOutputStream("output.txt", true), true);
            for(Contributors c:Cnames)
            {
                System.setOut(out);
                System.out.print(c.name + " ");
            }
        }

        public String toString() {
            return name + " "+ Arrays.toString(Pskills.entrySet().toArray());
        }
    }
    
    static class Contributors {
        public String name; 
        public int numSkills;
        public Map<String, Integer> Cskills;
        public Boolean availability = true;
        
        public Contributors(String name, int numSkills, Map<String, Integer> Cskills) {
            this.name = name;
            this.numSkills = numSkills;
            this.Cskills = Cskills;

        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return name;
        }
    }
        public static void main(String[] args) throws Exception {
    
            File file = new File("input.txt");
            Scanner op = new Scanner(file);
            int c = op.nextInt();
            int p = op.nextInt();

            Contributors[] C = new Contributors[c];


            

            for(int i=0;i<c;i++){
                Map<String, Integer> cSkills = new HashMap<String, Integer>();
                String name = op.next();
                int nSkills = op.nextInt();
                for(int j=0;j<nSkills;j++)
                {
                    cSkills.put(op.next(), op.nextInt());
                }

                C[i] = new Contributors(name,nSkills,cSkills);


            }

    
            Projects[] pro = new Projects[p];
    
            for(int i = 0; i < p; i++) {
                String name = op.next();
                int days = op.nextInt();
                int score = op.nextInt();
                int best_before = op.nextInt();
                int rolls = op.nextInt();
                Map<String, Integer> rollsList = new HashMap<>();
                //System.out.println(name+" "+days+" "+score+" "+best_before + " " + rolls);
                for(int j=0;j<rolls;j++){
                    rollsList.put(op.next(), op.nextInt());
                }
                //System.out.println(rollsList.get("C++"));
                pro[i] = new Projects(name, days,score,best_before,rolls,rollsList);
            }


            System.out.println(pro[1].toString());
            //assignProjects(pro[1], c[1]);

            
        for(int i=0;i<pro.length;i++){
            //Boolean stop = false;
            for (String name : pro[i].Pskills.keySet())
            {
                int lvl = pro[i].Pskills.get(name);
                for(int k=0;k<C.length;k++){
                    if(C[k].Cskills.containsKey(name)&&C[k].Cskills.get(name)>=lvl)
                    {
                        pro[i].Cnames.add(C[k]);
                        C[k].availability = false;
                        //System.out.println(C[k].name+" "+i);
                        //stop = true;

                    }
                }
            }
        }
        PrintStream out = new PrintStream(
            new FileOutputStream("output.txt", true), true);
            System.setOut(out);
            System.out.println(pro.length);
            
        for(int i=0;i<pro.length;i++){

            System.out.print(pro[i].name + "\n");
            System.setOut(out);
            pro[i].printContibuters();
            System.out.println();
        }
    }
}


//     public static void assignProjects(Projects[] p ,Contributors[] c) {

//         for(int i=0;i<p.length;i++){
//             for(int j=0;j<p[i].noRolls;j++){
//                 for (String name : p[i].Pskills.keySet())
//                 {
//                     int lvl = p[i].Pskills.get(name);
//                     for(int k=0;k<c.length;k++){
//                         if(c[k].Cskills.containsKey(name)&&c[k].Cskills.get(name)>=lvl&&c[k].availability==true)
//                         {
//                             p[i].Cnames.add(c[k]);
//                             c[k].availability = false;
//                         }
//                     }
//                 }
//             }
//         }
//     }
// }