package Listener.demo01;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StudentChange {

    transient protected PropertyChangeSupport listeners
            = new PropertyChangeSupport(this);

    /**
     * 添加监听器
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    /**
     * 激活监听器
     */
    protected void firePropertyChange(String prop, Object old, Object newValue) {
        listeners.firePropertyChange(prop, old, newValue);
    }

    /**
     * 移除监听器
     */
    public void removePropertyChangeListener(PropertyChangeListener l) {
        listeners.removePropertyChangeListener(l);
    }

}
