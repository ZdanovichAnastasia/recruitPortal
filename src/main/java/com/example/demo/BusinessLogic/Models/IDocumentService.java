package com.example.demo.BusinessLogic.Models;


import java.io.ByteArrayOutputStream;

public interface IDocumentService {
    ByteArrayOutputStream generate(Resume resume) throws Exception;
}
