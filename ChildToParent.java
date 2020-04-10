import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChildToParent {
    public ChildToParent(Path path) throws IOException {
		DirectoryStream<Path> stream = Files.newDirectoryStream(path);
		Path parent = path.getParent();
		boolean delete = true;
		for (Path file : stream) {
			System.out.println("Moving: " + file.getFileName());
			assert parent != null : path.getFileName() + "doesn't have a parent!";
			try {
				moveFolder(file.toFile(), parent.resolve(file.getFileName()).toFile());
			} catch (IOException e) {
				System.out.println(file.toString() + " couldn't be moved");
				delete = false;
				e.printStackTrace();
			}

		}
		stream.close();

		if (delete) {
			deleteDirectory(path);
		}
	}

	private static void deleteDirectory(Path path) throws IOException {
		DirectoryStream<Path> stream = Files.newDirectoryStream(path);
		for (Path file : stream) {
			if(file.toFile().isDirectory()) {
				deleteDirectory(file);
				Files.deleteIfExists(file);
			}
			if(file.toFile().isFile()) {
				Files.deleteIfExists(file);
			}
		}
		stream.close();
		Files.deleteIfExists(path);
    }
    
        /**
     * This function recursively copy all the sub folder and files from sourceFolder to destinationFolder
     * */
    private static void  moveFolder(File sourceFolder, File destinationFolder) throws IOException
    {
        //Check if sourceFolder is a directory or file
        //If sourceFolder is file; then move the file directly to new location
        if (sourceFolder.isDirectory()) 
        {
            //Verify if destinationFolder is already present; If not then create it
            if (!destinationFolder.exists()) 
            {
                destinationFolder.mkdir();
            }
             
            //Get all files from source directory
            String files[] = sourceFolder.list();
             
            //Iterate over all files and copy them to destinationFolder one by one
            for (String file : files) 
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                 
                //Recursive function call
                moveFolder(srcFile, destFile);
            }
        }
        else
        {
            //Move the file content from one place to another 
            Files.move(sourceFolder.toPath(), destinationFolder.toPath());
        }
    }

}