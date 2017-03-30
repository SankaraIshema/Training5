package com.sankara.fork_join;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderScanner extends RecursiveTask<Long> {
	
	private Path path = null;
	private String filter = "*";
	private long numberOfFiles = 0;
	
	
	public FolderScanner() { }

	public FolderScanner(Path path, String filter) {
		super();
		this.path = path;
		this.filter = filter;
	}
	
	
	
	public long sequentialScan() throws ScanException, IOException {
		if(path == null || path.equals("")) {
			throw new ScanException("The path entered is not valid.");
		}
		
		System.out.println("Scanning of the folder " + path 
								+ " looking for files of the following type : " + filter);
		
		try {
			DirectoryStream<Path> listing = Files.newDirectoryStream(path);
			for(Path path0 : listing) {
				if(Files.isDirectory(path0.toAbsolutePath())) {
					FolderScanner fs = new FolderScanner(path0.toAbsolutePath(), filter);
					numberOfFiles += fs.sequentialScan();
				}
			}
		} 
		catch (IOException e) { e.printStackTrace(); }
		
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, filter)) {
			for(Path path0 : listing) {
				numberOfFiles++;
			}
		}
		catch (IOException e) { e.printStackTrace(); }
		return numberOfFiles;
	}
	
	
	
	public long parallelScan() throws ScanException {
		List<FolderScanner> listOfFolscan = new ArrayList<>();
		
		if(path == null || path.equals("")) {
			throw new ScanException("The path entered is not valid");
		}
		
		System.out.println("Scanning of the folder " + path 
				+ " looking for files of the following type : " + filter);
		
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)) {
			for(Path path0 : listing) {
				if(Files.isDirectory(path0.toAbsolutePath())) {
					FolderScanner folscan = new FolderScanner(path0.toAbsolutePath(), filter);
					
					listOfFolscan.add(folscan);
					folscan.fork();
				}
			}
		}
		catch(IOException e) { e.printStackTrace(); }
		
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, filter)) {
			for(Path path0 : listing) {
				numberOfFiles++;
			}
		}
		catch(IOException e) { e.printStackTrace(); }
		
		for(FolderScanner folderScanner : listOfFolscan) {
			numberOfFiles += folderScanner.join();
		}
		return numberOfFiles;
	}
	
	

	@Override
	protected Long compute() {
		long result = 0;
		
		try {
			result = this.parallelScan();
			
		} catch (ScanException e) { e.printStackTrace(); }
		
		return result;
	}
	
	public long getResult() {
		return this.numberOfFiles;
	}
}
