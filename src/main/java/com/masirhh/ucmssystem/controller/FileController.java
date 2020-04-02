package com.masirhh.ucmssystem.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.masirhh.ucmssystem.domain.Ufile;
import com.masirhh.ucmssystem.service.UfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(("/ucms/file"))
@CrossOrigin
public class FileController {

    @Value("${fileurl}")
    private String baseUrl;
    @Value("${fileuploadurl}")
    private String localUrl;
    @Autowired
    private UfileService ufileService;

    @GetMapping
    public R<String> getFileById(Long id) {
        return R.ok(baseUrl);
    }

    @PostMapping
    public R<String> upAvatar(MultipartFile avatar) {
        String originalFilename = avatar.getOriginalFilename();
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString();
        String url = baseUrl + "/avatar/" + filename + extname;
        File newFile = new File(localUrl + File.separator + "avatar" + File.separator + filename + extname);
        try {
            avatar.transferTo(newFile);
            Ufile ufile = new Ufile();
            ufile.setPath(url);
            ufile.setName(originalFilename);
            ufileService.save(ufile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.failed("500");
        }
        return R.ok(url);
    }
}
