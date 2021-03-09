import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
    static byte[] getBinary(String s) {
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
     * @return converted string from given bytes
     */
    static String getString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for (int i = 0; i < Byte.SIZE * bytes.length; i++)
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }

    public static void huffmanCompress(Node root, String string, Map<Character, String> huffmanCode) {
        // return if the node is an internal node
        if (root == null) {
            return;
        }
        // insert a mapping into a map if the node is a leaf node
        if (isLeaf(root)) {
            huffmanCode.put(root.character, string.length() > 0 ? string : "1");
        }

        huffmanCompress(root.leftChild, string + '0', huffmanCode);
        huffmanCompress(root.rightChild, string + '1', huffmanCode);
    }
 
    public static final StringBuilder decompressedString = new StringBuilder();

    public static int huffmanDecompress(Node root, int index, StringBuilder stringBuilder) {
        if (root == null) {
            return index;
        }

        if (isLeaf(root)) {
            decompressedString.append(root.character);
            return index;
        }

        index ++;

        root = (stringBuilder.charAt(index) == '0') ? root.leftChild : root.rightChild;
        index = huffmanDecompress(root, index, stringBuilder);
        return index;
    }

    /**
     * saving the decompressed Huffman Code to a file with type String
     */
    public static void saveDecompressedToFile() {
        // specifying the path of the output file
        try (FileOutputStream fileOutputStream = new FileOutputStream("./out/huffman_decompressed_file.txt", true)) {
            fileOutputStream.write(decompressedString.toString().getBytes());
        }
        catch (IOException error) {
            // printing the stack trace if an I/O exception has occured
            error.printStackTrace();
        }
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
     * @param inputString the string to be compressed 
     */
    public static void buildHuffmanTree(String inputString) {
        // assigning variable encodeStartTime to be the current time in ms
        final long encodeStartTime = System.currentTimeMillis();

        Map<Character, Integer> frequency = new HashMap<>();
        PriorityQueue<Node> priorityQueue;

        if (inputString == null || inputString.length() == 0) {
            return;
        }

        for (char character : inputString.toCharArray()) {
            frequency.put(character, frequency.getOrDefault(character, 0) + 1);
        }

        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(l -> l.frequency));

        for (var entry : frequency.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() != 1) {
            Node leftChild = priorityQueue.poll();
            Node rightChild = priorityQueue.poll();

            int frequencySum = leftChild.frequency + rightChild.frequency;
            priorityQueue.add(new Node (null, frequencySum, leftChild, rightChild));
        }

        Node root = priorityQueue.peek();

        Map<Character, String> huffmanCode = new HashMap<>();
        huffmanCompress(root, "", huffmanCode);

        // building a stringBuilder object for encoded string
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : inputString.toCharArray()) {
            stringBuilder.append(huffmanCode.get(character));
        }

        // converting the encoded stringBuilder object to binary
        byte[] binaryConverted = getBinary(stringBuilder.toString());

        // specifying the output file for converted binary
        try (OutputStream output = new FileOutputStream("./out/huffman_compressed_file.bin")) {
            // writing the binary to the output file
            output.write(binaryConverted);
        } 
        catch (IOException exception) {
            // printing the stack trace if an I/O exception has occured
            exception.printStackTrace();
        }

        // assigning variable encodeEndTime to be the current time in ms
        final long encodeEndTime = System.currentTimeMillis();
    
        // DECODE
        // assigning variable encodeEndTime to be the current time in ms
        final long decodeStartTime = System.currentTimeMillis();
        if (isLeaf(root)) {
            while (root.frequency-- > 0) {
                System.out.print(root.character);
            }
        }
        else {
            int index = -1;
            while (index < stringBuilder.length() - 1) {
                index = huffmanDecompress(root, index, stringBuilder);
            }
        }

        // calling function saveDecompressedToFile
        saveDecompressedToFile();

        // assigning variable encodeEndTime to be the current time in ms
        final long decodeEndTime = System.currentTimeMillis();

        // returning the total time taken for the file to be compressed and saved
        System.out.println("Total encode time: " + (encodeEndTime - encodeStartTime) + "ms");

        // returning the total time taken for the file to be decompressed and saved
        System.out.println("Total decode time: " + (decodeEndTime - decodeStartTime) + "ms");
    }

    public static void main(String[] args) {
        String filePath = "./resources/datasets/dataset_real(world_leaders).txt";
        buildHuffmanTree(readLineToString(filePath));
    }
}
