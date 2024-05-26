package com.tigrisblog.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tigrisblog.entity.Image;
import com.tigrisblog.repos.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

  private final AmazonS3 s3Client;
  private final ImageRepository imageRepository;

  @Value("${aws.s3.bucket}")
  private String bucketName;
  @Value("${aws.region}")
  private String bucketRegion;
  String fileName;

  @Transactional
  public String uploadFile(final MultipartFile multipartFile) {
    try {
      final File file = convertMultiPartFileToFile(multipartFile);
      fileName = uploadFileToS3Bucket(bucketName, file);
      file.deleteOnExit();
    } catch (final AmazonServiceException ex) {
      System.out.println("Error while uploading file = " + ex.getMessage());
    }
    return String.format("https://s3.%s.amazonaws.com/%s/%s", bucketRegion, bucketName, fileName);
  }

  private File convertMultiPartFileToFile(final MultipartFile multipartFile) {

    final File file = new File(multipartFile.getOriginalFilename());

    try (final FileOutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write(multipartFile.getBytes());
    } catch (final IOException ex) {
      System.out.println("Error converting the multi-part file to file= " + ex.getMessage());
    }
    return file;
  }

  @Transactional
  String uploadFileToS3Bucket(final String bucketName, final File file) {

    final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, file.getName(), file);
    s3Client.putObject(putObjectRequest);

    return file.getName();
  }

  public void deleteFileFromS3(Long id) {

    Image image = imageRepository.findByArticleId(id).orElseThrow(() -> new IllegalArgumentException("Image not found"));

    String imageUrl = image.getUrl();
    String fileName = getImageFileNameFromUrl(imageUrl);
    AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(bucketRegion).build();
    s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));

  }

  private String getImageFileNameFromUrl(String imageUrl) {
    return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
  }
}
