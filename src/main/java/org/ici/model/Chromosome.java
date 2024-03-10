package org.ici.model;

import java.util.Random;


public class Chromosome {

    private String value;
    private int fitness;

    public Chromosome(int length){
        this.createChromosome(length);
        this.setFitness();
    }

    private void createChromosome(int length){
        Random random = new Random();
        StringBuilder chromosome = new StringBuilder();

        for(int i = 0; i < length; i++){
            chromosome.append(random.nextInt(2));
        }
        this.value = chromosome.toString();
    }

    public void setValue(String value){
        for(char e : value.toCharArray()){
            if(!(e == '1' || e == '0')){
                throw new RuntimeException("Detected a no binary element in the chromosome value");
            }

            this.value = value;
            this.setFitness();
        }
    }

    private void setFitness(){
        int counter = 0, fitness = 0;

        for(char e : this.value.toCharArray()){
            if(e == '1'){
                counter++;
            } else {
                if(counter > fitness) fitness = counter;
                counter = 0;
            }
        }

        if(counter > fitness) fitness = counter; // for chromosomes which ends with 1

        this.fitness = fitness;
    }

    public String getValue() {
        return this.value;
    }

    public int getFitness(){
        return this.fitness;
    }

    @Override
    public String toString(){
        return STR."{ Value: \{this.getValue()}, fitness: \{this.getFitness()} }";
    }
}
