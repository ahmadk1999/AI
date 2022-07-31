package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;









public class Driver  {

	public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException {

		Population population =new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopultion();
		GeneticAlgorithm geneticAlgorithm=new GeneticAlgorithm();
		System.out.println("---------------------------------------------");
		System.out.println("Geneartion 0  | Fittest chromosome fitness : "+population.getChromosome()[0].getFitness() );
		printPopulation(population,"Target");
		int geneartionNumber=1;
		
		while(true) {
			System.out.println("\n--------------------------------------------");
			population=geneticAlgorithm.envlove(population);
			population.sortChromosomeByFitness();
			System.out.println("Geneartion "+geneartionNumber+" | Fittest chromosome fitness : "+ population.getChromosome()[0].getFitness() );
			printPopulation(population,"Target");
			geneartionNumber++;
			int cek=Target(population.getChromosome()[0]);
			if(cek==0 ) {
				break;
			}
			
		}

	          
	}
	public static void printPopulation(Population population,String heading) {
		System.out.println(heading);
		System.out.println("---------------------------------------------");
		for(int i=0;i<population.getChromosome().length;i++) {
			System.out.println("chromosome "+i+" :"+population.getChromosome()[i].toString()
					+" | Fitness : "+ population.getChromosome()[i].getFitness());
		}
	}
	
	public static int Target(Chromosome chromosome) {
		int[] check=new int[40];
		for(int i=0;i<40;i++) {
			check[i]=0;
		}
		for(int i=0;i<chromosome.getGen().size();i++) {
			if(check[chromosome.getGen().get(i).getIdProject()]!=0) {
				return -1;
				
			}
			else {
				check[chromosome.getGen().get(i).getIdProject()]=1;
			}
		}
		return 0;
	}
	
	
}

 