package Homework_5;

public class Main {
    public static void main(String[] args) {
        Animals[] animalMarathon = {
            new Cat("Byakko", 200, 0, 2),
            new Dog("Hayabusa", 500, 10, 0.5f),
            new Horse("Nobu", 1500, 100, 3),
            new Bird("Beri", 5, 0, 0.2f) };

        float distanceToRun = 200;
        float distanceToSwim = 5;
        float heightToJump = 1.5f;

        for (int i = 0; i < animalMarathon.length; i++) {
            if (animalMarathon[i] instanceof Bird)
            {
                System.out.println(animalMarathon[i].getName() + " is trying to run " + distanceToRun + " meters and " +
                        (animalMarathon[i].run(distanceToRun) ? "succeeds!" : "fails..." +
                                (((Bird) animalMarathon[i]).godMode() ? " but activates god mode and does it anyway!" : "")));
                System.out.println(animalMarathon[i].getName() + " is trying to swim " + distanceToSwim + " meters and " +
                        (animalMarathon[i].swim(distanceToSwim) ? "succeeds!" : "fails..." +
                                (((Bird) animalMarathon[i]).godMode() ? " but activates god mode and does it anyway!" : "")));
                System.out.println(animalMarathon[i].getName() + " is trying to jump " + heightToJump + " meters and " +
                        (animalMarathon[i].jump(heightToJump) ? "succeeds!" : "fails..." +
                                (((Bird) animalMarathon[i]).godMode() ? " but activates god mode and does it anyway!" : "")) +
                        "\n==============================");
            }

            else{
                System.out.println(animalMarathon[i].getName() + " is trying to run " + distanceToRun + " meters and " +
                        (animalMarathon[i].run(distanceToRun) ? "succeeds!" : "fails..."));
                System.out.println(animalMarathon[i].getName() + " is trying to swim " + distanceToSwim + " meters and " +
                        ((animalMarathon[i].getSwimmingDistance() == 0) ? "turns out that it can't swim!" :
                                animalMarathon[i].swim(distanceToSwim) ? "succeeds!" : "fails..."));
                System.out.println(animalMarathon[i].getName() + " is trying to jump " + heightToJump + " meters and " +
                        (animalMarathon[i].jump(heightToJump) ? "succeeds!" : "fails...") +
                        "\n==============================");
            }
        }
    }
}
