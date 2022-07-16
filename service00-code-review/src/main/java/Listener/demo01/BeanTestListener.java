package Listener.demo01;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BeanTestListener implements PropertyChangeListener {

    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        if (evt.getPropertyName().equals(Student.TEST))
            System.out.println("I need do something, what is the something");
    }
}
