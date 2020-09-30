/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author poczt
 */
public class Parser
{
    File file;
    public Parser(File file){
        this.file = file;
    }

    public String[][] getData() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> dataList = new ArrayList<>();
        while (scanner.hasNextLine())
        {
            dataList.add(lineSplit(scanner.nextLine()));
        }

        String[][] data = new String[dataList.size()][];
        for(int i = 0; i < data.length; i++){
            data[i] = dataList.get(i);
        }
        return data;
    }

    public String[] lineSplit(String input){
        String[] tokens = input.split(" ");
        return tokens;
    }
}
