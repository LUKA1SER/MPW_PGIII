package model;

public class TestClass {
    public static void main(String[] args) {
        Ocean ocean = new Ocean();
        int[][] oceanArray = ocean.getOceanTiles();
        char eingabe;
        oceanArray[1][4] = -1;
        ocean.placeFish(1,3);
        ocean.placeFish(1,3);
        ocean.placeFish(3,2);

        while (true) {
            for (int i = 0; i < oceanArray.length; i++) {
                for (int j = 0; j < oceanArray[1].length; j++) {
                    if (j == ocean.getWhaleCol() && i == ocean.getWhaleRow()) {
                        switch (ocean.getWhaleDirection()) {
                            case 0:
                                System.out.print("^" + " ");
                                break;
                            case 1:
                                System.out.print("<" + " ");
                                break;
                            case 2:
                                System.out.print("v" + " ");
                                break;
                            case 3:
                                System.out.print(">" + " ");
                                break;
                        }
                        continue;
                    }

                    System.out.print(oceanArray[i][j] + " ");
                }
                System.out.println();
            }


            eingabe = IO.readChar("Eingabe");

            switch(eingabe) {
                case 't': ocean.getWhale().turn();
                            break;
                case 'm': ocean.getWhale().move();
                            break;
                case 'n': ocean.getWhale().pickFish();
                            break;
                case 'p': ocean.getWhale().putFish();
                            break;
                case 's': oceanArray = ocean.setOceanSize(3,3);
                            break;
                case 'g': oceanArray = ocean.setOceanSize(10, 10);
                            break;

            }








         }
    }
}
