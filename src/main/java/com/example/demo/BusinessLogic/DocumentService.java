package com.example.demo.BusinessLogic;

import com.example.demo.BusinessLogic.Models.Resume;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class DocumentService {
    public static ByteArrayOutputStream generate(Resume resume) throws Exception {
        URI wordTemplate = generateDocumentPath(resume.getSimpleName());
        WordprocessingMLPackage wordMLPackage = Docx4J.load(new File(wordTemplate));
        String xmlData = resume.serialize();
        Docx4J.bind(wordMLPackage, xmlData, Docx4J.FLAG_NONE);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Docx4J.save(wordMLPackage, stream, Docx4J.FLAG_NONE);

        return stream;
    }

    private static String docxExtension = "docx";
    private static String templateDirectory = "templates";

    private static URI generateDocumentPath(String className) throws URISyntaxException {
        String templatePath = new StringBuilder()
                .append(templateDirectory)
                .append("/")
                .append(className)
                .append(".")
                .append(docxExtension)
                .toString();
        return getResourcesPath(templatePath);
    }

    private static URI getResourcesPath(String path) throws URISyntaxException {
        return ClassLoader.getSystemResource(path).toURI();
    }


}
