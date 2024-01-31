package com.datafilter.jsondatafilter;

import com.datafilter.jsondatafilter.entityes.Condition;
import com.datafilter.jsondatafilter.entityes.Data;
import com.datafilter.jsondatafilter.entityes.ProcessRequest;
import com.datafilter.jsondatafilter.entityes.Result;
import com.datafilter.jsondatafilter.services.DataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JsonDataFilterApplicationTests {

  @InjectMocks
    DataService dataService;

    private static Data getData() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "Jon");
        map1.put("email", "exemple@gmail.com");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "Bob");
        map2.put("email", "zxemple@gmail.com");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "Jon");
        map3.put("email", "qq@gmail.com");
        dataList.add(map1);
        dataList.add(map2);
        dataList.add(map3);
        Data data = new Data(dataList);
        return data;
    }

    private static Condition getCondition(){
        List<Map<String, Object>> include = new ArrayList<>();
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("name", "Jon");
        include.add(conditionMap);
        List<String> sort_by = new ArrayList<>(List.of("email"));
        return new Condition(include, sort_by);
    }

    private static Result getResult(){
        Map<String, Object> mapResult1 = new HashMap<>();
        mapResult1.put("name", "Jon");
        mapResult1.put("email", "exemple@gmail.com");
        Map<String, Object> mapResult2 = new HashMap<>();
        mapResult2.put("name", "Jon");
        mapResult2.put("email", "qq@gmail.com");
        return new Result(new ArrayList<>(Arrays.asList(mapResult1, mapResult2)));
    }



    @Test
    void processTest(){
      ProcessRequest processRequest = new ProcessRequest(getData(), getCondition());
      Result result = dataService.process(processRequest.getData(), processRequest.getCondition());

      Assertions.assertEquals(getResult(), result);
  }
}
