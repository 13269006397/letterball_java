package com.letterball.common;

public interface Constants {

    /*************************************************************************************************/
    /* 系统状态 */
    /************************************************************************************************/

    public static final String ERROR_LOGIN_PWD           = "密码错误,请重新输入！";
    public static final String ERROR_LOGIN_USER_NAME     = "账号不存在，请重新输入!";
    public static final String ERROR_LOGIN_ERROR         = "登陆错误";
    public static final String ERROR_LOGIN__NOPHONE      = "请输入手机号";
    public static final String ERROR_SEND_QCORE          = "验证码发送失败";
    public static final String SUCCESS_SEND_QCORE        = "验证码发送成功";
    public static final String ERROR_QCORE_              = "验证码错误";
    public static final String ADD_SUCCESS               = "新增成功";
    public static final String ADD_ERROR                 = "新增失败";
    public static final String FIND_NULL                 = "查询为空";
    public static final String UPDATE_SUCCESS            = "修改成功";
    public static final String UPDATE_ERROR              = "修改失败";
    public static final String DELETE_SUCCESS            = "删除成功";
    public static final String DELETE_ERROR              = "删除失败";
    public static final String EXAMINE_SUCCESS           = "审核通过";
    public static final String EXAMINE_ERROR             = "审核失败";



    /*************************************************************************************************/
    /* 响应码 */
    /************************************************************************************************/

    public static final int SUCCESS          = 200;        //成功
    public static final int ERROR            = 201;        //失败
    public static final int LOGINERROR       = 202;        //用户名或密码错误
    public static final int ACCESSERROR      = 203;        //权限不足
    public static final int REMOTEERROR      = 204;        //远程调用失败
    public static final int REPERROR         = 205;        //重复操作

    /*************************************************************************************************/
    /* CRUD操作字段参数名称 */
    /************************************************************************************************/

    public static final String COMM_QUERY_RESP_ITEM      = "items";
    public static final String COMM_QUERY_RESP_TOTAL     = "total";
    public static final String COMM_QUERY_RESP_RESULT    = "result";
    public static final String COMM_QUERY_RESP_RPAGE     = "page";
    public static final String COMM_QUERY_RESP_LIMIT     = "limit";
    public static final String COMM_QUERY_RESP_LIST      = "list";

    /*************************************************************************************************/
    /* 数字参数类 */
    /************************************************************************************************/

    public static final Long COMM_DATA_ZERO     = 0L;

    /*************************************************************************************************/
    /* 查询参数 */
    /************************************************************************************************/

    public static final String SERCH_DATA_TELEPHONE      = "telephone";
    public static final String SERCH_DATA_USERNAME       = "username";
    public static final String SERCH_DATA_QC_CODE        = "qcCode";
    public static final String SERCH_DATA_ID             = "id";
    public static final String SEARCH_LABEL_NAME         = "labelName";
    public static final String SEARCH_STATE              = "state";
    public static final String SEARCH_RECOMMEND          = "recommend";
    public static final String SEARCH_IS_HOT             = "ishot";
    public static final String SEARCH_LABEL_ID           = "labelId";
    public static final String SEARCH_THUMBUP            = "thumbup";






}
