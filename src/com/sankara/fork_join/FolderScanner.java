package com.sankara.fork_join;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/* Our project here is to create a scanner for our files.
 * That scanner will be able to search specific types of files
 * depending on the filter we give it. 
 * We will try two methods. In the first, the same thread will sequentially
 * search each directory encountered in the folder we chose to scan.
 * The second will create a new thread for each directory encountered and then
 * all these threads will join themselves to give us the ultimate result.
 * This second technique is called the Fork/Join Pattern.
 * To make use of such a pattern we must make our class inherit from RecursiveTask<V>.
 * Since we want the whole process to return a Long, our class extends RecursiveTask<Long>.
 * Let's begin.
 */
public class FolderScanner extends RecursiveTask<Long> {
	
	private Path path = null;      // We will use the Path object to specify the folder we wish to scan.
	private String filter = "*";   // The filter will narrow our search. Example : "*.doc"
	private long numberOfFiles = 0;// This variable will receive the number of files corresponding to our search.
	
	
	public FolderScanner() { }
	
	// CONSTRUCTOR
	public FolderScanner(Path path, String filter) {
		super();
		this.path = path;
		this.filter = filter;
	}
	
	
	// SEQUENTIALSCAN
	public long sequentialScan() throws ScanException, IOException {
		
		// We set up our ScanException.
		if(path == null || path.equals("")) {
			throw new ScanException("The path entered is not valid.");
		}
		
		// This message will show up for each directory we scan.
		System.out.println("Scanning of the folder " + path 
								+ " looking for files of the following type : " + filter);
		
		/* First we must tell our program to launch the seqentialScan 
		 * for each directory it meets. 
		 * Second we must tell our program what sequentialScan is actually about.
		 */
		
		try { // FIRST
			
			// We set up our DirectoryStream.
			DirectoryStream<Path> listing = Files.newDirectoryStream(path);
			
			/* We say the following : Into our DirectoryStream should you encounter
			 * a directory, I want you to create a new FolderScanner and launch 
			 * the sequentialScan method into that directory.
			 */
			for(Path path0 : listing) {
				if(Files.isDirectory(path0.toAbsolutePath())) {
					FolderScanner fs = new FolderScanner(path0.toAbsolutePath(), filter);
					numberOfFiles += fs.sequentialScan();
				}
			}
		} 
		catch (IOException e) { e.printStackTrace(); }
		
		// SECOND
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, filter)) {
			
			/* Notice that our DirectoryStream is given our filter in parameter.
			 * This way we only get the files we are looking for.
			 * All is left to do is increment our numberOfFiles and retrieve it
			 * with a return.
			 */
			for(Path path0 : listing) {
				numberOfFiles++;
			}
		}
		catch (IOException e) { e.printStackTrace(); }
		return numberOfFiles;
	}
	
	
	// PARALLELSCAN
	public long parallelScan() throws ScanException {
		
		// We will use that list to merge the results of all the threads.
		List<FolderScanner> listOfFolscan = new ArrayList<>();
		
		// We set up our ScanException.
		if(path == null || path.equals("")) {
			throw new ScanException("The path entered is not valid");
		}
		
		// This message will show up for each directory we scan.
		System.out.println("Scanning of the folder " + path 
				+ " looking for files of the following type : " + filter);
		
		// We set up our DirectoryStream
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)) {
			
			/* We say the following : Into our DirectoryStream should you encounter
			 * a directory, I want you to create a new FolderScanner and launch 
			 * the parallelScan method into that directory. But to do it in another Thread
			 * instead of folscan.parallelScan(), we say folscan.fork().
			 */
			for(Path path0 : listing) {
				if(Files.isDirectory(path0.toAbsolutePath())) {
					FolderScanner folscan = new FolderScanner(path0.toAbsolutePath(), filter);
					
					// We add the new FolderScan to the list we will use to merge all the results later on.
					listOfFolscan.add(folscan);
					folscan.fork();
				}
			}
		}
		catch(IOException e) { e.printStackTrace(); }
		
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, filter)) {
			
			/* Notice that our DirectoryStream is given our filter in parameter.
			 * This way we only get the files we are looking for.
			 * We then increment our numberOfFiles. 
			 */
			for(Path path0 : listing) {
				numberOfFiles++;
			}
		}
		catch(IOException e) { e.printStackTrace(); }
		
		// We iterate our list of FolderScanner and merge our results in numberOfFiles
		for(FolderScanner folderScanner : listOfFolscan) {
			numberOfFiles += folderScanner.join();
		}
		return numberOfFiles;
	}
	
	
	/* When we extends RecursiveTask<Long> we have to override the compute() method.
	 * This method tells the program what to do. In our case, to call the parallelScan() method.
	 */
	@Override
	protected Long compute() {
		long result = 0;
		
		try {
			result = this.parallelScan();	
		} 
		catch (ScanException e) { e.printStackTrace(); }
		return result;
	}
	
	// We make sure we can access with ease outside the class.
	public long getResult() {
		return this.numberOfFiles;
	}
}
