package org.ici;

import org.ici.model.Chromosome;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        // Generate population

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the length of each chromosome: ");
        int length = scanner.nextInt();

        System.out.print("Enter how many chromosomes you want: ");
        int numberChrome = scanner.nextInt();

        System.out.print("\nEnter 1 for mutation or 2 for crossover: ");
        int choose = scanner.nextInt();

        System.out.print("\nEnter the number of generations: ");
        int numGenerations = scanner.nextInt();

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        geneticAlgorithm.generatePopulation(numberChrome, length);
        System.out.println("\nGeneration number 1");
        System.out.println(geneticAlgorithm.toString());

        for(int i = 1; i < numGenerations; i++){
            // Variation

            System.out.println("variation");

            if(choose == 1){
                geneticAlgorithm.mutate();
            } else if (choose == 2) {
                geneticAlgorithm.crossover();
            } else {
                throw new RuntimeException(STR."Invalid option, value: \{choose}");
            }

            System.out.println(geneticAlgorithm.toString());

            // New generation

            System.out.println(STR."\nGeneration number \{i+1}");

            geneticAlgorithm.selection();
            System.out.println(geneticAlgorithm.toString());
        }

        scanner.close();
    }

}