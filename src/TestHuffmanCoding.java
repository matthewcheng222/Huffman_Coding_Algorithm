import java.util.HashMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestHuffmanCoding extends HuffmanCoding{
    public static void testBook01() {
        String origin = "book_Alice's_Adventures_In_Wonderland(ENG).txt";
        String destination = "(compressed)book_Alice's_Adventures_In_Wonderland(ENG).bin";
        String output = "(decompressed)book_Alice's_Adventures_In_Wonderland(ENG).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testBook02() {
        String origin = "book_Alice's_Adventures_In_Wonderland(FR).txt";
        String destination = "(compressed)book_Alice's_Adventures_In_Wonderland(FR).bin";
        String output = "(decompressed)book_Alice's_Adventures_In_Wonderland(FR).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testBook03() {
        String origin = "book_Alice's_Adventures_In_Wonderland(PT).txt";
        String destination = "(compressed)book_Alice's_Adventures_In_Wonderland(PT).bin";
        String output = "(decompressed)book_Alice's_Adventures_In_Wonderland(PT).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testBook04() {
        String origin = "book_Oliver_Twist(ENG).txt";
        String destination = "(compressed)book_Oliver_Twist(ENG).bin";
        String output = "(decompressed)book_Oliver_Twist(ENG).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testBook05() {
        String origin = "book_Oliver_Twist(FR).txt";
        String destination = "(compressed)book_Oliver_Twist(FR).bin";
        String output = "(decompressed)book_Oliver_Twist(FR).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testBook06() {
        String origin = "book_Oliver_Twist(PT).txt";
        String destination = "(compressed)book_Oliver_Twist(PT).bin";
        String output = "(decompressed)book_Oliver_Twist(PT).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testDataset01() {
        String origin = "dataset_artifical(rs.13).txt";
        String destination = "(compressed)dataset_artifical(rs.13).bin";
        String output = "(decompressed)dataset_artifical(rs.13).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testDataset02() {
        String origin = "dataset_artificial(fib41).txt";
        String destination = "(compressed)dataset_artificial(fib41).bin";
        String output = "(decompressed)dataset_artificial(fib41).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testDataset03() {
        String origin = "dataset_pseudo_real(dsources.001.2).txt";
        String destination = "(compressed)dataset_pseudo_real(dsources.001.2).bin";
        String output = "(decompressed)dataset_pseudo_real(dsources.001.2).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testDataset04() {
        String origin = "dataset_pseudo_real(english.001.2).txt";
        String destination = "(compressed)dataset_pseudo_real(english.001.2).bin";
        String output = "(decompressed)dataset_pseudo_real(english.001.2).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }

    public static void testDataset05() {
        String origin = "dataset_real(world_leaders).txt";
        String destination = "(compressed)dataset_real(world_leaders).bin";
        String output = "(decompressed)dataset_real(world_leaders).txt";
        HuffmanCoding.huffmanCompress(origin, destination);
        try (FileInputStream fis = new FileInputStream("./out/" + destination + ".huffmancode")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
            ois.close();
            byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + destination));
            StringBuilder toDecompress = getString(bytesToDecompress);
            HuffmanCoding.huffmanDecompress(huffmanCode, toDecompress, output);
        }
        catch (Exception e) {}
    }


    public static void testBooks() {
        testBook01();
        testBook02();
        testBook03();
        testBook04();
        testBook05();
        testBook06();
    }

    public static void testDatasets() {
        testDataset01();
        testDataset02();
        testDataset03();
        testDataset04();
        testDataset05();
    }
    public static void main(String[] args) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream("./out/output.txt"));
            System.setOut(out);
        }
        catch (FileNotFoundException e) {}
        testBooks();
    }
}
