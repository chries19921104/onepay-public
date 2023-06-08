package org.example.common.base;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CommResp {
    private Integer code;
    private String msg;
    private Object data;

    public static CommResp SUCCEE(){
        CommResp commResp = new CommResp();
        commResp.code = 0;
        commResp.msg = "SUCCEE";
        return commResp;
    }

    public static CommResp FAIL(){
        CommResp commResp = new CommResp();
        commResp.code = 1;
        commResp.msg = "FAIL";
        return commResp;
    }
    public static CommResp FAIL(String msg){
        CommResp commResp = new CommResp();
        commResp.code = 1;
        commResp.msg = msg;


        return commResp;
    }

    public static CommResp FAIL_UPDATE(){
        CommResp commResp = new CommResp();
        commResp.code = 1;
        commResp.msg = "update error";
        return commResp;
    }

    public static CommResp FAIL_DELETE(){
        CommResp commResp = new CommResp();
        commResp.code = 1;
        commResp.msg = "delete error";
        return commResp;
    }
    public static CommResp FAIL_SAVE(){
        CommResp commResp = new CommResp();
        commResp.code = 1;
        commResp.msg = "save error";
        return commResp;
    }

    public static CommResp data(Object data){
        CommResp commResp = new CommResp();
        commResp.setCode(1);
        commResp.setMsg("SUCCEE");
        commResp.setData(data);
        return commResp;
    }

    public static Map<String, Boolean> success(){
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


}
