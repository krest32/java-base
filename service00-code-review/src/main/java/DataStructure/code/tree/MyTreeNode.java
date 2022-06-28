package DataStructure.code.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: krest
 * @date: 2021/1/3 13:55
 * @description: MyTreeNode entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTreeNode {
    /**
     * Node Value
     */
    Object value;

    /**
     * leftChildNode
     */
    MyTreeNode leftChild;

    /**
     * rightChildNode
     */
    MyTreeNode rightChild;

}
