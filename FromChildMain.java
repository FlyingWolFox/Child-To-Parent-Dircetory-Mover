import java.io.IOException;
import java.nio.file.Paths;

public class FromChildMain {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("	usage: Child_To_Parent_Folder <dir> ...");
			System.exit(0);
		}

		for (String path : args) {
			if (Paths.get(path).toFile().exists()) {
				try {
					new ChildToParent(Paths.get(path));
				} catch (IOException e) {
					System.out.println("Error opening " + path);
					e.printStackTrace();
				}
			} else
				System.out.println(path + " doesn't exists!");
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