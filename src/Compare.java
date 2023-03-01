import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Compare {
	public static void main(String[] args) throws IOException {
		File file = new File("resources/loesungen");
		if (file.exists() && file.isDirectory()) {
			for (File i : Objects.requireNonNull(file.listFiles())) {
				String aufgabe = Files.readString(Path.of(i.getPath()));
				String loesung = Files.readString(Path.of("resources/loesungen/" + i.getName()));
				aufgabe = removeStuffWeDontCareAbout(aufgabe);
				loesung = removeStuffWeDontCareAbout(loesung);
				System.out.printf("%s stimmt %s%n", i.getName().split("\\.")[0], aufgabe.equals(loesung) ? "" : "nicht");
			}
		}
	}

	private static String removeStuffWeDontCareAbout(String s) {
		return s.chars().filter(x -> switch ((char) x) {
			case ' ', '\n', '\t' -> false;
			default -> true;
		}).toString();
	}
}
