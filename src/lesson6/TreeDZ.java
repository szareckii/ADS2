package lesson6;

public class TreeDZ {

    public static void main(String[] args) {

        final int RANGE = 50;   // [-25...+25]
        final int colTree = 20;
        int countBal = 0;


        Tree[] array  = new TreeImplDZ[colTree];

        for (int j = 0; j < colTree; j++) {
            array[j] = new TreeImplDZ<>();

            for (int i = 0; i < 15; i++) {
                int id = -1 * RANGE / 2 + (int) (Math.random() * RANGE);
                array[j].add(id);

            }

            if (array[j].isBal()) {
                countBal++;
            }

//          array[j].display();
//            System.out.println(array[j].isBal());
        }

        System.out.println("All trees: " + colTree);
        System.out.println("Balanced: " + countBal);
        System.out.println("Not balanced: " + (colTree - countBal));
        System.out.println("Percentage of balanced trees: " + (double) countBal/(colTree) * 100 + "%");
        System.out.println("Percentage of not balanced trees: " + (1- (double) countBal/(colTree)) * 100 + "%");

    }
}
