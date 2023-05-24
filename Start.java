/*
 * Vimal Kumawat (vkumaa6a)
 *
 * @author Vimal Kumawat
 * @email vimal.kumawat@airbus.com
 * @version 1.0
 *
 * This project focuses on the implementation of a distributed system, where
 * there are several nodes who communicate among each other via messages. Each
 * node generates a random value and adds its own value while passing the
 * message to the next node.
 */

import java.util.Random;

/**
 * Start class contains the main method, which is required to execute the
 * program. It generates a new random number before creating a new node and
 * assigns those numbers to the respective node
 *
 * @author Vimal
 *
 * @variable id is the id of the node to be created
 * @variable config_path is the path of the configuration file from where the
 * data is fetched
 */
public class Start implements Runnable {

    private String directoryOfFiles;
    private String config_path;

    public Start(String directoryOfFiles, String config_path) {
        this.directoryOfFiles = directoryOfFiles;
        this.config_path = config_path;
    }

    public void run() {
        
        String[] file_content = readAllFileContents(directoryOfFiles);
        int currentComparisons = file_content.length/2;
        if(file_content.length%2==1){
            currentComparisons++;
        }
        Random random = new Random();
        int id = random.nextInt(9999) + 100;
        int rand = random.nextInt((9999 - 100) + 1) + 100;
        startNode(1, rand, id);
        long start = System.currentTimeMillis();
        long end = start + 2 * 1000; // 0.2 second
        while (System.currentTimeMillis() < end) {

        }
        int i=0;
        while(currentComparisons>1){
            if(i==file_content.length){
                startNode(2, file_content[i], file_content[i], id);
            }else{
                startNode(2, file_content[i], file_content[i+1], id);
            }
            i+=2;
        }
        
    }

    private void startNode(int type, String file1, String file2, int id) {
        Node n = new Node(type, rand, id, config_path);
        Thread t = new Thread(n);
        t.start();
    }

    public static void main(String... args) {
        String directoryOfFiles = args[0];
        String config_path = args[1];
        /**
         * To run the program manually without pas.sing command line arguments
         * int no_of_node = 4; String config_path = "configuration.txt";
         */
        Start start = new Start(directoryOfFiles, config_path);
        Thread t = new Thread(start);
        t.start();
    }

    /**
     * Read all the contents of each file 
     * Returns String array containing the content of each file
     */
    private String[] readAllFileContents(String directory){
        
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        
        String[] file_contents = new String[listOfFiles.length]

        for (int i = 0; i < listOfFiles.length; i++) {
            String file_path = directory + "/" + listOfFiles[i].getName();
                    try (FileReader fileReader = new FileReader(file_path)) {
                        BufferedReader fileBufferReader = new BufferedReader(fileReader);
                        file_contents[i] = fileBufferReader.lines()
                            .collect(Collectors.joining(System.lineSeparator()));
                    } catch (IOException e) {
                        // exception handling
                        file_contents[i] = ""
                    }
        }
    }

}
