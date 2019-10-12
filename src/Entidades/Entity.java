package Entidades;

/**
 *
 * @author Thairam Michel
 */
public interface Entity {

    public abstract String getFields();

    public abstract String getValues();

    public abstract String getKeyField();

    public abstract int getKeyValue();

    public abstract void setKeyValue(int value);

}
