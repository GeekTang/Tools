package com.fd.watchdog;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Path;
import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		try {
			WatchService watcher = FileSystems.getDefault().newWatchService();
			Path dir = (new File("test")).toPath();
			dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

			for (;;) {

				// wait for key to be signaled
				WatchKey key;
				try {
					key = watcher.take();
				} catch (InterruptedException x) {
					return;
				}

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					// This key is registered only
					// for ENTRY_CREATE events,
					// but an OVERFLOW event can
					// occur regardless if events
					// are lost or discarded.
					if (kind == OVERFLOW) {
						continue;
					}

					// The filename is the
					// context of the event.
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path filename = ev.context();

					// Verify that the new
					// file is a text file.

					// Resolve the filename against the directory.
					// If the filename is "test" and the directory is "foo",
					// the resolved name is "test/foo".
					Path child = dir.resolve(filename);
					System.out.println(child.toFile().getAbsolutePath()
							+ "has " + kind);
					continue;

				}

				// Reset the key -- this step is critical if you want to
				// receive further watch events. If the key is no longer valid,
				// the directory is inaccessible so exit the loop.
				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
