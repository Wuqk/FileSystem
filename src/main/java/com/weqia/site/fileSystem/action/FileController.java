package com.weqia.site.fileSystem.action;

import com.weqia.site.fileSystem.bo.FileService;
import com.weqia.site.fileSystem.pojo.fileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/file")

/**
 * @ClassName FileController
 * @Description 控制文件上传的类
 * @Author wqk
 * @Date 2019/12/18 16:33
 */
public class FileController {


    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/upLoad", method = RequestMethod.POST)
    public int upLoad(MultipartFile file) {
        if (file.isEmpty()) {
            return 0;
        } else {
            return fileService.upLoad(file);
        }
    }

    /**
     * 查找当前用户所有文件
     * @return
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public List<fileEntity> findAll() {
        return fileService.findAll();
    }

    /**
     * 下载文件
     * @param response
     * @param fid
     * @throws IOException
     */
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(HttpServletResponse response, String fid) throws IOException {
       fileService.downLoadFile(response,Integer.parseInt(fid));
    }

    /**
     * 删除文件
     * @param fid
     * @return
     */
    @RequestMapping(value = "/delFile")
    public int delFile(String fid){
        return fileService.del(Integer.parseInt(fid));
    }

}
