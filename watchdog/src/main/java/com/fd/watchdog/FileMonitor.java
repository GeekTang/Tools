package com.fd.watchdog;

import java.io.File;
import java.nio.file.Path;

public interface FileMonitor {

	void add(String filename);
	
	void add(Path path);
	
	void add(File file);
	
	void remove(String filename);
	
	void remove(File file);
	
	void remove(Path path);
	
	void stop();
	
	void start();
	
	void register(String fileName, FileListener listener);
	
	void register(File file, FileListener listener);
	
	void register(Path path, FileListener listener);
}
