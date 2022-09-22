package springboottest.demo.dict;

/**
 *  表HdReport字典信息
 * @author spjiangl@isstech.com
 * @date 2022-05-18
 */
public enum HdReportDict {
    DECLARE_STATUS_NO_UPLOAD(0),
    DECLARE_STATUS_YES_UPLOAD(1),
    DECLARE_STATUS_PASS_UPLOAD(2),
    DECLARE_STATUS_NOPASS_UPLOAD(2);
    private final int code;

    HdReportDict(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}