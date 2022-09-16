import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class singleton {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("filename not given");
        }
        else {
            String file_name = args[0];

            try {
                File myObj = new File(file_name);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    List<String> list = Arrays.asList(data.split(", "));
                    int[] int_arr = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        int_arr[i] = Integer.parseInt(list.get(i));
                    }
                    System.out.println(singleton_search(int_arr, 0, int_arr.length - 1));
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }


    public static int singleton_search(int[] element_list, int min, int max) {
        int middle_element, middle_left, middle_right;
        int middle_index = (max - min) / 2 + min;

        // if there is only 1 element left then it must be the singleton
        if (max - min == 0) {
            return element_list[max];
        }

        middle_element = element_list[middle_index];

        // if there are 3 or more elements
        middle_left = element_list[middle_index - 1];
        middle_right = element_list[middle_index + 1];
        // if singleton happens to be the middle element then return it
        if (middle_element != middle_left && middle_element != middle_right) {
            return middle_element;
        }
        // if even number of elements to right of middle
        if ((max - middle_index) % 2 == 0) {
            // if same head right middle inclusive
            if (middle_element == middle_right) {
                return singleton_search(element_list, middle_index, max);
            }
            // if different head left middle inclusive
            else {
                return singleton_search(element_list, min, middle_index);
            }
        }
        // if odd number of elements to right of middle
        else {
            // if same head left middle exclusive
            if (middle_element == middle_right) {
                return singleton_search(element_list, min, middle_index - 1);
            }
            // if different head right middle exclusive
            else {
                return singleton_search(element_list, middle_index + 1, max);
            }
        }
    }
}
