import java.io.FileWriter;
import java.io.IOException;

// Defining the interface that our output option must have.
public interface Output {
    void println(String s);
    void print(String s);
    void close();
}
// This makes the classes sub-types of the interface and hence we can use it for both of them.

// To print the results to a file. We may additionally choose to encrypt this for security reasons.
class FileOP implements Output {    
    
    private FileOP() {
        try { fw = new FileWriter("Output.txt"); } catch (Exception e) { e.printStackTrace(); }
    }
    private static FileOP fo = new FileOP();
    public static FileOP getInstance(){
        return fo;
    }

    FileWriter fw;
    public void print(String s) {
        try { fw.write(s); } catch (IOException e) { e.printStackTrace(); }
    }
    public void println(String s) {
        try { fw.write(s + "\n"); } catch (IOException e) { e.printStackTrace(); }
    }
    public void close(){
        try { fw.close(); } catch (IOException e) { e.printStackTrace(); }
    }
}

// To display the results on screen at runtime.
class SysOP implements Output{
    
    private SysOP() {}
    private static SysOP so = new SysOP();
    public static SysOP getInstance(){
        return so;
    }
    
    public void print(String s) {
        System.out.print(s);
    }
    public void println(String s) {
        System.out.println(s);
    }
    public void close(){
    }
} 
