package test;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: krest
 * @date: 2021/5/21 18:40
 * @description:
 */

public class sentinelTest {

    public static void main(String[] args){
        initFlowerRule();
        int i=0;
        while(true){
            try(Entry entry= SphU.entry("HelloWorld")){

                System.out.println(LocalDateTime.now().toString()+":Hello world");
                i++;
                if(i%10==0){
                    throw new Exception("null 10");
                }
            }catch (BlockException ex) {
                // 处理被流控的逻辑
                //  System.out.println("blocked!");
            } catch (Exception e) {
                Tracer.trace(e);   //记录异常
            }
        }
    }


    public static   void initFlowerRule(){
        List<FlowRule> rules=new ArrayList<>();
        FlowRule rule=new FlowRule();
        rule.setResource("hello");  //资源配置
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);  //设置流量 限制每秒 请求次数不超过20
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}

