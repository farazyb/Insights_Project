package com.sea.reporter.util;


import com.sea.reporter.model.dto.Different;
import com.sea.reporter.util.CalendarConversion;
import com.sea.reporter.util.comparison.ComparisonList;
import lombok.extern.log4j.Log4j2;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import java.io.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Log4j2
public class Tools {
    public enum EncryptStatus {
        ZIP_WITH_PASSWORD, ZIP, RAW, ZIP_AND_RAW, ZIP_WITH_PASSWORD_AND_RAW
    }

    private static String remoteHost = System.getenv("FTP_HOST") != null ? System.getenv("FTP_HOST") : "localhost";
    private static String sftpRemoteHost = System.getenv("SFTP_HOST") != null ? System.getenv("SFTP_HOST") : "localhost";
    private static String username = System.getenv("FTP_USERNAME") != null ? System.getenv("FTP_USERNAME") : "user";
    private static String sftpUsername = System.getenv("SFTP_USERNAME") != null ? System.getenv("SFTP_USERNAME") : "user";
    private static String sftpPassword = System.getenv("SFTP_PASSWORD") != null ? System.getenv("SFTP_PASSWORD") : "password";
    private static String password = System.getenv("FTP_PASSWORD") != null ? System.getenv("FTP_PASSWORD") : "password";
    private static int port = Integer.parseInt(System.getenv("FTP_PORT") != null ? System.getenv("FTP_PORT") : "21");

    public static File createFile(String path) throws IOException {
        File dir = new File(path);
        if (dir.isDirectory()) {
            throw new IOException("Path must be Contain Name Of The File");
        }
        if (new File(dir.getParent()).mkdirs()) {
            log.info("File created successfully ({})", path);
        } else {
            log.warn("Failed to create directory!({})", path);
        }
        int counter = 0;
        while (dir.exists()) {
            counter++;
            dir.renameTo(new File(dir.getPath() + ".old." + counter));
        }
        File destFile = new File(path);
        destFile.createNewFile();
        return destFile;
    }

