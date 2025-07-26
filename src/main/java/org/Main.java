package org;

import org.simulation.entities.Entity;
import org.simulation.Simulation;
import org.simulation.entities.Herbivore;
import org.simulation.entities.Predator;
import org.simulation.entities.objects.Grass;
import org.simulation.utils.InputHandler;
import org.simulation.config.GameParameters;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameParameters gameParameters = InputHandler.fromConsole();

        Simulation simulation = new Simulation(gameParameters);
        simulation.startSimulation();
        System.out.println("Симуляция запущена! Для паузы/продолжения нажмите 'P'");

        while(true) {
            String input = scanner.nextLine().strip().toLowerCase();
            if(!input.isEmpty()) {
                switch (input) {
                    case "ah" -> addEntity(simulation, Herbivore.class);
                    case "ag" -> addEntity(simulation, Grass.class);
                    case "ap" -> addEntity(simulation, Predator.class);
                    case "l" ->  {
                        System.out.println(simulation.printLogs());
                        simulation.clearLogs();
                    }
                    case "p" -> {
                        simulation.pauseSimulation();
                        System.out.println("На паузе");
                    }
                    case "r" -> simulation.resumeSimulation();
                    case "x" -> System.exit(0);
                    case "help" -> System.out.println(
                            """
                                ah -> добавить травоядное \n
                                ag -> добавить траву \n
                                ap -> добавить хищника \n
                                l -> вывести логи \n
                                p -> пауза симуляция \n
                                r -> продолжение симуляции \n
                                x -> выход из симуляции \n
                            """
                    );
                    default -> System.out.println("Неверная команда: " + input + ". Чтобы посмотреть доступные команды введите help");
                }
            }
        }
    }

    private static <T extends Entity> void addEntity(Simulation simulation, Class<T> tClass) {
        if (simulation.isRunning()) {
            System.out.println("Поставьте на паузу.");
        }
        else {
            simulation.addCreateAction(tClass);
        }
    }
}