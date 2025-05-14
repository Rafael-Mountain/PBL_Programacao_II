package controller.dataBase.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controller.dataBase.repository.RepositoryContent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagement<t> {
    private String fileName;
    private ObjectMapper objectMapper;
    private String path;
    private Class<t> clazz;

    public FileManagement(String fileName, Class<t> clazz) {
        this.fileName = fileName;
        this.clazz = clazz;
        Path cwd = Paths.get("").toAbsolutePath();
        this.path = cwd + File.separator + fileName + ".json";
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public RepositoryContent<t> load() throws IOException {
        verifyFile();
        return objectMapper.readValue(
                new File(path),
                objectMapper.getTypeFactory()
                        .constructParametricType(RepositoryContent.class, clazz)
        );
    }

    public void dump(RepositoryContent<t> repositoryContent) {
        verifyFile();
        try {
            System.out.println(this.path);
            objectMapper.writeValue(new File(this.path), repositoryContent); // Passa todos os atributos da classes e outros objetos relacionados
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + fileName, e);
        }
    }

    public void verifyFile() {
        File file = new File(this.path);
        if (!file.exists()) {
            String json = "{\"listItems\":[],\"idCounter\":0}";
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json);
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo padrão: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        //usado para verificar o funcionamento do FileManagement
        //FileManagement fm = new FileManagement("test");
        //fm.verifyFile();
    }
}
