package hash2020;

public class Book implements Comparable<Integer>{

    static int idGen = 0;

    int id;
    int score;

    public Book(int score) {
        id = idGen;
        idGen++;
        this.score = score;
    }

    public int getId(){
        return id;
    }

    public int getScore(){
        return score;
    }

    public int compareTo(Integer id1){
        return Main.bookHashMap.get(id).getScore() - Main.bookHashMap.get(id1).getScore();
    }
}
