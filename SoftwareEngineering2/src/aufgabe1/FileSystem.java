package aufgabe1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileSystem implements Path {
	
	/*
	public static void readFile() {
		try (InputStream is = Files.newInputStream (path)) {
			ObjectMapper mapper = new ObjectMapper ();
			JsonNode root = mapper . readTree (is);
			// Verwenden Sie den ObjectMapper hier , um
			// die Daten aus der Datei auszulesen .
		} catch ( IOException e) {
			e. printStackTrace ();
		}
	}
	
	
	public static void writeFile() {
		try (OutputStream os = Files.newOutputStream (path);
				JsonGenerator jg = factory.createGenerator (os)) {
				// Verwenden Sie jg um fuer jeden Eintrag im Telefonbuch
				// entsprechende Objekte im JSON zu erzeugen
		} catch ( IOException e) {
			e. printStackTrace ();
		}
	}
	*/
	
	@Override
	public int compareTo(Path arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean endsWith(Path arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Path getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.nio.file.FileSystem getFileSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path getName(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNameCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Path getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAbsolute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Path normalize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WatchKey register(WatchService arg0, Kind<?>[] arg1, Modifier... arg2) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path relativize(Path arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path resolve(Path arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean startsWith(Path arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Path subpath(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path toAbsolutePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path toRealPath(LinkOption... arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI toUri() {
		// TODO Auto-generated method stub
		return null;
	}

}
