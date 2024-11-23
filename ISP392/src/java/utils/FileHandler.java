/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utils;

/**
 *
 * @author DELL
 */

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileHandler {

    private String uploadDir;

    public FileHandler(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String saveFile(Part filePart) throws IOException {
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir();
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String filePath = uploadDir + File.separator + fileName;

        filePart.write(filePath);
        return fileName;
    }

    public boolean deleteFile(String fileName) {
        File file = new File(uploadDir + File.separator + fileName);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public File getFile(String fileName) {
        return new File(uploadDir + File.separator + fileName);
    }
}
