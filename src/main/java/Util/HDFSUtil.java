package Util;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;

/**
 * @author GCJL
 * @date 2021/5/6 13:44
 */
public class HDFSUtil {
    private static FileSystem fs;

    private static void openFS() {
        try {
            fs = Connected.getHDFS();//Get the HBase connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeFS() {
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Upload a local file to HDFS
     *
     * @param localFile  Local file path
     * @param remoteFile Destination path in HDFS
     * @throws IOException
     */
    public static void uploadFile(Path localFile, Path remoteFile) throws IOException {
        openFS();
        fs.copyFromLocalFile(localFile, remoteFile);
        closeFS();
    }

    /**
     * Download a file from HDFS
     * @param localFile
     * @param remoteFile
     * @throws IOException
     */
    public static void downloadFile(Path localFile, Path remoteFile) throws IOException {
        openFS();
        fs.copyToLocalFile(localFile, remoteFile);
        closeFS();
    }

    /**
     * Read file contents
     * @param targetPath
     * @throws IOException
     */
    public static void readFile(Path targetPath) throws IOException {
        openFS();
        FSDataInputStream open = fs.open(targetPath);
        IOUtils.copyBytes(open,System.out,1024);
        closeFS();
    }

    /**
     * Rename a file
     * @param absolutePath Absolute path of the file to rename
     * @param newName   New file name
     * @throws IOException
     */
    public static void renameFile(Path absolutePath,String newName) throws IOException {
        openFS();
        Path targetPath = new Path(absolutePath.getParent().toString() +"/"+ newName);
        fs.rename(absolutePath,targetPath);
        closeFS();
    }
    public static void moveFile(Path oldPath,Path newPath) throws IOException {
        openFS();
        FSDataInputStream fdis = fs.open(oldPath);
        FSDataOutputStream fdos = fs.create(newPath);

        IOUtils.copyBytes(fdis,fdos,1024);
        fs.delete(oldPath,true);
        closeFS();
    }
    /**
     * List and print files in the specified directory
     *
     * @param targetPath Target path
     * @throws IOException
     */
    public static void listFiles(Path targetPath) throws IOException {
        openFS();
        FileStatus[] fss = fs.listStatus(targetPath);

        if (targetPath.getName().equals("")) {
            System.out.println("/");
        } else {
            System.out.println(targetPath.getName());
        }

        for (FileStatus f : fss) {
            //Handle a directory
            if (f.isDirectory()) {
                for (int i = 0; i < f.getPath().depth(); i++)
                    System.out.print("-");
                listFiles(f.getPath());
            }
            //Handle a file
            if (f.isFile()) {
                for (int i = 0; i < f.getPath().depth(); i++)
                    System.out.print("-");
                System.out.print("F:" + f.getPath().getName() + "\n");
            }

        }
        closeFS();
    }

    /**
     * Create a file
     *
     * @param targetPath Target path
     * @param recursive  Whether to create parent directories recursively
     * @throws IOException
     */
    public static void mkFile(Path targetPath, boolean recursive) throws IOException {
        openFS();
        String src = targetPath.toString();
        String[] split = src.split("/");
        if (!split[0].equals("")) {
            System.out.println("Invalid path!");
        } else {
            StringBuilder sb = new StringBuilder();
            Path newsrc = null;
            for (int i = 1; i < split.length; i++) {
                sb.append("/").append(split[i]);
                newsrc = new Path(sb.toString());

                if (recursive) {  //Whether recursive creation is allowed
                    //Check whether the file or directory exists
                    if (!fs.exists(newsrc)) {
                        //Create a file for the final segment and directories for earlier segments
                        if (i == split.length - 1) {
                            fs.create(newsrc);
                        } else {
                            fs.mkdirs(newsrc);
                        }
                    }
                }
            }
        }
        closeFS();
    }

    /**
     * Create a directory
     *
     * @param targetPath Target path
     * @throws IOException
     */
    public static void mkDirectory(Path targetPath) throws IOException {
        openFS();
        fs.create(targetPath);
        closeFS();
    }

    /**
     * Delete a file or directory
     * @param targetPath Target path
     * @param recursive Whether to delete recursively
     * @throws IOException
     */
    public static void delete(Path targetPath, boolean recursive) throws IOException {
        openFS();
        //Check whether the target exists
        if (fs.exists(targetPath)) {
            fs.delete(targetPath, recursive);
        }else {
            System.out.println("The target path does not exist!");
        }
        closeFS();
    }


}
