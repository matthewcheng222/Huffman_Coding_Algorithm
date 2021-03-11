import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//Huffman Tree Node
class Node {
    Node leftChild = null;
    Node rightChild = null;
    Character character;
    Integer frequency;

    /**
     * constructor for initialising a node.
     * 
     * @param character the character associated with the node
     * @param frequency the frequency of a node
     * @param leftChild the left child of a node
     * @param rightChild the right child of a node
     */
    public Node (Character character, Integer frequency, Node leftChild, Node rightChild) {
        this.character = character;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * constructor for initialising a node.
     * 
     * @param character the character associated with the node
     * @param frequency the frequency of a node
     */
    Node(Character character, Integer frequency) {
        this.character = character;
        this.frequency = frequency;
    }
}

class HuffmanCoding{
    /**
     * read all lines of a file and convert it to a string.
     * 
     * @param filePath the relative path of file to be read
     * @return strings read from the file
     */
    public static String readLineToString(String filePath) {
        // initialising StringBuilder toReturnString
        StringBuilder toReturnString = new StringBuilder();

        // append each line to toReturnString
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(line -> toReturnString.append(line).append("\n"));
        }
        catch (IOException error) {
            // printing the stack trace if an I/O exception has occured
            error.printStackTrace();
        }
        return toReturnString.toString();
    }

