package hash2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Main {

    public static HashMap<Integer, Boolean> booksScanned = new HashMap<Integer, Boolean>();
    public static HashMap<Integer, Book> bookHashMap = new HashMap<>();
    public static HashMap<Integer, Integer> commonBooks = new HashMap<>();

    public static void main(String[] args) {

        String file1 = "E:\\Users\\poczt\\Algorithms\\src\\hash2020\\b_read_on.txt";
        String file2 = "E:\\Users\\poczt\\Algorithms\\src\\hash2020\\c_incunabula.txt";
        String file3 = "E:\\Users\\poczt\\Algorithms\\src\\hash2020\\d_tough_choices.txt";
        String file4 = "E:\\Users\\poczt\\Algorithms\\src\\hash2020\\e_so_many_books.txt";
        String file5 = "E:\\Users\\poczt\\Algorithms\\src\\hash2020\\f_libraries_of_the_world.txt";
        String output = "1.in";
        File file = new File(file1);
        Parser parser = new Parser(file);

        String[][] data = null;
        try {
            data = parser.getData();
        } catch (Exception e) {

        }
        ArrayList<Library> libraries = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();


        int booksNum = 0;
        int librariesNum = 0;
        int daysForScanning = 0;

        booksNum = Integer.parseInt(data[0][0]);
        librariesNum = Integer.parseInt(data[0][1]);
        daysForScanning = Integer.parseInt(data[0][2]);
        for (int i = 0; i < booksNum; i++) {
            booksScanned.put(i, false);
            books.add(new Book(Integer.parseInt(data[1][i])));
            bookHashMap.put(i, books.get(i));
            commonBooks.put(i, 0);
        }

        for (int i = 2; i < data.length - 2; i += 2) {
            int bNum = Integer.parseInt(data[i][0]);
            int sgProc = Integer.parseInt(data[i][1]);
            int shipLim = Integer.parseInt(data[i][2]);
            ArrayList<Integer> bookIds = new ArrayList<>();
            for (int j = 0; j < bNum; j++) {
                bookIds.add(Integer.parseInt(data[i + 1][j]));
                commonBooks.replace(Integer.parseInt(data[i + 1][j]), commonBooks.get(Integer.parseInt(data[i + 1][j])) + 1);
            }
            libraries.add(new Library(bNum, sgProc, shipLim, bookIds));
        }

        //sorting books
        for (Library lib : libraries) {
            lib.sortBooks();
        }

        //sorting libs
        Collections.sort(libraries);

        for(Library lib: libraries){
            System.out.println("booksNum: " + lib.booksNum + " setuptime: " + lib.setupTime + " capacity: " + lib.bookScanCapacity +
                    " score: " + lib.score() + " cms: " + lib.commones());
        }

        ArrayList<Library> scannedLibraries = new ArrayList<>();
        int count = 0;
        int daysToScan = 0;
        Library library = libraries.get(0);
        daysToScan = library.setupTime;
        for(Library lib: libraries){
            if(lib.setupTime < 10){
                library = lib;
                break;
            }
        }
        for (int i = 0; i < daysForScanning; i++) {
            for (Library lib : scannedLibraries) {
                lib.scanBooks();
            }
            if (daysToScan == 0) {
                count++;
                library = libraries.get(count);
                while(library.score() < 2000){
                    count++;
                    library = libraries.get(count);
                }
                scannedLibraries.add(library);
                daysToScan = library.setupTime;
            }
            daysToScan--;
        }

        System.out.println(scannedLibraries.size());

//        for (Integer name: commonBooks.keySet()){
//            String key = name.toString();
//            String value = commonBooks.get(name).toString();
//            System.out.println(key + " " + value);
//        }

        try {
            PrintStream out = new PrintStream(new FileOutputStream(output));
            System.setOut(out);
        } catch (FileNotFoundException e) {
            System.out.println("Print error!");
        }

        System.out.println(scannedLibraries.size());
        for (Library lib : scannedLibraries) {
            if (!lib.getScannedBooks().isEmpty()) {
                System.out.println(lib.id + " " + lib.booksScanned.size());
                System.out.println(lib.getScannedBooks());
            }
        }
    }
}
