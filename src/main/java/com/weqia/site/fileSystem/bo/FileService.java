package com.weqia.site.fileSystem.bo;

import com.weqia.site.fileSystem.dao.FileMapping;
import com.weqia.site.fileSystem.pojo.User;
import com.weqia.site.fileSystem.pojo.fileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

/**
 * 文件操作类
 */
@Service
@Transactional
public class FileService {

    @Autowired
    private FileMapping fileMapping;

    @Autowired
    private HttpSession session;

    /**
     * 文件上传,上传成功返回1，否则返回0
     * @param file
     * @return
     */
    public int upLoad(MultipartFile file) {
        //获取文件名
        String fname = file.getOriginalFilename();
        try {
            //获取项目路径
            User user = (User) session.getAttribute("user");
            String path = System.getProperty("user.dir")+File.separator+user.getUid();
            File file1 = new File(path);
            if (!file1.exists()) {
                file1.mkdir();
            }
            //将文件信息存入数据库
            fileEntity fEntity = new fileEntity(fname,path,user.getUid());
            fileMapping.upLoad(fEntity);
            //存储文件
            file.transferTo(new File(path + File.separator + file.getOriginalFilename()));
            return 1;
        } catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * 查询所有文件
     * @return
     */
    public List<fileEntity> findAll(){
        User user = (User) session.getAttribute("user");
        return fileMapping.findAll(user.getUid());
    }

    /**
     * 根据文件id获取文件信息
     * @param fid
     * @return
     */
    public fileEntity findById(int fid){
        return fileMapping.findById(fid);
    }

    /**
     * 根据文件id下载文件
     * @param response
     * @param fid
     */
    public void downLoadFile(HttpServletResponse response,int fid){
        //1、获取文件实体
        fileEntity fileEntity = findById(fid);
        //2、获取文件路径
        String path = Paths.get(fileEntity.getFpath(), fileEntity.getFname()).toString();
        //3、设置强制下载不打开
        response.setContentType("application/x-msdownload;charset=utf-8");
        try {
            //4、下载文件的名称
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileEntity.getFname()).getBytes("gbk"), "iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //5、文件输入流
        FileInputStream fis = null;
        //6、字符输入流
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(new File(path));
            bis = new BufferedInputStream(fis);
            //7、输出流下载文件
            OutputStream os = response.getOutputStream();
            byte[] buff = new byte[2048];
            int len;
            while (-1 != (len = bis.read(buff))) {
                os.write(buff,0,len);
                //8、刷新缓存
                os.flush();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            //9、关闭流
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     *  根据文件id删除文件
     * @param fid
     * @return
     */
    public int del(int fid){
        //1、获取文件实体
        fileEntity fileEntity = findById(fid);
        //2、获取文件
        String path = fileEntity.getFpath()+File.separator+fileEntity.getFname();
        File f = new File(path);
        //3、判断文件是否存在，若存在则删除返回1，否则返回0
        fileMapping.del(fid);
        if (f.exists()){
            f.delete();
            return 1;
        }else {
            return 0;
        }
    }
}
