package cn.xp1997.xp.sys.base;


@SuppressWarnings("all")
public class Result {

    //状态码
    private int code;
    //信息
    private String msg;
    //数据
    private Object data;
    //数据量
    private Long count;

    private static String SUCCESS = "success";
    private static String FAIL = "fail";
    private static int SUCCESS_CODE = 0;
    private static int FAIL_CODE = -1;

    public Result(){

    }

    public Result(int code, String msg, Object data, Long count){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }



    /**
     * 请求成功
     */
    public static Result success(){
        return new Result(SUCCESS_CODE, SUCCESS);
    }

    public static Result success(Object data){
        return new Result(SUCCESS_CODE, SUCCESS, data);
    }

    public static Result success(Object data, Long count){
        return new Result(SUCCESS_CODE, SUCCESS, data, count);
    }

    public static Result success(String msg, Object data){
        return new Result(SUCCESS_CODE, msg, data);
    }

    public static Result success(String msg, Object data, Long count){
        return new Result(SUCCESS_CODE, msg, data, count);
    }

    public static Result success(String msg){
        return new Result(SUCCESS_CODE, msg);
    }



    /**
     * 请求失败
     */
    public static Result fail(){
        return new Result(FAIL_CODE, FAIL);
    }


    public static Result fail(String msg){
        return new Result(FAIL_CODE, msg);
    }


    /**
     * 自定义
     */
    public static Result newResult(int code, String msg, Object data, Long count){
        return new Result(code, msg, data, count);
    }


    public static Result newResult(int code, String msg, Object data){
        return new Result(code, msg, data);
    }

    public static Result newResult(int code, String msg){
        return new Result(code, msg);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