    public static boolean createExcel(String[] headers, File file, List<?> objectList) throws IOException, IllegalAccessException {
        PrintWriter out = new PrintWriter(file, "UTF-8");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
            for (Object t2 : objectList) {
                Field[] fields = t2.getClass().getDeclaredFields();
                List<Object> values = new ArrayList<>();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(t2);
                    values.add("'" + value);
                }
                printer.printRecord(values);
            }
        }
        out.close();
        return true;
    }

    public static boolean createExcel(File file, ComparisonList<?> objectList) throws IllegalAccessException, IOException {
        Object t = objectList.get(0);
        Field[] fields = t.getClass().getDeclaredFields();
        List<String> headers = new ArrayList<>();
        for (Field field : fields) {
            headers.add(field.getName());
        }
        return createExcel(headers.toArray(new String[fields.length]), file, objectList);
    }

    public static boolean createTextFile(File file, List<?> collection) throws FileNotFoundException, UnsupportedEncodingException {
        OutputStream os = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
        for (Object o : collection) {
            pw.println(o.toString());
        }
        pw.close();
        return true;
    }

    public static boolean addTextToFile(File file, Object o) throws FileNotFoundException, UnsupportedEncodingException {
        try (FileWriter f = new FileWriter(file, true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);
             ) {
            p.append(o.toString() + System.lineSeparator());
        } catch (IOException i) {
            i.printStackTrace();
        }
        return true;
    }

    private static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));


        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

    private static boolean compress(File source, File dist) throws IOException {
        char[] password;
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setCompressionLevel(CompressionLevel.FASTER);
        zipParameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
        zipParameters.setWriteExtendedLocalFileHeader(true);
        password = generatePassword(10);
        ZipFile zipFile = new ZipFile(dist, password);
        zipFile.addFile(source, zipParameters);
        FileWriter myWriter = new FileWriter(dist + ".password.txt");
        myWriter.write(password);
        myWriter.close();
        return true;
    }

    public static boolean compress(File source, File dist, EncryptStatus encryptStatus) throws IOException {

        try {
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionLevel(CompressionLevel.FASTER);
            ZipFile zipFile;
            switch (encryptStatus) {
                case RAW:
                    return true;
                case ZIP_AND_RAW:
                    zipFile = new ZipFile(dist);
                    zipFile.addFile(source, zipParameters);
                    return true;
                case ZIP:
                    zipFile = new ZipFile(dist);
                    zipFile.addFolder(source, zipParameters);
                    FileUtils.deleteDirectory(source);
                    //source.delete();
                    return true;
                case ZIP_WITH_PASSWORD:
                    compress(source, dist);
                    source.delete();
                    return true;
                case ZIP_WITH_PASSWORD_AND_RAW:
                    compress(source, dist);
                    return true;
            }

        } catch (Exception ex) {
            log.error("Exception in compress File ->{}", ex);
            return false;
        }
        return false;
    }

    public static List<String> readFileString(File source) throws IOException {
        return Files.readAllLines(source.toPath());
    }

    public static boolean SFTPDownload(File source, File destination) {


        return false;
    }

    public static boolean SFTPUpload(File source, File destination) {
        try {
            FileSystemOptions opts = new FileSystemOptions();
            SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
            SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);
            FileSystemManager manager = VFS.getManager();
            FileObject local = manager.resolveFile(
                    source.getPath());
            FileObject remote = manager.resolveFile(
                    "sftp://" + sftpUsername + ":" + sftpPassword + "@" + sftpRemoteHost, opts);
            remote.copyFrom(local, Selectors.SELECT_SELF);
            local.close();
            remote.close();
        } catch (FileSystemException e) {
            log.error("Exception in Upload File to remote -> {}", e);
            return false;
        }
        return false;
    }

    public static boolean FTPUpload(File source, String destinationPath) {
        File firstLocalFile = source;
        String firstRemoteFile = destinationPath;
        boolean done = false;
        try {
            InputStream inputStream = new FileInputStream(firstLocalFile);
            log.info("Start uploading first file");
            FTPClient ftpClient = loginToFTPServer(remoteHost, username, password, port);
            done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
        } catch (FileNotFoundException ex) {
            log.error("Field to upload into FTP server -> {}", ex);
        } catch (IOException ex) {
            log.error("Field to upload into FTP server -> {}", ex);
        }
        return done;
    }

    public static boolean FTPDownload(File destinationPath, String remoteFilePath) {
        boolean result = false;
        FTPClient ftpClient = loginToFTPServer(remoteHost, username, password, port);
        try {
            log.info("Start Download File FROM FTP Server FILE({} , {})", remoteHost, remoteFilePath);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(destinationPath));
            ftpClient.retrieveFile(remoteFilePath, outputStream1);
            outputStream1.close();
        } catch (IOException ioEx) {
            log.error("Exception in Download File FromFTP Server -> {} ", ioEx);
        }
        return result;
    }

    public static List<String> listFTPDirectory(String path) {
        FTPClient ftpClient = loginToFTPServer(remoteHost, username, password, port);
        showServerReply(ftpClient);

        int replyCode = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            log.warn("Field To Connect To {}", remoteHost);
            return null;
        }

        FTPFile[] files1 = new FTPFile[0];
        try {
            files1 = ftpClient.listFiles(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return printFileDetails(files1);

    }

    private static FTPClient loginToFTPServer(String ip, String user, String pass, int port) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.COMPRESSED_TRANSFER_MODE);
        } catch (Exception ex) {
            log.error("Field to log in into FTP server -> {}", ex);
        }

        return ftpClient;
    }

    private static List<String> printFileDetails(FTPFile[] files) {
        List<String> export = new ArrayList<>();
        DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (FTPFile file : files) {
            String details = file.getName();
            if (file.isDirectory()) {
                details = "[" + details + "]";
            }
            details += "\t\t" + file.getSize();
            details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
            export.add(details);
        }
        return export;
    }

    private static void printNames(String files[]) {
        if (files != null && files.length > 0) {
            for (String aFile : files) {
                System.out.println(aFile);
            }
        }
    }

    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                log.info("SERVER: " + aReply);
            }
        }
    }

    public static void main(String[] args) throws IOException {
    }
}
