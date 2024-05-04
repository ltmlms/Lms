import java.io.Serializable;
import java.util.ArrayList;

public class SerializableList<T> extends ArrayList<T> implements Serializable {

    public SerializableList() {
        super();
    }

    public SerializableList(int initialCapacity) {
        super(initialCapacity);
    }

    // If needed, you can override other methods of ArrayList here

    // Implement readObject and writeObject methods for custom serialization if necessary
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            add((T) in.readObject());
        }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
        out.writeInt(size());
        for (T element : this) {
            out.writeObject(element);
        }
    }
}
