package aufgabe1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileSystem {
	
	private static Path path = Paths.get("TelefonEntries.json");
	
	public static List<TelefonEntry> readEntriesFromFile() {
		List<TelefonEntry> entries = new ArrayList<>();
		try (InputStream is = Files.newInputStream(path)) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootArray = mapper.readTree(is);
			for(JsonNode root : rootArray) {
				String firstName = root.path("firstName").asText();
				String lastName = root.path("lastName").asText();
				String number = root.path("number").asText();
				entries.add(new TelefonEntry(lastName, firstName, number));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}
	
	public static void writeFile(List<TelefonEntry> entries) {
		JsonFactory factory = new JsonFactory ();
		try (OutputStream os = Files.newOutputStream(path);
				JsonGenerator jg = factory.createGenerator(os)) {
			jg.writeStartArray();
			for(TelefonEntry entry : entries) {
				jg.writeStartObject();
				jg.writeStringField("lastName", entry.getLastName());
				jg.writeStringField("firstName", entry.getFirstName());
				jg.writeStringField("number", entry.getNumber());
				jg.writeEndObject();
			}
			jg.writeEndArray();
			jg.close();
		} catch ( IOException e) {
			e. printStackTrace ();
		}
	}
}