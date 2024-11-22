package Composite;

import java.util.ArrayList;
import java.util.List;

abstract class FileSystemItem {
    protected String name;

    public FileSystemItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getSize();
    public abstract void printStructure(String indent);
}

class File extends FileSystemItem {
    private int size;

    public File (String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "File: " + name + " (Size: " + size + "KB)");
    }
}

class Directory extends FileSystemItem {
    private List<FileSystemItem> children = new ArrayList<>();

    public Directory (String name) {
        super(name);
    }

    public void addItem(FileSystemItem item) {
        children.add(item);
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemItem item : children) {
            totalSize += item.getSize();
        }
        return totalSize;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "Directory: " + name);
        for (FileSystemItem item : children) {
            item.printStructure(indent + " ");
        }
    }
}

public class FileSystem {
    public static void main(String[] args) {
        File f1 = new File("document.txt", 20);
        File f2 = new File("image.png", 30);
        File f3 = new File("video.mp4", 40);

        Directory root = new Directory("Root");
        Directory media = new Directory("Media");

        root.addItem(f1);
        media.addItem(f2);
        media.addItem(f3);
        root.addItem(media);

        root.printStructure("");
    }
}