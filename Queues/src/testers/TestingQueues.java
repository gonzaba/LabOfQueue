package testers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import classes.ArrayQueue;
import classes.Pair;
import classes.SLLQueue;


public class TestingQueues {
	public static void main(String[] args) {
		SLLQueue<Pair> processingQueue = new SLLQueue<Pair>();
		SLLQueue<Pair> inputQueue = new SLLQueue<Pair>();
		ArrayQueue<Pair> terminatedJobs = new ArrayQueue<Pair>();
		
		String txtFile = "inputFiles";
		File file = new File(txtFile, "input1.txt");
		BufferedReader br = null;
        String line;
        int key = 0;
        int value =0;
        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                String[] count =line.split(",");             
               //Convert to int
               key = Integer.parseInt(count[0]);
               value = Integer.parseInt(count[1].substring(count[1].length()-1));
               Pair element = new Pair(key,value,0);
              // System.out.println("Lista de valores:" + element.toString()+"\n");
               //Enqueuing
              inputQueue.enqueue(element);  
              
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();}
            }
        }
    
        //SET TIME
        int time = 0;
      				
		while(! processingQueue.isEmpty() || ! inputQueue.isEmpty())
		{
			if(!processingQueue.isEmpty()) {
				Pair temp = processingQueue.first();
				temp.setSecond(temp.getSecond()-1);
				
				//System.out.println(temp.getSecond());
				if( temp.getSecond() == 0){//Verifico que Service time no sea 0 
				
					//si service time es 0 entonces se mueve a terminated jobs
					temp.setThird(time);
					//System.out.println(time);
					terminatedJobs.enqueue(processingQueue.dequeue()); //saco el job de processing para ponerlo en terminated
					//System.out.println(temp.toString());
				}
				else {
					processingQueue.enqueue(processingQueue.dequeue());
				}
			}
				
			if(!inputQueue.isEmpty())
			{
				Pair ntemp = inputQueue.first();
				if(ntemp.getFirst()==time)
					processingQueue.enqueue(inputQueue.dequeue());
			}
			time++;
		}
			
		//computing time
		float totalTime = 0;
		float valor1 = 0;
		float valor2 = 0;
		float count = 0;
		
		
		while(!(terminatedJobs.isEmpty())) {
			valor1 = terminatedJobs.first().getFirst();
			valor2 = terminatedJobs.first().getThird();
			//System.out.println(valor1);
			//System.out.println(valor2);
			totalTime= (valor2-valor1) + totalTime;
			count++;
			//System.out.println("Orden de entrada a Terminated Jobs:" + terminatedJobs.first().toString()+ "\n");
			terminatedJobs.dequeue();
		}
		totalTime=totalTime/count;
		
		
		System.out.print("Averange Time in System is: ");
		System.out.printf("%.2f", totalTime);
	
		
		
		
	}
}
