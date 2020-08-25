package com.letterball.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.FileReport;
import com.letterball.entity.Permission;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.mapper.FileReportMapper;
import com.letterball.mapper.PermissionMapper;
import com.letterball.mapper.UserMapper;
import com.letterball.service.UserService;
import com.letterball.utils.NumberUtils;
import com.letterball.utils.RedisUtils;
import com.letterball.utils.TokenUtils;
import com.letterball.vo.UserVO;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private FileReportMapper fileReportMapper;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private TokenUtils tokenUtils;

    /**
     * 根据手机号查询用户
     *
     * @param userVO
     * @return
     */
    @Override
    public User selectUserByMobile(UserVO userVO) {
        HashMap<String, Object> requestmap = new HashMap<>();
        String mobile = userVO.getMobile();
        if (!StringUtils.isEmpty(mobile)) {
            requestmap.put(Constants.SEARCH_MOBILE, mobile);
        }
        return userMapper.selectUserByMobile(requestmap);
    }

    /**
     * 用户登录验证
     *
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase userLogin(UserVO userVO) {
        User user = selectUserByMobile(userVO);
        HashMap<String, Object> resultParams = new HashMap<>();

        if(user.getIsDelete().equals(Constants.PERMISSION_TWO)){
            return setResultError(Constants.ERROR_LOGIN_USER);
        }

        //账号不存在
        if (StringUtils.isEmpty(user)) {
            return setResultError(Constants.ERROR_LOGIN_USER_NAME);
        }
        //验证码登录
        if (!StringUtils.isEmpty(userVO.getVfCode())) {
            String key = redisUtils.getKey(userVO.getMobile());
            if (key.equals(userVO.getVfCode())) {
                try {
                    //生成Token
                    String token = tokenUtils.token(user.getMobile(), user.getPassword());
                    resultParams.put(Constants.SEARCH_LOGIN_TOKEN, token);
                    resultParams.put(Constants.SEARCH_USER_ID, user.getId());
                    resultParams.put(Constants.SEARCH_USER_PERMISSION, user.getPermission());

                    //修改最后登陆时间
                    user.setLastTime(new Date());
                    userMapper.updateUserById(user);
                    return setResult(Constants.SUCCESS, Constants.SUCCESS_LOGIN, resultParams);
                } catch (Exception e) {
                    return setResultError(Constants.ERROR_TRY_CATCH);
                }

            }
            return setResultError(Constants.ERROR_QCORE_);
        }
        //密码登录
        if (!StringUtils.isEmpty(userVO.getPassword())) {

            //账号存在 密码错误
            if (!StringUtils.isEmpty(user) && !user.getPassword().equals(userVO.getPassword())) {
                return setResultError(Constants.ERROR_LOGIN_PWD);
            }
            //密码正确
            if (user.getPassword().equals(userVO.getPassword())) {
                try {
                    //生成Token
                    String token = tokenUtils.token(user.getMobile(), user.getPassword());
                    resultParams.put(Constants.SEARCH_LOGIN_TOKEN, token);
                    resultParams.put(Constants.SEARCH_USER_ID, user.getId());
                    resultParams.put(Constants.SEARCH_USER_PERMISSION, user.getPermission());

                    //修改最后登陆时间
                    user.setLastTime(new Date());
                    userMapper.updateUserById(user);
                    return setResult(Constants.SUCCESS, Constants.SUCCESS_LOGIN, resultParams);
                } catch (Exception e) {
                    return setResultError(Constants.ERROR_TRY_CATCH);
                }
            }
        }
        return setResultError(Constants.ERROR_LOGIN_ERROR);
    }

    /**
     * 新增用户
     *
     * @param userVO
     * @return
     */
    @Override
    @Transactional
    public ResponseBase addUser(UserVO userVO, MultipartFile[] files) {
        User searchUser = selectUserByMobile(userVO);
        HashMap<String, Object> resultParams = new HashMap<>();
        User user = new User();


        if (StringUtils.isEmpty(searchUser)) {

            try {
                //数据上传

                String userId = new NumberUtils().randomUUID();
                BeanUtils.copyProperties(userVO, user);
                //放参数
                user.setMobile(userVO.getMobile());
                user.setRegTime(new Date());
                user.setId(userId);
                //初始化
                user.setIsDelete(Constants.PERMISSION_ONE);
                user.setFansCount(Constants.INT_ZERO);
                user.setFollowCount(Constants.INT_ZERO);
                userMapper.addUser(user);

                //初始化用户权限01 管理员 02普通用户 03vip用户
                Permission permission = new Permission();
                permission.setId(userId);
                permission.setPermission(Constants.PERMISSION_TWO);
                permissionMapper.insertUserPermission(permission);


                //附件上传
                if(null != files && files.length>0){
                    //上传文件逻辑
                    BufferedInputStream bis = null;
                    BufferedOutputStream bos = null;
                    InputStream in = null;
                    ByteArrayOutputStream baos = null;

                    String resourcePath = null;
                    String oriFileName = null;
                    String fileName = null;
                    try {
                        for (int i = 0; i < files.length; i++){
                            //附件id
                            String fileCode = UUID.randomUUID().toString().replace("-", "");
                            //源文件名称
                            oriFileName = files[i].getOriginalFilename();
                            System.err.println("原文件名为" + oriFileName);
                            if ("".equals(oriFileName)){
                                continue;
                            }
                            in = files[i].getInputStream();
                            // 新文件名称
                            fileName = getFileFullName(oriFileName);
                            // 全路径名
                            resourcePath = uploadFile(oriFileName,in,fileName);

                            FileReport fileReport = new FileReport();
                            fileReport.setUserId(userId);
                            fileReport.setFileCode(fileCode);
                            fileReport.setFilePath(resourcePath);
                            fileReport.setOriFileName(oriFileName);
                            // 做附件表新增操作
                            fileReportMapper.insetFile(fileReport);
                        }
                    }catch (Exception e){
                        return setResultError(Constants.ERROR_TRY_CATCH);
                    }finally {
                        try {
                            if (bis != null){
                                bis.close();
                            }
                            if (in != null){
                                in.close();
                            }
                            if (bos != null){
                                bos.close();
                            }
                            if (baos != null){
                                baos.close();
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                return setResultError(Constants.ERROR_ADD);
            }
        } else {
            return setResultError(Constants.ERROR_ADD_USER_MOBILE);
        }
        return setResultSuccessMsg(Constants.SUCCESS_ADD);
    }

    //得到文件全名
    private String getFileFullName(String oriFileName) throws  Exception {
        String random = UUID.randomUUID().toString().replace("-", "");
        String names = random+oriFileName;
        return names;
    }

    private String uploadFile(String oriFileName, InputStream in, String fileName) throws Exception {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String path;
        try{
            path = "D:/opt/filePath/" + File.separator + File.separator;
            File f = new File(path);
            if (!f.exists()){
                f.mkdir();
            }
            bis = new BufferedInputStream(in);
            bos = new BufferedOutputStream(new FileOutputStream(path + fileName));
            byte buffer [] = new byte[1024];
            int len = 0;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
            bos.flush();
        }catch (Exception e){
            return null;
        }finally {
            try{
                if (bis != null){
                    bis.close();
                }
                if (in != null){
                    in.close();
                }
                if (bos != null){
                    bos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return path + fileName;
    }

    /**
     * 根据用户id查询用户
     *
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase findUserById(UserVO userVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        HashMap<String, Object> resultMap = new HashMap<>();
        requestParams.put(Constants.SERCH_DATA_ID, userVO.getId());
        //查用户部分
        User user = userMapper.findUserById(requestParams);

        String userId = user.getId();
        List<FileReport> reportFileList = fileReportMapper.findFileReport(userId);

        resultMap.put(Constants.SEARCH_USER, user);
        resultMap.put(Constants.REPORT_FILE_LIST, reportFileList);
        return setResultSuccessData(resultMap);
    }

    /**
     * 查询用户列表
     *
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase findUserList(UserVO userVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        HashMap<String, Object> resultMap = new HashMap<>();
        long total = 0;
        if (!StringUtils.isEmpty(userVO.getMobile())) {
            requestParams.put(Constants.SEARCH_MOBILE, userVO.getMobile());
        }
        if (!StringUtils.isEmpty(userVO.getNickName())) {
            requestParams.put(Constants.SEARCH_NICK_NAME, userVO.getNickName());
        }
        if (!StringUtils.isEmpty(userVO.getPermission())) {
            requestParams.put(Constants.SEARCH_PERMISSION, userVO.getPermission());
        }
        if (!StringUtils.isEmpty(userVO.getIsDelete())) {
            requestParams.put(Constants.SEARCH_IS_DELETE, userVO.getIsDelete());
        }
        // 分页
        PageHelper.startPage(userVO.getPage(), userVO.getLimit());
        List<User> userList = userMapper.findUserList(requestParams);
        total = ((Page<User>) userList).getTotal();

        resultMap.put(Constants.COMM_QUERY_RESP_ITEM, userList);
        resultMap.put(Constants.COMM_QUERY_RESP_TOTAL, total);
        return setResultSuccessData(resultMap);
    }

    /**
     * 修改用户状态
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase updateUserStatus(UserVO userVO) {
        User user = new User();
        HashMap<String, Object> resultMap = new HashMap<>();
        BeanUtils.copyProperties(userVO, user);
        if (!StringUtils.isEmpty(userVO.getId())){
            user.setId(userVO.getId());
        }
        if (!StringUtils.isEmpty(userVO.getIsDelete())){
            user.setIsDelete(userVO.getIsDelete());
        }

            try{
                userMapper.updateUserById(user);
            }catch (Exception e){
                return setResultError(Constants.UPDATE_ERROR);
        }
        return setResultSuccessMsg(Constants.UPDATE_SUCCESS);
    }

    /**
     * 删除用户
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase deleteUserById(UserVO userVO) {
        String id = userVO.getId();
        try{
            if (!StringUtils.isEmpty(id)) {
                //  删除用户信息
                userMapper.deleteUserById(id);
                //  删除附件信息
                fileReportMapper.deleteFileById(id);
                //  删除角色信息
                permissionMapper.deletePermissionById(id);
            }
        }catch (Exception e){
            return setResultError(Constants.DELETE_ERROR);
        }
        return setResultSuccessMsg(Constants.DELETE_SUCCESS);
    }

    /**
     * 删除修改用户界面的附件
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase deleteFilesById(UserVO userVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        String fileId = userVO.getFileId();
        String id = userVO.getId();
        requestParams.put(Constants.SERCH_DATA_ID,id);
        requestParams.put(Constants.REPORT_FILE_ID, fileId);
        try{
            fileReportMapper.deleteFilesById(requestParams);

        }catch (Exception e){
            return setResultError(Constants.DELETE_ERROR);
        }

        return setResultSuccessMsg(Constants.DELETE_SUCCESS);
    }

    /**
     * 查询用户列表 返回list
     * @param userVO
     * @return
     */
    @Override
    public List<User> findUsersList(UserVO userVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        if (!StringUtils.isEmpty(userVO.getMobile())) {
            requestParams.put(Constants.SEARCH_MOBILE, userVO.getMobile());
        }
        if (!StringUtils.isEmpty(userVO.getNickName())) {
            requestParams.put(Constants.SEARCH_NICK_NAME, userVO.getNickName());
        }
        if (!StringUtils.isEmpty(userVO.getPermission())) {
            requestParams.put(Constants.SEARCH_PERMISSION, userVO.getPermission());
        }
        if (!StringUtils.isEmpty(userVO.getIsDelete())) {
            requestParams.put(Constants.SEARCH_IS_DELETE, userVO.getIsDelete());
        }
        // 分页
        PageHelper.startPage(userVO.getPage(), userVO.getLimit());
        List<User> userList = userMapper.findUserList(requestParams);

        return userList;
    }

    /**
     * 根据filePath下载文件
     */
    public ResponseBase downLoadFile(@RequestParam("filePath") String filePath, HttpServletRequest request, HttpServletResponse response){
        OutputStream toClient = null;
        InputStream in = null;
        File targetFile = null;
        String fileName = targetFile.getName();
        try{
            response.setContentType("application/octet-stream");
            response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            // 取文件流
            targetFile = new File(filePath);
            if (targetFile == null){
                return setResultError(Constants.FILE_NO_FIND);
            }
            response.addHeader("Content-disposition","attachment;filename=" + URLEncoder.encode(fileName));
            // 客户端输入流
            toClient = new BufferedOutputStream(response.getOutputStream());
            in = new FileInputStream(targetFile);
            // 读取文件输入拷贝到输出流toClient
            IOUtils.copy(in, toClient);
            // 输出
            response.flushBuffer();
        }catch (Exception e){
            return setResultError(e.getMessage());
        }finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(toClient);
        }
        return null;
    }
}
