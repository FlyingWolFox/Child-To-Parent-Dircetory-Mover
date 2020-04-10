public class Main {

    public static void showUsage() {
        System.out.println(" usage: ChildToParentDirectoryMover [mode] <dir> [mode] <dir> <dir> ...");
        System.out.println(" mode   Specifies what mode to work over the directory passed by argument:");
        System.out.println("   -ch  the directory is treated as child folther.");
        System.out.println("        Its contents will be moved to parent");
        System.out.println("   -p   the directory is treated as parent folder.");
        System.out.println("        The contents of all child directories will be moved to parent");
        System.out.println("");
        System.out.println(" Every directory will be treated as the mode before it. IE:");
        System.out.println("   ChildToParentDirectoryMover [modeA] <dir1> [modeB] <dir2> <dir3>");
        System.out.println(" Will be interpreted the same as:");
        System.out.println("   ChildToParentDirectoryMover [modeA] <dir1> [modeB] <dir2> [modeB] <dir3>");
        System.out.println(" If no mode is selected, nothing will happen");
        System.exit(0);
    }

    public static void main(String[] args) {
        if(args.length < 2)
            showUsage();

        if (!args[0].equals("-ch") && !args[0].equals("-p"))
            showUsage();

        String mode = args[0];
        String argArray[] = new String[1];
        for(String arg : args) {
            if (arg.equals("-ch") || arg.equals("-p")) {
                mode = arg;
                continue;
            }               

            argArray[0] = arg;

            if (mode.equals("-ch"))
                FromChildMain.main(argArray);

            if (mode.equals("-p"))
                FromParentMain.main(argArray);
        }
    }
}