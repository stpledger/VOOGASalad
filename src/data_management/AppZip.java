package data_management;

import java.io.*;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class AppZip
{
    List<String> fileList;
    List<String> addList;
    Set<File> ignoreSet;
    private String output = "C:/Users/Conrad/Desktop/Hello/Jar.jar";
    private String sourceFolder;
    private String projectFolder;

    AppZip(){
        fileList = new ArrayList<>();
        addList = new ArrayList<>();
        ignoreSet = new HashSet<>();
    }

    public void zip(File source, List<File> add, List<File> ignore, List<File> modules, String projectFolder){
        sourceFolder = source.getAbsolutePath().replace("\\","/");
        System.out.println(sourceFolder);
        System.out.println(projectFolder);
        ignoreSet = new HashSet<>((ignore));
        this.projectFolder = projectFolder.replace("\\","/");
        for(File file : add)
            addFile(file);
//        for(File module : modules)
//            addFile(module);
        generateFileList(source);
        zipIt(output);

    }

    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    public void zipIt(String zipFile){

        byte[] buffer = new byte[1024];

        try{
            System.out.println(zipFile);
            zipFile.replace("\\","/");
            System.out.println(zipFile);
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
            manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, "xstream-1.4.10.jar");
            manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, "GamePlayer.Main");
            FileOutputStream fos = new FileOutputStream(zipFile);
            JarOutputStream jos = new JarOutputStream(fos,manifest);

            System.out.println("Output to Zip : " + zipFile);

            for(String file : this.fileList){

                System.out.println("File Added : " + file.replace("\\","/"));
                JarEntry ze= new JarEntry(file.replace("\\","/"));
                jos.putNextEntry(ze);
                FileInputStream in = new FileInputStream(sourceFolder + "/" + file);
                int len;
                while ((len = in.read(buffer)) > 0) {
                    jos.write(buffer, 0, len);
                }
                in.close();
            }

            for(String file : this.addList){

                System.out.println("File Added : " + file.replace("\\","/"));
                JarEntry ze= new JarEntry(file.replace("\\","/"));
                jos.putNextEntry(ze);
                System.out.println(projectFolder + '/' + file);
                FileInputStream in = new FileInputStream(projectFolder + "/" + file);
                int len;
                while ((len = in.read(buffer)) > 0) {
                    jos.write(buffer, 0, len);
                }
                in.close();
            }

            jos.closeEntry();
            //remember close it
            jos.close();

            System.out.println("Done");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Traverse a directory and get all files,
     * and add the file into fileList
     * @param node file or directory
     */
    public void generateFileList(File node){
        if(node.isFile() && !ignoreSet.contains(node)){
            System.out.println(generateZipEntry(node.getAbsolutePath(),sourceFolder));
            fileList.add(generateZipEntry(node.getAbsolutePath(),sourceFolder));
        }
        if(node.isDirectory()&& !ignoreSet.contains(node)){
            String[] subNote = node.list();
            for(String filename : subNote){
                generateFileList(new File(node, filename));
            }
        }

    }

    public void addFile(File node){
        if(node.isFile() && !ignoreSet.contains(node)){
            System.out.println(generateZipEntry(node.getAbsolutePath(),projectFolder));
            addList.add(generateZipEntry(node.getAbsolutePath(),projectFolder));
        }        System.out.println(generateZipEntry(node.getAbsoluteFile().toString(),projectFolder));
        if(node.isDirectory()){
            String[] subNote = node.list();
            for(String filename : subNote){
                addFile(new File(node, filename));
            }
        }

    }

    /**
     * Format the file path for zip
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file, String source){
        return file.substring(source.length()+1, file.length());
    }


    //destroy a directory TO BE KEPT PRIVATE!!!!!!!!!
    //DO NOT FUCK WITH THIS METHOD IT WILL FUCK YOUR SHIT UP
    //SERIOUSLY IT WILL DELETE YOUR COMPUTER...
    private static void deleteDir(File dir) {
        if (!dir.exists())
            return;
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (File file : children) {
                deleteDir(file);
            }
        }
        dir.delete();
    }
}