package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean isNameAvailable(String name, Integer userId) {
        return fileMapper.getFile(name, userId) == null;
    }

    public void save(File file) {
        Integer id = fileMapper.insert(file);
    }

    public void delete(File file) {
        fileMapper.delete(file.getFileId());
    }
}
