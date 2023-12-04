import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

public class CSVFileReader implements Closeable, Iterable<ArrayList<String>> {
    Path path;
    BufferedReader br;
    String currentLine;

    public CSVFileReader(String path) throws FileNotFoundException{
        this.path = Path.of(path);
        br = new BufferedReader(new FileReader(path));
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        if(br != null){
            br.close();
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ArrayList<String>> iterator() {
        return new Iterator<ArrayList<String>>() {
            @Override
            public boolean hasNext() {
                return currentLine != null;
            }

            @Override
            public ArrayList<String> next() {
                try{
                    currentLine = br.readLine();
                    if(this.hasNext()){
                        return new CSVReader().split(currentLine);
                    }
                    return null;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
