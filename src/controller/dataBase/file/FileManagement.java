package controller.dataBase.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controller.dataBase.repository.RepositoryContent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagement<t> {
    private String fileName;
    private ObjectMapper objectMapper;
    private Path cwd;

    public FileManagement(String fileName) {
        this.fileName = fileName;
        cwd = Paths.get("").toAbsolutePath();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void dump(RepositoryContent<t> repositoryContent) {
        //verifyFile();
        //TODO: arrumar o local onde o arquivo vai ficar com base no config
        try {
            String path = cwd + File.separator + fileName + ".json";
            System.out.println(path);
            objectMapper.writeValue(new File(path), repositoryContent);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + fileName, e);
        }
    }
    public void verifyFile(){
        //TODO: fazer ele realmente verificar o arquivo
        Path cwd = Paths.get("").toAbsolutePath();
        File directory = cwd.toFile();
        File[] files = directory.listFiles();
    }

    public static void main(String[] args) {
        //usado para verificar o funcionamento do FileManagement
        FileManagement fm = new FileManagement("test");
        fm.verifyFile();
    }
}
