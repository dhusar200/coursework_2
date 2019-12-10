import java.util.Scanner;

class Dec2Hex {

    public static int Arg1;

    public static void main(String args[]) {
        for(int i = 0; i < args.length; i++) {
            if (!args[i].equals("")) {
                try {
                    Arg1 = Integer.parseInt(args[i]);

                    if (Arg1 > 0) {
                        char ch[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                        int rem, num;
                        num = Arg1;
                        String hexadecimal = "";
                        System.out.println("Converting the Decimal Value " + num + " to Hex...");

                        while (num != 0) {
                            rem = num % 16;
                            hexadecimal = ch[rem] + hexadecimal;
                            num = num / 16;
                        }
                        System.out.println("Hexadecimal representation is : " + hexadecimal);
                    } else {
                        System.err.println("Argument " + args[i] + " cannot be negative.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Argument" + args[i] + " must be an integer.");
                }
            } else {
                System.err.println("Argument needs to have an value.");
            }
        }
        System.out.println("Thank you for using our application!!");
    }
}
