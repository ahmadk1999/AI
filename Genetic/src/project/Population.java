package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Population {
	private Chromosome[] chromosome;
	
	public Population(int lenght) {
		chromosome=new Chromosome[lenght];
	}
	public Population initializePopultion() throws EncryptedDocumentException, InvalidFormatException, IOException {
		chromosome[0]=new Chromosome().initialzieChromosome();
		for(int i=1;i<chromosome.length;i++) {
			Chromosome c=new Chromosome();
			chromosome[i]= ((Chromosome) c).creatChromosome();
		}
		sortChromosomeByFitness();
		return this;
	}
	public void sortChromosomeByFitness() {
		
		Arrays.sort(chromosome, (cromossomo1, cromossomo2) ->{
            int flag = 0;
            if(cromossomo1.getFitness() > cromossomo2.getFitness())flag = -1;
                    else  flag =1;
            return flag;
           });
		
	}
	public Chromosome[] getChromosome() {
		return chromosome;
	}
	

}
