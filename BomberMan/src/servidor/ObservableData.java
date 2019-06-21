package servidor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObservableData {

	String data;
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);

	public void setData(String newData) {
		changes.firePropertyChange("data", this.data, newData);
		this.data = newData;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}
}
