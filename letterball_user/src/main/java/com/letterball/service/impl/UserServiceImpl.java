package com.letterball.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.Permission;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.mapper.PermissionMapper;
import com.letterball.mapper.UserMapper;
import com.letterball.service.UserService;
import com.letterball.utils.NumberUtils;
import com.letterball.utils.RedisUtils;
import com.letterball.utils.TokenUtils;
import com.letterball.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

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
    public ResponseBase addUser(UserVO userVO) {
        User searchUser = selectUserByMobile(userVO);
        HashMap<String, Object> resultParams = new HashMap<>();
        User user = new User();
        if (StringUtils.isEmpty(searchUser)) {
            try {
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
            } catch (Exception e) {
                return setResultError(Constants.ERROR_ADD);
            }
        } else {
            return setResultError(Constants.ERROR_ADD_USER_MOBILE);
        }
        return setResultSuccessMsg(Constants.SUCCESS_ADD);
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
        User user = userMapper.findUserById(requestParams);
        resultMap.put(Constants.SEARCH_USER, user);
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
}
