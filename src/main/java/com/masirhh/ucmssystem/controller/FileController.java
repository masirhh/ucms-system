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
import java.util.List;
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

    /**
     * 获取文件url
     * @param fileId 文件id
     * @return 文件地址
     */
    @GetMapping
    public R<String> getUrl(Long fileId) {
        Ufile byId = ufileService.getById(fileId);
        return R.ok(byId.getPath());
    }

    /**
     * 获取文件列表
     * @return
     */
    @GetMapping("/filelist")
    public R<List<Ufile>> getFileList(){
        List<Ufile> list = ufileService.list();
        return R.ok(list);
    }

    /***
     * 上传图片
     * @param avatar 图片
     * @return 文件信息
     */
    @PostMapping
    public R<Ufile> upAvatar(MultipartFile avatar) {
        Ufile ufile = new Ufile();
        String originalFilename = avatar.getOriginalFilename();
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString();
        String url = baseUrl + "/avatar/" + filename + extname;
        File newFile = new File(localUrl + File.separator + "avatar" + File.separator + filename + extname);
        try {
            avatar.transferTo(newFile);
            ufile.setPath(url);
            ufile.setName(originalFilename);
            ufileService.save(ufile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.failed("500");
        }
        return R.ok(ufile);
    }

    /**
     * 上传首页轮播图
     * @param img 图片
     * @param pos 轮播图位置
     * @return 是否上传成功
     * @throws IOException
     */
    @PostMapping("/uphome")
    public R<Boolean> upHomeImg(MultipartFile img, @RequestParam("pos") String pos) throws IOException {
        String baseurl = "D:\\WorkSpace\\ucmsfiles\\carousel\\";
        String fileurl = baseurl + "car" + pos+".png";
        File file = new File(fileurl);
        boolean exists = file.exists();
        if (exists) {
            file.delete();
            img.transferTo(file);
        } else {
            img.transferTo(file);
        }
        return R.ok(true);
    }

    /**
     * 删除文件表中的记录
     * @param ufile 文件
     * @return 是否删除成功
     */
    @DeleteMapping()
    public R<Boolean> deleteFile(@RequestBody Ufile ufile){
        boolean b = ufileService.removeById(ufile.getId());
        return R.ok(b);
    }
}
