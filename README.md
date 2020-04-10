# Child To Parent Directory Mover

This (kinda) script will move all contents of child directories (subdirectories) to parent directories easily!

## How to use

`ChildToParentDirectoryMover [mode] <dir> [mode] <dir> <dir> ...`
mode   Specifies what mode to work over the directory passed by argument:
  -ch  the directory is treated as child folther.
       Its contents will be moved to parent
  -p   the directory is treated as parent folder.
       The contents of all child in it directories will be moved to parent

Every directory will be treated as the mode before it. IE:
  `ChildToParentDirectoryMover [modeA] <dir1> [modeB] <dir2> <dir3>`
Will be interpreted the same as:
  `ChildToParentDirectoryMover [modeA] <dir1> [modeB] <dir2> [modeB] <dir3>`
If no mode is selected, nothing will happen

### The shortcuts

The shortcuts in the releases, parent_mode and child_mode, will treat the directories without needing give an option. The mode they use it's on the name. Better used with drag and droping.

## Warning abot compatibility

This script uses `java.nio` library to handle the files. They aren't available in all Java versions. Java 8 is granted to be working.
