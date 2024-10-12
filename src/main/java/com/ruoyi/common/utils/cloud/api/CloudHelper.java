package com.ruoyi.common.utils.cloud.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.cloud.consts.CloudWebAPI;
import com.ruoyi.common.utils.cloud.consts.CloudWebAPIEx;
import com.ruoyi.common.utils.cloud.pojo.DataResult;
import com.ruoyi.common.utils.cloud.util.CloudDataSource;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @describe cloud标准webapi调用工具
 * @author shenkai
 * @date 2022/10/13
 */
public class CloudHelper extends CloudInvoker {

    public CloudHelper(CloudDataSource dataSource) {
        super(dataSource);
    }

    /**
     * 保存表单
     * @param formId 表单标识
     * @param data 数据
     * @param fields 需要更新的字段
     * @return
     */
    public DataResult save(String formId, Map<String, Object> data, String... fields) throws JsonProcessingException {
        List<Object> fieldsArr = new ArrayList<>();
        for(String f: fields) {
            fieldsArr.add(f);
        }
        Map<String, Object> param = new HashMap<>();
        param.put("NeedUpDateFields", fieldsArr);
        param.put("NeedReturnFields", new ArrayList<>());
        param.put("IsDeleteEntry", "true");
        param.put("SubSystemId", "");
        param.put("IsVerifyBaseDataField", "false");
        param.put("IsEntryBatchFill", "true");
        param.put("ValidateFlag", "true");
        param.put("NumberSearch", "true");
        param.put("IsAutoAdjustField", "false");
        param.put("InterationFlags", "");
        param.put("IgnoreInterationFlag", "");
        param.put("Model", data);
        String res = super.callStandard(CloudWebAPI.SAVE.getUrl(), formId, param);
        DataResult result = parseResult(res);
        return result;
    }

    /**
     * 提交
     * @param formId 表单标识
     * @param number 单据编号
     * @return
     */
    public DataResult submit(String formId, String number) throws JsonProcessingException {
        List<Object> numbers = new ArrayList<>();
        numbers.add(number);
        Map<String, Object> param = new HashMap<>();
        param.put("CreateOrgId", 0);
        param.put("Numbers", numbers);
        param.put("Ids", "");
        param.put("SelectedPostId", 0);
        param.put("NetworkCtrl", "");
        param.put("IgnoreInterationFlag", "");
        String res = super.callStandard(CloudWebAPI.SUBMIT.getUrl(), formId, param);
        DataResult result = parseResult(res);
        return result;
    }

    /**
     * 审核
     * @param formId 表单标识
     * @param number 单据编号
     * @return
     */
    public DataResult audit(String formId, String number) throws JsonProcessingException {
        List<Object> numbers = new ArrayList<>();
        numbers.add(number);
        Map<String, Object> param = new HashMap<>();
        param.put("CreateOrgId", 0);
        param.put("Numbers", numbers);
        param.put("Ids", "");
        param.put("SelectedPostId", 0);
        param.put("NetworkCtrl", "");
        param.put("IgnoreInterationFlag", "");
        String res = super.callStandard(CloudWebAPI.AUDIT.getUrl(), formId, param);
        DataResult result = parseResult(res);
        return result;
    }

    /**
     * 删除表单
     * @param formId
     * @param list
     * @return
     */
    public DataResult delete(String formId, String[] list) throws JsonProcessingException {
        String ids = "";
        for (String id: list) {
            if(ids != "") {
                ids += ",";
            }
            ids += id;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("CreateOrgId", 0);
        param.put("Numbers", "");
        param.put("Ids", ids);
        param.put("NetworkCtrl", "");
        String res = super.callStandard(CloudWebAPI.DELETE.getUrl(), formId, param);
        DataResult result = parseResult(res);
        return result;
    }

    /**
     * 判断表单是否存在
     * @param formId
     * @param filter
     * @return
     */
    public DataResult exist(String formId, String filter) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("FormId", formId);
            param.put("FieldKeys", "FID");
            param.put("FilterString", filter);
            param.put("OrderString", "");
            param.put("TopRowCount", 0);
            param.put("StartRow", 0);
            param.put("Limit", 0);
            String json = super.callStandard(CloudWebAPI.QUERY.getUrl(), formId, param);
            ObjectMapper mapper = new ObjectMapper();
            List res = mapper.readValue(json, List.class);
            return DataResult.success(res);
        } catch (Exception e) {
            return DataResult.error(e);
        }
    }

    public DataResult parseResult(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            //返回的是数组
            if (json.indexOf("[") == 0) {
                List<Object> arr = mapper.readValue(json, List.class);
                return DataResult.success(arr);
            }
            //返回的对象
            Map<String, Object> res = mapper.readValue(json, Map.class);
            Map<String, Object> result = (Map<String, Object>) res.get("Result");
            Map<String, Object> response = (Map<String, Object>) result.get("ResponseStatus");
            if (!(Boolean) response.get("IsSuccess")) {
                //失败
                List<String> message = new ArrayList<String>();
                List<Object> errors = (List<Object>) response.get("Errors");
                for (Object obj : errors) {
                    Map<String, Object> err = (Map<String, Object>) obj;
                    message.add(err.get("Message").toString());
                }
                return DataResult.error(String.join(";", message));
            } else {
                if (result.containsKey("Result")) {
                    return DataResult.success(result.get("Result"));
                } else if (result.containsKey("NeedReturnData") && ((List)result.get("NeedReturnData")).size() > 0
                        && !result.get("NeedReturnData").toString().equals("[{}]")) {
                    return DataResult.success(result.get("NeedReturnData"));
                } else if (response.containsKey("SuccessEntitys")) {
                    return DataResult.success(response.get("SuccessEntitys"));
                } else {
                    return DataResult.error("返回结果解析失败");
                }
            }
        } catch (Exception e) {
            return DataResult.error(e);
        }
    }

    /**
     * 查询sql
     * @param sql
     */
    public List<List<Map<String, Object>>> sqlQuery(String sql) throws JsonProcessingException, UnsupportedEncodingException {
        byte[] bytes = sql.getBytes("UTF-8");
        String sqlText = Base64.getEncoder().encodeToString(bytes);
        String res = super.callCustom(CloudWebAPIEx.SQL_QUERY.getUrl(), sqlText);
        DataResult result =  parseCustomResult(res);
        if(result.isSuccess()) {
            return (List<List<Map<String, Object>>>)result.getData();
        } else {
            throw new RuntimeException(result.getMessage());
        }
    }

    public DataResult parseCustomResult(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map res = mapper.readValue(json, Map.class);
        if(Boolean.valueOf(res.get("isSuccess").toString())) {
            return DataResult.success(res.get("data"));
        } else {
            return DataResult.error(res.get("message").toString());
        }
    }
}
