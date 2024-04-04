package utils;

import javax.swing.*;
import java.util.List;

public class Utils {
    public static <T> T[] listToArray(List<T> list){
        //Crear un arreglo de object del tamaño de la lista
        T[] array = (T[]) new Object[list.size()];
        int i = 0;

        for (T iterator: list){
            array[i++] = iterator;
        }
     return array;
    }

    public static String manageDate(int date){
        try {
            if (date < 10){
                return 0 + String.valueOf(date);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"The date is invalid");
             return "Invalid date";

        }finally {
            return String.valueOf(date);
        }



    }

}
