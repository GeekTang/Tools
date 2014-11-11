package com.fd.watchdog;

import java.io.File;
import java.nio.file.WatchEvent;

public interface FileListener {
	void onChange(File file, WatchEvent.Kind<?> operationType);
}
