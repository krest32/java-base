package DataStructure.code.liner;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Krest
 * @date: 2021/1/3 10:24
 * @description: MyLinkedList's Node
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyNode {
    /**
     * User data
     */
    Object data;
    /**
     * Next Node Data
     */
    MyNode nextNode;

    public MyNode(Object data) {
        this.data = data;
    }

}
