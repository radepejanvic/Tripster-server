package com.tripster.project.service;

import com.tripster.project.dto.PhotoDTO;
import com.tripster.project.model.Photo;
import com.tripster.project.repository.PhotoRepository;
import com.tripster.project.service.interfaces.PhotoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Value(value="${photos}")
    private String directory;

    public Photo findOne(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public List<byte[]> findAllByAccommodationId(Long accommodationId) {

        List<byte[]> bytes = new ArrayList<>();

        try {
            for (Photo photo : photoRepository.findAllByAccommodationId(accommodationId)) {
                bytes.add(Files.readAllBytes(new File(directory.concat("/" + photo.getPath())).toPath()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bytes;
    }

    public List<PhotoDTO> findAllByAccommodationIdWithId(Long accommodationId) {

        List<PhotoDTO> photos = new ArrayList<>();

        try {
            for (Photo photo : photoRepository.findAllByAccommodationId(accommodationId)) {
                photos.add(new PhotoDTO(photo.getId(), Files.readAllBytes(new File(directory.concat("/" + photo.getPath())).toPath())));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return photos;
    }

    @Transactional
    @Override
    public void save(MultipartFile photoFile, Photo photo) throws IOException {

        photoRepository.save(photo);

        Path directoryPath = Path.of(directory);
        Path filePath = directoryPath.resolve(photo.getPath());

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Files.copy(photoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Transactional
    @Override
    public void remove(Long id, String path) {

        File photoFile = new File(directory + "/" + path);
        photoFile.delete();

        photoRepository.deleteById(id);
    }

    @Override
    public boolean hasPrimary(Long accommodationId) {
        return photoRepository.hasPrimary(accommodationId) != 0;
    }

    @Override
    public byte[] findPrimary(Long accommodationId) {

        byte[] bytes;
        Photo photo = photoRepository.findPrimary(accommodationId);

        if (photo == null) {
            return null;
        }

        try {

            bytes = Files.readAllBytes(
                    new File(directory.concat("/" + photo.getPath())).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bytes;
    }


}
