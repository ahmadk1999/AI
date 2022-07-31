package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Chromosome {
	private List<Gene> gen;
	private int fitness=0;
	private boolean isFitnessChanged=true;
	
	public Chromosome() {
		gen=new ArrayList<Gene>();
	}
	
	
	public List<Gene> getGen() {
		this.isFitnessChanged=true;
		return gen;
	}

	public void setGen(List<Gene> gen) {
		this.gen = gen;
	}

	public int getFitness() {
		if(isFitnessChanged) {
			this.fitness=calculateFitness();
			this.isFitnessChanged=false;
		}
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public Chromosome initialzieChromosome() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		int i=1;
		gen=new ArrayList<Gene>();
        File f=new File("D:\\downloads\\Selection.xlsx");
        FileInputStream fis = new FileInputStream(f);   //obtaining bytes from the file  
        Workbook wb= WorkbookFactory.create(f);
        Sheet sheet0=wb.getSheetAt(0);
        double d;
        int a;
        while(true) {
        	 	 Row row0 =sheet0.getRow(i);
        		 Cell cell0=row0.getCell(1);
        		 Cell cell1=row0.getCell(2);
        		 Cell cell2=row0.getCell(3);
        		 Cell cell3=row0.getCell(4);
        		 Gene g=new Gene();
        		 g.setnameGroup(cell0.toString());
        		
        		 int[] arr=new int[3];
        		 d=(Double.valueOf(cell1.toString()));
        		 a=(int)d;
        		 arr[0]=a;
        		 g.setIdProject(a);
        		 
        		 d=(Double.valueOf(cell2.toString()));
        		 a=(int)d;
        		 arr[1]=a;
        		 
        		 d=(Double.valueOf(cell3.toString()));
        		 a=(int)d;
        		 arr[2]=a;
        		 g.setChooseProjcet(arr);
 	             gen.add(g);
        		 i++;
        	if(sheet0.getRow(i)==null) {
        		break;
        	}
        }
        fis.close();
        
		return this;
	}
	
	public Chromosome creatChromosome() throws EncryptedDocumentException, InvalidFormatException, IOException {
		int i=1;
		gen=new ArrayList<Gene>();
        File f=new File("D:\\downloads\\Selection.xlsx");
        FileInputStream fis = new FileInputStream(f);   //obtaining bytes from the file  
        Workbook wb= WorkbookFactory.create(f);
        Sheet sheet0=wb.getSheetAt(0);
        double d;
        int a;
        
        while(true) {
        	 	 Row row0 =sheet0.getRow(i);
        		 Cell cell0=row0.getCell(1);
        		 Cell cell1=row0.getCell(2);
        		 Cell cell2=row0.getCell(3);
        		 Cell cell3=row0.getCell(4);
        		  d=(Double.valueOf(cell1.toString()));
        		  a=(int)d;
        		 Gene g=new Gene();
        		 g.setnameGroup(cell0.toString());
        		
        		 int[] arr=new int[3];
        		 
        		 d=(Double.valueOf(cell1.toString()));
        		 a=(int)d;
        		 arr[0]=a;
        		 
        		 d=(Double.valueOf(cell2.toString()));
        		 a=(int)d;
        		 arr[1]=a;
        		 
        		 d=(Double.valueOf(cell3.toString()));
        		 a=(int)d;
        		 arr[2]=a;
        		 
        		 g.setChooseProjcet(arr);
        		 int index=new Random().nextInt(arr.length);
        		 g.setIdProject(arr[index]);
        		 gen.add(g);
        		 i++;
        		 
        	if(sheet0.getRow(i)==null) {
        		break;
        	}
        }
        fis.close();
		
		
		return this;
	}
	
	public int calculateFitness() {
		int[] config=new int[40];
		for(int i=0;i<40;i++) {
			config[i]=0;
		}
		int chrfitnees=0;
		
		for(int i=0;i<gen.size();i++) {
			
		  if(config[gen.get(i).getIdProject()]==0) {
			  
			if(gen.get(i).getIdProject()==gen.get(i).getChooseProjcet()[0]) {
				chrfitnees=chrfitnees+10;
				config[gen.get(i).getIdProject()]=1;
			}
			else if(gen.get(i).getIdProject()==gen.get(i).getChooseProjcet()[1]) {
				chrfitnees=chrfitnees+9;
				config[gen.get(i).getIdProject()]=1;
			}
			else {
				chrfitnees=chrfitnees+8;
				config[gen.get(i).getIdProject()]=1;
			}
		  }
		  
			
		}
		
		return chrfitnees;
	}
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<gen.size();i++) {
			sb.append(gen.get(i).getIdProject());
			sb.append(" , ");
		}
		return sb.toString();
	}

	
	
	
	
	

}
