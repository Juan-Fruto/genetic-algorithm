package org.ici;

import org.ici.model.Chromosome;

import java.util.Random;

public class Mutations {

    private static Random random = new Random();

    private Mutations(){}

    public static void bitFlip(Chromosome chromosome, int n){
        StringBuffer newValue = new StringBuffer(chromosome.getValue());

        for(int i = 1; i < n; i++){
            int randomIndex = random.nextInt(chromosome.getValue().length());
            newValue.setCharAt(randomIndex, newValue.charAt(randomIndex) == '1' ? '0' : '1');
        }

        chromosome.setValue(newValue.toString());
    }

    public static void sweep(){
        throw new RuntimeException("Method not implemented");
    }

    public static void scramble(){
        throw new RuntimeException("Method not implemented");
    }

    public static void inversion(){
        throw new RuntimeException("Method not implemented");
    }

}