    /**
     * converting a given string to type binary.
     * 
     * @param s the string to be converted to binary
     * @return converted binary from given string
     */
    public static byte[] getBinary(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        while (sBuilder.length() % 8 != 0) {
            sBuilder.append('0');
        }
        s = sBuilder.toString();

        byte[] data = new byte[s.length() / 8];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                data[i >> 3] |= 0x80 >> (i & 0x7);
            }
        }
        return data;
    }

    /**
     * converting a given bytes to type string.
     * 
     * @param bytes bytes to be converted to string
     * @return converted stringbuilder from given bytes
     */
    public static StringBuilder getString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb;
    }

    /**
     * traversing the huffman tree and storing huffman codes in a map.
     * 
     * @param root
     * @param string
     * @param huffmanCode hash map that contains the huffman codes
     */
    public static void huffmanEncode(Node root, String string, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }
        // insert a mapping into a map if the node is a leaf node
        if (isLeaf(root)) {
            huffmanCode.put(root.character, string.length() > 0 ? string : "1");
        }

        // traversing the huffman tree by passing the left and right childs
        huffmanEncode(root.leftChild, string + '0', huffmanCode);
        huffmanEncode(root.rightChild, string + '1', huffmanCode);
    }

    /**
     * checking if the node is a leaf node.
     * 
     * @param root the node to be checked
     * @return true if the tree is an internal node
     */
    public static boolean isLeaf(Node root) {
        return root.leftChild == null && root.rightChild == null;
    }

    /**
     * building the Huffman Tree, compressing the string and saving the compressed string to a binary file.
     * 
     * @param origin the name of the file to be compressed
     * @param destination the name of the output file
     */
    public static void huffmanCompress(String origin, String destination) {
        // specifying the path of the file to be compressed
        String filePath = "./resources/fileToTest/" + origin;
        // calling function readLineToString to read lines from given file to string
        String inputString = readLineToString(filePath);

        // assigning variable compressStartTime to be the current time in ms
        final long compressStartTime = System.currentTimeMillis();

        // creating a hash map to store the frequency of characters
        Map<Character, Integer> frequency = new HashMap<>();
        PriorityQueue<Node> priorityQueue;

        // return if the input string (from target file) is empty
        if (inputString == null || inputString.length() == 0) {
            return;
        }

        // counting the frequency of a character and putting it in a map
        for (char character : inputString.toCharArray()) {
            frequency.put(character, frequency.getOrDefault(character, 0) + 1);
        }

        // initialising the priority queue 
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(l -> l.frequency));

        for (var entry : frequency.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() != 1) {
            Node leftChild = priorityQueue.poll();
            Node rightChild = priorityQueue.poll();

            // setting the frequency of internal node to be the sum of frequency of left and right child
            int frequencySum = leftChild.frequency + rightChild.frequency;
            priorityQueue.add(new Node (null, frequencySum, leftChild, rightChild));
        }

        Node root = priorityQueue.peek();

        // initialising a hash map object to store the huffman codes
        Map<Character, String> huffmanCode = new HashMap<>();
        huffmanEncode(root, "", huffmanCode);

        // storing the hash map huffmanCode to a file for future reference
        try (FileOutputStream fos = new FileOutputStream(new File("./out/" + destination + ".huffmancode"))) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(huffmanCode);
            oos.flush();
            oos.close();
        } 
        catch (Exception e) {
            // printing the stack trace if there is an exception
            e.printStackTrace();
        }
        
        // building a stringBuilder object to store the encoded string
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : inputString.toCharArray()) {
            stringBuilder.append(huffmanCode.get(character));
        }

        // converting the encoded stringBuilder object to binary
        byte[] binaryConverted = getBinary(stringBuilder.toString());

        // specifying the output file for converted binary
        try (OutputStream output = new FileOutputStream("./out/" + destination)) {
            // writing the binary to the output file
            output.write(binaryConverted);
        } 
        catch (IOException exception) {
            // printing the stack trace if an I/O exception has occured
            exception.printStackTrace();
        }
       
        // assigning variable compressEndTime to be the current time in ms
        final long compressEndTime = System.currentTimeMillis();

        // returning the statistics of compressing the file
        File originalFile = new File(filePath);
        File compressedFile = new File("./out/" + destination);
        double originalFileSizeBytes = (double)originalFile.length();
        double compressedFileSizeBytes = (double)compressedFile.length();
        System.out.println("Statistics of compressing the file:");
        System.out.println("Compress time: " + (compressEndTime - compressStartTime) + "ms");
        System.out.println("Location of compressed file: ./out/" + destination);
        System.out.println("Size of compressed file: " + compressedFileSizeBytes + " bytes");
        System.out.println("Compression rate:: " + (originalFileSizeBytes - compressedFileSizeBytes)/originalFileSizeBytes*100 + "%");
        System.out.println("");
    }

    /**
     * decompressing the Huffman encoded stringbuilder object with the hash map
     * 
     * @param huffmanCode the hash map storing the characters and the respective binary codes
     * @param toDecompress the stringbuilder object to be decompressed
     * @param destination the name of the file where the decompressed string will be stored
     */
    public static void huffmanDecompress(Map<Character, String> huffmanCode, StringBuilder toDecompress, String destination) {
        // assigning variable decompressStartTime to be the current time in ms
        final long decompressStartTime = System.currentTimeMillis();
        // creating a stringBuilder result which stores the decoded string
        StringBuilder result = new StringBuilder();
        // creating a stringBuilder temp which appends the encoded bits 
        StringBuilder temp = new StringBuilder();

        // creating a hash map to store the swapped huffmanCode
        HashMap<String, Character> swapped = new HashMap<>();
        // swapping the keys and the values of hash map huffmanCode and storing it in swapped
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            swapped.put(entry.getValue(), entry.getKey());
        }

        char[] bits = toDecompress.toString().toCharArray();
        for (int i = 0; i < bits.length; i++) {
            temp.append(bits[i]);
            Object value = swapped.get(temp.toString());
            // continue the for loop if value is not found
            if (value == null) {
                continue;
            }
            // appending the stringBuilder object with value
            result.append(value);
            // clearing the stringBuilder temp
            temp.setLength(0);
        }
        // calling function saveDecompressedToFile to save the decompressed string to given file
        saveDecompressedToFile(result, destination);

        // assigning variable compressEndTime to be the current time in ms
        final long decompressEndTime = System.currentTimeMillis();

        // returning the statistics of decompressing the file
        System.out.println("File decompressed:");
        System.out.println("Decompress time: " + (decompressEndTime - decompressStartTime) + "ms");
        System.out.println("Location of decompressed file: ./out/" + destination);
        System.out.println("");
    }

    /**
     * saving the decompressed Huffman Code to a file with type String
     * 
     * @param targetFileName the file name of the original file
     */
    public static void saveDecompressedToFile(StringBuilder toSaveDecompressed, String destination) {
        // specifying the path of the output file
        File outputDecompressedFile = new File("./out/" + destination);
        try (FileOutputStream outputStream = new FileOutputStream(outputDecompressedFile, false)) {
            outputStream.write(toSaveDecompressed.toString().getBytes());
        }
        catch (IOException error) {
            // printing the stack trace if an I/O exception has occured
            error.printStackTrace();
        }
    }

    /**
     * the main method.
     */
    public static void main(String[] args) {
        // clearing the console 
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

        System.out.println("Huffman Coding - Compression/Decompression");
        System.out.println("");
        System.out.println("");
        System.out.println("Please follow the format of entry: <option> <origin> <destination>");
        System.out.println("");
        System.out.println("For example, if you want to compress file <tocompress.txt> and store it as <compressed.bin>, enter:");
        System.out.println("compress tocompress.txt compressed.bin");
        System.out.println("");
        System.out.println("Otherwise, if you want to decompress file <compressed.bin> and save it as <decompressed.txt>, enter:");
        System.out.println("decompress compressed.bin tocompress.txt");
        System.out.println("");

        // initialising a scanner object
        Scanner userInput = new Scanner(System.in);
        String userSelection = userInput.nextLine();
        // splitting the user-inputted object with " "
        String[] userFields = userSelection.split(" ");
        // checking if the length of the splitted string array is 3 
        if (userFields.length == 3) {
            String option = userFields[0];
            String origin = userFields[1];
            String destination = userFields[2];
            if (option.equals("compress")) {
                huffmanCompress(origin, destination);
            }
            else if (option.equals("decompress")) {
                try (FileInputStream fis = new FileInputStream("./out/" + origin + ".huffmancode")) {
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    @SuppressWarnings("unchecked")
                    HashMap<Character,String> huffmanCode = (HashMap<Character,String>)ois.readObject();
                    ois.close();
                    byte[] bytesToDecompress = Files.readAllBytes(Paths.get("./out/" + origin));
                    StringBuilder toDecompress = getString(bytesToDecompress);
                    huffmanDecompress(huffmanCode, toDecompress, destination);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Please enter a valid option (compress/ decompress)");
            }
        }
        else {
            System.out.println("Please follow the correct format of entry");
        }
        userInput.close();
    }
}
