

/**
 * Write a description of Part1a here.
 * N6a
 * @author Dimgba Martha Otisi 
 * @2020,January
 * @martha_samuel_ 
 */
import java.util.ArrayList;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part1 {
public int findStopCodon(String dna, int startIndex, String stopCodon){
    //find the "TAA", starting from(startindex +3),call this result currindex
    int currIndex = dna.indexOf(stopCodon, startIndex+3);
    //as long as currindex is !-1
    while(currIndex != -1) {
    
        //check if (currindex - startindex) is a multiple of 3
        int diff = currIndex - startIndex;
        if(diff % 3 == 0){
            //if so currindex is your answer,return it
            return currIndex;
        }
        //if not,update currindex to the index of the nest "TAA" starting from (currindex+1
       
            else{
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
            //starting from currindex +1
            //if we exit loop,we didnt find stopcodon
            //so return dnastr.length()
           return dna.length(); 
        }
    

  public void testFindStopCodon(){
   //01234567890123456789012345
   String dna = "xxxyyzzzTAAxxxyyyzzzTAAxx";
   int dex = findStopCodon(dna, 0,"TAA");
   if(dex!=9) System.out.println("error on 9");
    
   
   dex = findStopCodon(dna, 9,"TAA");
      if(dex!=21) System.out.println("error on 21");
    
    
   dex = findStopCodon(dna, 1,"TAA");
      if(dex!=26) System.out.println("error on 26");
 
    
      dex = findStopCodon(dna, 0,"TAG");
         if(dex!=26) System.out.println("error on 26 TAG");
      System.out.println("test finished");
    }

public String findGene(String dna){
   //find the first occurence of "ATG" and call its index stratIndex
   int startIndex = dna.indexOf("ATG");
   //if startindex is -1(empty) the answer is an empty string
   if (startIndex == -1){
       return"";
    }
       //use taaindex to store findstopcodon(dnastr, startindex,"TAA") and call the result taaindex
       int taaIndex = findStopCodon(dna,startIndex,"TAA");
       //use tagindex to store findstopcodon(dnastr, startindex,"TGA") and call the result tgaindex
   int tagIndex = findStopCodon(dna,startIndex,"TAG");
  //use tgaindex to store findstopcodon(dnastr, startindex,"TAG") and call the result tagindex
  int tgaIndex = findStopCodon(dna,startIndex,"TGA");
  //take the smallest taaindex,tagindex and tgaindex,call it minindex
  //int temp = Math.min(taaIndex, tagIndex);
  //int minIndex = Math.min(temp, tgaIndex);
  int minIndex = 0;
  if(taaIndex == -1 ||
  //if(minIndex ==-1 OR
   (tgaIndex != -1 && tgaIndex < taaIndex)){
       //then set minIndex to tgaIndex
       minIndex = tgaIndex;
    }
       //else set  min Index to  taaIndex
    else
    {
        minIndex = taaIndex;
    }
   //if  minIndex == -1 OR
   if(minIndex == -1 ||
   //if taaIndex! = -1 and taaIndex<minIndex;
   (tagIndex != -1 && tagIndex < minIndex)){
      //then set minIndex to tagIndex
      minIndex = tagIndex;
    }
    //if minIndex == -1, return""
    if(minIndex == -1) {
        return "";
    }
    return dna.substring(startIndex, minIndex + 3);
}
public void testFindGene(){

        String dna= "CAAATGGGCGTCACTAGATAACCG";
        String gene = findGene(dna);
        System.out.println("This is my gene: " + gene);
        if (!gene.equals("ATGGGCGTCACTAGATAA")){
            System.out.println("error");
        }
        System.out.println("tests finished");
        dna =  "ACCATGGGGAAAGTCTGAACCTAGCCA";
        System.out.println("The dna string is :" + dna);
        
        System.out.println("Gene found is :" + gene);
        
        dna= "ATFCCCAAATACTAAGCCTAGAAA";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);
        
        dna= "ACCATGTCCGGAAATCCCTGAGGGTAGCCC";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);
        
        dna=  "ACCTACATGAAACCCTACGAT";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna);
        System.out.println("Gene found is :" + gene);

    
    
}

   public StorageResource getAllGenes(String dna){
       StorageResource geneList = new StorageResource ();
       int startIndex = 0;
                while(true){
                String currentGene = findGene(dna);
                if(currentGene.isEmpty()){
                
                break;
            } 
       geneList.add(currentGene);
           
        
        startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
    }
    return geneList;
}
public void testOn(String dna){
    System.out.println("Testing getAllGenes on " + dna);
  StorageResource genes = getAllGenes(dna);
  for(String g : genes.data()){
  System.out.println(g);
}
}
public void test(){
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    
}
}

   


    


