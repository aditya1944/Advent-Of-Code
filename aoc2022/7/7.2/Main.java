import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    private static int commandsIndex = 1;

    private static Directory rootDirectory = new Directory("root");

    public static void main(String[] args) throws IOException{
        var fileContents = Files.readString(Path.of("../input"));
        var commands = fileContents.split("\n");
        recurse(commands, rootDirectory);
        var rootDirectorySize = rootDirectory.getDirectorySize();
        // calculate minimum size of directory which is to be calculated
        var unusedSpace = 70000000 - rootDirectorySize;
        var unusedSpaceRequired = 30000000;
        var spaceTobeFreed = unusedSpaceRequired - unusedSpace;
        System.out.println(dfs(spaceTobeFreed));

    }

    private static long dfs(long minimumSizeRequired) {
        var maxSizeBelowMinimumSizeRequired = 30000000L;
        var queue = new ArrayDeque<Directory>();
        queue.addFirst(rootDirectory);
        while (!queue.isEmpty()) {
            var currDirectory = queue.removeLast();
            var currDirectorySize = currDirectory.getDirectorySize();
            if (currDirectorySize >= minimumSizeRequired) {
                maxSizeBelowMinimumSizeRequired = Math.min(currDirectorySize, maxSizeBelowMinimumSizeRequired);
            }
            for (var childDirectoriesSet: currDirectory.directories.entrySet()) {
                queue.add(childDirectoriesSet.getValue());
            }
        }
        return maxSizeBelowMinimumSizeRequired;
    }

    private static void recurse(String[] commands, Directory directory) {
        if (commandsIndex >= commands.length) {
            return;
        }
        while (commandsIndex < commands.length) {
            var currCommand = commands[commandsIndex];
            if (currCommand.charAt(2) == 'l') { // ls command outputs -> starting with only 'd' or a digit
                var index = commandsIndex+1;
                while (index < commands.length && (commands[index].charAt(0) == 'd' || Character.isDigit(commands[index].charAt(0)))) {
                    var command = commands[index];
                    if (command.charAt(0) == 'd') {
                        // this is sub directory
                        var subDirectoryName = command.substring(4);
                        if (!directory.directories.containsKey(subDirectoryName)) {
                            directory.directories.put(subDirectoryName, new Directory(subDirectoryName));
                        }
                    } else {
                        // this is a file
                        var fileDetails = command.split(" ");
                        var file = new File(fileDetails[1], Long.parseLong(fileDetails[0]));
                        directory.addFile(file);
                    }
                    index+=1;
                }
                // at this point index points to $ or commands ended
                commandsIndex = index;
            } else {
                // this command is cd
                // 3 options
                var operator = currCommand.substring(5).trim();
                commandsIndex += 1;
        
                if ("..".equals(operator)) {
                    return;
                }
        
                if ("/".equals(operator)) {
                    recurse(commands, rootDirectory);
                    return;
                } else {
                    // operator is directory name
                    if (directory.directories.containsKey(operator)) {
                        recurse(commands, directory.directories.get(operator));
                    } else {
                        directory.directories.put(operator, new Directory(operator));
                        recurse(commands, directory.directories.get(operator));
                    }
                }
            }
        }
    }
}

record File(String name, long size) {}
class Directory {
    // directories and files
    String name;
    private List<File> files;
    Map<String, Directory> directories;
    
    Directory(String name) {
        this.name = name;
        this.files = new ArrayList<File>();
        this.directories = new HashMap<>();
    }

    void addFile(File file){
        this.files.add(file);
    }

    public long getDirectorySize() {
        long size = 0;
        for (var files: this.files) {
            size += files.size();
        }
        // calculate sub directory size also
        for (var subDirectories: this.directories.entrySet()) {
            size += subDirectories.getValue().getDirectorySize();
        }
        return size;
    }
}