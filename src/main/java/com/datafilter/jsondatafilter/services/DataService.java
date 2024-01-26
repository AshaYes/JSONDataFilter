package com.datafilter.jsondatafilter.services;

import com.datafilter.jsondatafilter.entityes.Condition;
import com.datafilter.jsondatafilter.entityes.Data;
import com.datafilter.jsondatafilter.entityes.Result;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataService {
    public Result process(Data data, Condition condition) {
        List<Map<String, Object>> resultList = applyIncludeCondition(data.getData(), condition.getInclude());
        resultList = applySortByCondition(resultList, condition.getSort_by());
        Result result = new Result();
        result.setResult(resultList);
        return result;
    }

    private List<Map<String, Object>> applyIncludeCondition(List<Map<String, Object>> data,
                                                            List<Map<String, Object>> includeCondition) {
        if (includeCondition == null || includeCondition.isEmpty())
            return data;

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : data) {
            if (matchesIncludeCondition(item, includeCondition)) {
                result.add(item);
            }
        }
        return result;
    }

    private boolean matchesIncludeCondition(Map<String, Object> item,
                                            List<Map<String, Object>> includeCondition) {
        for (Map<String, Object> condition : includeCondition) {
            for (Map.Entry<String, Object> entry : condition.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                return item.containsKey(key) && item.get(key).equals(value);
            }
        }
        return false;
    }

    private List<Map<String, Object>> applySortByCondition(List<Map<String, Object>> filteredData,
                                                           List<String> sort_by) {

        if (sort_by.isEmpty())
            return filteredData;

        filteredData.sort((o1, o2) -> {
            for (String sort : sort_by) {
                @SuppressWarnings("unchecked")
                Comparable<Object> item1 = (Comparable<Object>) o1.get(sort);
                @SuppressWarnings("unchecked")
                Comparable<Object> item2 = (Comparable<Object>) o2.get(sort);
                int result = item1.compareTo(item2);
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        });

        return filteredData;

    }
}
