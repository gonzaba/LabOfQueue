package testers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import classes.Job;
import classes.SLLQueue;


public class TesterArrayQueue {
	public static void main(String[] args) {
		SLLQueue<Job> processingQueue = new SLLQueue<Job>();
		SLLQueue<Job> inputQueue = new SLLQueue<Job>();
		SLLQueue<Job> terminatedJobs = new SLLQueue<Job>();
		
		String txtFile = "inputFiles";
		File file = new File(txtFile, "input1.txt");
		BufferedReader br = null;
        String line;
        int id = 1;
        int arrivaltime =0;
        int rt = 0;
        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                String[] count =line.split(",");             
               //Convert to int
               arrivaltime= Integer.parseInt(count[0]);
               rt = Integer.parseInt(count[1].substring(count[1].length()-1));
               Job element = new Job(id,arrivaltime, rt);
              //Enqueuing
              inputQueue.enqueue(element);  
              id++;
              
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
				Job temp = processingQueue.first();
				
				//System.out.println( "serving " +temp);
				temp.isServed(1);
				
				if( temp.getRemainingTime() == 0){//Verifico que Service time no sea 0 
				
					//si service time es 0 entonces se mueve a terminated jobs
					temp.setDepartureTime(time);
					//System.out.println("ID" + temp.getPid() +  " Dept time " + temp.getDepartureTime());
					terminatedJobs.enqueue(processingQueue.dequeue()); //saco el job de processing para ponerlo en terminated
					//System.out.println(temp.toString());
				}
				else {
					processingQueue.enqueue(processingQueue.dequeue());
				}
			}
				
			if(!inputQueue.isEmpty())
			{
				Job ntemp = inputQueue.first();
				if(ntemp.getArrivalTime()==time)
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
			valor1 = terminatedJobs.first().getArrivalTime();
			valor2 = terminatedJobs.first().getDepartureTime();
		//	System.out.println(valor1);
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
