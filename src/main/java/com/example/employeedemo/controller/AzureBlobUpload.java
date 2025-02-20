package com.example.employeedemo.controller;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import org.springframework.stereotype.Component;

import java.io.*;

public class AzureBlobUpload {
    // Replace with your Azure Storage connection string
    private static final String CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=javatry;AccountKey=RwMo+s1m3PDJAE2IO5x++Gt2G++iJf2KMmMMRefGlz8c/uMDsJM4ERjdH9rbjzMX9Ymg/4huRDFg+AStAo4zYw==;EndpointSuffix=core.windows.net";
    private static final String CONTAINER_NAME = "images"; // Your Blob container name

    public static void main(String[] args) {
        // Replace with your local file path
        String filePath = "C:/Users/vaishnaviravindra_pa/Downloads/anna-pelzer-IGfIGP5ONV0-unsplash.jpg";
        String blobName = "sample.jpg"; // Name of the file in Azure Blob Storage

        try {
            uploadToBlobStorage(filePath, blobName);
        } catch (Exception e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
    }

    public static void uploadToBlobStorage(String filePath, String blobName) throws IOException {
        // Create a BlobServiceClient using the connection string
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(CONNECTION_STRING)
                .buildClient();

        // Get the BlobContainerClient
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(CONTAINER_NAME);

        // Ensure the container exists
        if (!containerClient.exists()) {
            containerClient.create();
            System.out.println("Created Blob Container: " + CONTAINER_NAME);
        }

        // Get the BlobClient
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        // Upload the file
        File file = new File(filePath);
        try (InputStream fileStream = new FileInputStream(file)) {
            blobClient.upload(fileStream, file.length(), true);
            System.out.println("File uploaded successfully: " + blobClient.getBlobUrl());
        }
    }
}

//    @PostMapping("/file")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        // Check if the file is empty
//        if (file.isEmpty()) {
//            return "No file selected to upload!";
//        }
//
//        try {
//            // Define the file path to save the uploaded file
//            String filePath = System.getProperty("C:\\Users\\vaishnaviravindra_pa\\Documents\\Movie.txt") + "/uploads/" + file.getOriginalFilename();
//            File dest = new File("C:\\Users\\vaishnaviravindra_pa\\Desktop\\DSA");
//
//            // Create the directory if it doesn't exist
//            dest.getParentFile().mkdirs();
//
//            // Transfer the file to the destination
//            file.transferTo(dest);
//
//            return "File uploaded successfully: " + filePath;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "File upload failed: " + e.getMessage();
//        }
//    }