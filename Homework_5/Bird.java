package Homework_5;

public class Bird extends Animals{
    Bird(String animalName, float runningDistance, float swimmingDistance, float jumpingHeight) {
        super("Bird", animalName, runningDistance, swimmingDistance, jumpingHeight);
    }

    protected boolean godMode(){
        return random.nextBoolean();
    }
}
