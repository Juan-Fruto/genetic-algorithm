package org.ici;

import org.ici.model.Chromosome;
import org.ici.utils.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class GeneticAlgorithm {

    public List<Chromosome> chromosomes = new ArrayList<>();

    private Random random;

    public GeneticAlgorithm(){
        this.random = new Random();
    }

    public void generatePopulation(int n, int length){
        for(int i= 0 ; i < n; i++){
            chromosomes.add(new Chromosome(length));
        }
    }

    public void setMutation(Mutations mutations){}

    public void fitnessStatistics(){
        int numberOfChromosomes = this.chromosomes.size();

        List<Pair<Chromosome, Float>> statistics = new ArrayList<>();

        // O(n^2) solution
        for(int i = 0; i < numberOfChromosomes; i++){
            int counter = 0;

            for(int j = 0; j < numberOfChromosomes; j++){
                if(this.chromosomes.get(i).getFitness() == this.chromosomes.get(j).getFitness()){
                    counter++;
                }
            }

            Float percent = (float) counter / numberOfChromosomes;
            statistics.add(new Pair<>(this.chromosomes.get(i), percent));
        }
    }

    public void mutate(){
        for(Chromosome chromosome : this.chromosomes){
            if(this.random.nextInt(6) == 5){ // 20% to mutate
                Mutations.bitFlip(chromosome, chromosomes.size());
            }
        }
    }

    public void crossover(){
        int range = this.chromosomes.size() / 5; // 20% of the population
        range = (range % 2) == 0 ? range +=1 : range; // convert range to an odd number
        int midIndex = (chromosomes.getFirst().getValue().length()) / 2;

        for(int i = 0; i < range; i+=2){
            String firstItemHalf = this.chromosomes.get(i).getValue().substring(0, midIndex);
            String firstItemRight = this.chromosomes.get(i).getValue().substring(midIndex);

            String secondItemHalf = this.chromosomes.get(i + 1).getValue().substring(0, midIndex);
            String secondItemRight = this.chromosomes.get(i + 1).getValue().substring(midIndex);

            this.chromosomes.get(i).setValue(firstItemHalf + secondItemRight);
            this.chromosomes.get(i + 1).setValue(secondItemHalf + firstItemRight);
        }
    }

    public void selection(){
        List<Chromosome> newPopulation = new ArrayList<>();

        int fitnessSum = this.chromosomes.stream()
                .mapToInt(Chromosome::getFitness)
                .sum();

        for(int i = 0; i < this.chromosomes.size(); i++){
            int randomValue = this.random.nextInt(fitnessSum);

            int partialSum = 0;
            for(int j = 0; j < this.chromosomes.size(); j++){
                partialSum += this.chromosomes.get(j).getFitness();
                if(partialSum >= randomValue){
                    newPopulation.add(this.chromosomes.get(j));
                    break;
                }
            }
        }

        this.chromosomes = newPopulation;

    }

    @Override
    public String toString(){
        return this.chromosomes.stream()
                .map(chromosome -> chromosome.toString())
                .collect(Collectors.joining("\n"));
    }

}

