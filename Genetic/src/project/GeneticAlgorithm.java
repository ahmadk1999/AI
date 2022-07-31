package project;

import java.util.Random;

public class GeneticAlgorithm {
	public static final int POPULATION_SIZE=20;
	public static final int TOURMANET_SELECTION_SIZE=2;
	public static final int NUMB_ELITE_CHR=4;
	public static final double MUTION_RATE=0.15;

	
	public Population envlove(Population population) {
		return mutatePopul(crossoverPopul(population));
	}
	private Population crossoverPopul(Population population) {
		Population crossoverPopulation=new Population(population.getChromosome().length);
		population.sortChromosomeByFitness();
		for(int i=0;i<NUMB_ELITE_CHR;i++) {
			crossoverPopulation.getChromosome()[i]=population.getChromosome()[i];
		}
		for(int i=NUMB_ELITE_CHR;i<population.getChromosome().length;i++) {
			
			Chromosome chromosome1=selectTourmentPopulation(population).getChromosome()[0];
			Chromosome chromosome2=selectTourmentPopulation(population).getChromosome()[0];
			
			crossoverPopulation.getChromosome()[i]=crossoverChromosome(chromosome1, chromosome2);
		}
		return crossoverPopulation;
	}
	private Chromosome crossoverChromosome(Chromosome chr1,Chromosome chr2) {
		Chromosome crossoverChromosome=new Chromosome();
		for(int i=0;i<chr1.getGen().size();i++) {
			if(Math.random() <0.5 ) {
				crossoverChromosome.getGen().add(chr1.getGen().get(i));
			}
			else {
				crossoverChromosome.getGen().add(chr2.getGen().get(i));
				
			}
		}
		return crossoverChromosome;	
	}
	private Population selectTourmentPopulation(Population population) {
		Population tournamentPopulation = new Population(TOURMANET_SELECTION_SIZE);
		for(int i=0;i<TOURMANET_SELECTION_SIZE;i++) {
			int index=new Random().nextInt(population.getChromosome().length);
		tournamentPopulation.getChromosome()[i]=
				population.getChromosome()[index];
		}
		tournamentPopulation.sortChromosomeByFitness();
		return tournamentPopulation;
	}
	
	
	
	
	private Population mutatePopul(Population population) {
		Population mutatePopulation=new Population(population.getChromosome().length);
		population.sortChromosomeByFitness();
		for(int i=0;i<NUMB_ELITE_CHR;i++) {
			mutatePopulation.getChromosome()[i]=population.getChromosome()[i];
			
		}
		for(int i=NUMB_ELITE_CHR;i<population.getChromosome().length;i++) {
			mutatePopulation.getChromosome()[i]=mutateChromosome(population.getChromosome()[i]);
		}
		
		
		return mutatePopulation;
	}
	
	
	private Chromosome mutateChromosome(Chromosome chromosome) {
		Chromosome mutechr=new Chromosome();
		
		
		for(int i=0;i<chromosome.getGen().size();i++) {
			if(Math.random()<MUTION_RATE) {
				int[] chose=new int[3];
				chose=chromosome.getGen().get(i).getChooseProjcet();
				 int index=new Random().nextInt(chose.length);
				Gene g=new Gene();
				g.setnameGroup(chromosome.getGen().get(i).getnameGroup());
				g.setChooseProjcet(chose);
				g.setIdProject(chose[index]);
				mutechr.getGen().add(g);
			}else {
				mutechr.getGen().add(chromosome.getGen().get(i));
				
			}
		}
		
		return mutechr;
	}
	
	

}
