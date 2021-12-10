package Task9;

public class Main {

    public static void main(String[] args) {
        ThreadService threadService = new ThreadService();

        ThreadGroup threadGroup1 = new ThreadGroup("First group");
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1, "Second group");
        ThreadGroup threadGroup3 = new ThreadGroup(threadGroup2, "Third group");

        new Thread(threadGroup1, () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "firstThread").start();


        new Thread(threadGroup2, () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "secondThread").start();

        new Thread(threadGroup2, () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thirdThread").start();

        new Thread(threadGroup3, () -> {
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "fourthThread").start();

        threadService.printTreadsInfo(threadGroup1);
        threadService.printTreadsInfo(threadGroup2);
        threadService.printTreadsInfo(threadGroup3);
    }
}
