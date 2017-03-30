
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

import com.sankara.fork_join.FolderScanner;
import com.sankara.fork_join.ScanException;

public class Main {
	
	public static void main(String[] args) {
		
		Path theWay = Paths.get("C:/Users/User/Pictures");
		String filter = "*.jpg";
		FolderScanner fs = new FolderScanner(theWay, filter);
		
		try {
			Long start = System.currentTimeMillis();
			Long result = fs.sequentialScan();
			Long end = System.currentTimeMillis();
			System.out.println("\nThere are " + result + " files with the extension " + filter + ".");
			System.out.println("Execution time is : " + (end - start) + " ms.");
		}
		catch(IOException e) { e.printStackTrace();}
		catch(ScanException e) { e.printStackTrace();}	
		
		Path theWay2 = Paths.get("C:/Users/User/Pictures");
		FolderScanner fs2 = new FolderScanner(theWay2, filter);
		int processors = Runtime.getRuntime().availableProcessors();
		ForkJoinPool pool = new ForkJoinPool(processors);
		
		Long start2 = System.currentTimeMillis();
		
		System.out.println("\n\n*************************************************************\n\n");
		pool.invoke(fs2);
		
		Long end2   = System.currentTimeMillis();
		
		System.out.println("\nThere are " + fs.getResult() + " files with the extension " + filter + ".");
		System.out.println("Execution time is : " + (end2 - start2) + " ms.");
	}
}

