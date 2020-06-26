import java.util.Random;

public class MainClass {
    private static int SIZE = 4;

    public static void main(String[] args) {
        int sum = 0;
        Random randomNumber = new Random();
        String[][] arr = new String[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                arr[i][j] = String.valueOf(randomNumber.nextInt(20));
            }
        }
//        arr[1][2] = "Adilet";

        try {
            sum = checkArr(arr);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

        System.out.println(getWorkingDays(DayOfTheWeek.SUNDAY));
    }

    public static int checkArr(String[][] arr) {
        int sum = 0;
        if (arr.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }

    public static String getWorkingDays(DayOfTheWeek day) {
        int num = 5 - day.ordinal();
        return (num > 0) ? String.valueOf(num * 24) + " hours left" : "Weekend";
    }
}

class MyArrayDataException extends RuntimeException {
    int row;
    int column;

    MyArrayDataException(int row, int column) {
        super(row + " " + column);
        this.row = row;
        this.column = column;
    }
}

class MyArraySizeException extends RuntimeException {
    MyArraySizeException() {
        super("Size is too big");
    }
}

enum DayOfTheWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
