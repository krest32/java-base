package zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zk.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

/**
 * @author: krest
 * @date: 2021/5/31 18:13
 * @description:
 */
public class zookeeperTest {


    private ZkClient zkClient;


    @Test
    // 创建节点
    public void testCreateNode(){
        // server地址 会话超时时间，链接超时时间，序列化方式
        ZkClient zkClient = new ZkClient(
                "192.168.42.132:2181,192.168.42.132:2182,192.168.42.132:2183",
                6000*30,
                60000,
                new SerializableSerializer());
        System.out.println(zkClient);
        // 持久节点
       zkClient.create("/node1","ZhangSan", CreateMode.PERSISTENT);
        // 持久有序节点
        zkClient.create("/node2","lisi", CreateMode.PERSISTENT_SEQUENTIAL);
        // 临时节点
        zkClient.create("/node3","wangwu", CreateMode.EPHEMERAL);
        // 临时有序节点
        zkClient.create("/node4","zhaoliu", CreateMode.EPHEMERAL_SEQUENTIAL);
        // 删除
        zkClient.delete("/node1");
        // 递归删除
        zkClient.deleteRecursive("/node1");

        // 查询当前节点下的所有子节点
        List<String> children = zkClient.getChildren("/");
        for (String child : children) {
            System.out.println(child);
        }

        // 查看某个节点数据，需要保证序列化的方式一致
        zkClient.readData("/node1");

        // 查看节点的状态信息
        Stat stat = new Stat();
        zkClient.readData("/node1",stat);
        stat.getAversion();
        stat.getCtime();
        stat.getCzxid();


        // 修改节点的数据
        User user = new User();
        user.setAge(23);
        user.setId("1");
        user.setName("杜鑫");
        zkClient.writeData("/node1",user);

        // 监听节点数据的变化：永久监听
        zkClient.subscribeDataChanges("/node1", new IZkDataListener() {
            // 数据变化是触发方法
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("当前节点路径："+dataPath);
                System.out.println("节点变化后数据："+data);
            }

            // 节点被删除了对应的方法
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("被删除的当前节点的路径："+dataPath);
            }
        });


        // 监听节点目录的变化：永久监听
        zkClient.subscribeChildChanges("node1", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("父节点名称："+parentPath);
                for (String currentChild : currentChilds) {
                    System.out.println("子节点："+currentChild);
                }
            }
        });

     zkClient.close();
    }

}
