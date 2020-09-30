package hash2020;

import java.util.HashMap;
import java.io.*;
import java.util.*;

/**
 * Library
 */
public class Library implements Comparable<Library> {

    static int idGenLib = 0;
    ArrayList<Integer> booksScanned = new ArrayList<>();
    HashMap<Book, Boolean> books;
    ArrayList<Integer> bookIds = new ArrayList<>();
    int booksNum;
    int setupTime;
    int bookScanCapacity;
    int id;

    public Library(int booksNum, int setupTime, int bookScanCapacity, ArrayList<Integer> bookIds) {
        id = idGenLib;
        idGenLib++;
        this.booksNum = booksNum;
        this.setupTime = setupTime;
        this.bookScanCapacity = bookScanCapacity;
        this.bookIds = bookIds;
    }

    public String getBookIds() {
        String ids = "";
        for (Integer integer : bookIds) {
            ids += integer + " ";
        }
        return ids;
    }

//    public int getNumUniqueBooks() {
//        int count = 0;
//        ArrayList<Integer> uniqueBookIDs = new ArrayList<>();
//        for (Book book : books.keySet()) {
//            if (!uniqueBookIDs.contains(book.id)) {
//                count++;
//                uniqueBookIDs.add(book.id);
//            }
//        }
//        return count;
//    }

    public void sortBooks() {
        Collections.sort(bookIds, Collections.reverseOrder());
    }

    public void scanBooks() {
        int capacity = bookScanCapacity;
        ArrayList<Integer> booksToRemove = new ArrayList<>();
        for (Integer integer : bookIds) {
            if (!Main.booksScanned.get(integer) && capacity > 0) {
                Main.booksScanned.replace(integer, true);
                booksToRemove.add(integer);
                booksScanned.add(integer);
                capacity--;
            }
        }
        bookIds.removeAll(booksToRemove);
        booksToRemove.clear();
    }

    public String getScannedBooks() {
        String bs = "";
        for (Integer integer : booksScanned) {
            bs += integer + " ";
        }
        return bs;
    }

    public int score() {
        int tS = 0;
        for (Integer integer : bookIds) {
            tS += Main.bookHashMap.get(integer).getScore();
        }
        return tS;
    }

    public int commones(){
        int cms = 0;
        for(Integer integer: bookIds){
            cms += Main.commonBooks.get(integer);
        }
        return cms;
    }

    public int compareTo(Library lib) {
//        if ((double)(score())/(double)(setupTime) >  (double)(lib.score())/(double)(lib.setupTime)) {
//            return 1;
//        } else {
//            return -1;
//        }
        return this.setupTime - lib.setupTime;

    }
}
