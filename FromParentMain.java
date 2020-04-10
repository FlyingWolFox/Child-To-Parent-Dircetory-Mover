import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FromParentMain {
    public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("	usage: Child_To_Parent_Folder(parent_mode) <dir> ...");
			System.exit(0);
		}

		for (String path : args) {
			try {
				DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path));
				for (Path file : stream) {
					if (file.toFile().exists()) {

						// for directories
						if (file.toFile().isDirectory()) {
							try {
								new ChildToParent(file);
							} catch (IOException e) {
								System.out.println("Error opening " + path);
								e.printStackTrace();
							}
						}

						// for files
						if (file.toFile().isFile()) {
							try {
								Files.move(file, Paths.get(path).resolve(file.getFileName()));
							} catch (IOException e) {
								System.out.println(file.toString() + " couldn't be moved");
								e.printStackTrace();
							}
						}
					} else
						System.out.println(path + " doesn't exists!");
				}

			} catch (IOException e) {
				System.out.println("Failed to get files and folders from " + path);
				e.printStackTrace();
			}
		}

		System.out.println("Completed!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Unable to sleep thread");
			e.printStackTrace();
		}
	}
}