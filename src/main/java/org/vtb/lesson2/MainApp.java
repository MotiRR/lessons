package org.vtb.lesson2;

import org.vtb.lesson2.entity.Animal;
import org.vtb.lesson2.entity.Cat;
import org.vtb.lesson2.entity.Dog;
import org.vtb.lesson2.entity.Tiger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainApp {


    public static Map<Class<? extends Animal>, List<Animal>> countCertainAnimal(ArrayList<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::getClass));
    }

    public static void launchMethod(ArrayList<Animal> animals) {
        animals.stream().map(animal -> {
            return animal.run(120) + " " + animal.swim(230);
        }).forEach(System.out::println);
    }

    public static void fillAnimals(ArrayList<Animal> animals) {
        Animal cat = new Cat("Кошка", false);
        Animal cat1 = new Cat("Кошка", true);
        Animal dog = new Dog("Бобик");
        Animal tiger = new Tiger("Тигор");
        animals.add(cat);
        animals.add(cat1);
        animals.add(dog);
        animals.add(tiger);
        Animal.count = 4;
    }

    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        fillAnimals(animals);
        launchMethod(animals);
        Map map = countCertainAnimal(animals);
        System.out.printf("Количество животных %d \n", animals.size());
        Collection<ArrayList> colAmimals = map.values();
        for(ArrayList animal : colAmimals) {
            System.out.println("Количество "+animal.get(0).getClass() + " " +animal.size());
        }
    }
}
